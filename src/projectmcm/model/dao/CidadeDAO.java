package projectmcm.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectmcm.model.domain.Cidade;
import projectmcm.model.domain.Estado;

public class CidadeDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public List<Cidade> listar() {
        String sql = "SELECT * FROM cidade";
        List<Cidade> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Cidade cidade = new Cidade();
                cidade.setIdCidade(resultado.getInt("id_cidade"));
                cidade.setNome(resultado.getString("nome"));
                
                Estado estado = new Estado();
                estado.setIdEstado(resultado.getInt("idEstado"));
                EstadoDAO estadoDAO = new EstadoDAO();                
                estadoDAO.setConnection(getConnection());
                cidade.setEstado(estadoDAO.buscar(estado));
                retorno.add(cidade);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Cidade buscar(Cidade cidade) {
        String sql = "SELECT * FROM cidade WHERE id_cidade=?";
        Cidade retorno = new Cidade();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cidade.getIdCidade());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                cidade.setNome(resultado.getString("nome"));
                Estado estado = new Estado();
                estado.setIdEstado(resultado.getInt("idEstado"));
                EstadoDAO estadoDAO = new EstadoDAO();                
                estadoDAO.setConnection(getConnection());
                cidade.setEstado(estadoDAO.buscar(estado));
                retorno = cidade;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
