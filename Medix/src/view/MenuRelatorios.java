package view;

import controller.RelatorioController;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import model.internacoes.Internacao;

public class MenuRelatorios {
    private final Scanner in;
    private final RelatorioController controller;

    public MenuRelatorios(Scanner in, RelatorioController controller) {
        this.in = in;
        this.controller = controller;
    }

    public void mostrar() {
        int op;
        do {
            System.out.println("=== RELATÓRIOS ===");
            System.out.println("1) Total de consultas");
            System.out.println("2) Consultas por médico (contagem)");
            System.out.println("3) Consultas por especialidade (contagem)");
            System.out.println("4) Faturamento estimado (todas)");
            System.out.println("5) Consultas em período (contagem)");
            System.out.println("6) Faturamento no período");
            System.out.println("7) Internações ativas (lista e total)");
            System.out.println("8) Consultas FUTURAS");
            System.out.println("9) Consultas PASSADAS");
            System.out.println("10) Agenda do médico (futuras)");
            System.out.println("11) Médico que mais atendeu");
            System.out.println("12) Especialidade mais procurada");
            System.out.println("13) Pacientes detalhados (com histórico)");
            System.out.println("0) Voltar");
            System.out.print("Opção: ");
            String s = in.nextLine().trim();
            op = s.isEmpty() ? -1 : Character.getNumericValue(s.charAt(0));

            try {
                switch (op) {
                    case 1 -> System.out.println("Total de consultas: " + controller.totalConsultas());
                    case 2 -> verMapa(controller.consultasPorMedico(), "MedicoID", "Qtde");
                    case 3 -> verMapa(controller.consultasPorEspecialidade(), "Especialidade", "Qtde");
                    case 4 -> System.out.printf("Faturamento estimado: R$ %.2f%n", controller.faturamentoEstimado());
                    case 5 -> {
                        String[] per = lerPeriodo();
                        System.out.println("Consultas no período: " + controller.totalConsultasNoPeriodo(per[0], per[1]));
                    }
                    case 6 -> {
                        String[] per = lerPeriodo();
                        System.out.printf("Faturamento no período: R$ %.2f%n",
                                controller.faturamentoNoPeriodo(per[0], per[1]));
                    }
                    case 7 -> {
                        List<Internacao> ativas = controller.listarInternacoesAtivas();
                        System.out.println("Ativas: " + ativas.size());
                        for (Internacao i : ativas) System.out.println(i);
                    }
                    case 8 -> controller.consultasFuturas().forEach(System.out::println);
                    case 9 -> controller.consultasPassadas().forEach(System.out::println);
                    case 10 -> {
                        System.out.print("ID do médico: ");
                        String mid = in.nextLine().trim();
                        controller.agendaMedicoFuturas(mid).forEach(System.out::println);
                    }
                    case 11 -> System.out.println(controller.medicoQueMaisAtendeu());
                    case 12 -> System.out.println(controller.especialidadeMaisProcurada());
                    case 13 -> controller.pacientesDetalhados().forEach(System.out::println);
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
