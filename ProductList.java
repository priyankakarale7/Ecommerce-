package com.example.ecomm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ProductList {

    public static TableView<Product> productTable;

    public static Pane getAllProducts(){

        ObservableList<Product> productsList = Product.getAllProducts();

        return createTableFromList( productsList);
    }


    public static Pane getSearchedProducts(String query){


        ObservableList<Product> productsList = Product.getSearchedProducts(query);
        return createTableFromList( productsList);
    }

    public static Pane createTableFromList(ObservableList<Product> productsList){
            TableColumn id = new TableColumn("Id");
            id.setCellValueFactory(new PropertyValueFactory<>("id"));

            TableColumn name = new TableColumn("Mobile");
            name.setCellValueFactory(new PropertyValueFactory<>("name"));

            TableColumn price = new TableColumn("Price");
            price.setCellValueFactory(new PropertyValueFactory<>("price"));

            TableColumn quantity = new TableColumn("Quantity");
            quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

            productTable = new TableView<>();
            productTable.setItems(productsList);
            productTable.getColumns().addAll(id, name, price, quantity);

            Pane tablePane = new Pane();
            tablePane.getChildren().add(productTable);

            return tablePane;

        }
    public static Pane getCart(ObservableList<Product> productsList){
        return createTableFromList( productsList);

    }
    public static Product getSelectedProduct(){
            return productTable.getSelectionModel().getSelectedItem();
    }
}
