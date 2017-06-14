
package br.edu.sysbank.controller;

import br.edu.sysbank.model.Conta;

public class CtrlConta {
      
    public boolean deposito(Conta conta, double valor){
        if (valor > 0){
            conta.setSaldo(conta.getSaldo()+valor);
            return true;
        }
        return false;
    }
    
    public boolean saque(Conta conta, double valor){
        if (conta.getSaldo() >= valor){
            conta.setSaldo(conta.getSaldo() - valor);
            return true;
        }
        return false;
    }
}
