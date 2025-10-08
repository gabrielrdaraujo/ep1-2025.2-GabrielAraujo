package model.pacientes;

// PacienteEspecial herda de Paciente e adiciona o atributo observacao
public class PacienteEspecial extends Paciente {
    private String observacao;

    // Construtor que chama o construtor da superclasse e inicializa observacao
    public PacienteEspecial(String id, String nome, String cpf, int idade, String planoSaude, String observacao) {
        super(id, nome, cpf, idade, planoSaude, true);
        this.observacao = observacao;
    }

    // Getters e Setters
    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    // Método toString que inclui a observacao
    @Override public String toString() {
        return "PacienteEspecial{" + super.toString() + ", obs='" + observacao + "'}";
    }
}