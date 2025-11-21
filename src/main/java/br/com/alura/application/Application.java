package br.com.alura.application;

import br.com.alura.Pessoa;
import br.com.alura.refl.ObjectToJson;

public class Application {
    public static void main(String[] args){
        Pessoa pessoa = new Pessoa(1, "Exemplo", "123.456.789-00");
        ObjectToJson objectToJson = new ObjectToJson();
        System.out.println(objectToJson.transformToJson(pessoa));
    }
}
