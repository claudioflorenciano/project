package br.edu.sysbank.DAO;

import br.edu.sysbank.model.Pessoa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.Conecta;

public abstract class PessoaDAO extends Conecta {

    PreparedStatement stman = null;
    ResultSet result = null;

    public boolean cadastrar(Pessoa pessoa) throws SQLException {
        String sqlPessoa = "insert into pessoa values "
                + "(null,?,?,?,?,?,?,?)";

        getConnection();
        stman = connection.prepareStatement(sqlPessoa);
        stman.setString(1, pessoa.getNome());
        stman.setString(2, pessoa.getCpf());
        stman.setString(3, pessoa.getEmail());
        stman.setString(4, pessoa.getPws());
        stman.setString(5, pessoa.getNumero());
        stman.setString(6, pessoa.getComplemento());
        stman.setString(7, pessoa.getEndereco().getCEP());
        stman.execute();
        stman.close();
        close();
        return true;
    }

    public boolean atualizar(Pessoa pessoa) throws SQLException {
        String sqlPessoa = "update pessoa set nome= ?, email= ?, numero = ?, complemento = ?, CEP = ? where idPessoa = ?;";
        //Atualiza Pessoa
        getConnection();
        stman = connection.prepareStatement(sqlPessoa);
        stman.setString(1, pessoa.getNome());
        stman.setString(2, pessoa.getEmail());
        stman.setString(3, pessoa.getNumero());
        stman.setString(4, pessoa.getComplemento());
        stman.setString(5, pessoa.getEndereco().getCEP());
        stman.setLong(6, pessoa.getIdPessoa());
        stman.executeUpdate();
        stman.close();
        close();
        return true;
    }

    public long buscaCPF(String cpf) {
        String sql = "select * from pessoa where cpf = ?;";
        long id = 0;
        try {
            getConnection();
            stman = connection.prepareStatement(sql);
            stman.setString(1, cpf);
            result = stman.executeQuery();
            result.first();
            id = result.getLong("idPessoa");
            result.close();
            stman.close();
        } catch (SQLException e) {
            System.err.println("Erro ao buscar o CPF: " + e.toString());
        } finally {
            close();
        }
        return id;
    }
    
    public boolean deletaPessoa(Pessoa pessoa){
        String sql = "DELETE * FROM pessoa WHERE idPessoa = ?";
        try{
            getConnection();
            stman = connection.prepareStatement(sql);
            stman.setLong(1, pessoa.getIdPessoa());
            stman.execute();
            stman.close();
            return true;
        }catch(SQLException e){
            System.err.println("Erro ao apagar registro: " + e.toString());
        }finally{
            close();
        }
        return false;
    }
}
