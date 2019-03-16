
package aula3clienteteste;

import aula3util.IChatAula;
import aula3util.Message;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import javax.swing.JOptionPane;

public class ChatTela extends javax.swing.JFrame {

    
    public ChatTela(String nome, Login l) {
        initComponents();
        
        l.setVisible(false);
        
        Mensagem.grabFocus();
        TextNome.setText(nome);
        
        // Avisar quando o usuario entrar na sala
        /*String nomeEntrada = "Usuário conectado";
        
        try {
            IChatAula objChat = (IChatAula) Naming
                    .lookup("rmi://localhost:8282/chat");
            Message msg = new Message(nomeEntrada, nome);
            objChat.sendMessage(msg);
            Conversa.setText(returnMessage(objChat.retrieveMessage()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        String nomeEntrada = "Chatbot";
        String mensagemRobo = "Olá " + nome + ", para consultar o status do seu pedido informe o número dele.";
        
        try {
            IChatAula objChat = (IChatAula) Naming
                    .lookup("rmi://localhost:8282/chat");
            Message msg = new Message(nomeEntrada, mensagemRobo);
            objChat.sendMessage(msg);
            Conversa.setText(returnMessage(objChat.retrieveMessage()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DigiteMensagem = new javax.swing.JLabel();
        Mensagem = new javax.swing.JTextField();
        Enviar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Conversa = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        TextNome = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        Chat = new javax.swing.JMenu();
        Sair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        DigiteMensagem.setText("Digite sua Mensagem: ");

        Mensagem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                MensagemFocusLost(evt);
            }
        });
        Mensagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MensagemActionPerformed(evt);
            }
        });
        Mensagem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                MensagemKeyReleased(evt);
            }
        });

        Enviar.setText("Enviar");
        Enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnviarActionPerformed(evt);
            }
        });

        Conversa.setEditable(false);
        Conversa.setColumns(20);
        Conversa.setRows(5);
        jScrollPane1.setViewportView(Conversa);

        TextNome.setEditable(false);
        jScrollPane2.setViewportView(TextNome);

        jLabel1.setText("Usuário conectado:");

        Chat.setText("Chat ");

        Sair.setText("Sair");
        Sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SairActionPerformed(evt);
            }
        });
        Chat.add(Sair);

        jMenuBar1.add(Chat);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Mensagem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Enviar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(DigiteMensagem)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DigiteMensagem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Enviar, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(Mensagem))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SairActionPerformed

    }//GEN-LAST:event_SairActionPerformed

    private void MensagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MensagemActionPerformed
        
    }//GEN-LAST:event_MensagemActionPerformed

    private void EnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnviarActionPerformed
        
        try {
            String nome = TextNome.getText();
            String msgp = "";
            String msgRobo = "";
            
            msgp = Mensagem.getText();
            
            IChatAula objChat = (IChatAula) Naming
                    .lookup("rmi://localhost:8282/chat");
            
            List<Message> msgAtuais = objChat.retrieveMessage();
            msgRobo = msgAtuais.get(msgAtuais.size()-1).getMessage();
            
            Message msg = new Message(nome, msgp);
            objChat.sendMessage(msg);
            
            // Pegando a resposta do robô
            Message msgRobot = objChat.getRobotMessage(msgRobo, msgp);
            objChat.sendMessage(msgRobot);
            Conversa.setText(returnMessage(objChat.retrieveMessage()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Mensagem.setText("");
    }//GEN-LAST:event_EnviarActionPerformed

    private void MensagemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MensagemKeyReleased
        if (evt.getKeyCode() == evt.VK_ENTER) {
            Enviar.doClick();
        } else if (evt.getKeyCode() == evt.VK_ESCAPE) {
            try {
                IChatAula objChat = (IChatAula) Naming
                        .lookup("rmi://localhost:8282/chat");
                Message msg = new Message("Usuário desconectado", TextNome.getText());
                objChat.sendMessage(msg);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.exit(0);
        }
    }//GEN-LAST:event_MensagemKeyReleased

    private void MensagemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_MensagemFocusLost
        
    }//GEN-LAST:event_MensagemFocusLost
    private static String returnMessage(List<Message> lst) {
        String valor = "";
        for (Message message : lst) {
            valor += message.getUser() + ": " + message.getMessage() + "\n";
        }
        return valor;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Chat;
    private javax.swing.JTextArea Conversa;
    private javax.swing.JLabel DigiteMensagem;
    private javax.swing.JButton Enviar;
    private javax.swing.JTextField Mensagem;
    private javax.swing.JMenuItem Sair;
    private javax.swing.JTextPane TextNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
