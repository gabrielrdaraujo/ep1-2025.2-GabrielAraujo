package controller;

import java.util.List;
import model.pacientes.Paciente;
import model.pacientes.PacienteRepo;

public class PacienteController {
    private final PacienteRepo repo;

    public PacienteController(PacienteRepo repo) {
        this.repo = repo;
    }

    public Paciente cadastrar(String nome, String cpf, int idade, String planoSaudeId, boolean especial, String observacao) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }

        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF não pode ser vazio.");
        }
        
        if (idade < 0 || idade > 120) {
            throw new IllegalArgumentException("Idade inválida.");
        }

        String id = infra.IdSequence.nextId("P");

        model.pacientes.Paciente p;
        if (especial) {
            p = new model.pacientes.PacienteEspecial(id, nome.trim(), cpf.trim(), idade, planoSaudeId, 
            observacao == null ? "" : observacao.trim());
        } else {
            p = new model.pacientes.Paciente(id, nome.trim(), cpf.trim(), idade, planoSaudeId, false);
        }

        repo.add(p);
        return p;
    }

    public List<Paciente> listarTodos() { 
        return repo.findAll(); 
    }

    public boolean removerPorId(String id) { 
        return repo.remove(id); 
    }

    public Paciente buscarPorId(String id) { 
        return repo.findById(id); 
    }
}
