package br.com.gentleman.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.gentleman.model.Carrinho;
import br.com.gentleman.model.Venda;
import br.com.gentleman.util.Conecta;

public class CarrinhoDAO extends Conecta{
	PreparedStatement stman;
	ResultSet result;
	
	public boolean addCarrinho(List<Carrinho> carrinhos){
		String sql = "INSERT INTO DetalhesVenda VALUES(?,?,?)";
		Long id = null;
		try{
			Carrinho carrinho = carrinhos.get(0);
			id = verificaVenda(carrinho.getVenda());
			if(id != null){
				getConnection();
				stman = connection.prepareStatement(sql);
				for(Carrinho c: carrinhos){
					stman.setLong(1, id);
					stman.setLong(2, c.getProduto().getIdProduto());
					stman.setLong(3, c.getQuantidade());
					stman.execute();
				}
				stman.close();
				return true;
			}
		}catch(SQLException erros){
			System.err.println("Erro ao cadastrar carrinho: " + erros.toString());
		}finally{
			close();
		}
		
			
		return false;
	}
	
	public boolean cadastrarVenda(Venda venda){
		String sql = "INSERT INTO Venda VALUES(null,?,?,?,?,?,?)";
		try{
			getConnection();
			stman = connection.prepareStatement(sql);
			stman.setLong(1, venda.getCliente().getIdCliente());
			stman.setDate(2, new Date(venda.getDataVenda().getTime()));
			stman.setString(3, venda.getCep());
			stman.setString(4, venda.getNumero());
			stman.setString(5, venda.getComplemento());
			stman.setFloat(6, venda.getValorTotal());
			stman.execute();
			stman.close();
			return true;
		}catch(SQLException erros){
			System.err.println("Erro ao cadastrar venda: " + erros.toString());
		}finally{
			close();
		}
		return false;
	}
	
	public Long verificaVenda(Venda venda){
		String sql = "SELECT * FROM Venda WHERE idCliente = ? AND dataVenda = ? AND "
				+ "cep = ? AND numero = ? AND complemento = ? AND precoTotal = ?";
		Long id = null;
		try{
			getConnection();
			stman = connection.prepareStatement(sql);
			stman.setLong(1, venda.getCliente().getIdCliente());
			stman.setDate(2, new Date(venda.getDataVenda().getTime()));
			stman.setString(3, venda.getCep());
			stman.setString(4, venda.getNumero());
			stman.setString(5, venda.getComplemento());
			stman.setFloat(6, venda.getValorTotal());
			result = stman.executeQuery();
			if(result.first()){
				id = result.getLong("idVenda");
			}
			stman.close();
			result.close();
		}catch(SQLException erros){
			System.err.println("Erro ao verificar venda: " + erros.toString());
		}finally{
			close();
		}
		return id;
	}
	
	public Venda retornaVenda(Venda venda){
		String sql = "SELECT * FROM Venda WHERE idCliente = ? AND dataVenda = ? AND "
				+ "cep = ? AND numero = ? AND complemento = ? AND precoTotal = ?";
		Venda vendaRecebida = null;
		ClienteDAO clienteDAO = new ClienteDAO();
		try{
			getConnection();
			stman = connection.prepareStatement(sql);
			stman.setLong(1, venda.getCliente().getIdCliente());
			stman.setDate(2, new Date(venda.getDataVenda().getTime()));
			stman.setString(3, venda.getCep());
			stman.setString(4, venda.getNumero());
			stman.setString(5, venda.getComplemento());
			stman.setFloat(6, venda.getValorTotal());
			result = stman.executeQuery();
			if(result.first()){
				vendaRecebida = new Venda(clienteDAO.getCliente(result.getLong("idCliente")));
				vendaRecebida.setIdVenda(result.getLong("idVenda"));
				vendaRecebida.setDataVenda(result.getDate("dataVenda"));
				vendaRecebida.setCep(result.getString("cep"));
				vendaRecebida.setNumero(result.getString("numero"));
				vendaRecebida.setComplemento(result.getString("complemento"));
				vendaRecebida.setValorTotal(result.getFloat("precoTotal"));
			}
			stman.close();
			result.close();
		}catch(SQLException erros){
			System.err.println("Erro ao verificar venda: " + erros.toString());
		}finally{
			close();
		}
		return vendaRecebida;
	}
}
