package projectmcm.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectmcm.model.domain.Agencia;
import projectmcm.model.domain.Funcionario;

public abstract class FuncionarioDAO {
    
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (nome, email, senha, cpf, rg, data_contratacao, tipo, id_agencia) VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getEmail());
            stmt.setString(3, funcionario.getSenha());
            stmt.setString(4, funcionario.getCpf());
            stmt.setString(5, funcionario.getRg());
            stmt.setDate(6, java.sql.Date.valueOf(funcionario.getDataContratacao()));
            stmt.setByte(7, funcionario.getTipo());            
            stmt.setInt(8, funcionario.getAgencia().getIdAgencia());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Funcionario funcionario) {
        String sql = "UPDATE funcionario SET nome=?, email=?, senha=?, cpf=?, rg=?, data_contratacao=?, tipo=?, id_agencia=? WHERE id_funcionario=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getEmail());
            stmt.setString(3, funcionario.getSenha());
            stmt.setString(4, funcionario.getCpf());
            stmt.setString(5, funcionario.getRg());
            stmt.setDate(6, java.sql.Date.valueOf(funcionario.getDataContratacao()));
            stmt.setByte(7, funcionario.getTipo());
            stmt.setInt(8, funcionario.getAgencia().getIdAgencia());
            stmt.setInt(9, funcionario.getIdFuncionario());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Funcionario funcionario) {
        String sql = "DELETE FROM funcionario WHERE id_funcionario=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, funcionario.getIdFuncionario());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Funcionario> buscar(Funcionario func){
        String sql = "SELECT * FROM funcionario WHERE id_funcionario=?";
        List<Funcionario> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, String.valueOf(func.getIdFuncionario()));
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {                
                Agencia agencia = new Agencia();
                Funcionario funcionario = new Funcionario();
                funcionario.setIdFuncionario(resultado.getInt("id_funcionario"));
                funcionario.setNome(resultado.getString("nome"));
                funcionario.setEmail(resultado.getString("email"));
                funcionario.setSenha(resultado.getString("senha"));
                funcionario.setCpf(resultado.getString("cpf"));
                funcionario.setRg(resultado.getString("rg"));
                funcionario.setDataContratacao(resultado.getDate("data_contratacao").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                funcionario.setTipo(resultado.getByte("tipo"));
                
                agencia.setIdAgencia(resultado.getInt("id_agencia"));

                //Obtendo os dados completos da Agencia associada ao gerente
                AgenciaDAO agenciaDAO = new AgenciaDAO();
                agenciaDAO.setConnection(connection);
                funcionario.setAgencia(agenciaDAO.buscar(agencia));
                retorno.add(funcionario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public Funcionario logar(Funcionario funcionario) {
        String sql = "SELECT * FROM funcionario WHERE email=? AND senha=?";
        Funcionario retorno = new Funcionario();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, funcionario.getEmail());            
            stmt.setString(2, funcionario.getSenha());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                Agencia agencia = new Agencia();
                funcionario.setNome(resultado.getString("nome"));
                funcionario.setEmail(resultado.getString("email"));
                funcionario.setSenha(resultado.getString("senha"));
                funcionario.setCpf(resultado.getString("cpf"));
                funcionario.setRg(resultado.getString("rg"));
                funcionario.setDataContratacao(resultado.getDate("data_contratacao").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                funcionario.setTipo(resultado.getByte("tipo"));
                agencia.setIdAgencia(resultado.getInt("id_agencia"));

                //Obtendo os dados completos da Agencia associada ao gerente
                AgenciaDAO agenciaDAO = new AgenciaDAO();
                agenciaDAO.setConnection(connection);
                funcionario.setAgencia(agenciaDAO.buscar(agencia));
            }else{
                funcionario = null;
            }
            retorno = funcionario;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public abstract Object listar();
    
    public abstract Object buscar(String texto);
}
