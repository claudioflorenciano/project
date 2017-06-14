package br.edu.sysbank.view;

import br.edu.sysbank.controller.CtrlEndereco;
import br.edu.sysbank.model.Cliente;
import br.edu.sysbank.model.Conta;
import br.edu.sysbank.model.Endereco;

public class Principal {

    public static void main(String[] args) {
        Endereco e = new Endereco();

        e.setCEP("");
        e.setLogradouro("Rua B");
        e.setBairro("");
        e.setCidade("Rio de Janeiro");
        e.setUF("RJ");

        Cliente c1 = new Cliente(e);

        e = new Endereco();
        e.setCEP("23520-050");
        e.setLogradouro("Rua C");
        e.setBairro("Campo Grande");
        e.setCidade("Rio de Janeiro");
        e.setUF("RJ");

        Cliente c2 = new Cliente(e);

        Conta conta1 = new Conta();
        conta1.setCliente(c1);

        Conta conta2 = new Conta();
        conta2.setCliente(c2);

        CtrlEndereco ctrlend = new CtrlEndereco();

        if (ctrlend.valida(conta1.getCliente().getEndereco()).equals("")) {
            System.out.println("Cliente 01: " + conta1.getCliente().getEndereco().getCEP());

        } else {
            System.err.println("Erros: \n" + ctrlend.valida(conta1.getCliente().getEndereco()));
        }

        System.out.println("Cliente 02: " + conta2.getCliente().getEndereco().getCEP());

    }
}
