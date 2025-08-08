package com.Ecommerce.demo.Components;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Random;


@Component
public  class OTPGenerator {

    @Autowired
    private Random secureRandom;

    public String getOTP() {
        String letters = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";

        char firstChar = letters.charAt(secureRandom.nextInt(letters.length()));
        char secondChar = letters.charAt(secureRandom.nextInt(letters.length()));

        //create a random 4 digit value
        String randomNumbers = String.valueOf(secureRandom.nextInt(10000));


        return firstChar + randomNumbers+ secondChar;
    }

}
