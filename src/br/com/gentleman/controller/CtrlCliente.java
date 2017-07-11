package br.com.gentleman.controller;

import br.com.gentleman.DAO.ClienteDAO;
import br.com.gentleman.model.Cliente;
import br.com.gentleman.util.ValidaCPF;

public class CtrlCliente {
	ClienteDAO clienteDAO;
	
	public boolean cadastrar(Cliente cliente){
		clienteDAO = new ClienteDAO();
		return clienteDAO.cadastraCliente(cliente);
	}
	
	public boolean verificar(String email, String pass){
		clienteDAO = new ClienteDAO();
		return clienteDAO.verificaCadastro(email, pass);
	}
	
	public Cliente logar(String email, String pass){
		clienteDAO = new ClienteDAO();
		return clienteDAO.getCliente(email, pass);
	}
	
	public String valida(Cliente cliente){
		String erros = "";
		if(cliente.getNomeCliente().isEmpty()){
			erros += "Nome em branco!";
		}
		
		if(cliente.getEmailCliente().isEmpty()){
			erros += "E-mail em branco!";
		}else if(cliente.getEmailCliente().indexOf(".") < 1 
				|| !cliente.getEmailCliente().contains("@")
				|| cliente.getEmailCliente().length() < 5){
			erros += "E-mail inválido!";
		}else if(cliente.getCelularCliente() == null){
			erros += "Celular em branco!";
		}else if(cliente.getCelularCliente().length() < 9){
			erros += "Celular inválido!";
		}else if(cliente.getDataNascimento() == null){
			erros += "Data inválida!";
		}
		
		return erros;
	}
	
	public String valida(Cliente cliente, String pass2){
		String erros = valida(cliente);
		erros += validaCPF(cliente.getCpfCliente());
		erros += validaPass(cliente.getClientPass(), pass2);
		return erros;
	}
	
	public String validaPass(String pass1, String pass2){
		String erros = "";
		if(pass1.isEmpty() || pass2.isEmpty()){
			erros += "Senha(s) em branco!";
		} else if(pass1.length() < 5){
			erros += "Senha muito curta. Mínimo 5 caracteres!";
		}else if(!pass1.equals(pass2)){
			erros += "Senhas não conferem!";
		}
		
		return erros;
	}
	
	public String validaCPF(String cpf){
		String erros = "";
		if(cpf.isEmpty()){
			erros += "CPF em branco!";
		}
		if(cpf.length() != 14){
			erros += "Poucos dígitos no CPF!";
		}
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");
		if(!ValidaCPF.isCPF(cpf)){
			erros += "CPF inválido!";
		}
		return erros;
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
