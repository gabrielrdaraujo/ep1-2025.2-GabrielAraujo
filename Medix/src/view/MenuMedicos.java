package view;

import controller.MedicoController;
import java.util.List;
import java.util.Scanner;
import model.medicos.Medico;

public class MenuMedicos {
    private final Scanner in;
    private final MedicoController controller;

    public MenuMedicos(Scanner in, MedicoController controller) {
        this.in = in;
        this.controller = controller;
    }

    public void mostrar() {
        int op;
        do {
            System.out.println("=== MÉDICOS ===");
            System.out.println("1) Cadastrar médico");
            System.out.println("2) Listar médicos");
            System.out.println("0) Voltar");
            System.out.print("Opção: ");
            String line = in.nextLine().trim();
            op = line.isEmpty() ? -1 : Character.getNumericValue(line.charAt(0));

            try {
                switch (op) {
                    case 1 -> cadastrarMedico();
                    case 2 -> listarMedicos();
                    case 0 -> System.out.println("Voltando...");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }

            System.out.println();
        } while (op != 0);
    }

    private void cadastrarMedico() {
        System.out.print("Nome: ");
        String nome = in.nextLine();

        System.out.print("CRM: ");
        String crm = in.nextLine();

        System.out.print("Especialidade: ");
        String especialidade = in.nextLine();

        System.out.print("Valor da consulta: ");
        double valorConsulta = Double.parseDouble(in.nextLine());

        Medico m = controller.cadastrar(nome, crm, especialidade, valorConsulta);
        System.out.println("Médico cadastrado: " + m);
    }

    private void listarMedicos() {
        List<Medico> lista = controller.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("(nenhum médico cadastrado)");
            return;
        }
        System.out.println("ID | NOME | CRM | ESPECIALIDADE | VALOR CONSULTA");
        for (Medico m : lista) {
            System.out.printf("%s | %s | %s | %s%n | %.2f%n",
                    m.getId(), m.getNome(), m.getCrm(), m.getEspecialidade(), m.getValorConsulta());
        }
    }
}
