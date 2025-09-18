import model.pacientes.Paciente;
import model.medicos.Medico;

public class App {
    public static void main(String[] args) {
        Paciente p = new Paciente("P1", "João Silva", "12345678901", 30);
        Medico m = new Medico("M1", "Dra. Ana", "CRM1234", "Cardiologia");

        System.out.println(p);
        System.out.println(m);
    }
}
