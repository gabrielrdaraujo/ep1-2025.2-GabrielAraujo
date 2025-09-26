package model.consultas;

public class Consulta {
    private final String id;
    private final String pacienteId;
    private final String medicoId;
    private final String dataHora; 
    private final String local;    
    private String status;   

    public Consulta(String id, String pacienteId, String medicoId, String dataHora, String local, String status) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.dataHora = dataHora;
        this.local = local;
        this.status = status;
    }

    public String getId() { 
        return id; 
    }

    public String getPacienteId() { 
        return pacienteId; 
    }

    public String getMedicoId() { 
        return medicoId; 
    }

    public String getDataHora() { 
        return dataHora; 
    }

    public String getLocal() { 
        return local; 
    }

    public String getStatus() { 
        return status; 
    }

    public void setStatus(String status) { this.status = status; }


    @Override
    public String toString() {
        return "Consulta{" +
                "id='" + id + '\'' +
                ", pacienteId='" + pacienteId + '\'' +
                ", medicoId='" + medicoId + '\'' +
                ", dataHora='" + dataHora + '\'' +
                ", local='" + local + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
