package view;

import controller.RelatorioController;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import model.internacoes.Internacao;

public class MenuRelatorios {
    private final Scanner in;
    private final RelatorioController ctrl;

    public MenuRelatorios(Scanner in, RelatorioController ctrl) {
        this.in = in;
        this.ctrl = ctrl;
    }

    public void mostrar() {
        int op;
        do {
            System.out.println("=== RELATÓRIOS ===");
            System.out.println("1) Total de consultas");
            System.out.println("2) Consultas por médico");
            System.out.println("3) Consultas por especialidade");
            System.out.println("4) Faturamento estimado (todas)");
            System.out.println("5) Consultas em período");
            System.out.println("6) Faturamento no período");
            System.out.println("7) Internações ativas (lista e total)");
            System.out.println("0) Voltar");
            System.out.print("Opção: ");
            String s = in.nextLine().trim();
            op = s.isEmpty() ? -1 : Character.getNumericValue(s.charAt(0));

            try {
                switch (op) {
                    case 1 -> System.out.println("Total de consultas: " + ctrl.totalConsultas());
                    case 2 -> verMapa(ctrl.consultasPorMedico(), "MedicoID", "Qtde");
                    case 3 -> verMapa(ctrl.consultasPorEspecialidade(), "Especialidade", "Qtde");
                    case 4 -> System.out.printf("Faturamento estimado: R$ %.2f%n", ctrl.faturamentoEstimado());
                    case 5 -> {
                        String[] per = lerPeriodo();
                        System.out.println("Consultas no período: " + ctrl.totalConsultasNoPeriodo(per[0], per[1]));
                    }
                    case 6 -> {
                        String[] per = lerPeriodo();
                        System.out.printf("Faturamento no período: R$ %.2f%n",
                                ctrl.faturamentoNoPeriodo(per[0], per[1]));
                    }
                    case 7 -> {
                        List<Internacao> ativas = ctrl.listarInternacoesAtivas();
                        System.out.println("Ativas: " + ativas.size());
                        for (Internacao i : ativas) System.out.println(i);
                    }
                    case 0 -> System.out.println("Voltando...");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
            System.out.println();
        } while (op != 0);
    }

    private void verMapa(Map<String,Integer> mapa, String k, String v) {
        System.out.println(k + " | " + v);
        mapa.forEach((key, val) -> System.out.println(key + " | " + val));
    }

    private String[] lerPeriodo() {
        System.out.print("Data/Hora inicial (yyyy-MM-dd HH:mm): ");
        String de = in.nextLine().trim();
        System.out.print("Data/Hora final   (yyyy-MM-dd HH:mm): ");
        String ate = in.nextLine().trim();
        if (de.isEmpty() || ate.isEmpty()) throw new IllegalArgumentException("Período inválido.");
        if (de.compareTo(ate) > 0) throw new IllegalArgumentException("Data inicial > data final.");
        return new String[]{de, ate};
    }
}
