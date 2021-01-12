package project;

import myinputs.Ler;
import java.util.ArrayList;

public class Auth{
    private int NIF_auth;
    private String senha_auth;

    public Auth(){
        NIF_auth = 0;
        senha_auth = "";
    }

    /**
     * Função que permite efetuar a autenticação na loja
     * @param pessoa ArrayList do tipo Pessoa;
     * @param account objeto do tipo Login, que permite identificar qual o tipo de Login;
     * @return 0 - Login não efetuado, 1 - Login efetuado como cliente, 2 - Login efetuado como administrador;
     */

    public Login authentication(ArrayList<Pessoa> pessoa, Login account)
    {
        System.out.println("NIF: ");
        NIF_auth = Ler.umInt();
        System.out.println("Senha: ");
        senha_auth = Ler.umaString();

        for(Pessoa pessoas : pessoa) {
            if (pessoas.getNIF() == NIF_auth && ((Conta) pessoas).getSenha().equals(senha_auth)){
                System.out.println("Login efetuado!");

                if(NIF_auth == 1){
                    account = new Login(pessoas, ((Conta) pessoas).getSenha());
                    account.setEstadoLogin(2);
                    return account;
                }
                account = new Login(pessoas, ((Conta) pessoas).getSenha());
                account.setEstadoLogin(1);
                return account;
            }
        }
        System.out.println("Login não efetuado!");

        account = new Login();
        account.setEstadoLogin(0);
        return account;
    }
}