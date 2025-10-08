package model.consultas;

import infra.CSV; // Importa a classe CSV para usar os métodos de leitura e escrita
import infra.Storage; // Importa a classe Storage para acessar os caminhos dos arquivos
import java.util.ArrayList; 
import java.util.List;

public class ConsultaRepo {
    // Lista das consultas
    private final List<Consulta> consultas = new ArrayList<>();

    // Construtor
    public ConsultaRepo() { 
        carregar(); 
    }

    // Métodos

    // Adiciona uma nova consulta a lista e salva no CSV
    public void add(Consulta c) {
        consultas.add(c);
        salvar();
    }

    // Lista todas as consultas
    public List<Consulta> findAll() {
        return consultas;
    }

    // Busca uma consulta pelo ID
    public Consulta findById(String id) {
        for (Consulta c : consultas) {
            if (c.getId().equals(id)) {
                return c;
            }
        } return null;
    }

    // Remove uma consulta pelo ID e salva no CSV
    public boolean remove(String id) {
        boolean removed = consultas.removeIf(c -> c.getId().equals(id));
        if (removed) {
            salvar();
        } return removed;
    }

    // Salva a lista de consultas no arquivo CSV (backup)
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

    // Carrega as consultas do arquivo CSV para a lista (inicialização)
    private void carregar() {
        consultas.clear();
        for (String[] r : CSV.read(Storage.CONSULTAS)) { 
            try {
                if (r.length >= 8) { // Cria o objeto Consulta com diagnóstico e prescrição
                    consultas.add(new Consulta(r[0], r[1], r[2], r[3], r[4], r[5], r[6], r[7]));
                } else if (r.length == 6) { // Cria o objeto Consulta sem diagnóstico e prescrição (vazios)
                    consultas.add(new Consulta(r[0], r[1], r[2], r[3], r[4], r[5], "", ""));
                }
            } catch (Exception ignored) { }
        }
    }

    // Verifica se há conflito de horário para o médico
    public boolean existeConflitoMedicoHorario(String medicoId, String dataHora) {
        for (Consulta c : consultas) {
            if ("AGENDADA".equals(c.getStatus()) 
                    && c.getMedicoId().equals(medicoId)
                    && c.getDataHora().equals(dataHora)) {
                return true;
            }
        } return false;
    }

    // Verifica se há conflito de local e horário
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
