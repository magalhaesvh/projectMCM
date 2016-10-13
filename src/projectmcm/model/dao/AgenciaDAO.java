package projectmcm.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectmcm.model.domain.Agencia;

public class AgenciaDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Agencia agencia) {
        String sql = "INSERT INTO agencia (nome, cnpj) VALUES(?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, agencia.getNome());
            stmt.setString(2, agencia.getCnpj());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AgenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Agencia agencia) {
        String sql = "UPDATE agencia SET nome=?, cnpj=? WHERE id_agencia=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, agencia.getNome());
            stmt.setString(2, agencia.getCnpj());
            stmt.setInt(3, agencia.getIdAgencia());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AgenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Agencia agencia) {
        String sql = "DELETE FROM agencia WHERE id_agencia=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, agencia.getIdAgencia());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AgenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Agencia> listar() {
        String sql = "SELECT * FROM agencia";
        List<Agencia> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Agencia agencia = new Agencia();
                agencia.setIdAgencia(resultado.getInt("id_agencia"));
                agencia.setNome(resultado.getString("nome"));
                agencia.setCnpj(resultado.getString("cnpj"));
                retorno.add(agencia);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public List<Agencia> buscar(String texto) {
        String sql = "SELECT * FROM agencia WHERE id_agencia=? OR nome=? OR cnpj=?";
        List<Agencia> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, texto);
            stmt.setString(2, texto);
            stmt.setString(3, texto);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Agencia agencia = new Agencia();
                agencia.setIdAgencia(resultado.getInt("id_agencia"));
                agencia.setNome(resultado.getString("nome"));
                agencia.setCnpj(resultado.getString("cnpj"));
                retorno.add(agencia);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Agencia buscar(Agencia agencia) {
        String sql = "SELECT * FROM agencia WHERE id_agencia=?";
        Agencia retorno = new Agencia();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, agencia.getIdAgencia());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                agencia.setNome(resultado.getString("nome"));
                agencia.setCnpj(resultado.getString("cnpj"));
                retorno = agencia;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
