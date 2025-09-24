package view;

import controller.PacienteController;

import java.util.Scanner;

public class MenuPrincipal {
    private final Scanner in = new Scanner(System.in);
    private final PacienteController pacienteController;

    public MenuPrincipal(PacienteController pacienteController) {
        this.pacienteController = pacienteController;
    }

    public void mostrar() {
        int op;
        do {
            System.out.println("=== MEDIX ===");
            System.out.println("1) Pacientes");
            System.out.println("2) Médicos (em construção)");
            System.out.println("3) Consultas (em construção)");
            System.out.println("4) Internações (em construção)");
            System.out.println("5) Planos (em construção)");
            System.out.println("6) Relatórios (em construção)");
            System.out.println("0) Sair");
            System.out.print("Opção: ");
            String line = in.nextLine().trim();
            op = line.isEmpty() ? -1 : Character.getNumericValue(line.charAt(0));

            switch (op) {
                case 1 -> new MenuPacientes(in, pacienteController).mostrar();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida ou ainda não implementada.");
            }

            System.out.println();
        } while (op != 0);
    }
}
