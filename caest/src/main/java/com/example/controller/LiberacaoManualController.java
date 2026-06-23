package com.example.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import com.example.dao.LiberacaoManualDAO;
import com.example.dao.VeiculoDAO;
import com.example.model.LiberacaoManual;
import com.example.model.Veiculo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LiberacaoManualController {

    @FXML
    private TextField txtPlaca;

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtCor;

    // --- NOMES ATUALIZADOS AQUI ---
    @FXML
    private TextField txtDocumento; 

    @FXML
    private TextField txtMotivo; 

    @FXML
    private AnchorPane paneMotorista; 

    // Variável para guardar o veículo entre um clique e outro
    private Veiculo veiculoSalvo = null;

    // --- SALVAR SÓ O VEÍCULO ---
    @FXML
    public void confirmarVeiculo(ActionEvent event) {
        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca(txtPlaca.getText());
        veiculo.setModelo(txtModelo.getText());
        veiculo.setCor(txtCor.getText());

        VeiculoDAO veiculoDAO = new VeiculoDAO();
        int idGerado = veiculoDAO.inserirVeiculo(veiculo);

        if (idGerado != -1) {
            veiculo.setId(idGerado);
            this.veiculoSalvo = veiculo; 
            
            // Pop-up de Sucesso!
            Alert aviso = new Alert(Alert.AlertType.INFORMATION);
            aviso.setTitle("Sucesso");
            aviso.setHeaderText(null);
            aviso.setContentText("Veículo inserido com sucesso! Preencha os dados do motorista.");
            aviso.showAndWait();

            // Mostra os campos do motorista
            paneMotorista.setVisible(true);

            // Trava os campos do carro
            txtPlaca.setDisable(true);
            txtModelo.setDisable(true);
            txtCor.setDisable(true);
        } else {
            System.out.println("Erro ao salvar o veículo.");
        }
    }

    // --- SALVAR A LIBERAÇÃO ---
    @FXML
    public void realizarLiberacao(ActionEvent event) {
        if (this.veiculoSalvo == null) {
            System.out.println("ERRO: Voce precisa confirmar o veiculo primeiro!");
            return; 
        }

        LiberacaoManual liberacao = new LiberacaoManual();
        
        // Puxando os dados dos campos com os nomes novos
        liberacao.setDocMotorista(txtDocumento.getText());
        liberacao.setMotivo(txtMotivo.getText());
        
        liberacao.setDataHora(LocalDateTime.now());
        liberacao.setVeiculo(this.veiculoSalvo); 

        LiberacaoManualDAO liberacaoDAO = new LiberacaoManualDAO();
        liberacaoDAO.inserirLiberacaoManual(liberacao);

        System.out.println("Liberação manual registrada com sucesso!");
        limparCampos(null); 
    }

    // --- LIMPAR TUDO ---
    @FXML
    public void limparCampos(ActionEvent event) {
        // Limpa os textos digitados
        txtPlaca.clear();
        txtModelo.clear();
        txtCor.clear();
        txtDocumento.clear(); // Limpando com o nome novo
        txtMotivo.clear();    // Limpando com o nome novo
        
        // Destrava os campos do veiculo para um novo cadastro
        txtPlaca.setDisable(false);
        txtModelo.setDisable(false);
        txtCor.setDisable(false);
        
        // Esconde os campos de motorista novamente
        if (paneMotorista != null) {
            paneMotorista.setVisible(false);
        }
        
        // Limpar o veiculo da memoria
        this.veiculoSalvo = null; 
    }

    // --- VOLTAR ---
    @FXML
    public void voltarHome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/HomeView.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}