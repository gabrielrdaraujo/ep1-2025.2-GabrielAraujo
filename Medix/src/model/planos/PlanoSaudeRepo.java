package model.planos;

import infra.CSV;
import infra.Storage;
import java.util.ArrayList;
import java.util.List;

public class PlanoSaudeRepo {
    private final List<PlanoSaude> planos = new ArrayList<>();

    public PlanoSaudeRepo() { 
        carregar(); 
    }

    public void add(PlanoSaude p) { 
        planos.add(p); salvar(); 
    }

    public List<PlanoSaude> findAll() { 
        return planos; 
    }

    public PlanoSaude findById(String id) {
        for (PlanoSaude p : planos) {
            if (p.getId().equals(id)) {
                return p;
            }
        } return null;
    }

    private void salvar() {
        List<String[]> linhas = new ArrayList<>();
        for (PlanoSaude p : planos) {
            linhas.add(new String[]{
                p.getId(), p.getNome(),
                String.valueOf(p.getDescontoConsulta()),
                String.valueOf(p.getDescontoInternacao())
            });
        }
        CSV.write(Storage.PLANOS, linhas);
    }

    private void carregar() {
        planos.clear();
        for (String[] r : CSV.read(Storage.PLANOS)) {
            if (r.length >= 4) {
                planos.add(new PlanoSaude(r[0], r[1], parse(r[2]), parse(r[3])));
            }
        }
    }
    private static double parse(String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException | NullPointerException e) {
            return 0.0;
        }
    }
}
