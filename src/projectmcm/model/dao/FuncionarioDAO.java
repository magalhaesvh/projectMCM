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

public class FuncionarioDAO {

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

    public Funcionario buscar(Funcionario func) {
        String sql = "SELECT * FROM funcionario WHERE id_funcionario=?";
        Funcionario retorno = new Funcionario();
        try {
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, String.valueOf(func.getIdFuncionario()));
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setIdFuncionario(resultado.getInt("id_funcionario"));
                funcionario.setNome(resultado.getString("nome"));
                funcionario.setEmail(resultado.getString("email"));
                funcionario.setSenha(resultado.getString("senha"));
                funcionario.setCpf(resultado.getString("cpf"));
                funcionario.setRg(resultado.getString("rg"));
                funcionario.setDataContratacao(resultado.getDate("data_contratacao").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                funcionario.setTipo(resultado.getByte("tipo"));

                Agencia agencia = new Agencia();
                agencia.setIdAgencia(resultado.getInt("id_agencia"));

                //Obtendo os dados completos da Agencia associada ao gerente
                AgenciaDAO agenciaDAO = new AgenciaDAO();
                agenciaDAO.setConnection(connection);
                funcionario.setAgencia(agenciaDAO.buscar(agencia));
                retorno = funcionario;
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
                funcionario.setDataContratacao(resultado.getDate("data_contratacao").toLocalDate());
                funcionario.setTipo(resultado.getByte("tipo"));
                agencia.setIdAgencia(resultado.getInt("id_agencia"));

                //Obtendo os dados completos da Agencia associada ao gerente
                AgenciaDAO agenciaDAO = new AgenciaDAO();
                agenciaDAO.setConnection(connection);
                funcionario.setAgencia(agenciaDAO.buscar(agencia));
            } else {
                funcionario = null;
            }
            retorno = funcionario;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public List<Funcionario> listarGerentes() {
        String sql = "SELECT * FROM funcionario WHERE tipo=2";
        List<Funcionario> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Funcionario gerente = new Funcionario();
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
                agenciaDAO.setConnection(connection);
                gerente.setAgencia(agenciaDAO.buscar(agencia));

                retorno.add(gerente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public List<Funcionario> buscarGerentes(String texto) {
        String sql = "SELECT * FROM funcionario WHERE tipo=2 AND (nome=? OR CPF=?)";
        List<Funcionario> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, texto);
            stmt.setString(2, texto);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Funcionario gerente = new Funcionario();
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
                agenciaDAO.setConnection(connection);
                gerente.setAgencia(agenciaDAO.buscar(agencia));

                retorno.add(gerente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public List<Funcionario> listarLocador() {
        String sql = "SELECT * FROM funcionario WHERE tipo=3";
        List<Funcionario> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Funcionario locador = new Funcionario();
                locador.setIdFuncionario(resultado.getInt("id_funcionario"));
                locador.setNome(resultado.getString("nome"));
                locador.setEmail(resultado.getString("email"));
                locador.setSenha(resultado.getString("senha"));
                locador.setCpf(resultado.getString("cpf"));
                locador.setRg(resultado.getString("rg"));
                locador.setDataContratacao(resultado.getDate("data_contratacao").toLocalDate());
                locador.setTipo(resultado.getByte("tipo"));

                Agencia agencia = new Agencia();
                agencia.setIdAgencia(resultado.getInt("id_agencia"));

                //Obtendo os dados completos da Agencia associada ao gerente
                AgenciaDAO agenciaDAO = new AgenciaDAO();
                agenciaDAO.setConnection(connection);
                locador.setAgencia(agenciaDAO.buscar(agencia));

                retorno.add(locador);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public List<Funcionario> buscarLocador(String texto) {
        String sql = "SELECT * FROM funcionario WHERE tipo=3 AND (nome=? OR CPF=?)";
        List<Funcionario> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, texto);
            stmt.setString(2, texto);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Funcionario locador = new Funcionario();
                locador.setIdFuncionario(resultado.getInt("id_funcionario"));
                locador.setNome(resultado.getString("nome"));
                locador.setEmail(resultado.getString("email"));
                locador.setSenha(resultado.getString("senha"));
                locador.setCpf(resultado.getString("cpf"));
                locador.setRg(resultado.getString("rg"));
                locador.setDataContratacao(resultado.getDate("data_contratacao").toLocalDate());
                locador.setTipo(resultado.getByte("tipo"));

                Agencia agencia = new Agencia();
                agencia.setIdAgencia(resultado.getInt("id_agencia"));

                //Obtendo os dados completos da Agencia associada ao gerente
                AgenciaDAO agenciaDAO = new AgenciaDAO();
                agenciaDAO.setConnection(connection);
                locador.setAgencia(agenciaDAO.buscar(agencia));

                retorno.add(locador);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public List<Funcionario> listarAdmin() {
        String sql = "SELECT * FROM funcionario WHERE tipo=1";
        List<Funcionario> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Funcionario administrador = new Funcionario();
                administrador.setIdFuncionario(resultado.getInt("id_administrador"));
                administrador.setNome(resultado.getString("nome"));
                administrador.setEmail(resultado.getString("email"));
                administrador.setSenha(resultado.getString("senha"));
                administrador.setCpf(resultado.getString("cpf"));
                administrador.setRg(resultado.getString("rg"));
                administrador.setDataContratacao(resultado.getDate("dataContratacao").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                administrador.setTipo(resultado.getByte("tipo"));

                Agencia agencia = new Agencia();
                agencia.setIdAgencia(resultado.getInt("id_agencia"));

                //Obtendo os dados completos da Agencia associada ao gerente
                AgenciaDAO agenciaDAO = new AgenciaDAO();
                agenciaDAO.setConnection(connection);
                administrador.setAgencia(agenciaDAO.buscar(agencia));

                retorno.add(administrador);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public List<Funcionario> buscarAdmin(String texto) {
        String sql = "SELECT * FROM funcionario WHERE tipo=1 AND (nome=? OR CPF=?)";
        List<Funcionario> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, texto);
            stmt.setString(2, texto);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Funcionario administrador = new Funcionario();
                administrador.setIdFuncionario(resultado.getInt("id_administrador"));
                administrador.setNome(resultado.getString("nome"));
                administrador.setEmail(resultado.getString("email"));
                administrador.setSenha(resultado.getString("senha"));
                administrador.setCpf(resultado.getString("cpf"));
                administrador.setRg(resultado.getString("rg"));
                administrador.setDataContratacao(resultado.getDate("data_contratacao").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                administrador.setTipo(resultado.getByte("tipo"));

                Agencia agencia = new Agencia();
                agencia.setIdAgencia(resultado.getInt("id_agencia"));

                //Obtendo os dados completos da Agencia associada ao gerente
                AgenciaDAO agenciaDAO = new AgenciaDAO();
                agenciaDAO.setConnection(connection);
                administrador.setAgencia(agenciaDAO.buscar(agencia));

                retorno.add(administrador);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}
