package project;

public class Login extends Pessoa{
    protected String senha;
    private boolean cliente;
    private int estadoLogin;

    public Login() {
        senha = "";
        cliente = true;
        estadoLogin = 0;
    }

    public Login(Pessoa p, String senha){
        super.primeiroNome = p.primeiroNome;
        super.ultimoNome = p.ultimoNome;
        super.NIF = p.NIF;
        super.endereco = p.endereco;
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isCliente() {
        return cliente;
    }

    public void setCliente(boolean cliente) {
        this.cliente = cliente;
    }

    public int getEstadoLogin() {
        return estadoLogin;
    }

    public void setEstadoLogin(int estadoLogin) {
        this.estadoLogin = estadoLogin;
    }

}
