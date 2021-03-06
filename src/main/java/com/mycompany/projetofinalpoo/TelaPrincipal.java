package com.mycompany.projetofinalpoo;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.NullPointerException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.*;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marcos
 */
public class TelaPrincipal extends javax.swing.JFrame { 
    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
        try {
            String s = GerenciadorDeArquivos.getInstance().lerDoArquivo();
        } catch(IOException e) {
           File file = new File(Controlador.getInstance().getUser()+".txt");
        }
    }
    
    public JButton getButtonEnviarMsg() {
        return this.BotaoEnviar;
    }
    
    public String getInputPara() {
        if(InputPara.getText() != null) {
            return InputPara.getText();
        } else {
            return null;
        }
    }
  
    
    public String getInputAssunto() {
        if(InputAssunto.getText() != null) {
            return InputAssunto.getText();
        } else {
            return null;
        }
    }
    
    public String getInputMensagem() {
        if(CorpoMensagem.getText() != null) {
            return CorpoMensagem.getText();
        } else {
            return null;
        }
    }
    public void UpdateTela() {
        ListaMensagens.setModel(new javax.swing.AbstractListModel<String>() {
        String[] strings =  Controlador.getInstance().msgVectorToString();
        @Override
        public int getSize() { return strings.length; }
        @Override
        public String getElementAt(int i) { return strings[i]; }
        });
    }
    
    public void UpdateTelaMsgsEnviadas() {
        ListaMensagens.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = Controlador.getInstance().msgSentToString();
            @Override
            public int getSize() { return strings.length; }
            @Override
            public String getElementAt(int i) { return strings[i]; }
        });
    }
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ListaAbas = new javax.swing.JTabbedPane();
        AbaPastas = new javax.swing.JPanel();
        Caixas = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        ListaMensagens = new javax.swing.JList<>();
        LogadoEm1 = new javax.swing.JLabel();
        AbaMensagens = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        InputPara = new javax.swing.JTextField();
        InputAssunto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        CorpoMensagem = new javax.swing.JTextArea();
        BotaoEnviar = new javax.swing.JButton();
        LogadoEm2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Caixas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Caixa de entrada", "Enviadas" }));
        Caixas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(Caixas.getSelectedIndex() == 0) {
                    UpdateTela();
                } else {
                    UpdateTelaMsgsEnviadas();
                }
            }
        });

        ListaMensagens.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = ConversadorServidor.getInstance().msgVectorToString();

            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        ListaMensagens.setToolTipText("");
        ListaMensagens.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if(Caixas.getSelectedIndex() == 0) {
                    try {
                        if(!e.getValueIsAdjusting() && ListaMensagens.getSelectedIndex() != -1) {
                            Message m = Controlador.getInstance().getMensagens()[ListaMensagens.getSelectedIndex()];
                            m.setTela(new TelaMensagem());
                            m.getTela().getAssunto().setText("Assunto: "+m.getAssunto());
                            m.getTela().getCorpoMsg().setText("Mensagem: "+ m.getMensagem());
                            m.getTela().getDe().setText("De: "+m.getDe());
                            m.getTela().getPara().setText("Para: "+m.getPara());
                        }
                    } catch(NullPointerException e1) {
                        return;
                    }
                } else {
                    try {
                        if(!e.getValueIsAdjusting() && ListaMensagens.getSelectedIndex() != -1) {
                            Message m = Controlador.getInstance().getMensagensEnviadas()[ListaMensagens.getSelectedIndex()];
                            m.setTela(new TelaMensagem());
                            m.getTela().getAssunto().setText("Assunto: "+m.getAssunto());
                            m.getTela().getCorpoMsg().setText("Mensagem: "+ m.getMensagem());
                            m.getTela().getDe().setText("De: "+m.getDe());
                            m.getTela().getPara().setText("Para: "+m.getPara());
                        }
                    }catch (NullPointerException e2) {
                        return;
                    }
                }
            }
        });
        jScrollPane2.setViewportView(ListaMensagens);

        LogadoEm1.setText("Logado como: "+Controlador.getInstance().getUser());

        javax.swing.GroupLayout AbaPastasLayout = new javax.swing.GroupLayout(AbaPastas);
        AbaPastas.setLayout(AbaPastasLayout);
        AbaPastasLayout.setHorizontalGroup(
            AbaPastasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AbaPastasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AbaPastasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AbaPastasLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(26, Short.MAX_VALUE))
                    .addGroup(AbaPastasLayout.createSequentialGroup()
                        .addComponent(Caixas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LogadoEm1)
                        .addGap(116, 116, 116))))
        );
        AbaPastasLayout.setVerticalGroup(
            AbaPastasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AbaPastasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AbaPastasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Caixas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LogadoEm1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                .addContainerGap())
        );

        ListaAbas.addTab("Pastas", AbaPastas);

        jLabel1.setText("Para:");

        jLabel2.setText("Assunto:");

        jLabel3.setText("Mensagem:");

        InputPara.setToolTipText("Digite o remetente");

        InputAssunto.setToolTipText("Digite o assunto");

        CorpoMensagem.setColumns(20);
        CorpoMensagem.setRows(5);
        CorpoMensagem.setToolTipText("Digite a mensagem.");
        CorpoMensagem.setWrapStyleWord(true);
        jScrollPane1.setViewportView(CorpoMensagem);

        BotaoEnviar.setText("Enviar");
        BotaoEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoEnviarActionPerformed(evt);
            }
        });

        LogadoEm2.setText("Logado como: "+Controlador.getInstance().getUser());

        javax.swing.GroupLayout AbaMensagensLayout = new javax.swing.GroupLayout(AbaMensagens);
        AbaMensagens.setLayout(AbaMensagensLayout);
        AbaMensagensLayout.setHorizontalGroup(
            AbaMensagensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AbaMensagensLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AbaMensagensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(AbaMensagensLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(InputPara, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AbaMensagensLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InputAssunto))
                    .addComponent(jLabel3))
                .addGap(101, 101, 101)
                .addComponent(LogadoEm2)
                .addContainerGap(175, Short.MAX_VALUE))
            .addGroup(AbaMensagensLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1)
                .addGap(18, 18, 18)
                .addComponent(BotaoEnviar)
                .addGap(23, 23, 23))
        );
        AbaMensagensLayout.setVerticalGroup(
            AbaMensagensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AbaMensagensLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(AbaMensagensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BotaoEnviar)
                    .addGroup(AbaMensagensLayout.createSequentialGroup()
                        .addGroup(AbaMensagensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(InputPara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LogadoEm2))
                        .addGap(18, 18, 18)
                        .addGroup(AbaMensagensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(InputAssunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26))
        );

        ListaAbas.addTab("Enviar Mensagens", AbaMensagens);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(ListaAbas)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ListaAbas)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotaoEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoEnviarActionPerformed

        String para = getInputPara();
        String assunto = getInputAssunto();
        String mensagem = getInputMensagem();
        ConversadorServidor.getInstance().enviarMensagem(para, assunto, mensagem);
        try {
            GerenciadorDeArquivos.getInstance().escreverNoArquivo(Controlador.getInstance().getUser(),para,assunto,mensagem);
            String s = GerenciadorDeArquivos.getInstance().lerDoArquivo();
        } catch (IOException e) {
        }
        
    }//GEN-LAST:event_BotaoEnviarActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AbaMensagens;
    private javax.swing.JPanel AbaPastas;
    private javax.swing.JButton BotaoEnviar;
    private javax.swing.JComboBox<String> Caixas;
    private javax.swing.JTextArea CorpoMensagem;
    private javax.swing.JTextField InputAssunto;
    private javax.swing.JTextField InputPara;
    private javax.swing.JTabbedPane ListaAbas;
    private javax.swing.JList<String> ListaMensagens;
    private javax.swing.JLabel LogadoEm1;
    private javax.swing.JLabel LogadoEm2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
