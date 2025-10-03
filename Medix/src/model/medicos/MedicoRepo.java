package model.medicos;

import infra.CSV;
import infra.Storage;

import java.util.ArrayList;
import java.util.List;

public class MedicoRepo {
    private final List<Medico> medicos = new ArrayList<>();

    public MedicoRepo() { carregar(); }

    public void add(Medico m) {
        medicos.add(m);
        salvar();
    }

    public List<Medico> findAll() {
        return medicos;
    }

    public Medico findById(String id) {
        for (Medico m : medicos) if (m.getId().equals(id)) return m;
        return null;
    }

    public boolean remove(String id) {
        boolean removed = medicos.removeIf(m -> m.getId().equals(id));
        if (removed) salvar();
        return removed;
    }

    private void salvar() {
        List<String[]> linhas = new ArrayList<>();
        for (Medico m : medicos) {
            linhas.add(new String[]{ m.getId(), m.getNome(), m.getCrm(), m.getEspecialidade() });
        }
        CSV.write(Storage.MEDICOS, linhas);
    }

    private void carregar() {
        List<String[]> linhas = CSV.read(Storage.MEDICOS);
        for (String[] c : linhas) {
            if (c.length == 4) {
                medicos.add(new Medico(c[0], c[1], c[2], c[3]));
            }
        }
    }
}
