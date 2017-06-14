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
public class Cargo {
    private String nomeCargo;
    private long idCargo;
    
    private Funcionario funcionario;

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }

    public long getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(long idCargo) {
        this.idCargo = idCargo;
    }
    
    public Funcionario getFuncionario(){
        return funcionario;
    }
    
}
