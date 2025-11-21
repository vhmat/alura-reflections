package br.com.alura.fixture;

import br.com.alura.Pessoa;

public class PessoaFixture {
    public static Pessoa buildPessoa(){
        return new Pessoa(1, "Exemplo", "123.456.789-00");
    }
}
