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
public class PedidoDB {
    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;

    public PedidoDB() {
        connection = ConexaoBanco.getConnection();
    }

    public Pedido getPedido(int id) throws SQLException {
        Pedido pedido = new Pedido();

        try {
            ps = this.connection.prepareStatement("select id, status, id_produto from pedido where id = " + id);
            rs = ps.executeQuery();

            while (rs.next()) {
                pedido.setId(rs.getInt("id"));
                pedido.setStatus(rs.getString("status"));
                pedido.setProduto(new Produto(rs.getInt("id_produto")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedido;
    }
}