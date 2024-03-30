package com.alessiomanai.spring.util;

public class PizzaUtils {

    public static Boolean validpizzaNumber(Integer pizza){

        return ((pizza % 5 == 0 ) && pizza <= 15 && pizza > 0);

    }

}
