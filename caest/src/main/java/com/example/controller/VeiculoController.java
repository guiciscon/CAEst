package com.example.controller;

import java.io.IOException;

import com.example.dao.MotoristaDAO;
import com.example.dao.VeiculoDAO;
import com.example.model.Motorista;
import com.example.model.Veiculo;

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

public class VeiculoController {
private Veiculo veiculoSelecionado;
@FXML
private TextField txtPlaca;
@FXML 
private TextField txtModelo;
@FXML
private TextField txtCor;
@FXML
private ComboBox<String> cbxIdMotorista; 
@FXML
private TableView<Motorista> tblMotoristas;
    @FXML
    private TableColumn<Motorista, Integer> colId;
    @FXML
    private TableColumn<Motorista, String> colNome;
    @FXML
    private TableColumn<Motorista, String> colMatricula;
@FXML
private TableView<Veiculo> tblVeiculos;
    @FXML
    private TableColumn<Veiculo, Integer> colIdV;
    @FXML
    private TableColumn<Veiculo, String> colPlaca;
    @FXML
    private TableColumn<Veiculo, String> colModelo;
    @FXML
    private TableColumn<Veiculo, String> colCor;
    @FXML
    private TableColumn<Veiculo, String> colMotorista;

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
public void salvarVeiculo(ActionEvent event) {
        Veiculo veiculo = new Veiculo();

        veiculo.setPlaca(txtPlaca.getText());
        veiculo.setModelo(txtModelo.getText());
        veiculo.setCor(txtCor.getText());
        try {
            int motoristaId = Integer.parseInt(cbxIdMotorista.getValue());
            Motorista motorista = new Motorista();
            motorista.setId(motoristaId);
            veiculo.setMotorista(motorista);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        VeiculoDAO veiculoDAO = new VeiculoDAO();
        veiculoDAO.inserirVeiculo(veiculo);
        carregarVeiculos();
}

@FXML
public void atualizarVeiculo(ActionEvent event){
    Veiculo veiculo = new Veiculo();
    try { if(veiculoSelecionado == null) {
        System.out.println("Nenhum veículo selecionado para atualização.");
        return;
    }
        veiculo.setId(veiculoSelecionado.getId());
        veiculo.setPlaca(txtPlaca.getText());
        veiculo.setModelo(txtModelo.getText());
        veiculo.setCor(txtCor.getText());
        int motoristaId = Integer.parseInt(cbxIdMotorista.getValue());
        Motorista motorista = new Motorista();
        motorista.setId(motoristaId);
        veiculo.setMotorista(motorista);

        VeiculoDAO dao = new VeiculoDAO();
        dao.atualizarVeiculo(veiculo);
        carregarVeiculos();
    } catch (NumberFormatException e) {
        System.out.println("ID do motorista inválido. Por favor, insira um número inteiro.");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
@FXML
public void limparCampos(ActionEvent event) {
        txtPlaca.clear();
        txtModelo.clear();
        txtCor.clear();
        cbxIdMotorista.getSelectionModel().clearSelection();}

@FXML void excluirVeiculo(ActionEvent event){
        try { if(veiculoSelecionado == null){
            System.out.println("Nenhum veículo selecionado para exclusão.");
            return;
        }
            VeiculoDAO dao = new VeiculoDAO();
            dao.deletarVeiculo(veiculoSelecionado.getId());
            carregarVeiculos();
         } catch (NumberFormatException e) {
        System.out.println("ID do motorista inválido. Por favor, insira um número inteiro.");
        } catch (Exception e) {
            e.printStackTrace();
        }
}


//funções de busca e carregamento
@FXML
private void carregarVeiculos() {
    VeiculoDAO veiculoDAO = new VeiculoDAO();

    ObservableList<Veiculo> dados =
        FXCollections.observableArrayList(
            veiculoDAO.listarVeiculos());

    tblVeiculos.setItems(dados);
}

@FXML
private void carregarMotoristas() {
    MotoristaDAO motoristaDAO = new MotoristaDAO();

    ObservableList<Motorista> dados =
        FXCollections.observableArrayList(
            motoristaDAO.listarMotoristas());

    tblMotoristas.setItems(dados);
}

@FXML
public void initialize(){
    colId.setCellValueFactory(new PropertyValueFactory<>("id"));
    colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));

    colIdV.setCellValueFactory(new PropertyValueFactory<>("id"));
    colPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
    colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
    colCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
    colMotorista.setCellValueFactory(new PropertyValueFactory<>("nomeMotorista"));
        carregarMotoristas();
        carregarVeiculos();

        MotoristaDAO motoristaDAO = new MotoristaDAO();
        
        for (Motorista motorista : motoristaDAO.listarMotoristas()){
            cbxIdMotorista.getItems().add(String.valueOf(motorista.getId()));
        }
}

}

