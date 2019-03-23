package aula3util;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdk.nashorn.internal.objects.NativeArray;

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
    public String getRobotQuestion() throws RemoteException {
        return Message.getMsgBot();
    }
    
    @Override
    public void setRobotQuestion(String msg) throws RemoteException {
        Message.setMsgBot(msg);
    }
    
    private String simplificarFrase(String msg) {
        //String msgSemPontuacao = msg.replaceAll(".", "").replaceAll(",", "").replaceAll("!", "");
        String[] palavras = msg.split(" ");
        
        String[] palavrasBoas = {"sim", "quero", "gostaria", "desejo", "não", "num", "nem", "sei", "lembro", "esqueci", "esqueceu", "perdi", "lá"};
        
        String fraseSimplificada = "";
        
        for (String palavra: palavras) {
            int num;
            try {
                num = Integer.parseInt(palavra);
            } catch (NumberFormatException e) {
                num = 0;
            }
            if (num == 0) {
                // É letra
                for (String palavraBoa: palavrasBoas) {
                    if (palavraBoa.equalsIgnoreCase(palavra)) {
                        fraseSimplificada += palavra + " ";
                    }
                }
            } else {
                // É número
                fraseSimplificada += palavra + " ";
            }
        }
        
        return fraseSimplificada.trim();
    }
    
    @Override
    public Message getRobotMessage(String msgRobot, String msgUser) throws RemoteException {
        // Classe boladona que vai fazer o role todo
        
        String msgUserS = this.simplificarFrase(msgUser);
        
        if (msgRobot.contains("para consultar o status do seu pedido informe o número dele")) {
            int numPedido;
            
            try {
                numPedido = Integer.parseInt(msgUserS);
            } catch (NumberFormatException e) {
                numPedido = 0;
            }
            
            if (numPedido == 0) { // Se chegou aqui, é porque foi informado algo além de números
                String[] frasesSemNumero = {"não sei", "num sei", "nem sei", "sei não", "não lembro", "esqueci", "esqueceu", "perdi", "sei lá"};
                String[] frasesRecusa = {"não", "não quero"};
                
                for (String frase: frasesSemNumero) {
                    if (frase.equalsIgnoreCase(msgUserS)) {
                        return new Message("Chatbot", "Entre em contato com nossa central de vendas no número (99) 9999-9999!");
                    }
                }
                
                for (String frase: frasesRecusa) {
                    if (frase.equalsIgnoreCase(msgUserS)) {
                        return new Message("Chatbot", "Desculpe, mas não posso lhe ajudar.");
                    }
                }
                
                return new Message("Chatbot", "Desculpe, não entendi!");
            } else {
                //return new Message("Chatbot", "Número dahora, vou procurar no banco.");
                // Buscar a parada no banco
                Pedido p = new Pedido();
                try {
                    PedidoDB pdb = new PedidoDB();
                    p = pdb.getPedido(numPedido);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                
                try {
                    if (!p.getStatus().isEmpty()) {
                        return new Message("Chatbot", "O status do seu pedido é: " + p.getStatus() + ".");
                    } else {
                        return new Message("Chatbot", "Pedido não encontrado!");
                    }
                } catch (NullPointerException e) {
                    return new Message("Chatbot", "Pedido não encontrado!");
                }
            }
        } else if (msgRobot.equals("Deseja consultar mais algum pedido?")) {
            msgUserS = msgUserS.toLowerCase().replaceAll("num", "não").replaceAll("nem", "não");
            
            String[] frasesSim = {"sim", "quero", "quero sim", "sim quero", "desejo", "desejo sim", "sim desejo", "gostaria", "gostaria sim", "sim gostaria"};
            String[] frasesNão = {"não", "quero não", "não quero", "desejo não", "não desejo", "gostaria não", "não gostaria"};
            String[] frasesIndeciso = {"sei lá", "sei não", "não sei"};

            for (String frase: frasesSim) {
                if (frase.equalsIgnoreCase(msgUserS)) {
                    return new Message("Chatbot", "Então para consultar o status do seu pedido informe o número dele");
                }
            }

            for (String frase: frasesNão) {
                if (frase.equalsIgnoreCase(msgUserS)) {
                    return new Message("Chatbot", "Adeus");
                }
            }
            
            for (String frase: frasesIndeciso) {
                if (frase.equalsIgnoreCase(msgUserS)) {
                    return new Message("Chatbot", "Tranquilo, não tô com pressa :D");
                }
            }
            
            return new Message("Chatbot", "Desculpe, não entendi!");
        } else {
            return new Message("Chatbot", "Pergunta do robô não configurada.");
        }
    }
    
}