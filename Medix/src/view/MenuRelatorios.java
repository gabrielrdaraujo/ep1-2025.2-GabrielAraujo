package view;

import controller.RelatorioController;

import java.util.Map;
import java.util.Scanner;

public class MenuRelatorios {
    private final Scanner in;
    private final RelatorioController ctrl;

    public MenuRelatorios(Scanner in, RelatorioController ctrl) {
        this.in = in; this.ctrl = ctrl;
    }

    public void mostrar() {
        int op;
        do {
            System.out.println("=== RELATÓRIOS ===");
            System.out.println("1) Total de consultas");
            System.out.println("2) Faturamento estimado (consultas)");
            System.out.println("3) Consultas por médico");
            System.out.println("0) Voltar");
            System.out.print("Opção: ");
            String s = in.nextLine().trim();
            op = s.isEmpty() ? -1 : Character.getNumericValue(s.charAt(0));

            switch (op) {
                case 1 -> System.out.println("Total de consultas: " + ctrl.totalConsultas());
                case 2 -> System.out.printf("Faturamento estimado: R$ %.2f%n", ctrl.faturamentoEstimado());
                case 3 -> {
                    Map<String,Integer> m = ctrl.consultasPorMedico();
                    System.out.println("MedicoID -> Qtde");
                    m.forEach((k,v)-> System.out.println(k + " -> " + v));
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
            System.out.println();
        } while (op != 0);
    }
}
