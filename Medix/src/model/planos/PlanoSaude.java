package model.planos;

public class PlanoSaude {
    private String id;
    private String nome;
    private double descontoConsulta;
    private double descontoInternacao;

    public PlanoSaude(String id, String nome, double descontoConsulta, double descontoInternacao) {
        this.id = id;
        this.nome = nome;
        this.descontoConsulta = descontoConsulta;
        this.descontoInternacao = descontoInternacao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "PlanoSaude{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", descontoConsulta=" + descontoConsulta +
                ", descontoInternacao=" + descontoInternacao +
                '}';
    }
}
