package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.consultas.Consulta;
import model.consultas.ConsultaRepo;
import model.medicos.Medico;
import model.medicos.MedicoRepo;
import model.pacientes.Paciente;
import model.pacientes.PacienteRepo;
import model.planos.PlanoSaude;
import model.planos.PlanoSaudeRepo;

public class RelatorioController {
    private final ConsultaRepo consultaRepo;
    private final PacienteRepo pacienteRepo;
    private final MedicoRepo   medicoRepo;
    private final PlanoSaudeRepo planoRepo;

    public RelatorioController(ConsultaRepo c, PacienteRepo p, MedicoRepo m, PlanoSaudeRepo pl) {
        this.consultaRepo = c; 
        this.pacienteRepo = p; 
        this.medicoRepo = m; 
        this.planoRepo = pl;
    }

    public int totalConsultas() { return consultaRepo.findAll().size(); }

    public double faturamentoEstimado() {
        double total = 0.0;
        List<Consulta> consultas = consultaRepo.findAll();

        for (Consulta c : consultas) {
            Medico med = medicoRepo.findById(c.getMedicoId());
            if (med == null) continue;
            double preco = med.getValorConsulta();

            Paciente pac = pacienteRepo.findById(c.getPacienteId());
            double fator = 1.0;
            if (pac != null && pac.getPlanoSaudeId() != null) {
                PlanoSaude pl = planoRepo.findById(pac.getPlanoSaudeId());
                if (pl != null) fator = 1.0 - (pl.getDescontoConsulta() / 100.0);
            }
            total += preco * fator;
        }
        return total;
    }

    public Map<String, Integer> consultasPorMedico() {
        Map<String,Integer> mapa = new HashMap<>();
        for (Consulta c : consultaRepo.findAll()) {
            mapa.merge(c.getMedicoId(), 1, Integer::sum);
        }
        return mapa;
    }
}
