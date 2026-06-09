package com.example.app;

import com.example.model.Motorista;
import com.example.dao.MotoristaDAO;
import com.example.dao.VeiculoDAO;
import com.example.model.Veiculo;

public class Main {
    public static void main(String[] args) {
        Motorista motorista = new Motorista();
        motorista.setId(6);
        //motorista.setNome("Gabriel");
        //motorista.setCpf("22");
        //motorista.setMatricula("SL305067X");
        //motorista.setVinculo("Aluno");

        MotoristaDAO motoristaDAO = new MotoristaDAO();
        //motoristaDAO.inserirMotorista(motorista);
        //motoristaDAO.deletarMotorista(4);
        //motoristaDAO.atualizarMotorista(motorista);
        motoristaDAO.listarMotoristas();

        Veiculo veiculo = new Veiculo();
        veiculo.setId(2);
        //veiculo.setPlaca("ABC1233");
        //veiculo.setModelo("GRT Nissan");
        //veiculo.setCor("Preto");
        //veiculo.setMotorista(motorista);

        VeiculoDAO veiculoDAO = new VeiculoDAO();
        //veiculoDAO.inserirVeiculo(veiculo);
        //veiculoDAO.deletarVeiculo(1);
        //veiculoDAO.atualizarVeiculo(veiculo);
        veiculoDAO.listarVeiculos();
        
    }
}