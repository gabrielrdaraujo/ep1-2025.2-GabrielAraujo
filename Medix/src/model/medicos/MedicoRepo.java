package model.medicos;

import infra.CSV;
import infra.Storage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MedicoRepo {
    // Lista de médicos
    private final List<Medico> medicos = new ArrayList<>();

    // Construtor (carrega o iniciar o repositório)
    public MedicoRepo() {
        garantirPastaData();
        carregar();
    }

    // Métodos

    // Adiciona um novo médico e salva
    public void add(Medico m) {
        medicos.add(m);
        salvar();
    }

    // Lista todos os médicos
    public List<Medico> findAll() {
        return medicos;
    }

    // Busca médico por ID
    public Medico findById(String id) {
        for (Medico m : medicos) {
            if (m.getId().equals(id)) {
                return m;
            }
        } return null;
    }

    // Remove médico por ID e salva
    public boolean remove(String id) {
        boolean removed = medicos.removeIf(m -> m.getId().equals(id));
        if (removed) {
            salvar();
        } return removed;
    }


    // Método para salvar dados em CSV (backup)
    private void salvar() {
        List<String[]> linhas = new ArrayList<>();
        for (Medico m : medicos) {
            linhas.add(new String[] {
                m.getId(),
                m.getNome(),
                m.getCrm(),
                m.getEspecialidade(),
                String.valueOf(m.getValorConsulta())
            });
        }
        CSV.write(Storage.MEDICOS, linhas);
    }

    // Método para carregar dados de CSV (inicialização)
    private void carregar() {
        List<String[]> linhas = CSV.read(Storage.MEDICOS);
        medicos.clear();
        for (String[] c : linhas) {
            if (c.length >= 5) {
                medicos.add(new Medico(c[0], c[1], c[2], c[3], parseDoubleSafe(c[4])));
            } else if (c.length == 4) {
                medicos.add(new Medico(c[0], c[1], c[2], c[3], 0.0));
            }
        }
    }

    // Método auxiliar para converter String em double com tratamento de erros
    public static double parseDoubleSafe(String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    // Garante que a pasta data/ exista
    private void garantirPastaData() {
        try {
            Files.createDirectories(Paths.get(Storage.DATA_FOLDER));
        } catch (IOException e) {
            System.err.println("Aviso: não foi possível garantir a pasta data/: " + e.getMessage());
        }
    }
}
