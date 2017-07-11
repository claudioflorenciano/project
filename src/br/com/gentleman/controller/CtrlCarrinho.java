package br.com.gentleman.controller;

import java.util.List;

import br.com.gentleman.DAO.CarrinhoDAO;
import br.com.gentleman.model.Carrinho;
import br.com.gentleman.model.Venda;

public class CtrlCarrinho {
	CarrinhoDAO carrinhoDAO;
	
	public boolean cadastraVenda(Venda venda){
		carrinhoDAO = new CarrinhoDAO();
		return carrinhoDAO.cadastrarVenda(venda);
	}
	
	public boolean cadastraCarrinho(List<Carrinho> carrinhos){
		carrinhoDAO = new CarrinhoDAO();
		return carrinhoDAO.addCarrinho(carrinhos);
	}
	
	public Venda verificaVenda(Venda venda){
		carrinhoDAO = new CarrinhoDAO();
		return carrinhoDAO.retornaVenda(venda);
	}
	
	public String validaCarrinho(String cep, String numero, String complemento){
		String erros = "";
		
		if(cep.equals("") || numero.equals("") || complemento.equals("")){
			erros += "Campos vazios!";
		}
		if(cep.length() != 9){
			erros += "CEP inválido!";
		}
		return erros;
	}
}
