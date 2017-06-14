/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.sysbank.controller;

import br.edu.sysbank.DAO.FuncionarioDAO;
import br.edu.sysbank.model.Funcionario;
import java.util.List;

/**
 *
 * @author MrMojo
 */
public class CtrlFuncionario extends CtrlPessoa{
    FuncionarioDAO funcionarioDAO;
    
    public boolean salvar(Funcionario funcionario){
        funcionarioDAO = new FuncionarioDAO();
        return funcionarioDAO.cadastrar(funcionario);
    }
    
    public List listarCargos(){
        funcionarioDAO = new FuncionarioDAO();
        return funcionarioDAO.pesquisaCargos();
    }
    
    public boolean adicionarCargo(String nome){
        funcionarioDAO = new FuncionarioDAO();
        return funcionarioDAO.addCargo(nome);
    }
    
    public List pesquisar(String dados){
        funcionarioDAO = new FuncionarioDAO();
        return funcionarioDAO.pesquisa(dados);
    }
    
    public Funcionario getFuncionario(long id){
        funcionarioDAO = new FuncionarioDAO();
        return funcionarioDAO.getFuncionario(id);
    }
    
    public boolean atualizar(Funcionario funcionario) throws Exception{
        funcionarioDAO = new FuncionarioDAO();
        return funcionarioDAO.atualizarBanco(funcionario);
    }
    
    public boolean deleta(Funcionario funcionario){
        funcionarioDAO = new FuncionarioDAO();
        return funcionarioDAO.deletaFuncionario(funcionario);
    }
}
