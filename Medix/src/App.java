import controller.MedicoController;
import controller.PacienteController;
import model.medicos.MedicoRepoMem;
import model.pacientes.PacienteRepoMem;
import view.MenuPrincipal;

public class App {
    public static void main(String[] args) {
        // Repositórios em memória
        PacienteRepoMem pacienteRepo = new PacienteRepoMem();
        MedicoRepoMem medicoRepo = new MedicoRepoMem();

        // Controllers
        PacienteController pacienteController = new PacienteController(pacienteRepo);
        MedicoController medicoController = new MedicoController(medicoRepo);

        // Menu principal
        new MenuPrincipal(pacienteController, medicoController).mostrar();

        System.out.println("Encerrando o Medix. Até mais!");
    }
}
