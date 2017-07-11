package br.com.gentleman.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.gentleman.model.Cliente;
import br.com.gentleman.util.Conecta;

public class ClienteDAO extends Conecta{
	PreparedStatement stman;
	ResultSet result;
	
	public boolean cadastraCliente(Cliente cliente){
		String sql = "INSERT INTO Cliente VALUES (null,?,?,?,?,?,?,?,?)";
		try{
			if(verificaCadastro(cliente.getEmailCliente(), cliente.getClientPass()) == false){	
				getConnection();
				stman = connection.prepareStatement(sql);
				stman.setString(1, cliente.getNomeCliente());
				stman.setString(2, cliente.getSobrenomeCliente());
				stman.setString(3, cliente.getCpfCliente());
				stman.setDate(4, new Date(cliente.getDataNascimento().getTime()));
				stman.setString(5, cliente.getSexoCliente());
				stman.setString(6, cliente.getCelularCliente());
				stman.setString(7, cliente.getEmailCliente());
				stman.setString(8, cliente.getClientPass());
				stman.execute();
				stman.close();
				return true;
			}
		}catch(SQLException erros){
			System.err.println("Erro ao cadastrar cliente: " + erros.toString());
		}finally{
			close();
		}
		
		return false;
	}
	
	public boolean verificaCadastro(String email, String pass){
		String sql = "SELECT * FROM Cliente WHERE emailCliente LIKE ? AND senhaCliente = ?";
		try{
			getConnection();
			stman = connection.prepareStatement(sql);
			stman.setString(1, email+"%");
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
	
	public Cliente getCliente(String email, String pass){
		String sql = "SELECT * FROM Cliente WHERE emailCliente LIKE ? AND senhaCliente = ?";
		Cliente cliente = new Cliente();
		try{
			getConnection();
			stman = connection.prepareStatement(sql);
			stman.setString(1, email+"%");
			stman.setString(2, pass);
			result = stman.executeQuery();
			if(result.first()){
				cliente.setIdCliente(result.getLong("idCliente"));
				cliente.setNomeCliente(result.getString("nomeCliente"));
				cliente.setSobrenomeCliente(result.getString("sobrenomeCliente"));
				cliente.setCpfCliente(result.getString("cpfCliente"));
				cliente.setDataNascimento(result.getDate("dataNascimento"));
				cliente.setSexoCliente(result.getString("sexoCliente"));
				cliente.setCelularCliente(result.getString("celularCliente"));
				cliente.setEmailCliente(result.getString("emailCliente"));
				cliente.setClientPass(result.getString("senhaCliente"));
			}
			result.close();
			stman.close();
		}catch(SQLException erros){
			System.err.println("Erro ao pesquisar cliente:" + erros.toString());
		}finally{
			close();
		}
		return cliente;
	}
	
	public Cliente getCliente(Long id){
		String sql = "SELECT * FROM Cliente WHERE idCliente = ?";
		Cliente cliente = new Cliente();
		try{
			getConnection();
			stman = connection.prepareStatement(sql);
			stman.setLong(1, id);
			result = stman.executeQuery();
			if(result.first()){
				cliente.setIdCliente(result.getLong("idCliente"));
				cliente.setNomeCliente(result.getString("nomeCliente"));
				cliente.setSobrenomeCliente(result.getString("sobrenomeCliente"));
				cliente.setCpfCliente(result.getString("cpfCliente"));
				cliente.setDataNascimento(result.getDate("dataNascimento"));
				cliente.setSexoCliente(result.getString("sexoCliente"));
				cliente.setCelularCliente(result.getString("celularCliente"));
				cliente.setEmailCliente(result.getString("emailCliente"));
				cliente.setClientPass(result.getString("senhaCliente"));
			}
			result.close();
			stman.close();
		}catch(SQLException erros){
			System.err.println("Erro ao pesquisar cliente:" + erros.toString());
		}finally{
			close();
		}
		return cliente;
	}
}
