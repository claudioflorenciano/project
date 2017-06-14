package br.edu.sysbank.DAO;

import br.edu.sysbank.model.Cliente;
import br.edu.sysbank.model.Endereco;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends PessoaDAO {

    PreparedStatement stman = null;
    ResultSet result = null;

    public boolean cadastrar(Cliente cliente) {
        String sql = "insert into cliente values (null,?,?,?)";
        try {
            
            cliente.setIdPessoa(buscaCPF(cliente.getCpf()));
            super.cadastrar(cliente);
            
            getConnection();
            stman = connection.prepareStatement(sql);
            stman.setDate(1, new Date(cliente.getDataCadastro().getTime()));
            stman.setBoolean(2, cliente.isAtivo());
            stman.setLong(3, cliente.getIdPessoa());
            stman.execute();
            stman.close();
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar o Cliente: " + e.toString());
        }
        return false;
    }

    public List pesquisa(String dados) {
        List<Cliente> clientes = new ArrayList<Cliente>();
        String sql = "Select * "
                + "from cliente as c, endereco as e, pessoa as p "
                + "where "
                + "p.nome like ? and "
                + "p.idPessoa = c.idPessoa and "
                + "e.CEP = p.CEP;";
        try {
            getConnection();
            stman = connection.prepareStatement(sql);
            stman.setString(1, dados + "%");
            result = stman.executeQuery();
            //result.first();
            while (result.next()) {
                Endereco endereco = new Endereco();
                Cliente cliente = new Cliente(endereco);
                cliente.setIdPessoa(result.getLong("idPessoa"));
                cliente.setIdCliente(result.getLong("idCliente"));
                cliente.setNome(result.getString("nome"));
                cliente.setEmail(result.getString("email"));
                cliente.setCpf(result.getString("cpf"));
                cliente.setNumero(result.getString("numero"));
                cliente.setComplemento(result.getString("complemento"));
                endereco.setCEP(result.getString("CEP"));
                endereco.setLogradouro(result.getString("Logradouro"));
                endereco.setBairro(result.getString("Bairro"));
                endereco.setCidade(result.getString("Cidade"));
                endereco.setUF(result.getString("UF"));
                cliente.setEndereco(endereco);
                cliente.setAtivo(result.getBoolean("ativo"));
                cliente.setDataCadastro(result.getDate("dataCadastro"));
                clientes.add(cliente);
            }
            result.close();
            stman.close();
        } catch (Exception e) {
            System.err.println("Erro ao pesquisar Cliente: " + e.toString());
        } finally {
            close();
        }
        return clientes;
    }

    public boolean atualizarBanco(Cliente cliente) {
        String sql = "update cliente set ativo = ? where idPessoa = ?;";
        try {
            
            cliente.setIdPessoa(buscaCPF(cliente.getCpf()));

            super.atualizar(cliente);
            
            getConnection();
            stman = connection.prepareStatement(sql);
            stman.setBoolean(1, cliente.isAtivo());
            stman.setLong(2, cliente.getIdPessoa());
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
    
    public boolean deletaCliente(Cliente cliente){
        String sql = "DELETE * FROM cliente WHERE idPessoa = ?";
        try{
            cliente.setIdPessoa(buscaCPF(cliente.getCpf()));
            
            getConnection();
            stman = connection.prepareStatement(sql);
            stman.setLong(1, cliente.getIdPessoa());
            stman.execute();
            stman.close();
            super.deletaPessoa(cliente);
            return true;
        }catch(SQLException e){
            System.err.println("Erro ao apagar registro: " + e.toString());
        }finally{
            close();
        }
        return false;
    }

    public Cliente getCliente(long id) {
        String sqlPessoa = ""
                + "select * "
                + "from pessoa as p, endereco as e, cliente as c "
                + "where c.idCliente = ? and "
                + "e.cep = p.CEP and "
                + "c.idPessoa = p.idPessoa;";
        Endereco endereco = new Endereco();
        Cliente cliente = new Cliente(endereco);
        try {
            getConnection();
            stman = connection.prepareStatement(sqlPessoa);
            stman.setLong(1, id);
            result = stman.executeQuery();
            result.first();
            if (result.first()) {
                //Pessoa
                cliente.setIdPessoa(result.getInt("idPessoa"));
                cliente.setNome(result.getString("nome"));
                cliente.setCpf(result.getString("cpf"));
                cliente.setEmail(result.getString("email"));
                cliente.setComplemento(result.getString("complemento"));
                cliente.setNumero(result.getString("numero"));
                cliente.setPws(result.getString("pws"));
                //Endereco
                endereco.setCEP(result.getString("CEP"));
                endereco.setLogradouro(result.getString("Logradouro"));
                endereco.setCidade(result.getString("Cidade"));
                endereco.setBairro(result.getString("Bairro"));
                endereco.setUF(result.getString("Uf"));
                cliente.setEndereco(endereco);
                //Cliente
                cliente.setDataCadastro(result.getDate("dataCadastro"));
                cliente.setAtivo(result.getBoolean("ativo"));
                cliente.setIdCliente(result.getLong("idCliente"));
                stman.close();
                result.close();
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao buscar ID: " + erro.toString());
        } finally {
            close();
        }
        return cliente;
    }
}
