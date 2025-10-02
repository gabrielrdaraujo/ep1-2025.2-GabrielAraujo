package controller;

import infra.IdSequence;
import model.consultas.Consulta;
import model.consultas.ConsultaRepoMem;
import model.medicos.MedicoRepoMem;
import model.pacientes.PacienteRepoMem;

import java.util.List;

public class ConsultaController {
    private ConsultaRepoMem consultaRepo;
    private PacienteRepoMem pacienteRepo;
    private MedicoRepoMem medicoRepo;

    public ConsultaController(ConsultaRepoMem consultaRepo, PacienteRepoMem pacienteRepo, MedicoRepoMem medicoRepo) {
        this.consultaRepo = consultaRepo;
        this.pacienteRepo = pacienteRepo;
        this.medicoRepo = medicoRepo;
    }

    public Consulta agendar(String pacienteId, String medicoId, String dataHora, String local) {
        if (pacienteId == null || pacienteId.trim().isEmpty()) {
            throw new IllegalArgumentException("Paciente inválido.");
        }

        if (medicoId == null || medicoId.trim().isEmpty()) {
            throw new IllegalArgumentException("Médico inválido.");
        }

        if (dataHora == null || dataHora.trim().isEmpty()) {
            throw new IllegalArgumentException("Data/Hora inválida.");
        }

        if (local == null || local.trim().isEmpty()) {
            throw new IllegalArgumentException("Local inválido.");
        }

        if (pacienteRepo.findById(pacienteId) == null) {
            throw new IllegalArgumentException("Paciente não encontrado: " + pacienteId);
        }

        if (medicoRepo.findById(medicoId) == null) {
            throw new IllegalArgumentException("Médico não encontrado: " + medicoId);
        }

        if (consultaRepo.existeConflitoMedicoHorario(medicoId, dataHora)) {
            throw new IllegalStateException("Conflito: este médico já possui consulta nesse horário.");
        }

        if (consultaRepo.existeConflitoLocalHorario(local, dataHora)) {
            throw new IllegalStateException("Conflito: já há consulta agendada neste local e horário.");
        }

        String id = IdSequence.nextId("C"); 
        
        Consulta c = new Consulta(id, pacienteId, medicoId, dataHora.trim(), local.trim(), "AGENDADA");
        consultaRepo.add(c);
        return c;
    }

    public List<Consulta> listarTodas() {
        return consultaRepo.findAll();
    }
}