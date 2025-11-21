package br.com.alura.refl;

import br.com.alura.Pessoa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ObjectToJsonTest {

    Pessoa pessoa = new Pessoa(1, "Exemplo", "123.456.789-00");

    @Test
    public void shouldTransform() {
        ObjectToJson objectToJson = new ObjectToJson();
        String json = objectToJson.transformToJson(pessoa);

        System.out.println(json);

        Assertions.assertInstanceOf(String.class, json);
    }

}
