package projectmcm.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectmcm.model.domain.Status;
import projectmcm.model.domain.Veiculo;

public class VeiculoDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Veiculo veiculo) {
        String sql = "INSERT INTO veiculo (cor,valor, placa, chassi, ano_modelo, "
                + "ano_fabricacao, observacoes, motor, ar_condicionado, "
                + "vidro_eletrico,trava_eletrica, direcao_eletrica, cambio_automatico, abs, air_bag, `4x4`, id_status, marca, modelo, id_agencia) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, veiculo.getCor());
            stmt.setFloat(2, veiculo.getValor());
            stmt.setString(3, veiculo.getPlaca());
            stmt.setString(4, veiculo.getChassi());
            stmt.setInt(5, veiculo.getAnoModelo());
            stmt.setInt(6, veiculo.getAnoFabricacao());
            stmt.setString(7, veiculo.getObservacoes());
            stmt.setFloat(8, veiculo.getMotor());
            stmt.setBoolean(9, veiculo.isArCondicionado());
            stmt.setBoolean(10, veiculo.isVidroEletrico());
            stmt.setBoolean(11, veiculo.isTravaEletrica());
            stmt.setBoolean(12, veiculo.isDirecaoEletrica());
            stmt.setBoolean(13, veiculo.isCambioAutomatico());
            stmt.setBoolean(14, veiculo.isAbs());
            stmt.setBoolean(15, veiculo.isAirBag());
            stmt.setBoolean(16, veiculo.isTracao4x4());
            stmt.setInt(17, veiculo.getStatus().getIdStatus());
            stmt.setString(18, veiculo.getMarca());
            stmt.setString(19, veiculo.getModelo());            
            stmt.setInt(20, veiculo.getAgencia().getIdAgencia());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Veiculo veiculo) {
        String sql = "UPDATE veiculo SET cor=?, valor=?,placa=?,chassi=?,ano_modelo=?, ano_fabricacao=?, observacoes=?, motor=?, ar_condicionado=?,  vidro_eletrico=?, trava_eletrica=?, direcao_eletrica=?, cambio_automatico=?, abs=?, air_bag=?, `4x4`=?, id_status=?,marca=?, modelo=?, id_agencia=? WHERE id_veiculo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, veiculo.getCor());
            stmt.setFloat(2, veiculo.getValor());
            stmt.setString(3, veiculo.getPlaca());
            stmt.setString(4, veiculo.getChassi());
            stmt.setInt(5, veiculo.getAnoModelo());
            stmt.setInt(6, veiculo.getAnoFabricacao());
            stmt.setString(7, veiculo.getObservacoes());
            stmt.setFloat(8, veiculo.getMotor());
            stmt.setBoolean(9, veiculo.isArCondicionado());
            stmt.setBoolean(10, veiculo.isVidroEletrico());
            stmt.setBoolean(11, veiculo.isTravaEletrica());
            stmt.setBoolean(12, veiculo.isDirecaoEletrica());
            stmt.setBoolean(13, veiculo.isCambioAutomatico());
            stmt.setBoolean(14, veiculo.isAbs());
            stmt.setBoolean(15, veiculo.isAirBag());
            stmt.setBoolean(16, veiculo.isTracao4x4());
            stmt.setInt(17, veiculo.getStatus().getIdStatus());
            stmt.setString(18, veiculo.getMarca());
            stmt.setString(19, veiculo.getModelo());            
            stmt.setInt(20, veiculo.getAgencia().getIdAgencia());
            stmt.setInt(21, veiculo.getIdVeiculo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Veiculo veiculo) {
        String sql = "DELETE FROM veiculo WHERE id_veiculo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, veiculo.getIdVeiculo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Veiculo> listar() {
        String sql = "SELECT * FROM veiculo";
        List<Veiculo> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setIdVeiculo(resultado.getInt("id_veiculo"));
                veiculo.setMarca(resultado.getString("marca"));
                veiculo.setModelo(resultado.getString("modelo"));
                veiculo.setCor(resultado.getString("cor"));
                veiculo.setValor(resultado.getFloat("valor"));
                veiculo.setPlaca(resultado.getString("placa"));
                veiculo.setChassi(resultado.getString("chassi"));
                veiculo.setAnoModelo(resultado.getInt("ano_modelo"));
                veiculo.setAnoFabricacao(resultado.getInt("ano_fabricacao"));
                veiculo.setObservacoes(resultado.getString("observacoes"));
                veiculo.setMotor(resultado.getFloat("motor"));
                veiculo.setArCondicionado(resultado.getBoolean("ar_condicionado"));
                veiculo.setVidroEletrico(resultado.getBoolean("vidro_eletrico"));
                veiculo.setTravaEletrica(resultado.getBoolean("trava_eletrica"));
                veiculo.setDirecaoEletrica(resultado.getBoolean("direcao_eletrica"));
                veiculo.setCambioAutomatico(resultado.getBoolean("cambio_automatico"));
                veiculo.setAbs(resultado.getBoolean("abs"));
                veiculo.setAirBag(resultado.getBoolean("air_bag"));
                veiculo.setTracao4x4(resultado.getBoolean("4x4"));
                
                Status status = new Status();
                status.setIdStatus(resultado.getInt("id_agencia"));

                //Obtendo os dados completos da Agencia associada ao gerente
                StatusDAO statusDAO = new StatusDAO();
                statusDAO.setConnection(connection);
                veiculo.setStatus(statusDAO.buscar(status));
                
                retorno.add(veiculo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Veiculo buscar(Veiculo veiculo) {
        String sql = "SELECT * FROM veiculo WHERE id_veiculo=?";
        Veiculo retorno = new Veiculo();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, veiculo.getIdVeiculo());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                veiculo.setCor(resultado.getString("cor"));
                veiculo.setMarca(resultado.getString("marca"));
                veiculo.setModelo(resultado.getString("modelo"));
                veiculo.setValor(resultado.getFloat("valor"));
                veiculo.setPlaca(resultado.getString("placa"));
                veiculo.setChassi(resultado.getString("chassi"));
                veiculo.setAnoModelo(resultado.getInt("ano_modelo"));
                veiculo.setAnoFabricacao(resultado.getInt("ano_fabricacao"));
                veiculo.setObservacoes(resultado.getString("observacoes"));
                veiculo.setMotor(resultado.getFloat("motor"));
                veiculo.setArCondicionado(resultado.getBoolean("ar_condicionado"));
                veiculo.setVidroEletrico(resultado.getBoolean("vidro_eletrico"));
                veiculo.setTravaEletrica(resultado.getBoolean("trava_eletrica"));
                veiculo.setDirecaoEletrica(resultado.getBoolean("direcao_eletrica"));
                veiculo.setCambioAutomatico(resultado.getBoolean("cambio_automatico"));
                veiculo.setAbs(resultado.getBoolean("abs"));
                veiculo.setAirBag(resultado.getBoolean("air_bag"));
                veiculo.setTracao4x4(resultado.getBoolean("4x4"));
                Status status = new Status();
                status.setIdStatus(resultado.getInt("id_agencia"));

                //Obtendo os dados completos da Agencia associada ao gerente
                StatusDAO statusDAO = new StatusDAO();
                statusDAO.setConnection(connection);
                veiculo.setStatus(statusDAO.buscar(status));
                retorno = veiculo;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }


public List<Veiculo> buscar(String texto) {
        String sql = "SELECT * FROM veiculo";
        List<Veiculo> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setIdVeiculo(resultado.getInt("id_veiculo"));
                veiculo.setMarca(resultado.getString("marca"));
                veiculo.setModelo(resultado.getString("modelo"));
                veiculo.setCor(resultado.getString("cor"));
                veiculo.setValor(resultado.getFloat("valor"));
                veiculo.setPlaca(resultado.getString("placa"));
                veiculo.setChassi(resultado.getString("chassi"));
                veiculo.setAnoModelo(resultado.getInt("ano_modelo"));
                veiculo.setAnoFabricacao(resultado.getInt("ano_fabricacao"));
                veiculo.setObservacoes(resultado.getString("observacoes"));
                veiculo.setMotor(resultado.getFloat("motor"));
                veiculo.setArCondicionado(resultado.getBoolean("ar_condicionado"));
                veiculo.setVidroEletrico(resultado.getBoolean("vidro_eletrico"));
                veiculo.setTravaEletrica(resultado.getBoolean("trava_eletrica"));
                veiculo.setDirecaoEletrica(resultado.getBoolean("direcao_eletrica"));
                veiculo.setCambioAutomatico(resultado.getBoolean("cambio_automatico"));
                veiculo.setAbs(resultado.getBoolean("abs"));
                veiculo.setAirBag(resultado.getBoolean("air_bag"));
                veiculo.setTracao4x4(resultado.getBoolean("4x4"));
                Status status = new Status();
                status.setIdStatus(resultado.getInt("id_agencia"));

                //Obtendo os dados completos da Agencia associada ao gerente
                StatusDAO statusDAO = new StatusDAO();
                statusDAO.setConnection(connection);
                veiculo.setStatus(statusDAO.buscar(status));
                retorno.add(veiculo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}