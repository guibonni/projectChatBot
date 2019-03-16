package aula3util;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

public class ChatAula extends UnicastRemoteObject implements IChatAula {
    public ChatAula() throws RemoteException {
        super ();
    }
    
    private static final long serialVersionUID = 1L;
    
    @Override
    public void sendMessage(Message msg) throws RemoteException {
        Message.setLstMessage(msg);
        // Role do banco
        try {
            MessageDB mdb = new MessageDB();
            mdb.inserir(msg);  
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Message> retrieveMessage() throws RemoteException {
        return Message.getLstMessage();
    }
    
    @Override
    public Message getRobotMessage(String msgRobot, String msgUser) throws RemoteException {
        // Classe boladona que vai fazer o role todo
        
        if (msgRobot.contains("para consultar o status do seu pedido informe o número dele")) {
            int numPedido;
            
            try {
                numPedido = Integer.parseInt(msgUser);
            } catch (NumberFormatException e) {
                numPedido = 0;
            }
            
            if (numPedido == 0) {
                return new Message("Chatbot", "Número do pedido inválido!");
            } else {
                return new Message("Chatbot", "Número dahora, vou procurar no banco.");
            }
        } else {
            return new Message("Chatbot", "Pergunta do robô não configurada.");
        }
    }
    
}