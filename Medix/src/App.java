import controller.ConsultaController;
import controller.MedicoController;
import controller.PacienteController;
import model.consultas.ConsultaRepoMem;
import model.medicos.MedicoRepoMem;
import model.pacientes.PacienteRepoMem;
import view.MenuPrincipal;

public class App {
    public static void main(String[] args) {
        // Repositórios em memória
        PacienteRepoMem pacienteRepo = new PacienteRepoMem();
        MedicoRepoMem medicoRepo = new MedicoRepoMem();
        ConsultaRepoMem consultaRepo = new ConsultaRepoMem();

        // Controllers
        PacienteController pacienteController = new PacienteController(pacienteRepo);
        MedicoController medicoController = new MedicoController(medicoRepo);
        ConsultaController consultaController = new ConsultaController(consultaRepo, pacienteRepo, medicoRepo);

        // Menu principal
        new MenuPrincipal(pacienteController, medicoController, consultaController).mostrar();

        System.out.println("Encerrando o Medix. Até mais!");
    }
}
