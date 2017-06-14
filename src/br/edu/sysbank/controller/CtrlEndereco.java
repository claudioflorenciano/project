
package br.edu.sysbank.controller;

import br.edu.sysbank.DAO.EnderecoDAO;
import br.edu.sysbank.model.Endereco;

public class CtrlEndereco {
    public String valida(Endereco endereco){
        String erros = "";
        if(pesquisar(endereco.getCEP()) == null){
            erros += "CEP em inválido.\n";
        }
        if (endereco.getLogradouro().equals("")){
            erros += "Logradouro em branco.\n";
        }
        if (endereco.getBairro().equals("")){
            erros += "Bairro em branco.\n";
        }
        if (endereco.getCidade().equals("")){
            erros += "Cidade em branco.\n";
        }
        if (endereco.getUF().equals("")){
            erros += "Estado em branco.\n";
        }
        return erros;
    }
    
    public boolean cadastrar(Endereco endereco){
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        return enderecoDAO.cadastra(endereco);
    }
    
    public Endereco pesquisar(String cep){
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        return enderecoDAO.pesquisa(cep);
    }
}
