package controller;

import infra.IdSequence;
import java.util.List;
import model.pacientes.Paciente;
import model.pacientes.PacienteRepo;

public class PacienteController {
    private final PacienteRepo repo;

    public PacienteController(PacienteRepo repo) {
        this.repo = repo;
    }

    public Paciente cadastrar(String nome, String cpf, int idade) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF não pode ser vazio.");
        }
        if (idade < 0 || idade > 120) {
            throw new IllegalArgumentException("Idade inválida.");
        }

        String id = IdSequence.nextId("P");
        Paciente p = new Paciente(id, nome.trim(), cpf.trim(), idade);
        repo.add(p);
        return p;
    }

    public Paciente cadastrar(String nome, String cpf, int idade, String planoSaudeId, boolean especial) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF não pode ser vazio.");
        }
        if (idade < 0 || idade > 120) {
            throw new IllegalArgumentException("Idade inválida.");
        }

        String id = IdSequence.nextId("P");
        Paciente p = new Paciente(id, nome.trim(), cpf.trim(), idade, planoSaudeId, especial);
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
