/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.sysbank.model;

/**
 *
 * @author MrMojo
 */
public class Funcionario extends Pessoa{
    private long idFuncionario;
    private Cargo cargo;
    
    public Funcionario(Endereco endereco) {
        super(endereco);
    }

    public long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

   public Cargo getCargo(){
       return cargo;
   }
   
   public void setCargo(Cargo cargo){
       this.cargo = cargo;
   }
   

}
