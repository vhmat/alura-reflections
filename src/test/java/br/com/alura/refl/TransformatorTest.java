package br.com.alura.refl;

import br.com.alura.Cliente;
import br.com.alura.Pessoa;
import br.com.alura.PessoaDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

public class TransformatorTest {

    Pessoa pessoa = new Pessoa(1, "Exemplo", "123.456.789-00");
    Transformator transformator = new Transformator();
    Cliente cliente = new Cliente();

    @Test
    public void shouldBeNotNull() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        PessoaDTO pessoaDTO = transformator.transform(pessoa);

        Assertions.assertNotNull(pessoaDTO);
    }

    @Test
    public void shouldNotTransform(){
        Assertions.assertThrows(ClassNotFoundException.class, () -> {
            transformator.transform(cliente);
        });
    }

    @Test
    public void shouldTransformWhenSomeFieldIsNull() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Pessoa pessoa = new Pessoa();
        PessoaDTO pessoaDTO = transformator.transform(pessoa);

        Assertions.assertNull(pessoaDTO.getNome());
        Assertions.assertNull(pessoaDTO.getCpf());
    }

    @Test
    public void shouldTransform() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        PessoaDTO pessoaDTO = transformator.transform(pessoa);

        Assertions.assertInstanceOf(PessoaDTO.class, pessoaDTO); // verifica se a instancia eh realmente um PessoaDTO
        Assertions.assertEquals(pessoa.getNome(), pessoaDTO.getNome()); //verifica se os atributos foram refletidos corretamente
        Assertions.assertEquals(pessoa.getCpf(), pessoaDTO.getCpf());
    }
}