package com.example.restaurantuifragment.data;

import android.content.Context;
import android.content.SharedPreferences;

public class BasicDatabase {
    public static final String choice = "CHOICE";
    public static final String signUpEmail = "SIGNUP_EMAIL";
    public static final String signUpFullName = "FULL_NAME";
    public static final String signUpMobile = "MOBILE";
    public static final String signUpPassword = "SIGNUP_PASSWORD";
    public static final String loginEmail = "LOGIN_EMAIL";
    public static final String loginPassword = "LOGIN_PASSWORD";

    private static SharedPreferences preferences;

    public BasicDatabase() {

    }

    public static SharedPreferences getInstance(Context context){
        if (preferences == null){
            preferences = context.getSharedPreferences("com.example.restaurantuifragment", Context.MODE_PRIVATE);
        }
        return preferences;
    }

}
