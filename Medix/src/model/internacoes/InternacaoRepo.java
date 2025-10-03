package model.internacoes;

import infra.CSV;
import infra.Storage;

import java.util.ArrayList;
import java.util.List;

public class InternacaoRepo {
    private final List<Internacao> internacoes = new ArrayList<>();

    public InternacaoRepo() { carregar(); }

    public void add(Internacao i) {
        internacoes.add(i);
        salvar();
    }

    public List<Internacao> findAll() {
        return internacoes;
    }

    public Internacao findById(String id) {
        for (Internacao i : internacoes) {
            if (i.getId().equals(id)) return i;
        }
        return null;
    }

    public boolean remove(String id) {
        boolean removed = internacoes.removeIf(i -> i.getId().equals(id));
        if (removed) salvar();
        return removed;
    }

    public boolean quartoOcupado(String quarto) {
        for (Internacao i : internacoes) {
            if (i.getQuarto().equalsIgnoreCase(quarto) && i.estaAtiva()) {
                return true;
            }
        }
        return false;
    }

    public List<Internacao> listarAtivas() {
        List<Internacao> ativas = new ArrayList<>();
        for (Internacao i : internacoes) {
            if (i.estaAtiva()) ativas.add(i);
        }
        return ativas;
    }

    private void salvar() {
        List<String[]> linhas = new ArrayList<>();
        for (Internacao i : internacoes) {
            linhas.add(new String[]{
                i.getId(),
                i.getPacienteId(),
                i.getMedicoId(),
                i.getQuarto(),
                i.getDataEntrada(),
                i.getDataSaida() == null ? "" : i.getDataSaida()
            });
        }
        CSV.write(Storage.INTERNACOES, linhas);
    }

    private void carregar() {
        List<String[]> linhas = CSV.read(Storage.INTERNACOES);
        for (String[] r : linhas) {
            if (r.length == 6) {
                String dataSaida = r[5].isBlank() ? null : r[5];
                internacoes.add(new Internacao(r[0], r[1], r[2], r[3], r[4], dataSaida));
            }
        }
    }
}
