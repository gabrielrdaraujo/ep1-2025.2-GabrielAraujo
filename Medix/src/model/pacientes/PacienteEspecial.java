package model.pacientes;

public class PacienteEspecial extends Paciente {
    private String planoSaude;

    public PacienteEspecial(String id, String nome, String cpf, int idade, String planoSaude) {
        super(id, nome, cpf, idade);
        this.planoSaude = planoSaude;
    }

    public String getPlanoSaude() {
        return planoSaude;
    }

    public void setPlanoSaude(String planoSaude) {
        this.planoSaude = planoSaude;
    }

    @Override
    public String toString() {
        return "PacienteEspecial{" +
                "planoSaude='" + planoSaude + '\'' +
                ", id='" + getId() + '\'' +
                ", nome='" + getNome() + '\'' +
                ", cpf='" + getCpf() + '\'' +
                ", idade=" + getIdade() +
                '}';
    }
}