package com.example.model;

public class LiberacaoManual {
    private int id;
    private String docMotorista;
    private String motivo;
    private java.time.LocalDateTime dataHora; 
    private Veiculo veiculo;
    
    public LiberacaoManual() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocMotorista() {
        return docMotorista;
    }

    public void setDocMotorista(String docMotorista) {
        this.docMotorista = docMotorista;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public java.time.LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(java.time.LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

}
