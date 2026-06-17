package com.example.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.model.LiberacaoManual;
import com.example.util.ConnectionFactory;

public class LiberacaoManualDAO {

    public void inserirLiberacaoManual(LiberacaoManual liberacao){
        String sql = "INSERT INTO LiberacaoManual (docMotorista, motivo, dataHora, idVeiculo) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, liberacao.getDocMotorista());
            stmt.setString(2, liberacao.getMotivo());
            stmt.setObject(3, liberacao.getDataHora());
            stmt.setInt(4, liberacao.getVeiculo().getId());
            stmt.executeUpdate();

            System.out.println("Liberação manual registrada!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    



}
