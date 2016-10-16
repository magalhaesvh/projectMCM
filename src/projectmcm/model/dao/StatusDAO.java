package projectmcm.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectmcm.model.domain.Status;

public class StatusDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Status status) {
        String sql = "INSERT INTO status (nome, tipo) VALUES(?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, status.getNome());
            stmt.setString(2, String.valueOf(status.getTipo()));
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StatusDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Status status) {
        String sql = "UPDATE status SET nome=?, tipo=? WHERE id_status=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, status.getNome());
            stmt.setString(2, String.valueOf(status.getTipo()));
            stmt.setInt(3, status.getIdStatus());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StatusDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Status status) {
        String sql = "DELETE FROM status WHERE id_status=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, status.getIdStatus());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StatusDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Status> listar() {
        String sql = "SELECT * FROM status";
        List<Status> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Status status = new Status();
                status.setIdStatus(resultado.getInt("id_status"));
                status.setNome(resultado.getString("nome"));
                status.setTipo(resultado.getByte("tipo"));
                retorno.add(status);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public List<Status> buscar(String texto) {
        String sql = "SELECT * FROM status WHERE id_status=? OR nome=?";
        List<Status> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, texto);
            stmt.setString(2, texto);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Status status = new Status();
                status.setIdStatus(resultado.getInt("id_status"));
                status.setNome(resultado.getString("nome"));
                status.setTipo(resultado.getByte("tipo"));
                retorno.add(status);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Status buscar(Status status) {
        String sql = "SELECT * FROM status WHERE id_status=?";
        Status retorno = new Status();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, status.getIdStatus());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                status.setNome(resultado.getString("nome"));
                status.setTipo(resultado.getByte("tipo"));
                retorno = status;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public Map<String, Integer> quantidadeItensPorStatus(Status status){
        String sql = "SELECT count(s.id_status) as quantidade, s.nome FROM `status` AS s LEFT JOIN veiculo AS v ON s.id_status=v.id_status LEFT JOIN vistoria AS vi ON vi.id_status=s.id_status WHERE s.id_status=?";
        Map<String, Integer> retorno = new HashMap();
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, status.getIdStatus());
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                retorno.put(resultado.getString("nome"), resultado.getInt("quantidade"));
            }
            return retorno;
        } catch (SQLException ex) {
            Logger.getLogger(StatusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
