package com.mycompany.projetofinalpoo;

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
    private int numMsgs = 0;

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
        System.out.println(mensagens);
        this.mensagens = mensagens;
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
        if(usuario == null) {
            return;
        }
        while(true) {
            Message[] mensagensAux = ConversadorServidor.getInstance().getMensagens(usuario);
            if(mensagensAux == null) {
                return;
            }
            System.out.println("Passou aqui");
            if(numMsgs < mensagensAux.length) {
                setMensagens(mensagensAux);
                setNumMsgs(mensagens.length);
                for(Message m : mensagens) {
                    System.out.println(m.msgToString());
                }
                return;
            }
            try {
               sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
         }
                
        }
    }
}
    
    
