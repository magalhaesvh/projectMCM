package projectmcm.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectmcm.model.domain.Agencia;
import projectmcm.model.domain.Gerente;

public class GerenteDAO extends FuncionarioDAO {
    public List<Gerente> listar() {
        String sql = "SELECT * FROM funcionario WHERE tipo=2";
        List<Gerente> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = super.getConnection().prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Gerente gerente = new Gerente();
                gerente.setIdFuncionario(resultado.getInt("id_funcionario"));
                gerente.setNome(resultado.getString("nome"));
                gerente.setEmail(resultado.getString("email"));
                gerente.setSenha(resultado.getString("senha"));
                gerente.setCpf(resultado.getString("cpf"));
                gerente.setRg(resultado.getString("rg"));
                gerente.setDataContratacao(resultado.getDate("data_contratacao").toLocalDate());
                gerente.setTipo(resultado.getByte("tipo"));
                
                Agencia agencia = new Agencia();
                agencia.setIdAgencia(resultado.getInt("id_agencia"));

                //Obtendo os dados completos da Agencia associada ao gerente
                AgenciaDAO agenciaDAO = new AgenciaDAO();
                agenciaDAO.setConnection(super.getConnection());
                gerente.setAgencia(agenciaDAO.buscar(agencia));
                
                retorno.add(gerente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
        
    public List<Gerente> buscar(String texto) {
        String sql = "SELECT * FROM funcionario WHERE tipo=2 AND (nome=? OR CPF=?)";
        List<Gerente> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, texto);
            stmt.setString(2, texto);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Gerente gerente = new Gerente();
                gerente.setIdFuncionario(resultado.getInt("id_funcionario"));
                gerente.setNome(resultado.getString("nome"));
                gerente.setEmail(resultado.getString("email"));
                gerente.setSenha(resultado.getString("senha"));
                gerente.setCpf(resultado.getString("cpf"));
                gerente.setRg(resultado.getString("rg"));
                gerente.setDataContratacao(resultado.getDate("data_contratacao").toLocalDate());
                gerente.setTipo(resultado.getByte("tipo"));
                
                Agencia agencia = new Agencia();
                agencia.setIdAgencia(resultado.getInt("id_agencia"));

                //Obtendo os dados completos da Agencia associada ao gerente
                AgenciaDAO agenciaDAO = new AgenciaDAO();
                agenciaDAO.setConnection(super.getConnection());
                gerente.setAgencia(agenciaDAO.buscar(agencia));
                
                retorno.add(gerente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
