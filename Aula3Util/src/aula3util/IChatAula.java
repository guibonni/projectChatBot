package aula3util;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IChatAula extends Remote{

    void sendMessage(Message msg) throws RemoteException;
    List<Message> retrieveMessage() throws RemoteException;
    String getRobotQuestion() throws RemoteException;
    void setRobotQuestion(String msg) throws RemoteException;
    Message getRobotMessage(String msgRobot, String msgUser) throws RemoteException;
}
