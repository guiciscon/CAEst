package com.example.controller;

import java.io.IOException;

import com.example.model.Motorista;
import com.example.dao.MotoristaDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class MotoristaController {
private Motorista motoristaSelecionado;
@FXML
private TextField txtNome;
@FXML
private TextField txtCpf;
@FXML
private TextField txtMatricula;
@FXML
private ComboBox<String> cbxVinculo;
@FXML
private TableView<Motorista> tblMotoristas;
    @FXML
    private TableColumn<Motorista, Integer> colId;
    @FXML
    private TableColumn<Motorista, String> colNome;
    @FXML
    private TableColumn<Motorista, String> colCpf;
    @FXML
    private TableColumn<Motorista, String> colMatricula;
    @FXML
    private TableColumn<Motorista, String> colVinculo;

//@@@botões e navegação@@@
@FXML
public void voltarHome(ActionEvent event) {
    try {
        Parent root = FXMLLoader.load(
                getClass().getResource("/view/HomeView.fxml"));

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

@FXML
public void salvarMotorista(ActionEvent event){
    Motorista motorista = new Motorista();

    motorista.setNome(txtNome.getText());
    motorista.setCpf(txtCpf.getText());
    motorista.setMatricula(txtMatricula.getText());
    motorista.setVinculo(cbxVinculo.getValue());

    MotoristaDAO motoristaDAO = new MotoristaDAO();
    motoristaDAO.inserirMotorista(motorista);
    carregarTabela();
}

@FXML
public void atualizarMotorista(ActionEvent event){
    Motorista motorista = new Motorista();
    try { if(motoristaSelecionado == null) {
        System.out.println("Nenhum motorista selecionado para atualização.");       
        return;
    }
        motorista.setId(motoristaSelecionado.getId());
        motorista.setNome(txtNome.getText());
        motorista.setCpf(txtCpf.getText());
        motorista.setMatricula(txtMatricula.getText());
        motorista.setVinculo(cbxVinculo.getValue());

        MotoristaDAO dao = new MotoristaDAO();
        dao.atualizarMotorista(motorista);
        carregarTabela();
    } catch (NumberFormatException e) {
        System.out.println("ID inválido. Por favor, insira um número inteiro.");
    }
}

@FXML
public void limparCampos(ActionEvent event){
    txtNome.clear();
    txtCpf.clear();
    txtMatricula.clear();
    cbxVinculo.getSelectionModel().clearSelection();
}

@FXML
public void excluirMotorista(ActionEvent event){
    try { if(motoristaSelecionado == null) {
        System.out.println("Nenhum motorista selecionado para atualização.");       
        return;
    }
        MotoristaDAO dao = new MotoristaDAO();
        dao.deletarMotorista(motoristaSelecionado.getId());
        carregarTabela();
    } catch (NumberFormatException e) {
        System.out.println("ID inválido. Por favor, insira um número inteiro.");
    }
}

//funções de busca e carregamento
@FXML
public void buscarMotorista(ActionEvent event){
    String idText = txtNome.getText(); // Usando txtNome para entrada do ID
    try {
        int id = Integer.parseInt(idText);
        MotoristaDAO dao = new MotoristaDAO();
        dao.buscarMotoristaPorId(id);
    } catch (NumberFormatException e) {
        System.out.println("ID inválido. Por favor, insira um número inteiro.");
    }
}

public void initialize(){
        cbxVinculo.getItems().addAll("aluno", "servidor", "terceirizado");

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        colVinculo.setCellValueFactory(new PropertyValueFactory<>("vinculo"));

        carregarTabela();
        tblMotoristas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                motoristaSelecionado = newSelection;
                txtNome.setText(newSelection.getNome());
                txtCpf.setText(newSelection.getCpf());
                txtMatricula.setText(newSelection.getMatricula());
                cbxVinculo.setValue(newSelection.getVinculo());
            }
        });

}

private void carregarTabela() {

    MotoristaDAO dao = new MotoristaDAO();

    ObservableList<Motorista> dados =
            FXCollections.observableArrayList(
                    dao.listarMotoristas());

    tblMotoristas.setItems(dados);
}

}
