package view;

import controller.PacienteController;
import java.util.List;
import java.util.Scanner;
import model.pacientes.Paciente;

public class MenuPacientes {
    private final Scanner in;
    private final PacienteController controller;

    public MenuPacientes(Scanner in, PacienteController controller) {
        this.in = in;
        this.controller = controller;
    }

    public void mostrar() {
        int op;
        do {
            System.out.println("=== PACIENTES ===");
            System.out.println("1) Cadastrar paciente");
            System.out.println("2) Listar pacientes");
            System.out.println("0) Voltar");
            System.out.print("Opção: ");
            String line = in.nextLine().trim();
            op = line.isEmpty() ? -1 : Character.getNumericValue(line.charAt(0));

            try {
                switch (op) {
                    case 1 -> cadastrarPaciente();
                    case 2 -> listarPacientes();
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

    private void cadastrarPaciente() {
        System.out.print("Nome: ");
        String nome = in.nextLine();

        System.out.print("CPF (apenas números): ");
        String cpf = in.nextLine();

        Integer idade = null;
        while (idade == null) {
            System.out.print("Idade: ");
            String s = in.nextLine();
            try {
                idade = Integer.parseInt(s.trim());
            } catch (NumberFormatException ex) {
                System.out.println("Informe um número inteiro para a idade.");
            }
        }

        Paciente p = controller.cadastrar(nome, cpf, idade);
        System.out.println("Paciente cadastrado: " + p);
    }

    private void listarPacientes() {
        List<Paciente> lista = controller.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("(nenhum paciente cadastrado)");
            return;
        }
        System.out.println("ID | NOME | CPF | IDADE");
        for (Paciente p : lista) {
            System.out.printf("%s | %s | %s | %d%n",
                    p.getId(), p.getNome(), p.getCpf(), p.getIdade());
        }
    }
}
