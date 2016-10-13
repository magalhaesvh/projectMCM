package projectmcm.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectmcm.model.domain.Endereco;

public class EnderecoDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Endereco endereco) {
        String sql = "INSERT INTO endereco (id_cidade, logradouro, numero, complemento, bairro, id_cliente, tipo, comissao, id_status) VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, endereco.getIdCidade());
            stmt.setString(2, endereco.getLogradouro());
            stmt.setInt(3, endereco.getNumero());
            stmt.setString(4, endereco.getComplemento());
            stmt.setString(5, endereco.getBairro());
            stmt.setInt(6, endereco.getIdCliente());
            stmt.setInt(7, endereco.getIdAgencia());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Endereco endereco) {
        String sql = "UPDATE endereco SET (id_cidade=?, logradouro=?, numero=?, complemento=?, bairro=?, id_cliente=?, id_agencia=?) WHERE id_endereco=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, endereco.getIdCidade());
            stmt.setString(2, endereco.getLogradouro());
            stmt.setInt(3, endereco.getNumero());
            stmt.setString(4, endereco.getComplemento());
            stmt.setString(5, endereco.getBairro());
            stmt.setInt(6, endereco.getIdCliente());
            stmt.setInt(7, endereco.getIdAgencia());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Endereco endereco) {
        String sql = "DELETE FROM endereco WHERE id_endereco=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, endereco.getIdEndereco());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Endereco> listar() {
        String sql = "SELECT * FROM endereco";
        List<Endereco> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Endereco endereco = new Endereco();
                endereco.setIdEndereco(resultado.getInt("id_endereco"));
                endereco.setIdCidade(resultado.getInt("id_cidade"));
                endereco.setLogradouro(resultado.getString("logradouro"));
                endereco.setNumero(resultado.getInt("numero"));
                endereco.setComplemento(resultado.getString("complemento"));
                endereco.setBairro(resultado.getString("bairro"));
                endereco.setIdCliente(resultado.getInt("id_cliente"));
                endereco.setIdAgencia(resultado.getInt("id_agencia"));
                retorno.add(endereco);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Endereco buscar(Endereco endereco) {
        String sql = "SELECT * FROM endereco WHERE id_endereco=?";
        Endereco retorno = new Endereco();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, endereco.getIdEndereco());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                endereco.setIdCidade(resultado.getInt("id_cidade"));
                endereco.setLogradouro(resultado.getString("logradouro"));
                endereco.setNumero(resultado.getInt("numero"));
                endereco.setComplemento(resultado.getString("complemento"));
                endereco.setBairro(resultado.getString("bairro"));
                endereco.setIdCliente(resultado.getInt("id_cliente"));
                endereco.setIdAgencia(resultado.getInt("id_agencia"));
                retorno = endereco;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
