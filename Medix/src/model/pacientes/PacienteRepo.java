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
        }
        return null;
    }

    public boolean remove(String id) {
        boolean removed = pacientes.removeIf(p -> p.getId().equals(id));
        if (removed) salvar();
        return removed; 
    }

    private void salvar() {
        List<String[]> linhas = new ArrayList<>();
        for (Paciente p : pacientes) {
            linhas.add(new String[]{p.getId(), p.getNome(), p.getCpf(), String.valueOf(p.getIdade())});
        }
        CSV.write(Storage.PACIENTES, linhas);
    }

    private void carregar() {
        List<String[]> linhas = CSV.read(Storage.PACIENTES);
        for (String[] campos : linhas) {
            if (campos.length == 4) {
                Paciente p = new Paciente(campos[0], campos[1], campos[2], Integer.parseInt(campos[3]));
                pacientes.add(p);
            }
        }
    }
}
