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
import projectmcm.model.domain.Cliente;
import projectmcm.model.domain.Funcionario;
import projectmcm.model.domain.Locacao;
import projectmcm.model.domain.Plano;
import projectmcm.model.domain.Status;

public class LocacaoDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Locacao locacao) {
        String sql = "INSERT INTO locacao (id_cliente, id_plano, id_locador, data_inicio, data_final, km_inicial, km_final, id_status, id_agencia_devolucao) VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, locacao.getCliente().getIdCliente());
            stmt.setInt(2, locacao.getPlano().getIdPlano());
            stmt.setInt(3, locacao.getLocador().getIdFuncionario());
            stmt.setDate(4, locacao.getDataInicio());
            stmt.setDate(5, locacao.getDataFinal());
            stmt.setFloat(6, locacao.getKmInicial());
            stmt.setFloat(7, locacao.getKmFinal());
            stmt.setInt(8, locacao.getStatus().getIdStatus());
            stmt.setInt(9, locacao.getAgenciaDevolucao().getIdAgencia());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(LocacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Locacao locacao) {
        String sql = "UPDATE locacao SET (id_cliente=?, id_plano=?, id_locador=?, data_inicio=?, data_final=?, km_inicial=?, km_final=?, id_status=?, id_agencia_devolucao) WHERE id_locacao=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, locacao.getCliente().getIdCliente());
            stmt.setInt(2, locacao.getPlano().getIdPlano());
            stmt.setInt(3, locacao.getLocador().getIdFuncionario());
            stmt.setDate(4, locacao.getDataInicio());
            stmt.setDate(5, locacao.getDataFinal());
            stmt.setFloat(6, locacao.getKmInicial());
            stmt.setFloat(7, locacao.getKmFinal());
            stmt.setInt(8, locacao.getStatus().getIdStatus());
            stmt.setInt(9, locacao.getAgenciaDevolucao().getIdAgencia());
            stmt.setInt(10, locacao.getIdLocacao());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(LocacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Locacao locacao) {
        String sql = "DELETE FROM locacao WHERE id_locacao=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, locacao.getIdLocacao());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(LocacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Locacao> listar() {
        String sql = "SELECT * FROM locacao";
        List<Locacao> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Locacao locacao = new Locacao();
                locacao.setIdLocacao(resultado.getInt("id_locacao"));
                locacao.setDataInicio(resultado.getDate("data_inicio"));
                locacao.setDataFinal(resultado.getDate("data_final"));
                locacao.setKmInicial(resultado.getFloat("km_inicial"));
                locacao.setKmFinal(resultado.getFloat("km_final"));
                
                Cliente cliente = new Cliente();                
                cliente.setIdCliente(resultado.getInt("id_cliente")); 
                ClienteDAO clienteDAO = new ClienteDAO();
                clienteDAO.setConnection(connection);
                locacao.setCliente(clienteDAO.buscar(cliente));
                
                Plano plano = new Plano();                
                plano.setIdPlano(resultado.getInt("id_plano")); 
                PlanoDAO planoDAO = new PlanoDAO();
                planoDAO.setConnection(connection);
                locacao.setPlano(planoDAO.buscar(plano));
                
                Funcionario locador = new Funcionario();                
                locador.setIdFuncionario(resultado.getInt("id_funcionario")); 
                FuncionarioDAO locadorDAO = new FuncionarioDAO();
                locadorDAO.setConnection(connection);
                locacao.setLocador(locadorDAO.buscar(locador));
                                
                Agencia agencia = new Agencia();                
                agencia.setIdAgencia(resultado.getInt("id_agencia")); 
                AgenciaDAO agenciaDAO = new AgenciaDAO();
                agenciaDAO.setConnection(connection);
                locacao.setAgenciaDevolucao(agenciaDAO.buscar(agencia));                
                
                Status status = new Status();                
                status.setIdStatus(resultado.getInt("id_status")); 
                StatusDAO statusDAO = new StatusDAO();
                statusDAO.setConnection(connection);
                locacao.setStatus(statusDAO.buscar(status));
                retorno.add(locacao);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LocacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Locacao buscar(Locacao locacao) {
        String sql = "SELECT * FROM locacao WHERE id_locacao=?";
        Locacao retorno = new Locacao();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, locacao.getIdLocacao());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                locacao.setIdLocacao(resultado.getInt("id_locacao"));
                locacao.setDataInicio(resultado.getDate("data_inicio"));
                locacao.setDataFinal(resultado.getDate("data_final"));
                locacao.setKmInicial(resultado.getFloat("km_inicial"));
                locacao.setKmFinal(resultado.getFloat("km_final"));
                
                Cliente cliente = new Cliente();                
                cliente.setIdCliente(resultado.getInt("id_cliente")); 
                ClienteDAO clienteDAO = new ClienteDAO();
                clienteDAO.setConnection(connection);
                locacao.setCliente(clienteDAO.buscar(cliente));
                
                Plano plano = new Plano();                
                plano.setIdPlano(resultado.getInt("id_plano")); 
                PlanoDAO planoDAO = new PlanoDAO();
                planoDAO.setConnection(connection);
                locacao.setPlano(planoDAO.buscar(plano));
                
                Funcionario locador = new Funcionario();                
                locador.setIdFuncionario(resultado.getInt("id_funcionario")); 
                FuncionarioDAO locadorDAO = new FuncionarioDAO();
                locadorDAO.setConnection(connection);
                locacao.setLocador(locadorDAO.buscar(locador));
                                
                Agencia agencia = new Agencia();                
                agencia.setIdAgencia(resultado.getInt("id_agencia")); 
                AgenciaDAO agenciaDAO = new AgenciaDAO();
                agenciaDAO.setConnection(connection);
                locacao.setAgenciaDevolucao(agenciaDAO.buscar(agencia));
                
                
                Status status = new Status();                
                status.setIdStatus(resultado.getInt("id_status")); 
                StatusDAO statusDAO = new StatusDAO();
                statusDAO.setConnection(connection);
                locacao.setStatus(statusDAO.buscar(status));
                retorno = locacao;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LocacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
