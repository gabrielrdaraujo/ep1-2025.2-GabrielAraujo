package model.internacoes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class Internacao {
    private final String id;
    private final String pacienteId;
    private final String medicoId;
    private String quarto;
    private String dataEntrada;
    private String dataSaida;
    private double custoDia;

    public static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Internacao(String id, String pacienteId, String medicoId, 
    String quarto, String dataEntrada, String dataSaida, double custoDia) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.quarto = quarto;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.custoDia = custoDia;
    }

    public Internacao(String id, String pacienteId, String medicoId, 
    String quarto, String dataEntrada, String dataSaida) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.quarto = quarto;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.custoDia = 0.0;
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

    public void setQuarto(String quarto) {
        this.quarto = quarto;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
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

    public double getCustoDia() {
        return custoDia;
    }

    public void setCustoDia(double custoDia) {
        this.custoDia = custoDia;
    }

    public long diasInternado() {
        LocalDate entrada = LocalDate.parse(dataEntrada, FMT);
        LocalDate saida = estaAtiva() ? LocalDate.now() : LocalDate.parse(dataSaida, FMT);
        long dias = ChronoUnit.DAYS.between(entrada, saida);
        return Math.max(dias, 1);
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
                ", custoDia=" + custoDia +
                '}';
    }
}
