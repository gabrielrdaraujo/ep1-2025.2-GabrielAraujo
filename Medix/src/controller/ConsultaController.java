package controller;

import infra.IdSequence;
import model.consultas.Consulta;
import model.consultas.ConsultaRepoMem;
import model.medicos.MedicoRepoMem;
import model.pacientes.Paciente;
import model.pacientes.PacienteRepo;
import model.pacientes.PacienteRepoMem;

import java.util.List;

public class ConsultaController {
    private final ConsultaRepoMem consultaRepo;
    private final MedicoRepoMem  medicoRepo;

    private final PacienteRepoMem pacienteRepoMem;
    private final PacienteRepo    pacienteRepoCsv;

    public ConsultaController(ConsultaRepoMem consultaRepo, PacienteRepoMem pacienteRepoMem, MedicoRepoMem medicoRepo) {
        this.consultaRepo = consultaRepo;
        this.pacienteRepoMem = pacienteRepoMem;
        this.pacienteRepoCsv = null;
        this.medicoRepo = medicoRepo;
    }

    public ConsultaController(ConsultaRepoMem consultaRepo, PacienteRepo pacienteRepoCsv, MedicoRepoMem medicoRepo) {
        this.consultaRepo = consultaRepo;
        this.pacienteRepoMem = null;
        this.pacienteRepoCsv = pacienteRepoCsv;
        this.medicoRepo = medicoRepo;
    }

    private Paciente getPaciente(String id) {
        return (pacienteRepoMem != null) ? pacienteRepoMem.findById(id) : pacienteRepoCsv.findById(id);
    }

    public Consulta agendar(String pacienteId, String medicoId, String dataHora, String local) {
        if (pacienteId == null || pacienteId.isBlank()) throw new IllegalArgumentException("Paciente inválido.");
        if (medicoId   == null || medicoId.isBlank())   throw new IllegalArgumentException("Médico inválido.");
        if (dataHora   == null || dataHora.isBlank())   throw new IllegalArgumentException("Data/Hora inválida.");
        if (local      == null || local.isBlank())      throw new IllegalArgumentException("Local inválido.");

        if (getPaciente(pacienteId) == null)
            throw new IllegalArgumentException("Paciente não encontrado: " + pacienteId);
        if (medicoRepo.findById(medicoId) == null)
            throw new IllegalArgumentException("Médico não encontrado: " + medicoId);

        if (consultaRepo.existeConflitoMedicoHorario(medicoId, dataHora))
            throw new IllegalStateException("Conflito: este médico já possui consulta nesse horário.");
        if (consultaRepo.existeConflitoLocalHorario(local, dataHora))
            throw new IllegalStateException("Conflito: já há consulta agendada neste local e horário.");

        String id = IdSequence.nextId("C");
        Consulta c = new Consulta(id, pacienteId, medicoId, dataHora.trim(), local.trim(), "AGENDADA");
        consultaRepo.add(c);
        return c;
    }

    public List<Consulta> listarTodas() { return consultaRepo.findAll(); }
}
