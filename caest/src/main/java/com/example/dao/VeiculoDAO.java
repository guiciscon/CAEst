package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.model.Veiculo;
import com.example.util.ConnectionFactory;

public class VeiculoDAO {
   public void inserirVeiculo(Veiculo veiculo){
        String sql = "INSERT INTO veiculo (placa, modelo, cor, idMotorista) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setString(3, veiculo.getCor());
            stmt.setInt(4, veiculo.getMotorista().getId());

            stmt.executeUpdate();

            System.out.println("Veículo inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarVeiculo(int id){
        String sql = "DELETE FROM veiculo WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.println("Veículo deletado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }

    public void atualizarVeiculo(Veiculo veiculo){
        String sql = "UPDATE veiculo SET placa = ?, modelo = ?, cor = ?, motorista = ? WHERE id =?";
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {  
                
            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setString(3, veiculo.getCor());
            stmt.setInt(4, veiculo.getMotorista().getId());
            stmt.setInt(5, veiculo.getId());

            stmt.executeUpdate();

            System.out.println("Veículo atualizado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listarVeiculos(){
        String sql = "SELECT v.id, v.placa, v.modelo, v.cor, m.nome FROM veiculo v JOIN motorista m ON v.idMotorista = m.id";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            var rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Placa: " + rs.getString("placa"));
                System.out.println("Modelo: " + rs.getString("modelo"));
                System.out.println("Cor: " + rs.getString("cor"));
                System.out.println("Motorista: " + rs.getString("nome"));
                System.out.println("---------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void buscarVeiculoPorId(int id){
        String sql = "SELECT v.id, v.placa, v.modelo, v.cor, m.nome FROM veiculo v JOIN motorista m ON v.idMotorista = m.id WHERE v.id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            var rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Placa: " + rs.getString("placa"));
                System.out.println("Modelo: " + rs.getString("modelo"));
                System.out.println("Cor: " + rs.getString("cor"));
                System.out.println("Motorista: " + rs.getString("nome"));
            } else {
                System.out.println("Veículo não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();    
        }
    }
}

