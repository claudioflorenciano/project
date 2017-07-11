package br.com.gentleman.controller;

import br.com.gentleman.DAO.FuncionarioDAO;
import br.com.gentleman.model.Funcionario;



public class CtrlFuncionario {
	FuncionarioDAO funcionarioDAO;
	
	public boolean verificar(String email, String pass){
		funcionarioDAO = new FuncionarioDAO();
		return funcionarioDAO.verificaCadastro(email, pass);
	}
	
	public Funcionario logar(String email, String pass){
		funcionarioDAO = new FuncionarioDAO();
		return funcionarioDAO.getFuncionario(email, pass);
	}
	
	public String validaLogin(String email, String pass){
		String erros = "";
		if(email.isEmpty()){
			erros += "E-mail em branco!";
		}else if(email.indexOf(".") < 1 
				|| !email.contains("@")
				|| email.length() < 5){
			erros += "E-mail inválido!";
		}
		
		if(pass.isEmpty()){
			erros += "Senha em branco!";
		}else if(pass.length() < 5){
			erros += "Senha muito curta. Mínimo 5 caracteres!";
		}
		
		return erros;
	}
}
