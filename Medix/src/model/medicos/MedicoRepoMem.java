package model.medicos;

import java.util.ArrayList;
import java.util.List;

public class MedicoRepoMem {
    private final List<Medico> medicos = new ArrayList<>();

    public void add(Medico m) {
        medicos.add(m);
    }

    public List<Medico> findAll() {
        return medicos;
    }

    public Medico findById(String id) {
        for (Medico m : medicos) {
            if (m.getId().equals(id)) {
                return m;
            }
        }
        return null;
    }

    public boolean remove(String id) {
        return medicos.removeIf(m -> m.getId().equals(id));
    }
}
