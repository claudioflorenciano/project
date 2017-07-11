package br.com.gentleman.model;

public class Funcionario {
	private Long idFuncionario;
	private String nomeFuncionario;
	private String emailFuncionario;
	private String funcionarioPass;
	
	
	
	public Long getIdFuncionario() {
		return idFuncionario;
	}
	
	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}
	
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}
	
	public String getEmailFuncionario() {
		return emailFuncionario;
	}
	
	public void setEmailFuncionario(String emailFuncionario) {
		this.emailFuncionario = emailFuncionario;
	}
	
	public String getFuncionarioPass() {
		return funcionarioPass;
	}
	
	public void setFuncionarioPass(String funcionarioPass) {
		this.funcionarioPass = funcionarioPass;
	}
	
	
}
