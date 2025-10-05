package model.pacientes;

public class PacienteEspecial extends Paciente {
    private String observacao;

    public PacienteEspecial(String id, String nome, String cpf, int idade, String planoSaude, String observacao) {
        super(id, nome, cpf, idade);
        this.observacao = observacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override public String toString() {
        return "PacienteEspecial{" + super.toString() + ", obs='" + observacao + "'}";
    }
}