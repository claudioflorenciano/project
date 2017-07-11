package br.com.gentleman.model;

import java.util.Date;

public class Venda {
	private Long idVenda;
	private Cliente cliente;
	private Date dataVenda;
	private String cep;
	private String numero;
	private String complemento;
	private float valorTotal;
	
	
	public Venda(Cliente cliente){
		setCliente(cliente);
	}
	
	public Venda(){
		
	}
	
	
	public float getValorTotal(){
		return valorTotal;
	}
	
	public void setValorTotal(float valorTotal){
		this.valorTotal = valorTotal;
	}
	
	public Long getIdVenda() {
		return idVenda;
	}
	
	public void setIdVenda(Long idVenda) {
		this.idVenda = idVenda;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Date getDataVenda() {
		return dataVenda;
	}
	
	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getComplemento() {
		return complemento;
	}
	
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	
	
}
