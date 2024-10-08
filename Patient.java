package org.spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {

    private Connection connection;

    private Scanner scanner;

    public Patient(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }


    public void addPatient(){
        System.out.println("Enter the Patient Name");
        String name=scanner.next();
        System.out.println("Enter the Age :");
        String age=scanner.next();
        System.out.println("Enter the Gender :");
        String gender=scanner.next();

        try{
            String query="INSERT INTO PATIENT(NAME,AGE,GENDER) VALUES(?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,age);
            preparedStatement.setString(3,gender);
            int affectedRows=preparedStatement.executeUpdate();

            if(affectedRows>0)
                System.out.println("Data added sucessfully.....");
            else
                System.out.println("Failed to add......!!!!");

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void viewPatient(){
        String query="Select * from patient";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            ResultSet resultSet=preparedStatement.executeQuery();
            System.out.println("PATIENTS : ");
            System.out.println("|-----------|-------------------------------------|------------|-------------|");
            System.out.println("|     ID    |             PATIENT NAME            |     AGE    |    GENDER   |");
            System.out.println("|-----------|-------------------------------------|------------|-------------|");

            while (resultSet.next()) {
                int id=resultSet.getInt("Id");
                String name=resultSet.getString("Name");
                int age=resultSet.getInt("Age");
                String gender=resultSet.getString("Gender");
                System.out.printf("| %-9s | %-35s | %-9s | %-11s |\n",id,name,age,gender);
            }
            System.out.println("|-----------|-------------------------------------|------------|-------------|");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean getPatientById(int id) {
        String query = "select * from patient where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return true;
            else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
