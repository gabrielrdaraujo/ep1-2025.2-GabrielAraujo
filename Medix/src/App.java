import model.pacientes.PacienteRepoMem;
import model.medicos.MedicoRepoMem;
import model.consultas.ConsultaRepoMem;
import model.internacoes.InternacaoRepoMem;

import controller.PacienteController;
import controller.MedicoController;
import controller.ConsultaController;
import controller.InternacaoController;

import view.MenuPrincipal;

public class App {
    public static void main(String[] args) {
        PacienteRepoMem pacienteRepo = new PacienteRepoMem();
        MedicoRepoMem medicoRepo = new MedicoRepoMem();
        ConsultaRepoMem consultaRepo = new ConsultaRepoMem();
        InternacaoRepoMem internacaoRepo = new InternacaoRepoMem();

        PacienteController pacienteController = new PacienteController(pacienteRepo);
        MedicoController medicoController = new MedicoController(medicoRepo);
        ConsultaController consultaController = new ConsultaController(consultaRepo, pacienteRepo, medicoRepo);
        InternacaoController internacaoController = new InternacaoController(internacaoRepo, pacienteRepo, medicoRepo);

        new MenuPrincipal(pacienteController, medicoController, consultaController, internacaoController).mostrar();

        System.out.println("Encerrando o Medix. Até mais!");
    }
}
