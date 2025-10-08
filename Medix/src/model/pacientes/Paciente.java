package model.pacientes;

public class Paciente {
    // Atributos
    private final String id;
    private String nome;
    private String cpf;
    private int idade;
    private final String planoSaudeId; 
    private boolean especial;

    // Construtores
    
    // Construtor com plano de saúde vazio e especial padrão false
    public Paciente(String id, String nome, String cpf, int idade) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.planoSaudeId = null;
        this.especial = false;
    }

    // Construtor completo
    public Paciente(String id, String nome, String cpf, int idade, String planoSaudeId, boolean especial) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.planoSaudeId = (planoSaudeId != null && !planoSaudeId.isEmpty()) ? planoSaudeId : null; // Define como null se vazio
        this.especial = especial;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getPlanoSaudeId() {
        return planoSaudeId;
    }

    public boolean isEspecial() {
        return especial;
    }

    public void setEspecial(boolean especial) {
        this.especial = especial;
    }

    // Método toString
    @Override
    public String toString() {
        return "Paciente{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", idade=" + idade +
                ", planoSaudeId='" + planoSaudeId + '\'' +
                ", especial=" + especial +
                '}';
    }
}