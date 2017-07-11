package br.com.gentleman.model;

public class Tamanho {
	private Long idTamanho;
	private Departamento departamento;
	private String numero;
	
	
	
	public Tamanho(Departamento departamento){
		setDepartamento(departamento);
	}
	
	public Tamanho(){
		
	}
	
	public Long getIdTamanho() {
		return idTamanho;
	}
	
	public void setIdTamanho(Long idTamanho) {
		this.idTamanho = idTamanho;
	}
	
	public Departamento getDepartamento() {
		return departamento;
	}
	
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
}
