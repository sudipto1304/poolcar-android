package com.poolcar.utils;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


public class ValidatorTest {


    Validator validator;
    private String[] validEmail = new String[] { "mkyong@yahoo.com",
            "mkyong-100@yahoo.com", "mkyong.100@yahoo.com",
            "mkyong111@mkyong.com", "mkyong-100@mkyong.net",
            "mkyong.100@mkyong.com.au", "mkyong@1.com",
            "mkyong@gmail.com.com", "mkyong+100@gmail.com",
            "mkyong-100@yahoo-test.com" };

    private String[] invalidEmail = new String[] { "mkyong", "mkyong@.com.my",
            "mkyong123@gmail.a", "mkyong123@.com", "mkyong123@.com.com",
            ".mkyong@mkyong.com", "mkyong()*@gmail.com", "mkyong@%*.com",
            "mkyong..2002@gmail.com", "mkyong.@gmail.com",
            "mkyong@mkyong@gmail.com", "mkyong@gmail.com.1a" };


    @BeforeClass
    public void initData() {
        validator = new Validator();
    }



    @Test
    public void ValidEmailTest() {
        for (String temp : validEmail) {
            boolean valid = validator.validateEmailId(temp);
            System.out.println("Email is valid : " + temp + " , " + valid);
            Assert.assertEquals(valid, true);
        }

    }

    @Test
    public void InValidEmailTest() {

        for (String temp : invalidEmail) {
            boolean valid = validator.validateEmailId(temp);
            System.out.println("Email is valid : " + temp + " , " + valid);
            Assert.assertEquals(valid, false);
        }
    }

}
