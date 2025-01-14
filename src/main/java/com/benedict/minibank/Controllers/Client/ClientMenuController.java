package com.benedict.minibank.Controllers.Client;

import com.benedict.minibank.Models.Model;
import com.benedict.minibank.Views.ClientMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {
    public Button dashboard_btn;
    public Button clients_btn;
    public Button reports_btn;

    public Button profile_btn;
    public Button logout_btn;
    public Button report_btn;

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle){
        addListeners();
    }

    private void addListeners(){
        dashboard_btn.setOnAction(event -> onDashboard());
        logout_btn.setOnAction(event -> onLogout());
    }

    private void onDashboard(){
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.DASHBOARD);
    }

    private void onTransactions(){
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.TRANSACTIONS);
    }

    private void onAccounts(){
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.ACCOUNTS);
    }

    private void onLogout(){
        //Get stage
        Stage stage = (Stage) dashboard_btn.getScene().getWindow();
        //Close client window
        Model.getInstance().getViewFactory().closeStage(stage);
        //Show Login Window
        Model.getInstance().getViewFactory().showLoginWindow();
        //Set flag to false
        Model.getInstance().setClientLoginSuccessFlag(false);

    }
}
