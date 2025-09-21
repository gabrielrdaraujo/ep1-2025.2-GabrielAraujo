import infra.IdSequence;
import model.medicos.Medico;
import model.medicos.MedicoRepoMem;
import model.pacientes.Paciente;
import model.pacientes.PacienteRepoMem;

public class App {
    public static void main(String[] args) {
        // Repositórios em memória
        PacienteRepoMem pacienteRepo = new PacienteRepoMem();
        MedicoRepoMem medicoRepo = new MedicoRepoMem();

        // Cadastrar pacientes
        Paciente p1 = new Paciente(IdSequence.nextId("P"), "João Silva", "12345678901", 30);
        Paciente p2 = new Paciente(IdSequence.nextId("P"), "Maria Souza", "98765432100", 45);
        pacienteRepo.add(p1);
        pacienteRepo.add(p2);

        // Cadastrar médicos
        Medico m1 = new Medico(IdSequence.nextId("M"), "Dra. Ana", "CRM1234", "Cardiologia");
        Medico m2 = new Medico(IdSequence.nextId("M"), "Dr. Pedro", "CRM5678", "Ortopedia");
        medicoRepo.add(m1);
        medicoRepo.add(m2);

        // Listagens
        System.out.println("=== Pacientes ===");
        for (Paciente p : pacienteRepo.findAll()) System.out.println(p);

        System.out.println("=== Médicos ===");
        for (Medico m : medicoRepo.findAll()) System.out.println(m);
    }
}
