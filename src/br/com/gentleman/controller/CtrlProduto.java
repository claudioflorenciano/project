package br.com.gentleman.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Part;

import br.com.gentleman.DAO.ProdutoDAO;
import br.com.gentleman.model.Departamento;
import br.com.gentleman.model.Marca;
import br.com.gentleman.model.Produto;
import br.com.gentleman.model.Tamanho;
import br.com.gentleman.util.ArquivoPart;

public class CtrlProduto {
	ProdutoDAO produtoDAO;
	
	public Produto buscaProduto(Long id){
		produtoDAO = new ProdutoDAO();
		return produtoDAO.getProduto(id);
	}
	
	public List<Produto> buscaProdutos(){
		produtoDAO = new ProdutoDAO();
		return produtoDAO.getProdutos();
	}
	
	public List<Tamanho> buscaTamanhos(Long id){
		produtoDAO = new ProdutoDAO();
		return produtoDAO.getTamanhosDepartamento(id);
	}
	
	public List<Produto> buscaProdutos(String nome){
		produtoDAO = new ProdutoDAO();
		return produtoDAO.pesquisaProdutos(nome);
	}
	
	public List<Produto> buscaVestuarioProdutos(){
		produtoDAO = new ProdutoDAO();
		return produtoDAO.getVesturarioProdutos();
	}
	
	public List<Produto> buscaVestuarioProdutos(String nome){
		produtoDAO = new ProdutoDAO();
		return produtoDAO.pesquisaVestuarioProdutos(nome);
	}
	
	public List<Tamanho> buscaTamanhos(){
		produtoDAO = new ProdutoDAO();
		return produtoDAO.getTamanhos();
	}

	public List<Departamento> buscaDepartamentos(){
		produtoDAO = new ProdutoDAO();
		return produtoDAO.getDepartamentos();
	}

	public List<Marca> buscaMarcas(){
		produtoDAO = new ProdutoDAO();
		return produtoDAO.getMarcas();
	}
	
	public boolean cadProd(Produto produto){
		produtoDAO = new ProdutoDAO();
		return produtoDAO.cadastraProduto(produto);		
	}
	
	public boolean efetuaCompra(Produto produto, Long quant){
		produtoDAO = new ProdutoDAO();
		return produtoDAO.subtrairQuantidade(produto, quant);
	}
	
	public boolean getMarca(String marca){
		produtoDAO = new ProdutoDAO();
		return produtoDAO.pesquisaMarca(marca);
	}
	
	public boolean delProd(Long id){
		produtoDAO = new ProdutoDAO();
		return produtoDAO.deletaProduto(id);
	}
	
	public boolean cadMarca(String marca){
		produtoDAO = new ProdutoDAO();
		return produtoDAO.cadastraMarca(marca);
	}
	
	public boolean addQuant(Produto produto, Long quant){
		produtoDAO = new ProdutoDAO();
		return produtoDAO.adicionarQuantidade(produto, quant);
	}
	
	public boolean alteraProduto(String nome, String descricao, Float preco, Long id){
		produtoDAO = new ProdutoDAO();
		return produtoDAO.alteraProduto(nome, descricao, preco, id);
	}
	
	public String validaImg(Part part) throws IOException{
		String erros = "";
		ArquivoPart ap = new ArquivoPart();
		
		String path = "E:/Dados/Programação/eclipse/workspace/Gentleman/WebContent/imgDB/"+ap.getFileName(part);
		
		if(ap.getFileName(part) == null){
			erros += "Arquivo inválido!";
		}
		if(!ap.verificaIMG(path)){
			erros += "Tipo de arquivo incompatível!";
		}
		if(ap.verificaExists(path)){
			erros += "Arquivo já existente!";
		}
		
		return erros;
	}
	
	public String validaProd(Produto produto){
		String erros = "";
		if(produto.getNomeProduto().isEmpty()){
			erros += "Nome vazio!";
		}
		if(produto.getDescricaoProduto().isEmpty()){
			erros += "Descrição vazia!";
		}
		if(produto.getMarca().getIdMarca() == null){
			erros += "Nenhuma marca inserida!";
		}
		if(produto.getTamanho().getIdTamanho() == null){
			erros += "Nenhum tamanho inserido!";
		}
		if(produto.getTamanho().getDepartamento().getIdDeparamento() == null){
			erros += "Nenhum departamento inserido!";
		}
		return erros;
	}
	
	public String validaAlt(String nome, String descricao){
		String erros = "";
		if(nome.equals("")){
			erros += "Nome vazio!";
		}
		if(descricao.equals("")){
			erros += "Descrição vazia!";
		}
		return erros;
	}
}
