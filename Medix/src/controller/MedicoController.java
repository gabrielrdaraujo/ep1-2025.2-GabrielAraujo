package controller;

import infra.IdSequence;
import java.util.List;
import model.medicos.Medico;
import model.medicos.MedicoRepo;
import model.medicos.MedicoRepoMem;

public class MedicoController {
    private final MedicoRepoMem repoMem;
    private final MedicoRepo    repoCsv;

    public MedicoController(MedicoRepoMem repoMem) {
        this.repoMem = repoMem;
        this.repoCsv = null;
    }
    public MedicoController(MedicoRepo repoCsv) {
        this.repoCsv = repoCsv;
        this.repoMem = null;
    }

    public Medico cadastrar(String nome, String crm, String especialidade) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome não pode ser vazio.");
        if (crm == null || crm.isBlank()) throw new IllegalArgumentException("CRM não pode ser vazio.");
        if (especialidade == null || especialidade.isBlank()) throw new IllegalArgumentException("Especialidade não pode ser vazia.");

        String id = IdSequence.nextId("M");
        Medico m = new Medico(id, nome.trim(), crm.trim(), especialidade.trim());
        if (repoMem != null) repoMem.add(m); else repoCsv.add(m);
        return m;
    }

    public List<Medico> listarTodos() {
        return (repoMem != null) ? repoMem.findAll() : repoCsv.findAll();
    }

    public boolean removerPorId(String id) {
        return (repoMem != null) ? repoMem.remove(id) : repoCsv.remove(id);
    }

    public Medico buscarPorId(String id) {
        return (repoMem != null) ? repoMem.findById(id) : repoCsv.findById(id);
    }
}
