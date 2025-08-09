package com.Ecommerce.demo.Util.Email.EmailContent;

public class AdminQuestionAnswer {


    public static String getAdminAnswerNotification(String firstName, String lastName, String productName) {
        return "Mr/Mrs " + firstName + lastName + ", check the question you wanned an asnwer to on " + productName;
    }
}
