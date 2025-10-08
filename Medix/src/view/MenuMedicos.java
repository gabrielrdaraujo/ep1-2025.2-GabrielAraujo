package view;

import controller.MedicoController;
import java.util.List;
import java.util.Scanner;
import model.medicos.Medico;

public class MenuMedicos {
    // Atributos
    private final Scanner in;
    private final MedicoController controller;

    // Construtor
    public MenuMedicos(Scanner in, MedicoController controller) {
        this.in = in;
        this.controller = controller;
    }

    // Métodos

    // Menu Médicos
    public void mostrar() {
        int op;
        do {
            System.out.println("=== MÉDICOS ===");
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
                System.out.println("Erro: " + e.getMessage()); // Tratamento genérico de erros
            }
            System.out.println();
        } while (op != 0);
    }

    // Cadastrar médico
    private void cadastrar() {
        System.out.print("Nome: ");
        String nome = in.nextLine();

        System.out.print("CRM: ");
        String crm = in.nextLine();

        System.out.print("Especialidade: ");
        String esp = in.nextLine();

        System.out.print("Valor da consulta (R$): ");
        double valor = lerDouble(in.nextLine());

        Medico m = controller.cadastrar(nome, crm, esp, valor);
        System.out.println("Médico cadastrado: " + m);
    }

    // Listar médicos
    private void listar() {
        List<Medico> medicos = controller.listarTodos();
        if (medicos.isEmpty()) {
            System.out.println("Nenhum médico cadastrado."); // Mensagem se a lista estiver vazia
            return;
        }
        for (Medico m : medicos) System.out.println(m);
    }

    // Remover médico por ID
    private void remover() {
        System.out.print("ID do médico: ");
        String id = in.nextLine();
        boolean ok = controller.removerPorId(id);
        System.out.println(ok ? "Removido." : "ID não encontrado.");
    }

    // Método auxiliar para ler double com tratamento de erro com vírgula ou ponto
    private double lerDouble(String s) {
        try { 
            return Double.parseDouble(s.replace(',', '.')); 
        } catch (NumberFormatException e) { 
            throw new IllegalArgumentException("Número inválido."); 
        }
    }
}
