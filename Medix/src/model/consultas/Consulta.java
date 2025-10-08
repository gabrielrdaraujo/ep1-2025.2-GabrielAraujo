package model.consultas;

public class Consulta {
    // Atributos
    private final String id;
    private final String pacienteId;
    private final String medicoId;
    private String dataHora; 
    private String local;    
    private String status;   
    private String diagnostico;
    private String prescricao;

    // Construtor
    public Consulta(String id, String pacienteId, String medicoId, String dataHora, String local, String status, 
    String diagnostico, String prescricao) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.dataHora = dataHora;
        this.local = local;
        this.status = status;
        this.diagnostico = diagnostico == null ? "" : diagnostico; // Evita null
        this.prescricao = prescricao == null ? "" : prescricao;
    }

    // Getters e Setters
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

    public void setDataHora(String dataHora) { 
        this.dataHora = dataHora; 
    }

    public String getLocal() { 
        return local; 
    }

    public void setLocal(String local) { 
        this.local = local; 
    }

    public String getStatus() { 
        return status; 
    }

    public void setStatus(String status) { 
        this.status = status; 
    }

    public String getDiagnostico() { 
        return diagnostico; 
    }

    public void setDiagnostico(String diagnostico) { 
        this.diagnostico = diagnostico == null ? "" : diagnostico; // Evita null
    }

    public String getPrescricao() { 
        return prescricao; 
    }

    public void setPrescricao(String prescricao) { 
        this.prescricao = prescricao == null ? "" : prescricao; 
    }

    // toString é usado para printar o objeto
    @Override
    public String toString() {
        return "Consulta{" +
                "id='" + id + '\'' +
                ", pacienteId='" + pacienteId + '\'' +
                ", medicoId='" + medicoId + '\'' +
                ", dataHora='" + dataHora + '\'' +
                ", local='" + local + '\'' +
                ", status='" + status + '\'' +
                ", diagnostico='" + diagnostico + '\'' +
                ", prescricao='" + prescricao + '\'' +
                '}';
    }
}
