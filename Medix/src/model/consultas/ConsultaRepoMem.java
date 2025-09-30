package model.consultas;

import java.util.ArrayList;
import java.util.List;

public class ConsultaRepoMem {
    private List<Consulta> consultas = new ArrayList<>();

    public void add(Consulta c) {
        consultas.add(c);
    }

    public List<Consulta> findAll() {
        return consultas;
    }

    public Consulta findById(String id) {
        for (Consulta c : consultas) {
            if (c.getId().equals(id)) return c;
        }
        return null;
    }

    // Conflito mesmo médico no mesmo horário
    public boolean ConflitoMedicoHorario(String medicoId, String dataHora) {
        for (Consulta c : consultas) {
            if ("AGENDADA".equals(c.getStatus())
                    && c.getMedicoId().equals(medicoId)
                    && c.getDataHora().equals(dataHora)) {
                return true;
            }
        } return false;
    }

    // Conflito mesmo local no mesmo horário 
    public boolean ConflitoLocalHorario(String local, String dataHora) {
        for (Consulta c : consultas) {
            if ("AGENDADA".equals(c.getStatus())
                    && c.getLocal().equalsIgnoreCase(local)
                    && c.getDataHora().equals(dataHora)) {
                return true;
            }
        } return false;
    }
}
