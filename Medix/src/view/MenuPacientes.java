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
            System.out.println("1) Cadastrar");
            System.out.println("2) Listar");
            System.out.println("3) Remover por ID");
            System.out.println("0) Voltar");
            System.out.print("Opção: ");
            String s = in.nextLine().trim();
            op = s.isEmpty() ? -1 : Character.getNumericValue(s.charAt(0));

            try {
                switch (op) {
                    case 1 -> cadastrar();
                    case 2 -> listar();
                    case 3 -> remover();
                    case 0 -> System.out.println("Voltando...");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
            System.out.println();
        } while (op != 0);
    }

        private void cadastrar() {
        System.out.print("Nome: ");
        String nome = in.nextLine();

        System.out.print("CPF: ");
        String cpf = in.nextLine();

        System.out.print("Idade: ");
        int idade = lerInt(in.nextLine());

        System.out.print("Plano de saúde ID (deixe vazio se não tiver): ");
        String planoSaudeId = in.nextLine().trim();
        if (planoSaudeId.isBlank()) planoSaudeId = null;

        System.out.print("Paciente especial? (S/N): ");
        boolean especial = in.nextLine().trim().equalsIgnoreCase("S");

        String observacao = "";
        if (especial) {
            System.out.print("Observação (opcional): ");
            observacao = in.nextLine();
        }

        Paciente p = controller.cadastrar(nome, cpf, idade, planoSaudeId, especial, observacao);
        System.out.println("Paciente cadastrado: " + p.getId());
    }

    private void listar() {
        List<Paciente> pacientes = controller.listarTodos();
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }
        for (Paciente p : pacientes) System.out.println(p);
    }

    private void remover() {
        System.out.print("ID do paciente: ");
        String id = in.nextLine();
        boolean ok = controller.removerPorId(id);
        System.out.println(ok ? "Removido." : "ID não encontrado.");
    }

    private int lerInt(String s) {
        try { return Integer.parseInt(s.trim()); }
        catch (NumberFormatException e) { 
            throw new IllegalArgumentException("Número inteiro inválido."); 
        }
    }
}
