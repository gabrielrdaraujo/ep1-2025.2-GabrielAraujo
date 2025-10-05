package view;

import controller.ConsultaController;
import controller.InternacaoController;
import controller.MedicoController;
import controller.PacienteController;
import controller.PlanoSaudeController;
import controller.RelatorioController;
import java.util.Scanner;

public class MenuPrincipal {
    private final Scanner in = new Scanner(System.in);
    private final PacienteController pacienteController;
    private final MedicoController medicoController;
    private final ConsultaController consultaController;
    private final InternacaoController internacaoController;
    private final PlanoSaudeController planoController;    
    private final RelatorioController relatorioController;  

    public MenuPrincipal(PacienteController pacienteController,
                         MedicoController medicoController,
                         ConsultaController consultaController,
                         InternacaoController internacaoController,
                         PlanoSaudeController planoController,         
                         RelatorioController relatorioController) {   
        this.pacienteController = pacienteController;
        this.medicoController = medicoController;
        this.consultaController = consultaController;
        this.internacaoController = internacaoController;
        this.planoController = planoController;
        this.relatorioController = relatorioController;
    }

    public void mostrar() {
        int op;
        do {
            System.out.println("=== MEDIX ===");
            System.out.println("1) Pacientes");
            System.out.println("2) Médicos");
            System.out.println("3) Consultas");
            System.out.println("4) Internações");
            System.out.println("5) Planos de saúde");
            System.out.println("6) Relatórios");
            System.out.println("0) Sair");
            System.out.print("Opção: ");
            String line = in.nextLine().trim();
            op = line.isEmpty() ? -1 : Character.getNumericValue(line.charAt(0));

            switch (op) {
                case 1 -> new MenuPacientes(in, pacienteController).mostrar();
                case 2 -> new MenuMedicos(in, medicoController).mostrar();
                case 3 -> new MenuConsultas(in, consultaController).mostrar();
                case 4 -> new MenuInternacoes(in, internacaoController).mostrar();
                case 5 -> new MenuPlanosSaude(in, planoController).mostrar();          
                case 6 -> new MenuRelatorios(in, relatorioController).mostrar();   
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
            System.out.println();
        } while (op != 0);
    }
}
