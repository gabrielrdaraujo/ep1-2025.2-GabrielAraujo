package model.consultas;

import infra.CSV;
import infra.Storage;
import java.util.ArrayList;
import java.util.List;

public class ConsultaRepo {
    private final List<Consulta> consultas = new ArrayList<>();

    public ConsultaRepo() { 
        carregar(); 
    }

    public void add(Consulta c) {
        consultas.add(c);
        salvar();
    }

    public List<Consulta> findAll() {
        return consultas;
    }

    public Consulta findById(String id) {
        for (Consulta c : consultas) {
            if (c.getId().equals(id)) {
                return c;
            }
        } return null;
    }

    public boolean remove(String id) {
        boolean removed = consultas.removeIf(c -> c.getId().equals(id));
        if (removed) {
            salvar();
        } return removed;
    }

    public void salvar() {
        List<String[]> linhas = new ArrayList<>();
        for (Consulta c : consultas) {
            linhas.add(new String[]{
                    c.getId(),
                    c.getPacienteId(),
                    c.getMedicoId(),
                    c.getDataHora(),
                    c.getLocal(),
                    c.getStatus(),
                    c.getDiagnostico(),
                    c.getPrescricao()

            });
        } CSV.write(Storage.CONSULTAS, linhas);
    }

    private void carregar() {
        consultas.clear();
        for (String[] r : CSV.read(Storage.CONSULTAS)) {
            try {
                if (r.length >= 8) {
                    consultas.add(new Consulta(r[0], r[1], r[2], r[3], r[4], r[5], r[6], r[7]));
                } else if (r.length == 6) {
                    consultas.add(new Consulta(r[0], r[1], r[2], r[3], r[4], r[5], "", ""));
                }
            } catch (Exception ignored) { }
        }
    }

    public boolean existeConflitoMedicoHorario(String medicoId, String dataHora) {
        for (Consulta c : consultas) {
            if ("AGENDADA".equals(c.getStatus()) 
                    && c.getMedicoId().equals(medicoId)
                    && c.getDataHora().equals(dataHora)) {
                return true;
            }
        } return false;
    }

    public boolean existeConflitoLocalHorario(String local, String dataHora) {
        for (Consulta c : consultas) {
            if ("AGENDADA".equals(c.getStatus())
                    && c.getLocal().equalsIgnoreCase(local)
                    && c.getDataHora().equals(dataHora)) {
                return true;
            }
        } return false;
    }
}
