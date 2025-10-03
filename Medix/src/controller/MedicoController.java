package controller;

import infra.IdSequence;
import java.util.List;
import model.medicos.Medico;
import model.medicos.MedicoRepo;

public class MedicoController {
    private final MedicoRepo repo;

    public MedicoController(MedicoRepo repo) {
        this.repo = repo;
    }

    public Medico cadastrar(String nome, String crm, String especialidade) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome não pode ser vazio.");
        if (crm == null || crm.isBlank()) throw new IllegalArgumentException("CRM não pode ser vazio.");
        if (especialidade == null || especialidade.isBlank()) throw new IllegalArgumentException("Especialidade não pode ser vazia.");

        String id = IdSequence.nextId("M");
        Medico m = new Medico(id, nome.trim(), crm.trim(), especialidade.trim());
        repo.add(m);
        return m;
    }

    public List<Medico> listarTodos() { return repo.findAll(); }

    public boolean removerPorId(String id) { return repo.remove(id); }

    public Medico buscarPorId(String id) { return repo.findById(id); }
}
