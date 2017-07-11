package br.com.gentleman.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.gentleman.model.Departamento;
import br.com.gentleman.model.Marca;
import br.com.gentleman.model.Produto;
import br.com.gentleman.model.Tamanho;
import br.com.gentleman.util.Conecta;

public class ProdutoDAO extends Conecta {
	PreparedStatement stman;
	ResultSet result;
	
	public List<Produto> pesquisaProdutos(String nome){
		List<Produto> produtos = new ArrayList<Produto>();
		String sql = "SELECT * FROM Produto as p, Tamanho as t, Departamento as D, Marca as m "
				+ "WHERE p.nomeProduto LIKE ? AND p.idMarca = m.idMarca AND "
				+ "p.idTamanho = t.idTamanho AND t.idDepartamento = d.idDepartamento";
		try{
			getConnection();
			stman = connection.prepareStatement(sql);
			stman.setString(1, "%"+nome+"%");
			result = stman.executeQuery();
			while(result.next()){
				Marca marca = new Marca();
				marca.setIdMarca(result.getLong("idMarca"));
				marca.setNomeMarca(result.getString("nomeMarca"));
				Departamento departamento = new Departamento();
				departamento.setIdDeparamento(result.getLong("idDepartamento"));
				departamento.setNomeDepartamento(result.getString("nomeDepartamento"));
				Tamanho tamanho = new Tamanho(departamento);
				tamanho.setIdTamanho(result.getLong("idTamanho"));
				tamanho.setNumero(result.getString("numero"));
				Produto produto = new Produto(tamanho, marca);
				produto.setIdProduto(result.getLong("idProduto"));
				produto.setNomeProduto(result.getString("nomeProduto"));
				produto.setDescricaoProduto(result.getString("descricaoProduto"));
				produto.setImagemProduto(result.getString("imagemProduto"));
				produto.setQuantidadeEstoque(result.getInt("quantidadeEstoque"));
				produto.setPrecoProduto(result.getFloat("precoProduto"));
				produtos.add(produto);
			}
			result.close();
			stman.close();
		}catch(SQLException erros){
			System.err.println("Erro ao pesquisar produtos: " + erros.toString());
		}finally{
			close();
		}
		return produtos;
	}
	
	public Produto getProduto(Long id){
		String sql = "SELECT * FROM Produto as p, Tamanho as t, Departamento as D, Marca as m "
				+ "WHERE p.idProduto = ? AND p.idMarca = m.idMarca AND "
				+ "p.idTamanho = t.idTamanho AND t.idDepartamento = d.idDepartamento";
		Produto produto = null;
		try{
			getConnection();
			stman = connection.prepareStatement(sql);
			stman.setLong(1, id);
			result = stman.executeQuery();
			while(result.next()){
				Marca marca = new Marca();
				marca.setIdMarca(result.getLong("idMarca"));
				marca.setNomeMarca(result.getString("nomeMarca"));
				Departamento departamento = new Departamento();
				departamento.setIdDeparamento(result.getLong("idDepartamento"));
				departamento.setNomeDepartamento(result.getString("nomeDepartamento"));
				Tamanho tamanho = new Tamanho(departamento);
				tamanho.setIdTamanho(result.getLong("idTamanho"));
				tamanho.setNumero(result.getString("numero"));
				produto = new Produto(tamanho, marca);
				produto.setIdProduto(result.getLong("idProduto"));
				produto.setNomeProduto(result.getString("nomeProduto"));
				produto.setDescricaoProduto(result.getString("descricaoProduto"));
				produto.setImagemProduto(result.getString("imagemProduto"));
				produto.setQuantidadeEstoque(result.getInt("quantidadeEstoque"));
				produto.setPrecoProduto(result.getFloat("precoProduto"));
			}
			result.close();
			stman.close();
		}catch(SQLException erros){
			System.err.println("Erro ao pesquisar produtos: " + erros.toString());
		}finally{
			close();
		}
		return produto;
	}
	
	public List<Tamanho> getTamanhosDepartamento(Long idDepartamento){
		List<Tamanho> tamanhos = new ArrayList<Tamanho>();
		String sql = "SELECT * FROM Tamanho WHERE idDepartamento = ?";
		try{
			getConnection();
			stman = connection.prepareStatement(sql);
			stman.setLong(1, idDepartamento);
			result = stman.executeQuery();
			while(result.next()){
				Tamanho tamanho = new Tamanho();
				tamanho.setIdTamanho(result.getLong("idTamanho"));
				tamanho.setNumero(result.getString("numero"));
				tamanhos.add(tamanho);
			}
			result.close();
			stman.close();
		}catch(SQLException erros){
			System.err.println("Erro ao pesquisar tamanhos: " + erros.toString());
		}finally{
			close();
		}
		return tamanhos;
	}
	
	public boolean adicionarQuantidade(Produto produto, Long quant){
		String sql = "UPDATE Produto SET quantidadeEstoque = ? WHERE idProduto= ?";
		try{
			getConnection();
			stman = connection.prepareStatement(sql);
			stman.setLong(1, (produto.getQuantidadeEstoque() + quant));
			stman.setLong(2, produto.getIdProduto());
			stman.execute();
			stman.close();
			return true;
		}catch(SQLException erros){
			System.err.println("Erro ao adicionar produto: " + erros.toString());
		}finally{
			close();
		}
		return false;
	}
	
	public boolean cadastraProduto(Produto produto){
		String sql = "INSERT INTO Produto VALUES(null, ?,?,?,?,?,?,?)";
		try{
			getConnection();
			stman = connection.prepareStatement(sql);
			stman.setLong(1, produto.getTamanho().getIdTamanho());
			stman.setLong(2, produto.getMarca().getIdMarca());
			stman.setString(3, produto.getNomeProduto());
			stman.setString(4, produto.getDescricaoProduto());
			stman.setString(5, produto.getImagemProduto());
			stman.setLong(6, produto.getQuantidadeEstoque());
			stman.setFloat(7, produto.getPrecoProduto());
			stman.execute();
			stman.close();
			return true;
		}catch(SQLException erros){
			System.err.println("Erro ao cadastrar produto: " + erros.toString());
		}finally{
			close();
		}
		
		return false;
	}
	
	public boolean cadastraMarca(String marca){
		String sql = "INSERT INTO Marca VALUES(null, ?)";
		try{
			getConnection();
			stman = connection.prepareStatement(sql);
			stman.setString(1, marca);
			stman.execute();
			stman.close();
			return true;
		}catch(SQLException erros){
			System.err.println("Erro ao inserir marca: " + erros.toString());
		}finally{
			close();
		}
		
		return false;
	}
	
	public boolean pesquisaMarca(String marca){
		String sql = "SELECT * FROM Marca WHERE nomeMarca LIKE ?";
		try{
			getConnection();
			stman = connection.prepareStatement(sql);
			stman.setString(1, marca+"%");
			result = stman.executeQuery();
			if(result.first()){
				return true;
			}
			stman.close();
			result.close();
		}catch(SQLException erros){
			System.err.println("Erro ao inserir marca: " + erros.toString());
		}finally{
			close();
		}
		
		return false;
	}
	
	public boolean deletaProduto(Long id){
		String sql = "DELETE FROM Produto WHERE idProduto = ?";
		try{
			getConnection();
			stman = connection.prepareStatement(sql);
			stman.setLong(1, id);
			stman.execute();
			stman.close();
			return true;
		}catch(SQLException erros){
			System.err.println("Erro ao deletar produto: " + erros.toString());
		}finally{
			close();
		}
		return false;
	}
	
	public boolean getDepartamento(Tamanho tamanho){
		String sql = "SELECT * FROM Tamanho WHERE idDepartamento = ?";
		try{
			getConnection();
			stman = connection.prepareStatement(sql);
			stman.setLong(1, tamanho.getDepartamento().getIdDeparamento());
			stman.setLong(2, tamanho.getIdTamanho());
			stman.execute();
			stman.close();
			return true;
		}catch(SQLException erros){
			System.err.println("Erro ao cadastrar Departamento: " + erros.toString());
		}finally{
			close();
		}
		return false;
	}
	
	public boolean alteraProduto(String nome, String descricao, float preco, Long id){
		String sql = "UPDATE Produto set nomeProduto = ?, descricaoProduto = ?, "
				+ "precoProduto = ? WHERE idProduto = ?";
		try{
			getConnection();
			stman = connection.prepareStatement(sql);
			stman.setString(1, nome);
			stman.setString(2, descricao);
			stman.setFloat(3, preco);
			stman.setLong(4, id);
			stman.execute();
			stman.close();
			return true;
		}catch(SQLException erros){
			System.err.println("Erro ao atualizar produto: " + erros.toString());
		}finally{
			close();
		}
		
		return false;
	}
	
	public List<Produto> getProdutos(){
		List<Produto> produtos = new ArrayList<Produto>();
		String sql = "SELECT * FROM Produto as p, Tamanho as t, Departamento as D, Marca as m "
				+ "WHERE p.idMarca = m.idMarca AND "
				+ "p.idTamanho = t.idTamanho AND t.idDepartamento = d.idDepartamento";
		try{
			getConnection();
			stman = connection.prepareStatement(sql);
			result = stman.executeQuery();
			while(result.next()){
				Marca marca = new Marca();
				marca.setIdMarca(result.getLong("idMarca"));
				marca.setNomeMarca(result.getString("nomeMarca"));
				Departamento departamento = new Departamento();
				departamento.setIdDeparamento(result.getLong("idDepartamento"));
				departamento.setNomeDepartamento(result.getString("nomeDepartamento"));
				Tamanho tamanho = new Tamanho(departamento);
				tamanho.setIdTamanho(result.getLong("idTamanho"));
				tamanho.setNumero(result.getString("numero"));
				Produto produto = new Produto(tamanho, marca);
				produto.setIdProduto(result.getLong("idProduto"));
				produto.setNomeProduto(result.getString("nomeProduto"));
				produto.setDescricaoProduto(result.getString("descricaoProduto"));
				produto.setImagemProduto(result.getString("imagemProduto"));
				produto.setQuantidadeEstoque(result.getInt("quantidadeEstoque"));
				produto.setPrecoProduto(result.getFloat("precoProduto"));
				produtos.add(produto);
			}
			result.close();
			stman.close();
		}catch(SQLException erros){
			System.err.println("Erro ao pesquisar produtos: " + erros.toString());
		}finally{
			close();
		}
		return produtos;
	}
	
	public List<Produto> getVesturarioProdutos(){
		List<Produto> produtos = new ArrayList<Produto>();
		String sql = "SELECT * FROM Produto as p, Tamanho as t, Departamento as D, Marca as m "
				+ "WHERE p.idMarca = m.idMarca AND "
				+ "p.idTamanho = t.idTamanho AND t.idDepartamento = d.idDepartamento AND p.quantidadeEstoque > 0";
		try{
			getConnection();
			stman = connection.prepareStatement(sql);
			result = stman.executeQuery();
			while(result.next()){
				Marca marca = new Marca();
				marca.setIdMarca(result.getLong("idMarca"));
				marca.setNomeMarca(result.getString("nomeMarca"));
				Departamento departamento = new Departamento();
				departamento.setIdDeparamento(result.getLong("idDepartamento"));
				departamento.setNomeDepartamento(result.getString("nomeDepartamento"));
				Tamanho tamanho = new Tamanho(departamento);
				tamanho.setIdTamanho(result.getLong("idTamanho"));
				tamanho.setNumero(result.getString("numero"));
				Produto produto = new Produto(tamanho, marca);
				produto.setIdProduto(result.getLong("idProduto"));
				produto.setNomeProduto(result.getString("nomeProduto"));
				produto.setDescricaoProduto(result.getString("descricaoProduto"));
				produto.setImagemProduto(result.getString("imagemProduto"));
				produto.setQuantidadeEstoque(result.getInt("quantidadeEstoque"));
				produto.setPrecoProduto(result.getFloat("precoProduto"));
				produtos.add(produto);
			}
			result.close();
			stman.close();
		}catch(SQLException erros){
			System.err.println("Erro ao pesquisar produtos: " + erros.toString());
		}finally{
			close();
		}
		return produtos;
	}
	
	public List<Produto> pesquisaVestuarioProdutos(String nome){
		List<Produto> produtos = new ArrayList<Produto>();
		String sql = "SELECT * FROM Produto as p, Tamanho as t, Departamento as D, Marca as m "
				+ "WHERE p.nomeProduto LIKE ? AND p.idMarca = m.idMarca AND "
				+ "p.idTamanho = t.idTamanho AND t.idDepartamento = d.idDepartamento AND p.quantidadeEstoque > 0";
		try{
			getConnection();
			stman = connection.prepareStatement(sql);
			stman.setString(1, "%"+nome+"%");
			result = stman.executeQuery();
			while(result.next()){
				Marca marca = new Marca();
				marca.setIdMarca(result.getLong("idMarca"));
				marca.setNomeMarca(result.getString("nomeMarca"));
				Departamento departamento = new Departamento();
				departamento.setIdDeparamento(result.getLong("idDepartamento"));
				departamento.setNomeDepartamento(result.getString("nomeDepartamento"));
				Tamanho tamanho = new Tamanho(departamento);
				tamanho.setIdTamanho(result.getLong("idTamanho"));
				tamanho.setNumero(result.getString("numero"));
				Produto produto = new Produto(tamanho, marca);
				produto.setIdProduto(result.getLong("idProduto"));
				produto.setNomeProduto(result.getString("nomeProduto"));
				produto.setDescricaoProduto(result.getString("descricaoProduto"));
				produto.setImagemProduto(result.getString("imagemProduto"));
				produto.setQuantidadeEstoque(result.getInt("quantidadeEstoque"));
				produto.setPrecoProduto(result.getFloat("precoProduto"));
				produtos.add(produto);
			}
			result.close();
			stman.close();
		}catch(SQLException erros){
			System.err.println("Erro ao pesquisar produtos: " + erros.toString());
		}finally{
			close();
		}
		return produtos;
	}
	
	public boolean subtrairQuantidade(Produto produto, Long quant){
		String sql = "UPDATE Produto SET quantidadeEstoque = ? WHERE idProduto= ?";
		try{
			getConnection();
			stman = connection.prepareStatement(sql);
			stman.setLong(1, (produto.getQuantidadeEstoque() - quant));
			stman.setLong(2, produto.getIdProduto());
			stman.execute();
			stman.close();
			return true;
		}catch(SQLException erros){
			System.err.println("Erro ao comprar produto: " + erros.toString());
		}finally{
			close();
		}
		return false;
	}
	
	public List<Marca> getMarcas(){
		List<Marca> marcas = new ArrayList<Marca>();
		String sql = "SELECT * FROM marca";
		try{
			getConnection();
			stman = connection.prepareStatement(sql);
			result = stman.executeQuery();
			while(result.next()){
				Marca marca = new Marca();
				marca.setIdMarca(result.getLong("idMarca"));
				marca.setNomeMarca(result.getString("nomeMarca"));
				marcas.add(marca);
			}
			result.close();
			stman.close();
		}catch(SQLException erros){
			System.err.println("Erro ao pesquisar marcas :" + erros.toString());
		}finally{
			close();
		}
		return marcas;
	}
	
	public List<Departamento> getDepartamentos(){
		List<Departamento> departamentos = new ArrayList<Departamento>();
		String sql = "SELECT * FROM Departamento";
		try{
			getConnection();
			stman = connection.prepareStatement(sql);
			result = stman.executeQuery();
			while(result.next()){
				Departamento departamento = new Departamento();
				departamento.setIdDeparamento(result.getLong("idDepartamento"));
				departamento.setNomeDepartamento(result.getString("nomeDepartamento"));
				departamentos.add(departamento);
			}
			result.close();
			stman.close();
		}catch(SQLException erros){
			System.err.println("Erro ao pesquisar departamentos :" + erros.toString());
		}finally{
			close();
		}
		return departamentos;
	}
	
	
	public List<Tamanho> getTamanhos(){
		List<Tamanho> tamanhos = new ArrayList<Tamanho>();
		String sql = "SELECT * FROM Tamanho";
		try{
			getConnection();
			stman = connection.prepareStatement(sql);
			result = stman.executeQuery();
			while(result.next()){
				Tamanho tamanho = new Tamanho();
				tamanho.setIdTamanho(result.getLong("idTamanho"));
				tamanho.setNumero(result.getString("numero"));
				tamanhos.add(tamanho);
			}
			result.close();
			stman.close();
		}catch(SQLException erros){
			System.err.println("Erro ao pesquisar marcas :" + erros.toString());
		}finally{
			close();
		}
		return tamanhos;
	}
}
