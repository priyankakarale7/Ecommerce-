package com.example.ecomm;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Ecommerse extends Application {

    private final int width = 600, height = 500, headerSpace = 50;
    Pane bodyPane;
    //ProductList p_list = new ProductList();

    static Customer loggedInCustomer = null;

    ObservableList<Product> cartItemList = FXCollections.observableArrayList();

    private void addItemstoCart(Product product){
        if(cartItemList.contains(product)) return;
        cartItemList.add(product);
        System.out.println("product in cart = "+ cartItemList.stream().count());
    }

    private GridPane headerBar(){
        GridPane header = new GridPane();

        Label welcomeLabel = new Label("Welcome Customer");
        TextField searchBar = new TextField();
        Button searchButton = new Button("Search");
        Button loginButton = new Button("Login");


        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(loginPage());
            }
        });

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String search = searchBar.getText();
                //select * from products where name like search;
                String query = "SELECT * FROM products WHERE name LIKE'%"+search+"%';";

                bodyPane.getChildren().clear();
                if(search == "") {
                    bodyPane.getChildren().add(ProductList.getAllProducts());
                    return;
                }
                bodyPane.getChildren().add(ProductList.getSearchedProducts(query));
            }
        });


        Button cart = new Button("Cart");

        cart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(ProductList.getCart(cartItemList));
            }
        });
        header.add(searchBar,0,0);
        header.setHgap(10);
        header.add(searchButton, 1,0);
        //header.add(loginButton,2,0);


        header.add(welcomeLabel,4,0);
        header.add(cart,5,0);


        return header;

    }

    private void showDailouge(String message){
        //Creating a dialog
        Dialog<String> dialog = new Dialog<String>();
        //Setting the title
        dialog.setTitle("Order Status");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        //Setting the content of the dialog
        dialog.setContentText(message);
        //Adding buttons to the dialog pane
        dialog.getDialogPane().getButtonTypes().add(type);


        dialog.showAndWait();
    }



    private GridPane loginPage(){
        Label userLabel = new Label("User Name");
        Label passLabel = new Label("Password");
        TextField userName = new TextField();
        userName.setPromptText("Enter User Name");
        PasswordField passWord = new PasswordField();
        passWord.setPromptText("Enter User Password");

        Button loginButton = new Button("Login");
        Label messageLabel = new Label("Login - Message");

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String user = userName.getText();
                String pass = passWord.getText();
                if(Login.customerLogin(user,pass)){
                    messageLabel.setText("Login Successfull");
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(ProductList.getAllProducts());
                }else{
                    messageLabel.setText("Incorrect Credentials");
                }
            }
        });

        GridPane loginPane = new GridPane();
        loginPane.setTranslateY(50);
        loginPane.setVgap(10);
        loginPane.setHgap(10);

        loginPane.add(userLabel,0 ,0);
        loginPane.add(userName,1 ,0);
        loginPane.add(passLabel,0 ,1);
        loginPane.add(passWord,1 ,1);
        loginPane.add(loginButton,0 ,2);
        loginPane.add(messageLabel,1 ,2);

        return loginPane;


    }


    private void showDailouge2(String message){
        //Creating a dialog
        Dialog<String> dialog = new Dialog<String>();
        //Setting the title
        dialog.setTitle("Register");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        //Setting the content of the dialog
        dialog.setContentText(message);
        //Adding buttons to the dialog pane
        dialog.getDialogPane().getButtonTypes().add(type);


        dialog.showAndWait();
    }
    private GridPane SignInPage(){
        Label nameLabel = new Label("Name");
        Label userLabel = new Label("User Name");
        Label passLabel = new Label("Password");
        Label MobileLabel = new Label("Contact No.");
        Label emailLabel = new Label("Email Id");
        Label addressLabel = new Label("Address");

        TextField Name = new TextField();
        Name.setPromptText("Enter your Name");
        TextField userName = new TextField();
        userName.setPromptText("Set User Name");
        PasswordField passWord = new PasswordField();
        passWord.setPromptText("Set Password");
        TextField Mobile = new TextField();
        Mobile.setPromptText("Enter 10 digit mobile number");
        TextField email = new TextField();
        email.setPromptText("Enter your email-Id");
        TextField address = new TextField();
        address.setPromptText("Your Address");

        Button SignInButton = new Button("Sign-Up");
        Button SigninButton = new Button("Sign-In");

        SigninButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(loginPage());
            }
        });
        //Label messageLabel = new Label("You are Successfully registered with us");

        SignInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name = Name.getText();
                String user = userName.getText();
                String pass = passWord.getText();
                String mobile = Mobile.getText();
                String emailText = email.getText();
                String Address = address.getText();


                if(SignIn.Signin(name,user,pass,mobile,emailText,Address)){
                    showDailouge2("Congratulations..!!! You are Successfully registered with us");
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(ProductList.getAllProducts());
                }else{
                    showDailouge2("Your email or mobile number is Already Registerd with us");
                }
            }
        });

        GridPane SignInPane = new GridPane();
        SignInPane.setTranslateY(50);
        SignInPane.setVgap(10);
        SignInPane.setHgap(10);

        SignInPane.add(nameLabel,0 ,0);
        SignInPane.add(Name,1 ,0);
        SignInPane.add(userLabel,0 ,1);
        SignInPane.add(userName,1 ,1);
        SignInPane.add(passLabel,0 ,2);
        SignInPane.add(passWord,1 ,2);

        SignInPane.add(MobileLabel,0 ,3);
        SignInPane.add(Mobile,1 ,3);
        SignInPane.add(emailLabel,0 ,4);
        SignInPane.add(email,1 ,4);
        SignInPane.add(addressLabel,0 ,5);
        SignInPane.add(address,1 ,5);


        SignInPane.add(SignInButton,0 ,6);

        SignInPane.add(SigninButton,1 ,6);
        //SignInPane.add(messageLabel,1 ,6);

        return SignInPane;


    }

    private GridPane footerBar(){
        Button buyNow = new Button("Buy Now");

        buyNow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = ProductList.getSelectedProduct();
                boolean orderStatus = false;
                //System.out.println(product +" "+ loggedInCustomer);
                if (product != null && loggedInCustomer != null) {
                    orderStatus = Order.placeOrder(loggedInCustomer, product);

                    //System.out.println("Order placed");
                }
                if (orderStatus) {

                    showDailouge("Order Successful!!!!");

                }
                else {

                    showDailouge("Order can't be placed, please Sign In!!!!");
                }
            }
        });

        Button Cart = new Button("Add To Cart");
        Cart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = ProductList.getSelectedProduct();
                addItemstoCart(product);
            }
        });

        Button placeOrder = new Button("Place Order");
        placeOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = ProductList.getSelectedProduct();
                int orderCount = 0;

                if (!cartItemList.isEmpty() && loggedInCustomer != null) {
                    orderCount = Order.placeMultipleOrder(cartItemList,loggedInCustomer);
                    System.out.println("Product");
                }
                if (orderCount>0) {

                    showDailouge("Order for " + orderCount + " products Placed Successfully!!!!");

                }
                else {

                    showDailouge("Order can't be placed, please Sign In/ Add item to cart!");
                }
            }
        });
        GridPane footer = new GridPane();
        footer.setTranslateY(headerSpace+height);
        footer.setHgap(10);
        footer.add(buyNow,0,0);
        footer.add(Cart,1,0);
        footer.add(placeOrder,2,0);
        return footer;

    }
    private Pane createPane(){
        Pane root = new Pane();
        root.setPrefSize(width,height +2*headerSpace);

        bodyPane = new Pane();
        bodyPane.setPrefSize(width, height);
        bodyPane.setTranslateY(headerSpace);
        bodyPane.setTranslateX(10);
        bodyPane.getChildren().add(loginPage());

        GridPane header = headerBar();
        Button signoutButton = new Button("Log Out");

        signoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                loggedInCustomer = null;
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(loginPage());
            }
        });

        Button SignupButton = new Button("Sign-Up");
        SignupButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(SignInPage());
            }
        });
        if(loggedInCustomer!=null){
            header.add(signoutButton,3,0);
        }else
            header.add(SignupButton, 3,0);

        root.getChildren().addAll( header , bodyPane, footerBar()
        );
        // root.getChildren().add(loginPage());
        // root.getChildren().add(p_list.getAllProducts());

        return root;
    }



    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(createPane());
        stage.setTitle("ECommerce");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}