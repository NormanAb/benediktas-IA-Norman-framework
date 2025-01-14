package com.benedict.minibank.Models;

import com.benedict.minibank.Views.AccountType;
import com.benedict.minibank.Views.ViewFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;
    //Client
    private final Client client;
    private boolean clientLoginSuccessFlag;
    //Admin
   private boolean adminLoginSuccessFlag;

    private Model(){
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();
        //Client
        this.clientLoginSuccessFlag = false;
        this.client = new Client("","","",null,null, null);
        //Admin
        this.adminLoginSuccessFlag = false;
    }

    public static synchronized Model getInstance(){
        if(model == null){
            model = new Model();
        }
        return  model;
    }

    public ViewFactory getViewFactory(){
        return viewFactory;
    }

    public DatabaseDriver getDatabaseDriver(){
        return this.databaseDriver;
    }

    // Client methods section

     public boolean getClientSuccessFlag(){
        return this.clientLoginSuccessFlag;
     }

     public void  setClientLoginSuccessFlag(boolean flag){
        this.clientLoginSuccessFlag = flag;
     }

     public Client getClient(){
        return  client;
     }

    public void checkClientCredentials(String pAddress, String password){
        ResultSet resultSet = databaseDriver.getClientData(pAddress, password);
        try{
            if(resultSet != null && resultSet.isBeforeFirst()){
                this.client.firstNameProperty().set(resultSet.getString("FirstName"));
                this.client.lastNameProperty().set(resultSet.getString("LastName"));
                this.client.payeeAddressProperty().set(resultSet.getString("PayeeAddress"));
                String[] dateParts = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]),Integer.parseInt(dateParts[1]),Integer.parseInt(dateParts[2]));
                this.client.dateCreatedProperty().set(date);
                this.clientLoginSuccessFlag = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    // Admin methods section

    public boolean getAdminSuccessFlag(){
        return this.adminLoginSuccessFlag;
    }

    public void  setClientAdminSuccessFlag(boolean flag){
        this.adminLoginSuccessFlag = flag;
    }

    public void checkAdminCredentials(String pAddress, String password){
        ResultSet resultSet = databaseDriver.getAdminData(pAddress, password);
        try{
            if(resultSet != null && resultSet.isBeforeFirst()){
                this.adminLoginSuccessFlag = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
