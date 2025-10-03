package controller;

import infra.IdSequence;
import java.util.List;
import model.pacientes.Paciente;
import model.pacientes.PacienteRepo;
import model.pacientes.PacienteRepoMem;

public class PacienteController {

    private final PacienteRepoMem repoMem;
    private final PacienteRepo    repoCsv;

    public PacienteController(PacienteRepoMem repoMem) {
        this.repoMem = repoMem;
        this.repoCsv = null;
    }

    public PacienteController(PacienteRepo repoCsv) {
        this.repoCsv = repoCsv;
        this.repoMem = null;
    }

    public Paciente cadastrar(String nome, String cpf, int idade) {
        if (nome == null || nome.isBlank())  throw new IllegalArgumentException("Nome não pode ser vazio.");
        if (cpf  == null || cpf.isBlank())   throw new IllegalArgumentException("CPF não pode ser vazio.");
        if (idade < 0 || idade > 120)        throw new IllegalArgumentException("Idade inválida.");

        String id = IdSequence.nextId("P");
        Paciente p = new Paciente(id, nome.trim(), cpf.trim(), idade);

        if (repoMem != null) repoMem.add(p); else repoCsv.add(p);
        return p;
    }

    public List<Paciente> listarTodos() {
        return (repoMem != null) ? repoMem.findAll() : repoCsv.findAll();
    }

    public boolean removerPorId(String id) {
        if (repoMem != null) {
            repoMem.remove(id);
            return repoMem.findById(id) == null;
        } else {
            return repoCsv.remove(id);
        }
    }


    public Paciente buscarPorId(String id) {
        return (repoMem != null) ? repoMem.findById(id) : repoCsv.findById(id);
    }
}
