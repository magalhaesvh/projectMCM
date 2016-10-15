package projectmcm.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                + "ano_fabricacao, data_compra, observacoes, motor, ar_condicioando "
                + "vidro_eletrico,trava_eletrica, direcao_eletrica, cambio_automatico, abs, air_bag, 4x4, id_status) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, veiculo.getCor());
            stmt.setFloat(2, veiculo.getValor());
            stmt.setString(3, veiculo.getPlaca());
            stmt.setString(4, veiculo.getChassi());
            stmt.setString(5, veiculo.getAnoModelo().toString());
            stmt.setString(6, veiculo.getAnoFabricacao().toString());
            stmt.setString(7, veiculo.getDataCompra().toString());
            stmt.setString(8, veiculo.getObservacoes());
            stmt.setFloat(9, veiculo.getMotor());
            stmt.setBoolean(10, veiculo.isArCondicionado());
            stmt.setBoolean(11, veiculo.isVidroEletrico());
            stmt.setBoolean(12, veiculo.isTravaEletrica());
            stmt.setBoolean(13, veiculo.isDirecaoEletrica());
            stmt.setBoolean(14, veiculo.isCambioAutomatico());
            stmt.setBoolean(15, veiculo.isAbs());
            stmt.setBoolean(16, veiculo.isAirBag());
            stmt.setBoolean(17, veiculo.isTracao4x4());
            stmt.setInt(18, veiculo.getIdStatus());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Veiculo veiculo) {
        String sql = "UPDATE veiculo SET (cor=?, valor=?,placa=?,chassi=?,ano_modelo=?, ano_fabricacao=?, data_compra=?, observacoes=?, motor=?, ar_condicioando  vidro_eletrico=?, trava_eletrica=?, direcao_eletrica=?, cambio_automatico=?, abs=?, air_bag=?, 4x4=?, id_status=?) WHERE id_veiculo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, veiculo.getCor());
            stmt.setFloat(2, veiculo.getValor());
            stmt.setString(3, veiculo.getPlaca());
            stmt.setString(4, veiculo.getChassi());
            stmt.setString(5, veiculo.getAnoModelo().toString());
            stmt.setString(6, veiculo.getAnoFabricacao().toString());
            stmt.setString(7, veiculo.getDataCompra().toString());
            stmt.setString(8, veiculo.getObservacoes());
            stmt.setFloat(9, veiculo.getMotor());
            stmt.setBoolean(10, veiculo.isArCondicionado());
            stmt.setBoolean(11, veiculo.isVidroEletrico());
            stmt.setBoolean(12, veiculo.isTravaEletrica());
            stmt.setBoolean(13, veiculo.isDirecaoEletrica());
            stmt.setBoolean(14, veiculo.isCambioAutomatico());
            stmt.setBoolean(15, veiculo.isAbs());
            stmt.setBoolean(16, veiculo.isAirBag());
            stmt.setBoolean(17, veiculo.isTracao4x4());
            stmt.setInt(18, veiculo.getIdStatus());
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
                veiculo.setCor(resultado.getString("nome"));
                veiculo.setValor(resultado.getFloat("valor"));
                veiculo.setPlaca(resultado.getString("placa"));
                veiculo.setChassi(resultado.getString("chassi"));
                veiculo.setAnoModelo(resultado.getDate("ano_modelo"));
                veiculo.setAnoFabricacao(resultado.getDate("ano_fabricacao"));
                veiculo.setDataCompra(resultado.getDate("data_Compra"));
                veiculo.setObservacoes(resultado.getString("observacoes"));
                veiculo.setMotor(resultado.getFloat("motor"));
                veiculo.setArCondicionado(resultado.getBoolean("ar_condicionado"));
                veiculo.setVidroEletrico(resultado.getBoolean("vidro_eletrico"));
                veiculo.setTravaEletrica(resultado.getBoolean("trava_eletrica"));
                veiculo.setDirecaoEletrica(resultado.getBoolean("direcao_eletrica"));
                veiculo.setCambioAutomatico(resultado.getBoolean("cambio_autmotico"));
                veiculo.setAbs(resultado.getBoolean("abs"));
                veiculo.setAirBag(resultado.getBoolean("ait_bag"));
                veiculo.setTracao4x4(resultado.getBoolean("4x4"));
                veiculo.setIdStatus(resultado.getInt("id_status"));
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
                veiculo.setCor(resultado.getString("nome"));
                veiculo.setValor(resultado.getFloat("valor"));
                veiculo.setPlaca(resultado.getString("placa"));
                veiculo.setChassi(resultado.getString("chassi"));
                veiculo.setAnoModelo(resultado.getDate("ano_modelo"));
                veiculo.setAnoFabricacao(resultado.getDate("ano_fabricacao"));
                veiculo.setDataCompra(resultado.getDate("data_Compra"));
                veiculo.setObservacoes(resultado.getString("observacoes"));
                veiculo.setMotor(resultado.getFloat("motor"));
                veiculo.setArCondicionado(resultado.getBoolean("ar_condicionado"));
                veiculo.setVidroEletrico(resultado.getBoolean("vidro_eletrico"));
                veiculo.setTravaEletrica(resultado.getBoolean("trava_eletrica"));
                veiculo.setDirecaoEletrica(resultado.getBoolean("direcao_eletrica"));
                veiculo.setCambioAutomatico(resultado.getBoolean("cambio_autmotico"));
                veiculo.setAbs(resultado.getBoolean("abs"));
                veiculo.setAirBag(resultado.getBoolean("ait_bag"));
                veiculo.setTracao4x4(resultado.getBoolean("4x4"));
                veiculo.setIdStatus(resultado.getInt("id_status"));
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
                veiculo.setCor(resultado.getString("nome"));
                veiculo.setValor(resultado.getFloat("valor"));
                veiculo.setPlaca(resultado.getString("placa"));
                veiculo.setChassi(resultado.getString("chassi"));
                veiculo.setAnoModelo(resultado.getDate("ano_modelo"));
                veiculo.setAnoFabricacao(resultado.getDate("ano_fabricacao"));
                veiculo.setDataCompra(resultado.getDate("data_Compra"));
                veiculo.setObservacoes(resultado.getString("observacoes"));
                veiculo.setMotor(resultado.getFloat("motor"));
                veiculo.setArCondicionado(resultado.getBoolean("ar_condicionado"));
                veiculo.setVidroEletrico(resultado.getBoolean("vidro_eletrico"));
                veiculo.setTravaEletrica(resultado.getBoolean("trava_eletrica"));
                veiculo.setDirecaoEletrica(resultado.getBoolean("direcao_eletrica"));
                veiculo.setCambioAutomatico(resultado.getBoolean("cambio_autmotico"));
                veiculo.setAbs(resultado.getBoolean("abs"));
                veiculo.setAirBag(resultado.getBoolean("ait_bag"));
                veiculo.setTracao4x4(resultado.getBoolean("4x4"));
                veiculo.setIdStatus(resultado.getInt("id_status"));
                retorno.add(veiculo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}