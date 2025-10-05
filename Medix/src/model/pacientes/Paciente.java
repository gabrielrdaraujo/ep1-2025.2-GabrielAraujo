package model.pacientes;

public class Paciente {
    private String id;
    private String nome;
    private String cpf;
    private int idade;
    private String planoSaudeId; 
    private boolean especial;
    
    public Paciente(String id, String nome, String cpf, int idade) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.planoSaudeId = null;
        this.especial = false;
    }

    public Paciente(String id, String nome, String cpf, int idade, String planoSaudeId, boolean especial) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.planoSaudeId = (planoSaudeId != null && !planoSaudeId.isEmpty()) ? planoSaudeId : null;
        this.especial = especial;
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

    public void setPlanoSaudeId(String planoSaudeId) {
        this.planoSaudeId = planoSaudeId;
    }

    public boolean isEspecial() {
        return especial;
    }

    public void setEspecial(boolean especial) {
        this.especial = especial;
    }

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