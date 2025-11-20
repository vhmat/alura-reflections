package br.com.alura.refl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Transformator {
    public <I, O> O transform(I input) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz = input.getClass(); //retorna a classe em tempo de execucao
        Class<?> target = Class.forName(clazz + "DTO"); //retorna a classe em tempo de execucao que eh resultado da concatenacao da classe parametro + DTO

        O targetClass = (O) target.getDeclaredConstructor().newInstance(); //cria e inicializa a instancia da classe com base no seu construtor

        //retorna um array do tipo Fields com todos os atributos das classes em questao
        Field[] clazzFields = clazz.getDeclaredFields();
        Field[] targetFields = target.getDeclaredFields();

        return targetClass;
    }
}
