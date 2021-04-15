package com.mycompany.projetofinalpoo;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marcos
 */
public class Controlador extends Thread {
    private static Controlador instancia;
    private String usuario;
    private Message[] mensagens;
    private Message[] mensagensEnviadas;
    private int numMsgs = 0;
    private TelaLogin telaLogin;
    private TelaPrincipal telaPrincipal;

    public TelaLogin getTelaLogin() {
        return telaLogin;
    }

    public void setTelaLogin(TelaLogin telaLogin) {
        this.telaLogin = telaLogin;
    }
    
    public Message[] getMensagensEnviadas() {
        return mensagensEnviadas;
    }

    public void setMensagensEnviadas(Message[] mensagensEnviadas) {
        this.mensagensEnviadas = mensagensEnviadas;
    }

    public TelaPrincipal getTelaPrincipal() {
        return telaPrincipal;
    }

    public void setTelaPrincipal(TelaPrincipal telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
    }

    public static void main(String args[]) {
        
        TelaLogin telaLogin = new TelaLogin();
        Controlador.getInstance().setTelaLogin(telaLogin);
        Controlador.getInstance().getTelaLogin().setVisible(true);
       
    }
    public int getNumMsgs() {
        return numMsgs;
    }

    public void setNumMsgs(int numMsgs) {
        this.numMsgs = numMsgs;
    }
    

    public Message[] getMensagens() {
        return mensagens;
    }

    public void setMensagens(Message[] mensagens) {
        this.mensagens = mensagens;
    }
    
    public String[] msgVectorToString() {
        if(mensagens == null) {
            String[] s = { "Sem mensagens." };
            return s;
        }
        String[] vectorString = new String[mensagens.length];
        for(int i = 0; i < mensagens.length; i++) {
            vectorString[i] = mensagens[i].msgToString();
        }
        return vectorString;
    }
    
    public String[] msgSentToString() { 
        if(mensagensEnviadas == null) {
            String[] s = { "Sem mensagens." };
            return s;
        }
        String[] vectorString = new String[mensagensEnviadas.length];
        for(int i = 0; i < mensagensEnviadas.length; i++) {
            vectorString[i] = mensagensEnviadas[i].msgToString();
        }
        return vectorString;
    }
    
    public static Controlador getInstance() {
        if(instancia == null) {
            instancia = new Controlador();
            
        } 
        return instancia;
    }
    
    public String getUser() {
        if(usuario != null) {
            return usuario;
        } 
        else return null;
    }
    
    public void setUser(String s) {
        this.usuario = s;
    }
    
    Controlador() {
        super();
    }
    
    @Override
    public void run() {
        while(true) {
            try {
                Message[] mensagensAux = ConversadorServidor.getInstance().getMensagens(usuario);
                if(numMsgs < mensagensAux.length) {
                    setMensagens(mensagensAux);
                    setNumMsgs(mensagens.length);
                    for(Message m : mensagens) {
                    }
                    getTelaPrincipal().UpdateTela();
                }
            } catch (NullPointerException e) {
              try {
                  sleep(4000);
              } catch(InterruptedException e1) {
                  e.printStackTrace();
              }
                
            }
        }
        
    }
}
    
    
