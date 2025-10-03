package controller;

import infra.IdSequence;
import model.planos.PlanoSaude;
import model.planos.PlanoSaudeRepo;

import java.util.List;

public class PlanoSaudeController {
    private final PlanoSaudeRepo repo;
    public PlanoSaudeController(PlanoSaudeRepo repo) { this.repo = repo; }

    public PlanoSaude cadastrar(String nome, double descConsulta, double descInternacao) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome vazio.");
        }

        if (descConsulta < 0 || descConsulta > 100) {
            throw new IllegalArgumentException("Desconto consulta inválido.");
        }

        if (descInternacao < 0 || descInternacao > 100) {
            throw new IllegalArgumentException("Desconto internação inválido.");
        }

        String id = IdSequence.nextId("PS");
        PlanoSaude p = new PlanoSaude(id, nome.trim(), descConsulta, descInternacao);
        repo.add(p);
        return p;
    }

    public List<PlanoSaude> listar() { return repo.findAll(); }
    public PlanoSaude buscar(String id) { return repo.findById(id); }
}
