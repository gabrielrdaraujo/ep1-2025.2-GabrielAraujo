package controller;

import infra.IdSequence;
import model.consultas.Consulta;
import model.consultas.ConsultaRepo;     
import model.consultas.ConsultaRepoMem; 
import model.medicos.MedicoRepoMem;
import model.medicos.MedicoRepo;        
import model.pacientes.Paciente;
import model.pacientes.PacienteRepo;
import model.pacientes.PacienteRepoMem;

import java.util.List;

public class ConsultaController {
    private final ConsultaRepoMem consultaRepoMem;
    private final ConsultaRepo    consultaRepoCsv;

    private final PacienteRepoMem pacienteRepoMem;
    private final PacienteRepo    pacienteRepoCsv;

    private final MedicoRepoMem medicoRepoMem;
    private final MedicoRepo    medicoRepoCsv; 

    public ConsultaController(ConsultaRepoMem consultaRepo, PacienteRepoMem pacienteRepo, MedicoRepoMem medicoRepo) {
        this.consultaRepoMem = consultaRepo; this.consultaRepoCsv = null;
        this.pacienteRepoMem = pacienteRepo; this.pacienteRepoCsv = null;
        this.medicoRepoMem   = medicoRepo;   this.medicoRepoCsv   = null;
    }

    public ConsultaController(ConsultaRepo consultaRepo, PacienteRepo pacienteRepo, MedicoRepoMem medicoRepo) {
        this.consultaRepoMem = null;         this.consultaRepoCsv = consultaRepo;
        this.pacienteRepoMem = null;         this.pacienteRepoCsv = pacienteRepo;
        this.medicoRepoMem   = medicoRepo;   this.medicoRepoCsv   = null;
    }

    private Paciente getPaciente(String id) {
        return (pacienteRepoMem != null) ? pacienteRepoMem.findById(id) : pacienteRepoCsv.findById(id);
    }

    private boolean medicoExiste(String id) {
        if (medicoRepoMem != null)  return medicoRepoMem.findById(id) != null;
        if (medicoRepoCsv != null)  return medicoRepoCsv.findById(id) != null; 
        return false;
    }

    private boolean conflitoMedicoHorario(String medicoId, String dataHora) {
        if (consultaRepoMem != null) return consultaRepoMem.existeConflitoMedicoHorario(medicoId, dataHora);
        return consultaRepoCsv.existeConflitoMedicoHorario(medicoId, dataHora);
    }

    private boolean conflitoLocalHorario(String local, String dataHora) {
        if (consultaRepoMem != null) return consultaRepoMem.existeConflitoLocalHorario(local, dataHora);
        return consultaRepoCsv.existeConflitoLocalHorario(local, dataHora);
    }

    private void addConsulta(Consulta c) {
        if (consultaRepoMem != null) consultaRepoMem.add(c);
        else                         consultaRepoCsv.add(c);
    }

    public Consulta agendar(String pacienteId, String medicoId, String dataHora, String local) {
        if (pacienteId == null || pacienteId.isBlank()) {
            throw new IllegalArgumentException("Paciente inválido.");
        }
        if (medicoId   == null || medicoId.isBlank()) {
            throw new IllegalArgumentException("Médico inválido.");
        }
        if (dataHora   == null || dataHora.isBlank()) {
            throw new IllegalArgumentException("Data/Hora inválida.");
        }
        if (local      == null || local.isBlank()) {
            throw new IllegalArgumentException("Local inválido.");
        }

        if (getPaciente(pacienteId) == null) {
            throw new IllegalArgumentException("Paciente não encontrado: " + pacienteId);
        }
        if (!medicoExiste(medicoId)) {
            throw new IllegalArgumentException("Médico não encontrado: " + medicoId);
        }

        if (conflitoMedicoHorario(medicoId, dataHora)) {
            throw new IllegalStateException("Conflito: este médico já possui consulta nesse horário.");
        }
        if (conflitoLocalHorario(local, dataHora)) {
            throw new IllegalStateException("Conflito: já há consulta agendada neste local e horário.");
        }

        String id = IdSequence.nextId("C");
        Consulta c = new Consulta(id, pacienteId, medicoId, dataHora.trim(), local.trim(), "AGENDADA");
        addConsulta(c);
        return c;
    }

    public List<Consulta> listarTodas() {
        return (consultaRepoMem != null) ? consultaRepoMem.findAll() : consultaRepoCsv.findAll();
    }
}
