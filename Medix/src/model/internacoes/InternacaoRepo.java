package model.internacoes;

import infra.CSV; 
import infra.Storage;
import java.util.ArrayList;
import java.util.List;

public class InternacaoRepo {
    // Lista de internações
    private final List<Internacao> internacoes = new ArrayList<>();

    // Construtor (carrega os dados ao iniciar o repositório)
    public InternacaoRepo() { 
        carregar(); 
    }

    // Métodos

    // Adiciona uma nova internação e salva
    public void add(Internacao i) {
        internacoes.add(i);
        salvar();
    }

    // Lista todas as internações
    public List<Internacao> findAll() {
        return internacoes;
    }

    // Busca internação por ID
    public Internacao findById(String id) {
        for (Internacao i : internacoes) {
            if (i.getId().equals(id)) {
                return i;
            }
        } return null;
    }

    // Remove internação por ID e salva
    public boolean remove(String id) {
        boolean removed = internacoes.removeIf(i -> i.getId().equals(id));
        if (removed) {
            salvar();
        } return removed;
    }

    // Verifica se um quarto já está ocupado
    public boolean quartoOcupado(String quarto) {
        for (Internacao i : internacoes) {
            if (i.getQuarto().equalsIgnoreCase(quarto) && i.estaAtiva()) {
                return true;
            }
        } return false;
    }

    // Lista internações ativas (internações sem data de saída)
    public List<Internacao> listarAtivas() {
        List<Internacao> ativas = new ArrayList<>();
        for (Internacao i : internacoes) {
            if (i.estaAtiva()) ativas.add(i);
        } return ativas;
    }

    // Método para salvar dados em CSV (backup)
    private void salvar() {
        List<String[]> linhas = new ArrayList<>();
        for (Internacao i : internacoes) {
            linhas.add(new String[]{
                i.getId(),
                i.getPacienteId(),
                i.getMedicoId(),
                i.getQuarto(),
                i.getDataEntrada(),
                i.getDataSaida() == null ? "" : i.getDataSaida(),
                String.valueOf(i.getCustoDia())
            });
        }
        CSV.write(Storage.INTERNACOES, linhas);
    }

    // Carrega os dados do CSV para a lista de internações (inicialização)
    private void carregar() {
        internacoes.clear();
        for (String[] r : CSV.read(Storage.INTERNACOES)) {
            try {
                if (r.length >= 7) {
                    internacoes.add(new Internacao(r[0], r[1], r[2], r[3], r[4],
                            r[5].isBlank()? null : r[5], parse(r[6])));
                } else if (r.length == 6) {
                    internacoes.add(new Internacao(r[0], r[1], r[2], r[3], r[4],
                            r[5].isBlank()? null : r[5], 0.0));
                }
            } catch (Exception ignored) { }
        }
    }

    // Método auxiliar para converter String em double com tratamento de erros
    private static double parse(String s) { 
        try { 
            return Double.parseDouble(s); 
        } catch (NumberFormatException | NullPointerException e) { 
            return 0.0; 
        } 
    }
}
