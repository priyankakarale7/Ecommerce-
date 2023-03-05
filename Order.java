package com.example.ecomm;

import javafx.collections.ObservableList;

public class Order {

    public static boolean placeOrder(Customer customer , Product product){
        try{
            //INSERT INTO orders (customer_id, product id, status) VALUES(
            String query = "INSERT INTO orders ( customer_id, product_id, product_name, status) VALUES(" + customer.getId() + "," + product.getId() + ",'"+product.getName()+"',"+ "'Ordered');";
            Database db = new Database();
            db.insertUpdate(query);
            //System.out.println("abcd");
            return true;

        }catch(Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return false;

    }


    public static int placeMultipleOrder(ObservableList<Product> productlist, Customer customer){
        int count = 0;

        for(Product product : productlist){
            if(placeOrder(customer,product)) count++;
        }
        return count;
    }

}
