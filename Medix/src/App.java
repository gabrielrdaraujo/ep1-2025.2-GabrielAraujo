import controller.ConsultaController;
import controller.InternacaoController;
import controller.MedicoController;
import controller.PacienteController;
import model.consultas.ConsultaRepo;
import model.internacoes.InternacaoRepoMem;
import model.medicos.MedicoRepo;
import model.pacientes.PacienteRepo;
import view.MenuPrincipal;

public class App {
    public static void main(String[] args) {
        PacienteRepo pacienteRepo   = new PacienteRepo();
        MedicoRepo   medicoRepo     = new MedicoRepo();
        ConsultaRepo consultaRepo   = new ConsultaRepo();
        InternacaoRepoMem internacaoRepo = new InternacaoRepoMem(); 

        PacienteController pacienteController   = new PacienteController(pacienteRepo);
        MedicoController   medicoController     = new MedicoController(medicoRepo);
        ConsultaController consultaController   = new ConsultaController(consultaRepo, pacienteRepo,  null);

        ConsultaController consultaCtrlCompat = new ConsultaController(consultaRepo, pacienteRepo, new model.medicos.MedicoRepoMem());

        InternacaoController internacaoController = new InternacaoController(internacaoRepo, pacienteRepo, new model.medicos.MedicoRepoMem());

        new MenuPrincipal(pacienteController, medicoController, consultaCtrlCompat, internacaoController).mostrar();
        System.out.println("Encerrando o Medix. Até mais!");
    }
}
