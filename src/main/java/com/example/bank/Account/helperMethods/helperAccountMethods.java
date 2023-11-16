package com.example.bank.Account.helperMethods;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class helperAccountMethods {

    public String generateAccountNumber(){
        Random random = new Random();
        int randomPart = random.nextInt(900000000) + 1000000000;


        return String.valueOf(randomPart);
    }
}
