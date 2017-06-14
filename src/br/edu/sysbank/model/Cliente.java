
package br.edu.sysbank.model;

import java.util.Date;

public class Cliente extends Pessoa{
    private long idCliente;
    private boolean ativo;
    private Date dataCadatro;
    private Date dataBloqueio;
    
    public Cliente(Endereco endereco){
       super(endereco);
    }
   
    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDataCadastro() {
        return dataCadatro;
    }

    public void setDataCadastro(Date dataCadatro) {
        this.dataCadatro = dataCadatro;
    }

    public Date getDataBloqueio() {
        return dataBloqueio;
    }

    public void setDataBloqueio(Date dataBloqueio) {
        this.dataBloqueio = dataBloqueio;
    }
    
    
}
