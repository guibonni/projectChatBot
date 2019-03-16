/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula3util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author logatti
 */
public class MessageDB {
    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;

    public MessageDB() {
        connection = ConexaoBanco.getConnection();
    }

    public boolean inserir(Message m) throws SQLException {
        try {
            PreparedStatement stmt = this.connection
                            .prepareStatement("insert into mensagem(usuario, mensagem, data_mensagem) values (?, ?, sysdate())");

            stmt.setString(1, m.getUser());
            stmt.setString(2, m.getMessage());

            stmt.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e.toString());
        } finally {
            connection.close();
        }

        return false;
    }
}