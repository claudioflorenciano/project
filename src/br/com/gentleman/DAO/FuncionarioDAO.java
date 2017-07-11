package br.com.gentleman.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.gentleman.model.Funcionario;
import br.com.gentleman.util.Conecta;


public class FuncionarioDAO extends Conecta {
	PreparedStatement stman;
	ResultSet result;
	
	public boolean verificaCadastro(String email, String pass){
		String sql = "SELECT * FROM Funcionario WHERE email = ? AND senha = ?";
		try{
			getConnection();
			stman = connection.prepareStatement(sql);
			stman.setString(1, email);
			stman.setString(2, pass);
			result = stman.executeQuery();
			if(result.first()){
				return true;
			}
			result.close();
			stman.close();
		}catch(SQLException erros){
			System.err.println("Erro ao verificar usuario:" + erros.toString());
		}finally{
			close();
		}
		
		return false;
	}
	
	public Funcionario getFuncionario(String email, String pass){
		String sql = "SELECT * FROM Funcionario WHERE email LIKE ? AND senha = ?";
		Funcionario funcionario = new Funcionario();
		try{
			getConnection();
			stman = connection.prepareStatement(sql);
			stman.setString(1, email+"%");
			stman.setString(2, pass);
			result = stman.executeQuery();
			if(result.first()){
				funcionario.setIdFuncionario(result.getLong("idFuncionario"));
				funcionario.setNomeFuncionario(result.getString("nomeFuncionario"));
				funcionario.setEmailFuncionario(result.getString("email"));
				funcionario.setFuncionarioPass(result.getString("senha"));
			}
			result.close();
			stman.close();
		}catch(SQLException erros){
			System.err.println("Erro ao pesquisar funcionario:" + erros.toString());
		}finally{
			close();
		}
		return funcionario;
	}
}
