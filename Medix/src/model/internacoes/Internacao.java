package model.internacoes;

public class Internacao {
    private final String id;
    private final String pacienteId;
    private final String medicoId;
    private final String quarto;
    private final String dataEntrada;
    private String dataSaida;

    public Internacao(String id, String pacienteId, String medicoId, 
    String quarto, String dataEntrada, String dataSaida) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.quarto = quarto;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
    }

    public String getId() {
        return id;
    }

    public String getPacienteId() {
        return pacienteId;
    }

    public String getMedicoId() {
        return medicoId;
    }

    public String getQuarto() {
        return quarto;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public String getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }

    public boolean estaAtiva() {
        return dataSaida == null || dataSaida.isEmpty();
    }

    @Override
    public String toString() {
        return "Internacao{" +
                "id='" + id + '\'' +
                ", pacienteId='" + pacienteId + '\'' +
                ", medicoId='" + medicoId + '\'' +
                ", quarto='" + quarto + '\'' +
                ", dataEntrada='" + dataEntrada + '\'' +
                ", dataSaida='" + dataSaida + '\'' +
                '}';
    }
}
