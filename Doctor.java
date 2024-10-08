package org.spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {

    private Connection connection;



    public Doctor(Connection connection) {
        this.connection = connection;

    }

    public  void viewDoctor(){
        String query="Select * from doctor";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            ResultSet resultSet=preparedStatement.executeQuery();
            System.out.println("DOCTORS : ");
            System.out.println("|-----------|-------------------------------------|-----------------------|");
            System.out.println("|     ID    |             DOCTORS NAME            |     SPECIALIZATION    |");
            System.out.println("|-----------|-------------------------------------|-----------------------|");

            while (resultSet.next()) {
                int id=resultSet.getInt("Id");
                String name=resultSet.getString("Name");
                String specialization=resultSet.getString("Specialization");
                System.out.printf("| %-9s | %-35s | %-21s |\n",id,name,specialization);
            }
            System.out.println("|-----------|-------------------------------------|-----------------------|");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean getDoctorById(int id) {
        String query = "select * from doctor where id=?";
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



