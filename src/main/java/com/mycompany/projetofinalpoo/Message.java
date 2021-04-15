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
public class Message {
    private String De;
    private String Para;
    private String Assunto;
    private String Mensagem;
    private TelaMensagem Tela;

    public TelaMensagem getTela() {
        return Tela;
    }

    public void setTela(TelaMensagem Tela) {
        this.Tela = Tela;
    }
    
    Message(String de, String para, String assunto, String mensagem) {
        De = de;
        Para = para;
        Assunto = assunto;
        Mensagem = mensagem;
        
    }
    
    public String getDe() {
        return De;
    }

    public void setDe(String De) {
        this.De = De;
    }

    public String getPara() {
        return Para;
    }

    public void setPara(String Para) {
        this.Para = Para;
    }

    public String getAssunto() {
        return Assunto;
    }

    public void setAssunto(String Assunto) {
        this.Assunto = Assunto;
    }

    public String getMensagem() {
        return Mensagem;
    }

    public void setMensagem(String Mensagem) {
        this.Mensagem = Mensagem;
    }
    
    public String msgToString() {
        String s = "De: "+De+" Para: "+Para+" Assunto: "+Assunto;
        return s;
    
    }
    
}
