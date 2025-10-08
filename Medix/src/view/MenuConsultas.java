package view;

import controller.ConsultaController;
import java.util.List;
import java.util.Scanner;
import model.consultas.Consulta;

public class MenuConsultas {
    // Atributos
    private final Scanner in;
    private final ConsultaController controller;

    // Construtor
    public MenuConsultas(Scanner in, ConsultaController controller) {
        this.in = in;
        this.controller = controller;
    }

    // Métodos

    // Menu Consultas
    public void mostrar() {
        int op;
        do {
            System.out.println("=== CONSULTAS ===");
            System.out.println("1) Agendar consulta");
            System.out.println("2) Listar consultas");
            System.out.println("3) Concluir");
            System.out.println("4) Cancelar");
            System.out.println("0) Voltar");
            System.out.print("Opção: ");
            String line = in.nextLine().trim(); // Evita erro se o usuário só apertar Enter
            op = line.isEmpty() ? -1 : Character.getNumericValue(line.charAt(0)); // Pega o primeiro caractere

            try {
                switch (op) {
                    case 1 -> agendar();
                    case 2 -> listar();
                    case 3 -> concluir();
                    case 4 -> cancelar();
                    case 0 -> System.out.println("Voltando...");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) { // Tratamento de erros
                System.out.println("Erro: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
            System.out.println();
        } while (op != 0);
    }

    // Método para agendar consulta
    private void agendar() {
        System.out.print("ID do Paciente: ");
        String pacienteId = in.nextLine();

        System.out.print("ID do Médico: ");
        String medicoId = in.nextLine();

        System.out.print("Data e Hora (ex.: 2025-09-27 14:00): "); // Formato para compatibilidade futura
        String dataHora = in.nextLine();

        System.out.print("Local (ex.: Sala 101): ");
        String local = in.nextLine();

        Consulta c = controller.agendar(pacienteId, medicoId, dataHora, local);
        System.out.println("Consulta agendada: " + c);
    }

    // Listar consultas
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

    // Concluir consulta
    private void concluir() {
        System.out.print("Consulta ID: "); 
        String id = in.nextLine().trim();

        System.out.print("Diagnóstico: "); 
        String diag = in.nextLine();

        System.out.print("Prescrição: ");  
        String presc = in.nextLine();

        controller.concluir(id, diag, presc);
        System.out.println("Consulta concluída.");
    }
    
    // Cancelar consulta
    private void cancelar() {
        System.out.print("Consulta ID: "); 
        String id = in.nextLine().trim();
        
        controller.cancelar(id);
        System.out.println("Consulta cancelada.");
    }
}
