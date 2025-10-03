package controller;

import infra.IdSequence;
import java.util.List;
import model.consultas.Consulta;
import model.consultas.ConsultaRepo;
import model.medicos.MedicoRepo;
import model.pacientes.PacienteRepo;

public class ConsultaController {
    private final ConsultaRepo consultaRepo;
    private final PacienteRepo pacienteRepo;
    private final MedicoRepo   medicoRepo;

    public ConsultaController(ConsultaRepo consultaRepo, PacienteRepo pacienteRepo, MedicoRepo medicoRepo) {
        this.consultaRepo = consultaRepo;
        this.pacienteRepo = pacienteRepo;
        this.medicoRepo   = medicoRepo;
    }

    public Consulta agendar(String pacienteId, String medicoId, String dataHora, String local) {
        if (pacienteId == null || pacienteId.isBlank()) throw new IllegalArgumentException("Paciente inválido.");
        if (medicoId   == null || medicoId.isBlank())   throw new IllegalArgumentException("Médico inválido.");
        if (dataHora   == null || dataHora.isBlank())   throw new IllegalArgumentException("Data/Hora inválida.");
        if (local      == null || local.isBlank())      throw new IllegalArgumentException("Local inválido.");

        if (pacienteRepo.findById(pacienteId) == null)
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
