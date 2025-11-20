package main.java.br.com.alura.refl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Transformator {
    public <I, O> O transform(I input) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz = input.getClass(); //retorna a classe em tempo de execucao
        Class<?> target = Class.forName(clazz + "DTO"); //retorna a classe em tempo de execucao que eh resultado da concatenacao da classe parametro + DTO

        O targetClass = (O) target.getDeclaredConstructor().newInstance(); //cria e inicializa a instancia da classe com base no seu construtor

        //retorna um array do tipo Fields com todos os atributos das classes em questao
        Field[] clazzFields = clazz.getDeclaredFields();
        Field[] targetFields = target.getDeclaredFields();

        //itera pelos arrays atraves de streams aplicando a logica de verificacao e seta no dto o atributo da classe original
        Arrays.stream(clazzFields).forEach(c -> Arrays.stream(targetFields).forEach(t ->{
            validateFields(c, t);
            try {
                t.set(targetClass, c.get(input));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }));

        return targetClass;
    }

    //funcao que verifica se um campo de uma classe X eh igual a outro de uma classe Y (mesmo nome e mesmo tipo)
    private void validateFields(Field c, Field t){
        if(c.getName().equals(t.getName()) && c.getType().equals(t.getType())){
            //atributos iguais em nome e tipo, entao os torne acessiveis
            c.setAccessible(true);
            t.setAccessible(true);
        }
    }
}
