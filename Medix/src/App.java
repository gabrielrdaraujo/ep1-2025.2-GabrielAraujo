import controller.ConsultaController;
import controller.InternacaoController;
import controller.MedicoController;
import controller.PacienteController;
import controller.PlanoSaudeController;
import controller.RelatorioController;
import model.consultas.ConsultaRepo;
import model.internacoes.InternacaoRepo;
import model.medicos.MedicoRepo;
import model.pacientes.PacienteRepo;
import model.planos.PlanoSaudeRepo;
import view.MenuPrincipal;

public class App {
    public static void main(String[] args) {
        PacienteRepo   pacienteRepo   = new PacienteRepo();
        MedicoRepo     medicoRepo     = new MedicoRepo();
        ConsultaRepo   consultaRepo   = new ConsultaRepo();
        InternacaoRepo internacaoRepo = new InternacaoRepo();
        PlanoSaudeRepo planoRepo      = new PlanoSaudeRepo();

        PacienteController   pacienteController   = new PacienteController(pacienteRepo);
        MedicoController     medicoController     = new MedicoController(medicoRepo);
        ConsultaController   consultaController   = new ConsultaController(consultaRepo, pacienteRepo, medicoRepo);
        InternacaoController internacaoController = new InternacaoController(internacaoRepo, pacienteRepo, medicoRepo);
        PlanoSaudeController planoController      = new PlanoSaudeController(planoRepo);
        RelatorioController  relatorioController  = new RelatorioController(consultaRepo, pacienteRepo, medicoRepo, planoRepo);

        new MenuPrincipal(
            pacienteController,
            medicoController,
            consultaController,
            internacaoController,
            planoController,        
            relatorioController      
        ).mostrar();

        System.out.println("Encerrando o Medix. Até mais!");
    }
}
