package com.example.ecomm;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Product {
    private SimpleIntegerProperty id;

    private SimpleStringProperty name;

    private SimpleDoubleProperty price;

    private SimpleIntegerProperty quantity;

    public int getId(){
        return id.get();
    }
    public String getName(){
        return name.get();
    }
    public Double getPrice(){
        return price.get();
    }
    public int getQuantity(){
        return quantity.get();
    }



    public Product(int id, String name, Double price, int q){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.quantity = new SimpleIntegerProperty(q);
        //System.out.println(id +" "+name+" "+" "+price+" "+quantity);
    }

    public static ObservableList<Product> getAllProducts(){
        String getProd = "SELECT * FROM products;";
        return getProducts(getProd);

    }
    public static ObservableList<Product> getSearchedProducts(String query){
        //String getProd = "SELECT * FROM products;";

        return getProducts(query);


    }


    public static ObservableList<Product> getProducts(String query) {
        Database dbConn = new Database();
        ResultSet rs = dbConn.getQueryTable(query);
        ObservableList<Product> result = FXCollections.observableArrayList();
        try {
            if (rs != null) {
                while (rs.next()) {
                    // taking out values from resultSet
                    result.add(new Product(
                            rs.getInt("p_id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getInt("quantity")
                    ));

                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
