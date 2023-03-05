package com.example.ecomm;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;

public class Login {

    private static byte[] getSha(String input){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(input.getBytes(StandardCharsets.UTF_8));
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String getEncryptedPassword(String pw){
        try{
            BigInteger num = new BigInteger(1, getSha(pw));
            StringBuilder sb = new StringBuilder(num.toString(16));
            return sb.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static boolean customerLogin(String userEmail , String pw){
        //SELECT * FROM customer WHERE email = 'piyuu@gmail.com' and password = 'abcd';

       String encryptedPass = getEncryptedPassword(pw);
        String query = "SELECT * FROM customers WHERE email = '" + userEmail + "'and password ='" + pw + "'";
        Database db = new Database();
        try{
            ResultSet rs = db.getQueryTable(query);
           //System.out.println(rs.getInt("cid"));

            if(rs != null && rs.next()){
                Customer c = new Customer(rs.getInt("cid"),rs.getString("name"),rs.getString("email"));
                Ecommerse.loggedInCustomer = c;
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

//    public static void main(String[] args) {
//
//        //System.out.println(customerLogin('piyuu@gmail.com','1234'));
//
//        System.out.println(getEncryptedPassword("angad"));
//    }
}
