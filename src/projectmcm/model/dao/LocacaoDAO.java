package projectmcm.model.dao;

import java.sql.Connection;
import java.sql.Date;
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
import projectmcm.model.domain.Veiculo;

public class LocacaoDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Locacao locacao) {
        String sql = "INSERT INTO locacao (id_cliente, id_plano, id_locador, data_inicio, data_final, km_inicial, id_veiculo) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, locacao.getCliente().getIdCliente());
            stmt.setInt(2, locacao.getPlano().getIdPlano());
            stmt.setInt(3, locacao.getLocador().getIdFuncionario());
            stmt.setDate(4, Date.valueOf(locacao.getDataInicio()));
            stmt.setDate(5, Date.valueOf(locacao.getDataFinal()));
            stmt.setFloat(6, locacao.getKmInicial());
            stmt.setFloat(7, locacao.getVeiculo().getIdVeiculo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(LocacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Locacao locacao) {
        String sql = "UPDATE locacao SET id_cliente=?, id_plano=?, id_locador=?, data_inicio=?, data_final=?, km_inicial=?, id_veiculo=? WHERE id_locacao=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, locacao.getCliente().getIdCliente());
            stmt.setInt(2, locacao.getPlano().getIdPlano());
            stmt.setInt(3, locacao.getLocador().getIdFuncionario());
            stmt.setDate(4, Date.valueOf(locacao.getDataInicio()));
            stmt.setDate(5, Date.valueOf(locacao.getDataFinal()));
            stmt.setFloat(6, locacao.getKmInicial());
            stmt.setInt(7, locacao.getVeiculo().getIdVeiculo());
            stmt.setInt(8, locacao.getIdLocacao());
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
                locacao.setDataInicio(resultado.getDate("data_inicio").toLocalDate());
                locacao.setDataFinal(resultado.getDate("data_final").toLocalDate());
                locacao.setKmInicial(resultado.getFloat("km_inicial"));
                
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
                
                Veiculo veiculo = new Veiculo();                
                veiculo.setIdVeiculo(resultado.getInt("id_veiculo")); 
                VeiculoDAO veiculoDAO = new VeiculoDAO();
                veiculoDAO.setConnection(connection);
                locacao.setVeiculo(veiculoDAO.buscar(veiculo)); 
                
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
                locacao.setDataInicio(resultado.getDate("data_inicio").toLocalDate());
                locacao.setDataFinal(resultado.getDate("data_final").toLocalDate());
                locacao.setKmInicial(resultado.getFloat("km_inicial"));
                
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
                                
                Veiculo veiculo = new Veiculo();                
                veiculo.setIdVeiculo(resultado.getInt("id_veiculo")); 
                VeiculoDAO veiculoDAO = new VeiculoDAO();
                veiculoDAO.setConnection(connection);
                locacao.setVeiculo(veiculoDAO.buscar(veiculo)); 
                
                retorno = locacao;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LocacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
