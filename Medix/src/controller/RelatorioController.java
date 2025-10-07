package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import model.consultas.Consulta;
import model.consultas.ConsultaRepo;
import model.internacoes.Internacao;
import model.internacoes.InternacaoRepo;
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
    private final InternacaoRepo internacaoRepo;

    public RelatorioController(ConsultaRepo c, PacienteRepo p, MedicoRepo m, PlanoSaudeRepo pl, InternacaoRepo i) {
        this.consultaRepo = c;
        this.pacienteRepo = p;
        this.medicoRepo   = m;
        this.planoRepo    = pl;
        this.internacaoRepo = i;
    }

    public String agoraString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public List<Consulta> consultasFuturas() {
        String agora = agoraString();
        List<Consulta> out = new ArrayList<>();
        for (Consulta c : consultaRepo.findAll()) {
            if (c.getDataHora().compareTo(agora) > 0 && !"CANCELADA".equals(c.getStatus())) {
                out.add(c);
            }
        } return out;
    }

    public List<Consulta> consultasPassadas() {
        String agora = agoraString();
        List<Consulta> out = new ArrayList<>();
        for (Consulta c : consultaRepo.findAll()) {
            if (c.getDataHora().compareTo(agora) <= 0) {
                out.add(c);
            }
        } return out;
    }

    public List<Consulta> agendaMedicoFuturas(String medicoId) {
        String agora = agoraString();
        List<Consulta> out = new ArrayList<>();
        for (Consulta c : consultaRepo.findAll()) {
            if (c.getMedicoId().equals(medicoId) &&
                "AGENDADA".equals(c.getStatus()) &&
                c.getDataHora().compareTo(agora) > 0) {
                    out.add(c);
                }
        } return out;
    }

    public String medicoQueMaisAtendeu() {
        Map<String,Integer> contagem = new HashMap<>();
        for (Consulta c : consultaRepo.findAll()) {
            if ("CONCLUIDA".equals(c.getStatus()))
                contagem.merge(c.getMedicoId(), 1, Integer::sum);
        }
        
        String topId = null; int top = -1;
        for (var e : contagem.entrySet()) if (e.getValue() > top) { top = e.getValue(); topId = e.getKey(); }
        if (topId == null) return "sem dados";
        Medico m = medicoRepo.findById(topId);
        return m == null ? topId : (m.getNome() + " (" + m.getId() + ") - " + top + " consultas");
    }

    public String especialidadeMaisProcurada() {
        Map<String,Integer> contagem = new HashMap<>();
        for (Consulta c : consultaRepo.findAll()) {
            Medico m = medicoRepo.findById(c.getMedicoId());
            String esp = m == null ? "DESCONHECIDA" : m.getEspecialidade();
            contagem.merge(esp, 1, Integer::sum);
        }
        String top = null; int qt = -1;
        for (var e : contagem.entrySet()) if (e.getValue() > qt) { qt = e.getValue(); top = e.getKey(); }
        return top == null ? "sem dados" : top + " - " + qt + " consultas";
    }

    public List<String> pacientesDetalhados() {
        List<String> linhas = new ArrayList<>();
        for (Paciente p : pacienteRepo.findAll()) {
            linhas.add(p.toString());

            for (Consulta c : consultaRepo.findAll()) {
                if (c.getPacienteId().equals(p.getId())) {
                    linhas.add("  - " + c);
                }
            }

            for (var i : internacaoRepo.findAll()) {
                if (i.getPacienteId().equals(p.getId())) {
                    linhas.add("  * " + i);
                }
            }
            linhas.add("");
        }
        return linhas;
    }

    public int totalConsultas() {
        return consultaRepo.findAll().size();
    }

    public int totalInternacoesAtivas() {
        int n = 0;
        for (Internacao i : internacaoRepo.findAll()) if (i.estaAtiva()) n++;
        return n;
    }

    public List<Internacao> listarInternacoesAtivas() {
        List<Internacao> out = new ArrayList<>();
        for (Internacao i : internacaoRepo.findAll()) if (i.estaAtiva()) out.add(i);
        return out;
    }

    public Map<String,Integer> consultasPorMedico() {
        Map<String,Integer> mapa = new LinkedHashMap<>();
        for (Consulta c : consultaRepo.findAll()) {
            mapa.merge(c.getMedicoId(), 1, Integer::sum);
        }
        return mapa;
    }

    public Map<String,Integer> consultasPorEspecialidade() {
        Map<String,Integer> mapa = new LinkedHashMap<>();
        for (Consulta c : consultaRepo.findAll()) {
            Medico m = medicoRepo.findById(c.getMedicoId());
            String esp = (m == null ? "DESCONHECIDA" : m.getEspecialidade());
            mapa.merge(esp, 1, Integer::sum);
        }
        return mapa;
    }

    public List<Consulta> consultasNoPeriodo(String deInclusive, String ateInclusive) {
        List<Consulta> out = new ArrayList<>();
        for (Consulta c : consultaRepo.findAll()) {
            String dh = c.getDataHora();
            if (dh.compareTo(deInclusive) >= 0 && dh.compareTo(ateInclusive) <= 0) {
                out.add(c);
            }
        }
        return out;
    }

    public int totalConsultasNoPeriodo(String deInclusive, String ateInclusive) {
        return consultasNoPeriodo(deInclusive, ateInclusive).size();
    }

    private double precoLiquidoConsulta(Consulta c) {
        Medico med = medicoRepo.findById(c.getMedicoId());
        if (med == null) {
            return 0.0;
        }
        double preco = med.getValorConsulta();

        Paciente pac = pacienteRepo.findById(c.getPacienteId());
        double fator = 1.0;
        if (pac != null && pac.getPlanoSaudeId() != null) {
            PlanoSaude pl = planoRepo.findById(pac.getPlanoSaudeId());
            if (pl != null) fator = 1.0 - (pl.getDescontoConsulta() / 100.0);
        }
        return preco * fator;
    }

    public double faturamentoEstimado() {
        double total = 0.0;
        for (Consulta c : consultaRepo.findAll()) total += precoLiquidoConsulta(c);
        return total;
    }

    public double faturamentoNoPeriodo(String deInclusive, String ateInclusive) {
        double total = 0.0;
        for (Consulta c : consultasNoPeriodo(deInclusive, ateInclusive)) total += precoLiquidoConsulta(c);
        return total;
    }
}
