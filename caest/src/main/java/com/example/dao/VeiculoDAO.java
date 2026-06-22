package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Motorista;
import com.example.model.Veiculo;
import com.example.util.ConnectionFactory;

import javafx.scene.control.Alert;

public class VeiculoDAO {
   public int inserirVeiculo(Veiculo veiculo){
        String sql = "INSERT INTO veiculo (placa, modelo, cor, idMotorista) VALUES (?, ?, ?, ?)";
        
        // Adicionamos o PreparedStatement.RETURN_GENERATED_KEYS aqui:
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setString(3, veiculo.getCor());
            if (veiculo.getMotorista() != null) {
                stmt.setInt(4, veiculo.getMotorista().getId());
            } else {
                stmt.setNull(4, java.sql.Types.INTEGER);
            };

            stmt.executeUpdate();

            // Pega o ID que o MySQL gerou e retorna ele
            try (java.sql.ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idGerado = generatedKeys.getInt(1);
                    System.out.println("Veículo inserido com sucesso! ID gerado: " + idGerado);
                    return idGerado;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Retorna -1 caso dê algum erro
    }

    public void deletarVeiculo(int id){
        String sql = "DELETE FROM veiculo WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.println("Veículo deletado com sucesso!");
        } catch (Exception e) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Erro");
        alert.setHeaderText("Não foi possível excluir o veículo");
        alert.setContentText("Este veículo está vinculado a uma liberação manual.");

        alert.showAndWait();
        }
    }

    public void atualizarVeiculo(Veiculo veiculo){
        String sql = "UPDATE veiculo SET placa = ?, modelo = ?, cor = ?, idMotorista = ? WHERE id =?";
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {  
                
            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setString(3, veiculo.getCor());
            if (veiculo.getMotorista() != null) {
                stmt.setInt(4, veiculo.getMotorista().getId());
            } else {
                stmt.setNull(4, java.sql.Types.INTEGER);
            }
            stmt.setInt(5, veiculo.getId());

            stmt.executeUpdate();

            System.out.println("Veículo atualizado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Veiculo> listarVeiculos(){
        String sql = "SELECT v.id, v.placa, v.modelo, v.cor, m.nome, m.id AS idMotorista FROM veiculo v LEFT JOIN motorista m ON v.idMotorista = m.id";
        List<Veiculo> veiculos = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            var rs = stmt.executeQuery();

            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                Motorista motorista = new Motorista();

                if (rs.getObject("idMotorista") != null) {
                motorista.setId(rs.getInt("idMotorista"));
                motorista.setNome(rs.getString("nome"));
                veiculo.setMotorista(motorista);}

                veiculo.setId(rs.getInt("id"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setCor(rs.getString("cor"));
                
                if (rs.getObject("idMotorista") != null) {
                motorista.setId(rs.getInt("idMotorista"));
                motorista.setNome(rs.getString("nome"));
                veiculo.setMotorista(motorista);
            }

                veiculos.add(veiculo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veiculos;
    }

    public Veiculo buscarVeiculoPorId(int id){
        String sql = "SELECT v.id, v.placa, v.modelo, v.cor, m.id AS idMotorista, m.nome FROM veiculo v LEFT JOIN motorista m ON v.idMotorista = m.id  WHERE v.id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            var rs = stmt.executeQuery();

            if (rs.next()) {
                Veiculo veiculo = new Veiculo();
                Motorista motorista = new Motorista();

                veiculo.setId(rs.getInt("id"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setMotorista(motorista);

                if (rs.getObject("idMotorista") != null) {

                motorista.setId(rs.getInt("idMotorista"));
                motorista.setNome(rs.getString("nome"));

                veiculo.setMotorista(motorista);
            }
                return veiculo;
            } else {
                System.out.println("Veículo não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();    
        }
        return null;
    }
}

