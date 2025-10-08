package model.internacoes;

import java.time.LocalDate; // Classe LocalDate para manipulação com datas reais 
import java.time.format.DateTimeFormatter; // Classe DateTimeFormatter para formatar datas
import java.time.temporal.ChronoUnit; // Classe ChronoUnit para calcular a diferença entre datas


public class Internacao {
    // Atributos
    private final String id;
    private final String pacienteId;
    private final String medicoId;
    private String quarto;
    private String dataEntrada;
    private String dataSaida;
    private double custoDia;

    // Formato padrão para datas ("yyyy-MM-dd")
    public static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 

    // Construtores
    public Internacao(String id, String pacienteId, String medicoId, 
    String quarto, String dataEntrada, String dataSaida, double custoDia) { // Construtor completo
        this.id = id;
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.quarto = quarto;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.custoDia = custoDia;
    }

    public Internacao(String id, String pacienteId, String medicoId, 
    String quarto, String dataEntrada, String dataSaida) { // Com Custo diário padrão 0.0
        this.id = id;
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.quarto = quarto;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.custoDia = 0.0;
    }

    // Getters e Setters
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
        return dataSaida == null || dataSaida.isEmpty(); // Verifica se a internação está ativa (sem data de saída)
    }

    public double getCustoDia() {
        return custoDia;
    }

    public void setCustoDia(double custoDia) {
        this.custoDia = custoDia;
    }

    // Método para calcular o número de dias internado
    public long diasInternado() {
        LocalDate entrada = LocalDate.parse(dataEntrada, FMT);
        LocalDate saida = estaAtiva() ? LocalDate.now() : LocalDate.parse(dataSaida, FMT);
        long dias = ChronoUnit.DAYS.between(entrada, saida);
        return Math.max(dias, 1);
    }

    // Método toString
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
