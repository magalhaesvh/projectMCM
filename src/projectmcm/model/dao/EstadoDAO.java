package projectmcm.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectmcm.model.domain.Estado;

public class EstadoDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public List<Estado> listar() {
        String sql = "SELECT * FROM estado";
        List<Estado> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Estado estado = new Estado();
                estado.setIdEstado(resultado.getInt("id_estado"));
                estado.setNome(resultado.getString("nome"));
                estado.setSigla(resultado.getString("sigla"));
                retorno.add(estado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Estado buscar(Estado estado) {
        String sql = "SELECT * FROM estado WHERE id_estado=?";
        Estado retorno = new Estado();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, estado.getIdEstado());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                estado.setNome(resultado.getString("nome"));                
                estado.setSigla(resultado.getString("sigla"));
                retorno = estado;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
