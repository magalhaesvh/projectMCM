package projectmcm.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectmcm.model.domain.Plano;

public class PlanoDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Plano plano) {
        String sql = "INSERT INTO plano (nome, regulamento, descricao, id_tipo, calculo_quilometragem, valor_quilometragem, custo_fixo, valor_custo, diaria, valor_diaria) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, plano.getNome());
            stmt.setString(2, plano.getRegulamento());
            stmt.setString(3, plano.getDescricao());
            stmt.setInt(4, plano.getIdTipo());
            stmt.setBoolean(5, plano.isCalculoQuilometragem());
            stmt.setFloat(6, plano.getValorQuilometragem());
            stmt.setBoolean(7, plano.isCustoFixo());
            stmt.setFloat(8, plano.getValorCusto());
            stmt.setBoolean(9, plano.isDiaria());
            stmt.setFloat(10, plano.getValorDiaria());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Plano plano) {
        String sql = "UPDATE plano SET nome=?, regulamento=?, descricao=?, id_tipo=?, calculo_quilometragem=?, valor_quilometragem=?, custo_fixo=?, valor_custo=?, diaria=?, valor_diaria=? WHERE id_plano=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, plano.getNome());
            stmt.setString(2, plano.getRegulamento());
            stmt.setString(3, plano.getDescricao());
            stmt.setInt(4, plano.getIdTipo());
            stmt.setBoolean(5, plano.isCalculoQuilometragem());
            stmt.setFloat(6, plano.getValorQuilometragem());
            stmt.setBoolean(7, plano.isCustoFixo());
            stmt.setFloat(8, plano.getValorCusto());
            stmt.setBoolean(9, plano.isDiaria());
            stmt.setFloat(10, plano.getValorDiaria());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Plano plano) {
        String sql = "DELETE FROM plano WHERE id_plano=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, plano.getIdPlano());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Plano> listar() {
        String sql = "SELECT * FROM plano";
        List<Plano> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Plano plano = new Plano();
                plano.setNome(resultado.getString("nome"));
                plano.setRegulamento(resultado.getString("regulamento"));
                plano.setDescricao(resultado.getString("descricao"));
                plano.setIdTipo(resultado.getInt("id_tipo"));
                plano.setCalculoQuilometragem(resultado.getBoolean("calculo_quilometragem"));
                plano.setValorQuilometragem(resultado.getFloat("valor_quilometragem"));
                plano.setCustoFixo(resultado.getBoolean("custo_fixo"));
                plano.setValorCusto(resultado.getFloat("valor_custo"));
                plano.setDiaria(resultado.getBoolean("diaria"));
                plano.setValorDiaria(resultado.getFloat("valor_diaria"));
                
                retorno.add(plano);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Plano buscar(Plano plano) {
        String sql = "SELECT * FROM plano WHERE id_cliente=?";
        Plano retorno = new Plano();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, plano.getIdPlano());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                plano.setNome(resultado.getString("nome"));
                plano.setRegulamento(resultado.getString("regulamento"));
                plano.setDescricao(resultado.getString("descricao"));
                plano.setIdTipo(resultado.getInt("id_tipo"));
                plano.setCalculoQuilometragem(resultado.getBoolean("calculo_quilometragem"));
                plano.setValorQuilometragem(resultado.getFloat("valor_quilometragem"));
                plano.setCustoFixo(resultado.getBoolean("custo_fixo"));
                plano.setValorCusto(resultado.getFloat("valor_custo"));
                plano.setDiaria(resultado.getBoolean("diaria"));
                plano.setValorDiaria(resultado.getFloat("valor_diaria"));
                retorno = plano;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
