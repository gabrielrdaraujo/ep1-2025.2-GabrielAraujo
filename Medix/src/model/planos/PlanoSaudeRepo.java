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
        planos.add(p); 
        salvar(); 
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

    public void salvar() {
        List<String[]> linhas = new ArrayList<>();
        for (PlanoSaude p : planos) {
            linhas.add(new String[]{
                p.getId(), p.getNome(),
                String.valueOf(p.getDescontoConsulta()),
                String.valueOf(p.getDescontoConsultaIdoso()),
                String.valueOf(p.getDescontoInternacao()),
                String.valueOf(p.isInternacaoCurtaGratuita())
            });
        }
        CSV.write(Storage.PLANOS, linhas);
    }

    private void carregar() {
        planos.clear();
        for (String[] r : CSV.read(Storage.PLANOS)) {
            try {
                if (r.length >= 6) {
                    planos.add(new PlanoSaude(
                        r[0], r[1],
                        parse(r[2]), parse(r[3]), parse(r[4]),
                        parseBool(r[5])
                    ));
                } else if (r.length >= 4) {
                    planos.add(new PlanoSaude(r[0], r[1], parse(r[2]), parse(r[3])));
                }
            } catch (Exception ignored) { }
        }
    }

    private static double parse(String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException | NullPointerException e) {
            return 0.0;
        }
    }
    private static boolean parseBool(String s){
        if (s == null) return false;
        s = s.trim().toLowerCase();
        return s.equals("true") || s.equals("1") || s.equals("sim") || s.equals("y");
    }
}
