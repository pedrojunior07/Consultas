/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package misau.model;

/**
 *
 * @author Pedro Manjate
 */
public class Consulta {
    private int id;
    private int utenteId;
    private int funcionarioHospitalId;
    private int servicoId;
    private int hospitalId;
    private String dataConsulta; // Usar String para formato de data (ex.: "yyyy-MM-dd")
    private String horaConsulta; // Usar String para hora (ex.: "HH:mm")
    private String statusConsulta;
    private String prioridade;
    private String dataMarcacao; // Nullable

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUtenteId() {
        return utenteId;
    }

    public void setUtenteId(int utenteId) {
        this.utenteId = utenteId;
    }


    public int getFuncionarioHospitalId() {
        return funcionarioHospitalId;
    }

    public void setFuncionarioHospitalId(int funcionarioHospitalId) {
        this.funcionarioHospitalId = funcionarioHospitalId;
    }

    public int getServicoId() {
        return servicoId;
    }

    public void setServicoId(int servicoId) {
        this.servicoId = servicoId;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(String dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getHoraConsulta() {
        return horaConsulta;
    }

    public void setHoraConsulta(String horaConsulta) {
        this.horaConsulta = horaConsulta;
    }

    public String getStatusConsulta() {
        return statusConsulta;
    }

    public void setStatusConsulta(String statusConsulta) {
        this.statusConsulta = statusConsulta;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getDataMarcacao() {
        return dataMarcacao;
    }

    public void setDataMarcacao(String dataMarcacao) {
        this.dataMarcacao = dataMarcacao;
    }

    
    
}
