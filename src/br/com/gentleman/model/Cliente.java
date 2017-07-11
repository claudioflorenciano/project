package br.com.gentleman.model;

import java.util.Date;

public class Cliente {
	private Long idCliente;
	private String nomeCliente;
	private String sobrenomeCliente;
	private String cpfCliente;
	private Date dataNascimento;
	private String sexoCliente;
	private String celularCliente;
	private String emailCliente;
	private String clientPass;
	
	
	
	public Long getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	
	public String getNomeCliente() {
		return nomeCliente;
	}
	
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
	public String getSobrenomeCliente() {
		return sobrenomeCliente;
	}
	
	public void setSobrenomeCliente(String sobrenomeCliente) {
		this.sobrenomeCliente = sobrenomeCliente;
	}
	
	public String getCpfCliente() {
		return cpfCliente;
	}
	
	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String getSexoCliente() {
		return sexoCliente;
	}
	
	public void setSexoCliente(String sexoCliente) {
		this.sexoCliente = sexoCliente;
	}
	
	public String getCelularCliente() {
		return celularCliente;
	}
	
	public void setCelularCliente(String celularCliente) {
		this.celularCliente = celularCliente;
	}
	
	public String getEmailCliente() {
		return emailCliente;
	}
	
	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}
	
	public String getClientPass() {
		return clientPass;
	}
	
	public void setClientPass(String clientPass) {
		this.clientPass = clientPass;
	}
	
	
}
