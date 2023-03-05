package com.example.ecomm;

import java.math.BigInteger;

public class SignIn {


    public static boolean Signin( String name, String user,String pass, String mobile,String emailText,String Address){
        try{
            Login l = new Login();
            String encrypt = l.getEncryptedPassword(pass);
            //INSERT INTO orders (customer_id, product id, status) VALUES(
            String query = "INSERT INTO customers (name,password,email,mobile,address) VALUES('"+ name + "','" + encrypt +"','" + mobile +"','" + emailText +"','" + Address + "');" ;
            Database db = new Database();
            db.insertUpdate(query);

            return true;

        }catch(Exception e) {
            e.printStackTrace();
        }
        return false;

    }
}
