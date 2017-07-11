package br.com.gentleman.model;

public class Produto {
	private Long idProduto;
	private Tamanho tamanho;
	private Marca marca;
	private String nomeProduto;
	private String descricaoProduto;
	private String imagemProduto;
	private Integer quantidadeEstoque;
	private float precoProduto;
	
	
	
	public Produto(Tamanho tamanho, Marca marca){
		setTamanho(tamanho);
		setMarca(marca);
	}
	
	public Produto(){
		
	}
	
	public Long getIdProduto() {
		return idProduto;
	}
	
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	
	public Tamanho getTamanho() {
		return tamanho;
	}
	
	public void setTamanho(Tamanho tamanho) {
		this.tamanho = tamanho;
	}
	
	public Marca getMarca() {
		return marca;
	}
	
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	
	public String getNomeProduto() {
		return nomeProduto;
	}
	
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	
	public String getDescricaoProduto() {
		return descricaoProduto;
	}
	
	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}
	
	public String getImagemProduto() {
		return imagemProduto;
	}
	
	public void setImagemProduto(String imagemProduto) {
		this.imagemProduto = imagemProduto;
	}
	
	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	
	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	
	public float getPrecoProduto() {
		return precoProduto;
	}
	
	public void setPrecoProduto(float precoProduto) {
		this.precoProduto = precoProduto;
	}
	
}
