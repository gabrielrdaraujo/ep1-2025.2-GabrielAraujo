package model.pacientes;

import java.util.ArrayList;
import java.util.List;

public class PacienteRepoMem {
    private List<Paciente> pacientes = new ArrayList<>();

    public void add(Paciente p) { pacientes.add(p); }

    public List<Paciente> findAll() { return pacientes; }

    public Paciente findById(String id) {
        for (Paciente p : pacientes) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public void remove(String id) {
        pacientes.removeIf(p -> p.getId().equals(id));
    }
}