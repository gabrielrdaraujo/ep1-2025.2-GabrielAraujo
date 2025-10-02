package model.internacoes;

import java.util.ArrayList;
import java.util.List;

public class InternacaoRepoMem {
    private List<Internacao> internacoes = new ArrayList<>();

    public void add(Internacao i) {
        internacoes.add(i);
    }

    public List<Internacao> findAll() {
        return internacoes;
    }

    public Internacao findById(String id) {
        for(Internacao i : internacoes) {
            if(i.getId().equals(id)) {
                return i;
            }
        } return null;
    }

    public boolean quartoOcupado(String quarto) {
        for(Internacao i : internacoes) {
            if(i.getQuarto().equalsIgnoreCase(quarto) && i.estaAtiva()) {
                return true;
            }
        } return false;
    }

    public List<Internacao> listarAtivas() {
        List<Internacao> ativas = new ArrayList<>();
        for(Internacao i : internacoes) {
            if(i.estaAtiva()) {
                ativas.add(i);
            }
        } return ativas;
    }
}
