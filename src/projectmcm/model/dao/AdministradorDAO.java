package projectmcm.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectmcm.model.domain.Administrador;

public class AdministradorDAO extends FuncionarioDAO {
   
    @Override
    public List<Administrador> listar() {
        String sql = "SELECT * FROM administrador WHERE tipo=1";
        List<Administrador> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = super.getConnection().prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Administrador administrador = new Administrador();
                administrador.setIdFuncionario(resultado.getInt("id_administrador"));
                administrador.setNome(resultado.getString("nome"));
                administrador.setEmail(resultado.getString("email"));
                administrador.setSenha(resultado.getString("senha"));
                administrador.setCpf(resultado.getString("cpf"));
                administrador.setRg(resultado.getString("rg"));
                administrador.setDataContratacao(resultado.getDate("data_contratacao"));
                administrador.setTipo(resultado.getByte("tipo"));
                retorno.add(administrador);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
        
    public List<Administrador> buscar(String texto) {
        String sql = "SELECT * FROM administrador WHERE tipo=1 AND (nome=? OR CPF=?)";
        List<Administrador> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, texto);
            stmt.setString(2, texto);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Administrador administrador = new Administrador();
                administrador.setIdFuncionario(resultado.getInt("id_administrador"));
                administrador.setNome(resultado.getString("nome"));
                administrador.setEmail(resultado.getString("email"));
                administrador.setSenha(resultado.getString("senha"));
                administrador.setCpf(resultado.getString("cpf"));
                administrador.setRg(resultado.getString("rg"));
                administrador.setDataContratacao(resultado.getDate("data_contratacao"));
                administrador.setTipo(resultado.getByte("tipo"));
                retorno.add(administrador);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}
