import controller.ConsultaController;
import controller.InternacaoController;
import controller.MedicoController;
import controller.PacienteController;
import model.consultas.ConsultaRepo;
import model.internacoes.InternacaoRepo;
import model.medicos.MedicoRepo;
import model.pacientes.PacienteRepo;
import view.MenuPrincipal;

public class App {
    public static void main(String[] args) {
        // Repositórios CSV
        PacienteRepo   pacienteRepo   = new PacienteRepo();
        MedicoRepo     medicoRepo     = new MedicoRepo();
        ConsultaRepo   consultaRepo   = new ConsultaRepo();
        InternacaoRepo internacaoRepo = new InternacaoRepo();

        // Controllers
        PacienteController   pacienteController   = new PacienteController(pacienteRepo);
        MedicoController     medicoController     = new MedicoController(medicoRepo);
        ConsultaController   consultaController   = new ConsultaController(consultaRepo, pacienteRepo, medicoRepo);
        InternacaoController internacaoController = new InternacaoController(internacaoRepo, pacienteRepo, medicoRepo);

        // UI
        new MenuPrincipal(pacienteController, medicoController, consultaController, internacaoController).mostrar();
        System.out.println("Encerrando o Medix. Até mais!");
    }
}
