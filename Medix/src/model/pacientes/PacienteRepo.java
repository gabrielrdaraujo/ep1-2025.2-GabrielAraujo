package model.pacientes;

import infra.CSV;
import infra.Storage;
import java.util.ArrayList;
import java.util.List;

public class PacienteRepo {
    private final List<Paciente> pacientes = new ArrayList<>();

    public PacienteRepo() {
        carregar();
    }

    public void add(Paciente p) {
        pacientes.add(p);
        salvar();
    }

    public List<Paciente> findAll() {
        return pacientes;
    }

    public Paciente findById(String id) {
        for (Paciente p : pacientes) {
            if (p.getId().equals(id)) return p;
        } return null;
    }

    public boolean remove(String id) {
        boolean removed = pacientes.removeIf(p -> p.getId().equals(id));
        if (removed) {
            salvar();
        } return removed; 
    }

    private void salvar() {
        List<String[]> linhas = new ArrayList<>();
        for (Paciente p : pacientes) {
            if (p != null) {
                String tipo = (p instanceof PacienteEspecial || p.isEspecial()) ? "E" : "N";
                String obs = (p instanceof PacienteEspecial pe) ? pe.getObservacao() : "";
                String planoId = (p.getPlanoSaudeId() != null) ? p.getPlanoSaudeId() : "";

                linhas.add(new String[]{
                        p.getId(), p.getNome(), p.getCpf(), String.valueOf(p.getIdade()),
                        planoId, tipo, obs
                });
            }
        }
        CSV.write(Storage.PACIENTES, linhas);
    }

    private void carregar() {
        pacientes.clear();
        for (String[] c : CSV.read(Storage.PACIENTES)) {
            try {
                if (c.length >= 7) {
                    String id=c[0], nome=c[1], cpf=c[2];
                    int idade=Integer.parseInt(c[3]);
                    String planoId = c[4].isBlank()? null : c[4];
                    String tipo=c[5]; String obs=c[6];
                    if ("E".equalsIgnoreCase(tipo)) {
                        pacientes.add(new PacienteEspecial(id,nome,cpf,idade,planoId,obs));
                    } else {
                        pacientes.add(new Paciente(id,nome,cpf,idade,planoId,false));
                    }
                } else if (c.length == 5) {
                    pacientes.add(new Paciente(c[0], c[1], c[2], Integer.parseInt(c[3]),
                                               c[4].isBlank()? null:c[4], false));
                } else if (c.length == 4) {
                    pacientes.add(new Paciente(c[0], c[1], c[2], Integer.parseInt(c[3])));
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) {}
        }
    }
}
