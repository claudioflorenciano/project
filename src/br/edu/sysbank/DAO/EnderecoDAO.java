/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.sysbank.DAO;

import br.edu.sysbank.model.Endereco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.Conecta;



/**
 *
 * @author Aluno
 */
public class EnderecoDAO extends Conecta{
    PreparedStatement stman = null;
    ResultSet result = null;
    
    public boolean cadastra(Endereco endereco){
        
        String sql = "INSERT INTO endereco VALUES "
                + "(?,?,?,?,?)";
        try {
            getConnection();
            stman = connection.prepareStatement(sql);
            stman.setString(1, endereco.getCEP());
            stman.setString(2, endereco.getLogradouro());
            stman.setString(3, endereco.getBairro());
            stman.setString(4, endereco.getCidade());
            stman.setString(5, endereco.getUF());
            stman.execute();
            stman.close();
            return true;
        } catch (SQLException erros){
            System.err.println("Erro ao cadastrar: " + erros.toString());
        }finally{
            close();
        }
        return false;
    }
    
    public Endereco pesquisa(String cep){
        String sql = "SELECT * FROM endereco WHERE CEP = ?";
        Endereco e = new Endereco();
        try{
            getConnection();
            stman = connection.prepareStatement(sql);
            stman.setString(1, cep);
            result = stman.executeQuery();
            result.first();
            e.setCEP(result.getString("CEP"));
            e.setLogradouro(result.getString("Logradouro"));
            e.setBairro(result.getString("Bairro"));
            e.setCidade(result.getString("Cidade"));
            e.setUF(result.getString("UF"));
            result.close();
            stman.close();
        }catch(Exception erros){
            System.err.println("Erro ao pesquisar: " + erros.toString());
            e = null;
        }finally{
            close();
        }
        return e;
    }
}
