package com.example.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class HomeController {
@FXML
public void abrirMotoristas(ActionEvent event) {
    try {
        Parent root = FXMLLoader.load(
                getClass().getResource("/view/MotoristaView.fxml"));

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

public void abrirVeiculos(ActionEvent event) {
    try {
        Parent root = FXMLLoader.load(
                getClass().getResource("/view/VeiculoView.fxml"));

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

public void abrirLiberacaoManual(ActionEvent event){
    try{
        Parent root = FXMLLoader.load(getClass().getResource("/view/LiberacaoManualView.fxml"));

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e){
        e.printStackTrace();
    }
}
}

