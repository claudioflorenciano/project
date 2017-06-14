/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.sysbank.DAO;

import br.edu.sysbank.model.Cargo;
import br.edu.sysbank.model.Endereco;
import br.edu.sysbank.model.Funcionario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MrMojo
 */
public class FuncionarioDAO extends PessoaDAO{
    PreparedStatement stman = null;
    ResultSet result = null;
    
    public boolean cadastrar(Funcionario funcionario){
        String sqlFuncionario = "INSERT INTO funcionario VALUES (null,?,?)";
        try{
            super.cadastrar(funcionario);
            funcionario.setIdPessoa(buscaCPF(funcionario.getCpf()));
            getConnection();
            stman = connection.prepareStatement(sqlFuncionario);
            stman.setLong(1, funcionario.getIdPessoa());
            stman.setLong(2, funcionario.getCargo().getIdCargo());
            stman.execute();
            stman.close();
            return true;
        }catch (SQLException erro){
            
        }
        return false;
    }
    
    public List pesquisaCargos(){
        List<Cargo> cargos = new ArrayList<>();
        String sql = "SELECT * FROM cargo";
        try{
            getConnection();
            stman = connection.prepareStatement(sql);
            result = stman.executeQuery();
            while(result.next()){
                Cargo cargo = new Cargo();
                cargo.setIdCargo(result.getLong("idCargo"));
                cargo.setNomeCargo(result.getString("Cargo"));
                
                cargos.add(cargo); 
            }
        }catch(SQLException erro){
            System.err.println("Erro ao pesquisar Cargos: " + erro.toString());
        }finally{
            close();
        }
        return cargos;
    }
    
    public boolean addCargo(String nome){
        String sql = "INSERT INTO Cargo VALUES (null,?)";
        try{
            getConnection();
            stman = connection.prepareStatement(sql);
            stman.setString(1,nome);
            stman.execute();
            stman.close();
            return true;
        }catch(SQLException erro){
            System.err.println("Erro ao cadastrar no banco: " + erro);
        }finally{
            close();
        }
        return false;
    }
    
    public List pesquisa(String dados){
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionario as f, cargo as c, pessoa as p, endereco as e WHERE p.nome LIKE ? AND p.idPessoa = f.idPessoa AND c.idCargo = f.idCargo AND p.CEP = e.CEP;";
        try{
            getConnection();
            stman = connection.prepareStatement(sql);
            stman.setString(1, dados+"%");
            result = stman.executeQuery();
            //result.first();
            while(result.next()){
                Endereco endereco = new Endereco();
                Cargo cargo = new Cargo();
                Funcionario funcionario = new Funcionario(endereco);
                funcionario.setIdPessoa(result.getLong("idPessoa"));
                funcionario.setIdFuncionario(result.getLong("idFuncionario"));
                funcionario.setNome(result.getString("nome"));
                funcionario.setCpf(result.getString("cpf"));
                funcionario.setEmail(result.getString("email"));
                funcionario.setNumero(result.getString("numero"));
                funcionario.setComplemento(result.getString("complemento"));
                endereco.setCEP(result.getString("CEP"));
                endereco.setLogradouro(result.getString("logradouro"));
                endereco.setBairro(result.getString("bairro"));
                endereco.setCidade(result.getString("cidade"));
                endereco.setUF(result.getString("UF"));
                funcionario.setEndereco(endereco);
                cargo.setIdCargo(result.getLong("idCargo"));
                cargo.setNomeCargo(result.getString("Cargo"));
                funcionario.setCargo(cargo);
                funcionarios.add(funcionario);
                
            }
        }catch(SQLException erro){
            System.err.println("Erro ao pesquisar funcionario: " + erro.toString());
        }finally{
            close();
        }
        return funcionarios;
    }
    
    public Funcionario getFuncionario(long id) {
        String sqlPessoa = ""
                + "select * "
                + "from pessoa as p, endereco as e, funcionario as f, cargo as c "
                + "where f.idFuncionario = ? and "
                + "e.cep = p.CEP and "
                + "f.idPessoa = p.idPessoa and "
                + "f.idCargo = c.idCargo;";
        Endereco endereco = new Endereco();
        Funcionario funcionario = new Funcionario(endereco);
        Cargo cargo = new Cargo();
        try {
            getConnection();
            stman = connection.prepareStatement(sqlPessoa);
            stman.setLong(1, id);
            result = stman.executeQuery();
            result.first();
            if (result.first()) {
                //Pessoa
                funcionario.setIdPessoa(result.getInt("idPessoa"));
                funcionario.setNome(result.getString("nome"));
                funcionario.setCpf(result.getString("cpf"));
                funcionario.setEmail(result.getString("email"));
                funcionario.setComplemento(result.getString("complemento"));
                funcionario.setNumero(result.getString("numero"));
                funcionario.setPws(result.getString("pws"));
                //Endereco
                endereco.setCEP(result.getString("CEP"));
                endereco.setLogradouro(result.getString("Logradouro"));
                endereco.setCidade(result.getString("Cidade"));
                endereco.setBairro(result.getString("Bairro"));
                endereco.setUF(result.getString("Uf"));
                funcionario.setEndereco(endereco);
                //Cliente
                funcionario.setIdFuncionario(result.getLong("idFuncionario"));
                cargo.setIdCargo(result.getLong("idCargo"));
                cargo.setNomeCargo(result.getString("cargo"));
                funcionario.setCargo(cargo);
                stman.close();
                result.close();
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao buscar ID: " + erro.toString());
        } finally {
            close();
        }
        return funcionario;
    }
    
    public boolean atualizarBanco(Funcionario funcionario) {
        String sql = "update funcionario set idCargo = ? where idPessoa = ?;";
        try {
            
            funcionario.setIdPessoa(buscaCPF(funcionario.getCpf()));

            super.atualizar(funcionario);
            
            getConnection();
            stman = connection.prepareStatement(sql);
            stman.setLong(1, funcionario.getCargo().getIdCargo());
            stman.setLong(2, funcionario.getIdPessoa());
            stman.execute();
            stman.close();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o CPF: " + e.toString());
        } finally {
            close();
        }
        return false;
    }
    
    public boolean deletaFuncionario(Funcionario funcionario){
        String sql = "DELETE * FROM cliente WHERE idPessoa = ?";
        try{
            funcionario.setIdPessoa(buscaCPF(funcionario.getCpf()));
            
            getConnection();
            stman = connection.prepareStatement(sql);
            stman.setLong(1, funcionario.getIdPessoa());
            stman.execute();
            stman.close();
            super.deletaPessoa(funcionario);
            return true;
        }catch(SQLException e){
            System.err.println("Erro ao apagar registro: " + e.toString());
        }finally{
            close();
        }
        return false;
    }
}
