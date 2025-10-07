package view;

import controller.InternacaoController;
import java.util.List;
import java.util.Scanner;
import model.internacoes.Internacao;

public class MenuInternacoes {
    private final Scanner in;
    private final InternacaoController controller;

    public MenuInternacoes(Scanner in, InternacaoController controller) {
        this.in = in;
        this.controller = controller;
    }

    public void mostrar() {
        int op;
        do {
            System.out.println("=== INTERNAÇÕES ===");
            System.out.println("1) Internar paciente");
            System.out.println("2) Dar alta");
            System.out.println("3) Listar internações ativas");
            System.out.println("4) Listar todas as internações");
            System.out.println("0) Voltar");
            System.out.print("Opção: ");
            String line = in.nextLine().trim();
            op = line.isEmpty() ? -1 : Character.getNumericValue(line.charAt(0));

            try {
                switch (op) {
                    case 1 -> internar();
                    case 2 -> darAlta();
                    case 3 -> listarAtivas();
                    case 4 -> listarTodas();
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

    private void internar() {
        System.out.print("ID do Paciente: ");
        String pacienteId = in.nextLine();

        System.out.print("ID do Médico responsável: ");
        String medicoId = in.nextLine();

        System.out.print("Quarto (ex.: 101): ");
        String quarto = in.nextLine();

        System.out.print("Data de entrada (ex.: 2025-10-01): ");
        String dataEntrada = in.nextLine();

        System.out.print("Custo por dia (ex.: 150.0): ");
        double custoDia = Double.parseDouble(in.nextLine().trim().replace(',', '.'));

        Internacao i = controller.internar(pacienteId, medicoId, quarto, dataEntrada, custoDia);
        System.out.println("Internação criada: " + i);
    }

    private void darAlta() {
        System.out.print("ID da Internação: ");
        String internacaoId = in.nextLine();

        System.out.print("Data de saída (ex.: 2025-10-10): ");
        String dataSaida = in.nextLine();

        controller.darAlta(internacaoId, dataSaida);
        System.out.println("Alta registrada com sucesso.");
    }

    private void listarAtivas() {
        List<Internacao> lista = controller.listarAtivas();
        if (lista.isEmpty()) {
            System.out.println("(nenhuma internação ativa)");
            return;
        }
        System.out.println("ID | PACIENTE | MEDICO | QUARTO | ENTRADA | SAIDA");
        for (Internacao i : lista) {
            System.out.printf("%s | %s | %s | %s | %s | %s%n",
                    i.getId(), i.getPacienteId(), i.getMedicoId(),
                    i.getQuarto(), i.getDataEntrada(), String.valueOf(i.getDataSaida()));
        }
    }

    private void listarTodas() {
        List<Internacao> lista = controller.listarTodas();
        if (lista.isEmpty()) {
            System.out.println("(nenhuma internação cadastrada)");
            return;
        }
        System.out.println("ID | PACIENTE | MEDICO | QUARTO | ENTRADA | SAIDA");
        for (Internacao i : lista) {
            System.out.printf("%s | %s | %s | %s | %s | %s%n",
                    i.getId(), i.getPacienteId(), i.getMedicoId(),
                    i.getQuarto(), i.getDataEntrada(), String.valueOf(i.getDataSaida()));
        }
    }
}
