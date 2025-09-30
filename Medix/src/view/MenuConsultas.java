package view;

import controller.ConsultaController;
import model.consultas.Consulta;

import java.util.List;
import java.util.Scanner;

public class MenuConsultas {
    private final Scanner in;
    private final ConsultaController controller;

    public MenuConsultas(Scanner in, ConsultaController controller) {
        this.in = in;
        this.controller = controller;
    }

    public void mostrar() {
        int op;
        do {
            System.out.println("=== CONSULTAS ===");
            System.out.println("1) Agendar consulta");
            System.out.println("2) Listar consultas");
            System.out.println("0) Voltar");
            System.out.print("Opção: ");
            String line = in.nextLine().trim();
            op = line.isEmpty() ? -1 : Character.getNumericValue(line.charAt(0));

            try {
                switch (op) {
                    case 1 -> agendar();
                    case 2 -> listar();
                    case 0 -> System.out.println("Voltando...");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
            System.out.println();
        } while (op != 0);
    }

    private void agendar() {
        System.out.print("ID do Paciente: ");
        String pacienteId = in.nextLine();

        System.out.print("ID do Médico: ");
        String medicoId = in.nextLine();

        System.out.print("Data e Hora (ex.: 2025-09-27 14:00): ");
        String dataHora = in.nextLine();

        System.out.print("Local (ex.: Sala 101): ");
        String local = in.nextLine();

        Consulta c = controller.agendar(pacienteId, medicoId, dataHora, local);
        System.out.println("Consulta agendada: " + c);
    }

    private void listar() {
        List<Consulta> lista = controller.listarTodas();
        if (lista.isEmpty()) {
            System.out.println("(nenhuma consulta cadastrada)");
            return;
        }
        System.out.println("ID | PACIENTE | MEDICO | DATA/HORA | LOCAL | STATUS");
        for (Consulta c : lista) {
            System.out.printf("%s | %s | %s | %s | %s | %s%n",
                    c.getId(), c.getPacienteId(), c.getMedicoId(),
                    c.getDataHora(), c.getLocal(), c.getStatus());
        }
    }
}
