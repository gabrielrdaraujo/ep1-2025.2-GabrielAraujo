package model.planos;

public class PlanoSaude {
    private final String id;
    private String nome;
    private double descontoConsulta;
    private double descontoInternacao;
    private double descontoConsultaIdoso; // Desconto especial para idosos (60+)
    private boolean internacaoCurtaGratuita; // Internações menores que 7 dias são gratuitas

    // Construtores

    // Construtor completo
    public PlanoSaude(String id, String nome, double descontoConsulta, 
    double descontoInternacao, double descontoConsultaIdoso, boolean internacaoCurtaGratuita) {
        this.id = id;
        this.nome = nome;
        this.descontoConsulta = descontoConsulta;
        this.descontoInternacao = descontoInternacao;
        this.descontoConsultaIdoso = descontoConsultaIdoso;
        this.internacaoCurtaGratuita = internacaoCurtaGratuita;
    }

    // Construtor sem desconto para idosos e sem internação gratuita
    public PlanoSaude(String id, String nome, double descontoConsulta, double descontoInternacao) { 
        this.id = id;
        this.nome = nome;
        this.descontoConsulta = descontoConsulta;
        this.descontoInternacao = descontoInternacao;
        this.descontoConsultaIdoso = 0.0;
        this.internacaoCurtaGratuita = false;
    }


    // Getters e Setters
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getDescontoConsulta() {
        return descontoConsulta;
    }

    public void setDescontoConsulta(double descontoConsulta) {
        this.descontoConsulta = descontoConsulta;
    }

    public double getDescontoInternacao() {
        return descontoInternacao;
    }

    public void setDescontoInternacao(double descontoInternacao) {
        this.descontoInternacao = descontoInternacao;
    }

    public double getDescontoConsultaIdoso() {
        return descontoConsultaIdoso;
    }

    public void setDescontoConsultaIdoso(double descontoConsultaIdoso) {
        this.descontoConsultaIdoso = descontoConsultaIdoso;
    }

    public boolean isInternacaoCurtaGratuita() {
        return internacaoCurtaGratuita;
    }

    public void setInternacaoCurtaGratuita(boolean internacaoCurtaGratuita) {
        this.internacaoCurtaGratuita = internacaoCurtaGratuita;
    }

    // Método toString
    @Override
    public String toString() {
        return "PlanoSaude [id=" + id + ", nome=" + nome + ", descontoConsulta=" + descontoConsulta
                + ", descontoInternacao=" + descontoInternacao + ", descontoConsultaIdoso=" + descontoConsultaIdoso
                + ", internacaoCurtaGratuita=" + internacaoCurtaGratuita + "]";
    }
}
