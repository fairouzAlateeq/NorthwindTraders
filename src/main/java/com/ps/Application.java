package com.ps;
import java.sql.*;
import java.util.Scanner;

public class Application {
    public static void main (String[]args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/northwind",
                args[0],
                args[1]
        );



        Scanner scanner = new Scanner(System.in);
        System.out.println("Product ID: ");
        String userInput = scanner.nextLine();

        String query = "SELECT * FROM products WHERE ProductID = ?" ;
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userInput);
        ResultSet results = statement.executeQuery();

        while (results.next()){
            String name = results.getString("ProductName");
            Double price = results.getDouble("unitPrice");
            System.out.println(name + " " + price);
        }

        results.close();
        statement.close();
        connection.close();


    }
}
