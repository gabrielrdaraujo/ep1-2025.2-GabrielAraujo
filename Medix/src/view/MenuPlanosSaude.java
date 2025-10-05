package view;

import controller.PlanoSaudeController;
import java.util.List;
import java.util.Scanner;
import model.planos.PlanoSaude;

public class MenuPlanosSaude {
    private final Scanner in;
    private final PlanoSaudeController ctrl;

    public MenuPlanosSaude(Scanner in, PlanoSaudeController ctrl) {
        this.in = in;
        this.ctrl = ctrl;
    }

    public void mostrar() {
        int op;
        do {
            System.out.println("=== PLANOS DE SAÚDE ===");
            System.out.println("1) Cadastrar plano");
            System.out.println("2) Listar planos");
            System.out.println("0) Voltar");
            System.out.print("Opção: ");
            String s = in.nextLine().trim();
            op = s.isEmpty() ? -1 : Character.getNumericValue(s.charAt(0));

            try {
                switch (op) {
                    case 1 -> cadastrar();
                    case 2 -> listar();
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
        System.out.print("Nome do plano: ");
        String nome = in.nextLine();

        System.out.print("Desconto em consultas (%) [ex.: 20]: ");
        double dConsulta = lerDouble(in.nextLine());

        System.out.print("Desconto em internações (%) [ex.: 10]: ");
        double dIntern = lerDouble(in.nextLine());

        PlanoSaude p = ctrl.cadastrar(nome, dConsulta, dIntern);
        System.out.println("Plano cadastrado: " + p);
    }

    private void listar() {
        List<PlanoSaude> planos = ctrl.listar();
        if (planos.isEmpty()) {
            System.out.println("Nenhum plano cadastrado.");
            return;
        }
        System.out.println("ID | NOME | DESC_CONS(%) | DESC_INT(%)");
        for (PlanoSaude p : planos) {
            System.out.printf("%s | %s | %.1f | %.1f%n",
                    p.getId(), p.getNome(), p.getDescontoConsulta(), p.getDescontoInternacao());
        }
    }

    private double lerDouble(String s) {
        try { 
            return Double.parseDouble(s.replace(',', '.')); 
        } catch (NumberFormatException e) { 
            throw new IllegalArgumentException("Número inválido."); 
        }
    }
}
