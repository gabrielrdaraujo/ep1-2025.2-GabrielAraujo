package controller;

import infra.IdSequence;
import java.util.List;
import model.internacoes.Internacao;
import model.internacoes.InternacaoRepoMem;
import model.medicos.MedicoRepoMem;
import model.pacientes.Paciente;
import model.pacientes.PacienteRepo;
import model.pacientes.PacienteRepoMem;

public class InternacaoController {
    private final InternacaoRepoMem internacaoRepo;
    private final MedicoRepoMem     medicoRepo;

    private final PacienteRepoMem pacienteRepoMem;
    private final PacienteRepo    pacienteRepoCsv;

    public InternacaoController(InternacaoRepoMem internacaoRepo, PacienteRepoMem pacienteRepoMem, MedicoRepoMem medicoRepo) {
        this.internacaoRepo = internacaoRepo;
        this.pacienteRepoMem = pacienteRepoMem;
        this.pacienteRepoCsv = null;
        this.medicoRepo = medicoRepo;
    }

    public InternacaoController(InternacaoRepoMem internacaoRepo, PacienteRepo pacienteRepoCsv, MedicoRepoMem medicoRepo) {
        this.internacaoRepo = internacaoRepo;
        this.pacienteRepoMem = null;
        this.pacienteRepoCsv = pacienteRepoCsv;
        this.medicoRepo = medicoRepo;
    }

    private Paciente getPaciente(String id) {
        return (pacienteRepoMem != null) ? pacienteRepoMem.findById(id) : pacienteRepoCsv.findById(id);
    }

    public Internacao internar(String pacienteId, String medicoId, String quarto, String dataEntrada) {
        if (pacienteId == null || pacienteId.isBlank()) throw new IllegalArgumentException("Paciente inválido.");
        if (medicoId   == null || medicoId.isBlank())   throw new IllegalArgumentException("Médico inválido.");
        if (quarto     == null || quarto.isBlank())     throw new IllegalArgumentException("Quarto inválido.");
        if (dataEntrada== null || dataEntrada.isBlank())throw new IllegalArgumentException("Data de entrada inválida.");

        if (getPaciente(pacienteId) == null)
            throw new IllegalArgumentException("Paciente não encontrado: " + pacienteId);
        if (medicoRepo.findById(medicoId) == null)
            throw new IllegalArgumentException("Médico não encontrado: " + medicoId);

        if (internacaoRepo.quartoOcupado(quarto))
            throw new IllegalStateException("Quarto já está ocupado por outra internação ativa.");

        String id = IdSequence.nextId("I");
        Internacao i = new Internacao(id, pacienteId, medicoId, quarto.trim(), dataEntrada.trim(), null);
        internacaoRepo.add(i);
        return i;
    }

    public void darAlta(String internacaoId, String dataSaida) {
        if (internacaoId == null || internacaoId.isBlank()) throw new IllegalArgumentException("ID inválido.");
        if (dataSaida    == null || dataSaida.isBlank())    throw new IllegalArgumentException("Data de saída inválida.");

        Internacao i = internacaoRepo.findById(internacaoId);
        if (i == null)          throw new IllegalArgumentException("Internação não encontrada: " + internacaoId);
        if (!i.estaAtiva())     throw new IllegalStateException("Esta internação já possui alta.");

        i.setDataSaida(dataSaida.trim());
    }

    public List<Internacao> listarAtivas() { return internacaoRepo.listarAtivas(); }
    public List<Internacao> listarTodas()  { return internacaoRepo.findAll(); }
}
