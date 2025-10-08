package model.medicos;

public class Medico {
    // Atributos
    private String id;
    private String nome;
    private String crm;
    private String especialidade;
    private double valorConsulta;

    // Construtores

    // Construtor com valorConsulta padrão 100.0
    public Medico(String id, String nome, String crm, String especialidade) {
        this.id = id;
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
        this.valorConsulta = 100.0; 
    }

    // Construtor completo
    public Medico(String id, String nome, String crm, String especialidade, double valorConsulta) {
        this.id = id;
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
        this.valorConsulta = valorConsulta;
    }

    // Getters e Setters
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

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public double getValorConsulta() {
        return valorConsulta;
    }

    public void setValorConsulta(double valorConsulta) {
        this.valorConsulta = valorConsulta;
    }

    // Método toString 
    @Override
    public String toString() {
        return "Medico{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", crm='" + crm + '\'' +
                ", especialidade='" + especialidade + '\'' +
                " , valorConsulta=" + valorConsulta +
                '}';
    }
}