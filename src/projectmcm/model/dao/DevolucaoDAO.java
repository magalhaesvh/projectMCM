/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmcm.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectmcm.model.domain.Devolucao;
import projectmcm.model.domain.Locacao;

/**
 *
 * @author vh_ma
 */
public class DevolucaoDAO {
    
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Devolucao devolucao) {
        String sql = "INSERT INTO devolucao (id_locacao, data_entrega, km_final VALUES(?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, devolucao.getLocacao().getIdLocacao());
            stmt.setDate(2, (Date) devolucao.getDataDevolucao());
            stmt.setFloat(3, devolucao.getKmFinal());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(LocacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Devolucao devolucao) {
        String sql = "UPDATE devolucao SET (id_locacao=?, data_entrega=?, km_final=? ) WHERE id_locacao=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, devolucao.getLocacao().getIdLocacao());
            stmt.setDate(2, (Date) devolucao.getDataDevolucao());
            stmt.setFloat(3, devolucao.getKmFinal());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(LocacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Devolucao devolucao) {
        String sql = "DELETE FROM devolucao WHERE id_locacao=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, devolucao.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(LocacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
     public Devolucao buscar(Devolucao devolucao) {
        String sql = "SELECT * FROM devolucao WHERE id_locacao=?";
        Devolucao retorno = new Devolucao();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, devolucao.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                devolucao.setDataDevolucao(resultado.getDate(("data_devolucao")));
                devolucao.setKmFinal(resultado.getFloat("km_final"));
                
                retorno = devolucao;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LocacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
}
