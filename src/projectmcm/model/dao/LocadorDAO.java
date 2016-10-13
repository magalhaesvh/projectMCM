package projectmcm.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectmcm.model.domain.Locador;

public class LocadorDAO extends FuncionarioDAO {
   
    @Override
    public List<Locador> listar() {
        String sql = "SELECT * FROM funcionario WHERE tipo=3";
        List<Locador> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = super.getConnection().prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Locador locador = new Locador();
                locador.setIdFuncionario(resultado.getInt("id_funcionario"));
                locador.setNome(resultado.getString("nome"));
                locador.setEmail(resultado.getString("email"));
                locador.setSenha(resultado.getString("senha"));
                locador.setCpf(resultado.getString("cpf"));
                locador.setRg(resultado.getString("rg"));
                locador.setDataContratacao(resultado.getDate("data_contratacao"));
                locador.setTipo(resultado.getByte("tipo"));
                locador.setComissao(resultado.getInt("comissao"));
                retorno.add(locador);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LocadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
        
    public List<Locador> buscar(String texto) {
        String sql = "SELECT * FROM funcionario WHERE tipo=3 AND (nome=? OR CPF=?)";
        List<Locador> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, texto);
            stmt.setString(2, texto);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Locador locador = new Locador();
                locador.setIdFuncionario(resultado.getInt("id_funcionario"));
                locador.setNome(resultado.getString("nome"));
                locador.setEmail(resultado.getString("email"));
                locador.setSenha(resultado.getString("senha"));
                locador.setCpf(resultado.getString("cpf"));
                locador.setRg(resultado.getString("rg"));
                locador.setDataContratacao(resultado.getDate("data_contratacao"));
                locador.setTipo(resultado.getByte("tipo"));
                locador.setComissao(resultado.getFloat("comissao"));
                retorno.add(locador);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LocadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}
