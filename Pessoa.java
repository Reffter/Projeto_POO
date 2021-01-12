package project;

import java.io.Serializable;

public class Pessoa implements Serializable {
    protected String primeiroNome;
    protected String ultimoNome;
    protected int NIF;
    protected String endereco;

    public Pessoa(){
        primeiroNome = "";
        ultimoNome = "";
        NIF = 0;
        endereco = "";
    }

    /**
     * Método para criar uma pessoa
     */

    public Pessoa(String primeiroNome, String ultimoNome, int NIF, String endereco){
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.NIF = NIF;
        this.endereco = endereco;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public int getNIF() {
        return NIF;
    }

    public void setNIF(int NIF) {
        this.NIF = NIF;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "primeiroNome='" + primeiroNome + '\'' +
                ", ultimoNome='" + ultimoNome + '\'' +
                ", NIF=" + NIF +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
