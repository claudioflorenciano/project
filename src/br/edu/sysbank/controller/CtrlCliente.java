package br.edu.sysbank.controller;

import br.edu.sysbank.DAO.ClienteDAO;
import br.edu.sysbank.model.Cliente;
import java.util.List;

public class CtrlCliente extends CtrlPessoa {

    ClienteDAO clienteDAO;

    public boolean salvar(Cliente cliente) {
        clienteDAO = new ClienteDAO();
        return clienteDAO.cadastrar(cliente);
    }

    public List pesquisar(String dados) {
        clienteDAO = new ClienteDAO();
        return clienteDAO.pesquisa(dados);
    }
    
    public boolean atualizar(Cliente cliente) throws Exception{
        clienteDAO = new ClienteDAO();
        return clienteDAO.atualizarBanco(cliente);
    }

    public Cliente getCliente(long id){
        clienteDAO = new ClienteDAO();
        return clienteDAO.getCliente(id);
    }
    
    public boolean deleta(Cliente cliente){
        clienteDAO = new ClienteDAO();
        return clienteDAO.deletaCliente(cliente);
    }
}