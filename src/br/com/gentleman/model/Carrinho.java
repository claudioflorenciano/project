package br.com.gentleman.model;


public class Carrinho {
	private Venda venda;
	private Produto produto;
	private Integer quantidade;
	
	
	
	public Carrinho(Venda venda, Produto produto){
		setVenda(venda);
		setProduto(produto);
	}
	
	public Carrinho(Produto produto){
		setProduto(produto);
	}
	
	public Venda getVenda() {
		return venda;
	}
	
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	
}
