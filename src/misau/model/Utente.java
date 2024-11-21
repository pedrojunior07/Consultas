/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package misau.model;

/**
 *
 * @author Pedro Manjate
 */
public class Utente {
    private int id;
    private String nome;
    private String localidade;
    private String genero;
    private String tipoSaguineo;
    private String email;
    private String numeroSaude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTipoSaguineo() {
        return tipoSaguineo;
    }

    public void setTipoSaguineo(String tipoSaguineo) {
        this.tipoSaguineo = tipoSaguineo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroSaude() {
        return numeroSaude;
    }

    public void setNumeroSaude(String numeroSaude) {
        this.numeroSaude = numeroSaude;
    }
    
    
}
