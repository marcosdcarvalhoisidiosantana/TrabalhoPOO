package com.mycompany.projetofinalpoo;

import java.io.*;
import java.util.ArrayList;

public class GerenciadorDeArquivos {
    //1. Metodo construtor precisa ser private.
    //2. guardar uma referencia private para o objeto desta classe.
    //3. Metodo da classe para permitir acesso a instancia.
    private static GerenciadorDeArquivos instancia = null;
    public  static GerenciadorDeArquivos getInstance() throws IOException {
        if(instancia == null) {
            instancia = new GerenciadorDeArquivos();
        }
        return instancia;
    }
    //
    private File file;
    private GerenciadorDeArquivos() {
    }

    public void escreverNoArquivo(String De, String Para, String Assunto, String msg) throws IOException {
        file = new File(Controlador.getInstance().getUser()+".txt");
        FileWriter fw = new FileWriter(file,true);
        if(file.length() == 0) {
            String texto = De+","+Para+","+Assunto+","+msg;
            fw.write(texto);
        } else {
            String texto = "\n"+De+","+Para+","+Assunto+","+msg;
            fw.write(texto);
        }
        System.out.println("Escrito no arquivo.");
        fw.close();
    }

    public String lerDoArquivo() throws IOException {
        if(file == null) {
            file = new File(Controlador.getInstance().getUser()+".txt");
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        String resposta = "";
        String temp = br.readLine();
        ArrayList<Message> msgsEnviadas = new ArrayList<Message>();
        while(temp != null) {
            resposta = temp;
            String[] strAux = resposta.split(",");
            msgsEnviadas.add(new Message(strAux[0],strAux[1],strAux[2],strAux[3]));
            temp = br.readLine();
        }
        Message[] msgVector = new Message[msgsEnviadas.size()];
        msgVector = msgsEnviadas.toArray(msgVector);
        Controlador.getInstance().setMensagensEnviadas(msgVector);

        br.close();
        return resposta;
    }

}