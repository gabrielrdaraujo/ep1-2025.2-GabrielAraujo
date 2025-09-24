import controller.PacienteController;
import model.pacientes.PacienteRepoMem;
import view.MenuPrincipal;

public class App {
    public static void main(String[] args) {
        // Repositório em memória (por enquanto, sem CSV)
        PacienteRepoMem pacienteRepo = new PacienteRepoMem();

        // Controller
        PacienteController pacienteController = new PacienteController(pacienteRepo);

        // Menu principal
        new MenuPrincipal(pacienteController).mostrar();

        System.out.println("Encerrando o Medix. Até mais!");
    }
}
