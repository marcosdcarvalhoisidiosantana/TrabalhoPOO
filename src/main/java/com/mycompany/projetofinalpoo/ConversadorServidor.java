package com.mycompany.projetofinalpoo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

/*
 * Classe para conversar com o servidor (singleton).
 * Singleton para evitar multiplas instancias e concorrencia de acessos.
 * Criar o socket
 * Saber o que precisa ser escrito
 * Fazer a leitura da resposta
 * Disponibilizar a resposta para o resto da aplicacao
 */

/**
 *
 * @author marcos
 */
public class ConversadorServidor {
    private static ConversadorServidor instancia;
    private Socket socket;
    
    public static ConversadorServidor getInstance() {
        if(instancia == null) {
            instancia = new ConversadorServidor();
        }
        return instancia;
    }
    
    
    private void abrirSocket() throws IOException {
        System.out.println("Criando o socket...");
        socket = new Socket("catolicasc-bigdata-valmor123.mybluemix.net", 80);
        System.out.println("Conectado... " + socket.isConnected());
    }
    private ConversadorServidor() {
        
    }
    
    private void escreverParaOServidor(String what, String mensagem) throws IOException {
        PrintStream printer = new PrintStream(socket.getOutputStream());
        mensagem = mensagem.replace(" ", "%20");
        printer.println("GET /" + what + "?json="+mensagem+ " HTTP/1.1");
        System.out.println("GET /" + what + "?json="+mensagem+" HTTP/1.1");
        printer.println("Host: catolicasc-bigdata-valmor123.mybluemix.net");
        printer.println("Accept: */*");
        printer.println("Connection: Close");
        printer.println();
        System.out.println("Passou no escrever");
    }
    
    private String lerRespostaDoServidor() throws IOException {
        String resposta = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String linha = reader.readLine();
        while (linha != null) {
            resposta += linha;
            linha = reader.readLine();
        }
        reader.close();
        System.out.println("termino da leitura");
        return resposta;
    }
    

    public String login(String user) {
        String mensagem = "{ \"login\": { \"user-id\": \"" + user +"\" } }";
        String resposta = null;
        System.out.println(mensagem);
        try {
            abrirSocket();
            escreverParaOServidor("login", mensagem);
            resposta = lerRespostaDoServidor();
            socket.close();
            Controlador.getInstance().setUser(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resposta;
    }
    
    public String enviarMensagem(String destinatario, String assunto, String texto) {
        String mensagem = "{ \"send\": { \"remetente\": \"" + Controlador.getInstance().getUser() + "\", \"destinatario\": \""+ destinatario +"\", \"assunto\": \""+assunto+"\", \"texto\": \""+texto+"\" } }";
        String resposta = null;
        try {
            abrirSocket();
            escreverParaOServidor("send", mensagem);
            resposta = lerRespostaDoServidor();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resposta;
    }
    
    private ArrayList<Message> converterRespostaEmMensagens(String json) {
        ArrayList<Message> mensagens = null;
        json = json.substring(json.indexOf("\"mensagens\""));
        if(json.contains("\"mensagens\":[]")) {
            return null;
        }
        else if(json.contains("\"mensagens\":[")) {
            json = json.substring(json.indexOf('{'));
            json = json.substring(0, json.lastIndexOf('}'));
            json = json.substring(0, json.lastIndexOf(']'));
            String[] array = json.split("},");
            if(array.length == 0) {
                return null;
            }
            mensagens = new ArrayList<>(array.length);
            String remetente = "";
            String destinatario = "";
            String assunto = "";
            String texto = "";

            for(String s : array) {
                s = s.replace("{","");
                s = s.replace("}","");
                s = s.replace("\"", "");
                String a[] = s.split(",");
                String fields[];
                for(String element : a) {
                    fields = element.split(":");

                    if (fields[0].equalsIgnoreCase("remetente"))   remetente    = fields[1];
                    else if (fields[0].equalsIgnoreCase("destinatario")) destinatario = fields[1];
                    else if (fields[0].equalsIgnoreCase("assunto"))      assunto      = fields[1];
                    else if (fields[0].equalsIgnoreCase("texto"))        texto        = fields[1];
                }
                mensagens.add(new Message(remetente, destinatario, assunto, texto));
            }
        }
        return mensagens;
    }
    
    public Message[] getMensagens(String user) {
        String mensagem = "{ \"get\": { \"user-id\": \"" + user +"\" } }";
        String resposta = null;
        try {
            abrirSocket();
            escreverParaOServidor("get", mensagem);
            resposta = lerRespostaDoServidor();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //
        ArrayList<Message> array = converterRespostaEmMensagens(resposta);
        if(array == null) return null;
        Message[] response = new Message[array.size()];
        response = array.toArray(response);
        for(Message r : response) {
            r.msgToString();
        }
        return response;
    }
    public String[] msgVectorToString() {
        Message[] mensagens = getMensagens(Controlador.getInstance().getUser());
        if(mensagens == null) {
            String[] s = { "Sem mensagens." };
            return s;
        }
        String[] vectorString = new String[mensagens.length];
        for(int i = 0; i < mensagens.length; i++) {
            vectorString[i] = mensagens[i].msgToString();
            System.out.println(vectorString[i]);
        }
        return vectorString;
        
    }
    
}
