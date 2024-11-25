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
        boolean continueInput = true;

        while (continueInput) {
        System.out.println("Product ID(or type 'exit' to stop): ");
        String userInput = scanner.nextLine();

        if (userInput.equalsIgnoreCase("exit")) {
            continueInput = false;
            break;
        }
        String query = "SELECT * FROM products WHERE ProductID = ?" ;
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userInput);
        ResultSet results = statement.executeQuery();

        while (results.next()){
            Integer productID = results.getInt("ProductID");
            String name = results.getString("ProductName");
            Double price = results.getDouble("unitPrice");
            Integer unitsInStock = results.getInt("UnitsInStock");
            System.out.println("productID:" + productID + ", name:" + name + ", price:" + price + ", Stock:" + unitsInStock);
            System.out.println("---------------------------------------------------------");
        }

        results.close();
        statement.close();

}
        connection.close();
    }
}
