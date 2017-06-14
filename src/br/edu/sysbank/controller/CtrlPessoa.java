package br.edu.sysbank.controller;

import br.edu.sysbank.model.Pessoa;

public class CtrlPessoa {

    public String valida(Pessoa pessoa) {
        String erros = "";
        if (pessoa.getNome().isEmpty()) {
            erros += "Nome em branco.\n";
        }

        if (pessoa.getEmail().isEmpty()) {
            erros += "E-mail em branco.\n";
        } else if (pessoa.getEmail().indexOf(".") < 1
                || !pessoa.getEmail().contains("@")
                || pessoa.getEmail().length() < 5) {
            erros += "E-mail invalida.\n";
        }

        if (pessoa.getNumero().isEmpty()) {
            erros += "Numero em branco.\n";
        }

        return erros;
    }

    public String validaSenha(String pws1, String pws2) {
        String erros = "";
        if (pws1.isEmpty() || pws2.isEmpty()) {
            erros += "Senha(s) em branco.\n";
        } else if (pws1.length() < 5) {
            erros += "Senha muito curta. Minimo de 5 caracteres.\n";
        } else if (!pws1.equals(pws2)) {
            erros += "Senha(s) diferentes.\n";
        }
        return erros;
    }

    public String valida(Pessoa pessoa, String pws) {
        CtrlEndereco ctrlEndereco = new CtrlEndereco();
        String erros = valida(pessoa);
        erros += validaCPF(pessoa.getCpf());
        erros += ctrlEndereco.valida(pessoa.getEndereco());
        erros += validaSenha(pessoa.getPws(), pws);
        return erros;
    }

    public String validaCPF(String cpf) {
        String erros = "";
        if (cpf.isEmpty()) {
            erros += "CPF em branco.\n";
        } else if (cpf.length() != 11) {
            erros += "CPF com digitos errado.\n";
        } else if (!util.ValidaCPF.isCPF(cpf)) {
            erros += "CPF invalido.\n";
        }
        return erros;
    }
}
