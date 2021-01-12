package project;

import myinputs.Ler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Produto implements Serializable {
    private String Categoria;
    private String Designacao;
    private double PrecoVenda;
    private double PrecoCompra;
    private int Stock;
    private int ID;
    private static int ultimo = 0;
    private double Desconto;

    public Produto(){
        Categoria = "";
        Designacao = "";
        PrecoVenda = 0.0;
        PrecoCompra = 0.0;
        Stock = 0;
        Desconto = 0;
    }

    public Produto(String Categoria, String Designacao, double PrecoVenda, double PrecoCompra, int Stock){
        ultimo++;
        this.ID = ultimo;
        this.Categoria = Categoria;
        this.Designacao = Designacao;
        this.PrecoVenda = PrecoVenda;
        this.PrecoCompra = PrecoCompra;
        this.Stock = Stock;
        this.Desconto = 0;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getDesignacao() {
        return Designacao;
    }

    public void setDesignacao(String designacao) {
        Designacao = designacao;
    }

    public double getPrecoVenda() {
        return PrecoVenda;
    }

    public void setPrecoVenda(double preco) {
        PrecoVenda = preco;
    }

    public double getPrecoCompra() {
        return PrecoCompra;
    }

    public void setPrecoCompra(double precoCompra) {
        PrecoCompra = precoCompra;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public static void setUltimo(int ultimo) {
        Produto.ultimo = ultimo;
    }

    public static int getUltimo() {
        return ultimo;
    }

    public void setDesconto(double desconto) {
        Desconto = desconto;
    }

    public double getDesconto() {
        return Desconto;
    }

    @Override
    public String toString() {
        return ID + " | " + Categoria + " | " + Designacao + " | " + PrecoVenda + " | " + Stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(Categoria, produto.Categoria) && Objects.equals(Designacao, produto.Designacao);
    }

    /**
     * Função que permite adicionar produtos à lista de produtos
     * @param produtos ArrayList do tipo Produto
     * @return da ArrayList produtos, onde será guardado o novo produto
     */

    public ArrayList<Produto> adicionarProduto(ArrayList<Produto> produtos){
        boolean doesProductExist = false;

        do {
            System.out.println("Indique o nome do produto que pretende adicionar: ");
            Designacao = Ler.umaString();
            doesProductExist = false;

            for (Produto i : produtos){

                // todas as strings são convertidas para minúsculas para que o input não seja case sensitive
                if (i.getDesignacao().toLowerCase().equals(Designacao.toLowerCase())){
                    System.out.println("Este produto ja existe!");
                    doesProductExist = true;
                    break;
                }
            }
        }while(doesProductExist);

        System.out.println("Insira a categoria:");
        Categoria = Ler.umaString();

        System.out.println("Insira o preço de venda:");
        PrecoVenda = Ler.umDouble();

        System.out.println("Qual o preço de compra ao fornecedor?");
        PrecoCompra = Ler.umDouble();

        System.out.println("Quanto stock existe?");
        Stock = Ler.umInt();

        produtos.add(new Produto(Categoria, Designacao, PrecoVenda, PrecoCompra, Stock));

        // atualizar ficheiro
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("src\\project\\files\\produtos.dat"));
            os.writeInt(Produto.getUltimo());
            os.writeObject(produtos); // escrever o objeto no ficheiro
            os.flush(); // os dados são copiados de memória para o disco
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Produto adicionado!");
        return produtos;
    }

    /**
     * Função que permite remover produtos da lista de produtos
     * @param produtos ArrayList do tipo Produto
     * @return da ArrayList produtos, de onde será removido o produto
     */

    public ArrayList<Produto> removerProduto(ArrayList<Produto> produtos) throws Exception {
        int numero = 0;
        numero = verificarNumero();

        for (Produto i : produtos){
            if(i.getID() == numero){
                produtos.remove(i);
                break;
            }
        }
        // atualizar ficheiro
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("src\\project\\files\\produtos.dat"));
            os.writeInt(Produto.getUltimo());
            os.writeObject(produtos); // escrever o objeto no ficheiro
            os.flush(); // os dados são copiados de memória para o disco
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Produto removido!\n");
        return produtos;
    }

    /**
     * Função que permite editar um produto da lista de produtos
     * @param produtos ArrayList do tipo Produto
     * @return da ArrayList produtos, já com o produto editado
     */

    public ArrayList<Produto> editarProduto(ArrayList<Produto> produtos) {
        boolean produtoExiste = false;
        System.out.println("Qual o ID do produto a editar?");
        int editarID = Ler.umInt();

        for(Produto produto : produtos){
            if(produto.getID() == editarID){
                System.out.println("Produto existe! Insira os novos dados abaixo:");

                System.out.println("Insira a categoria:");
                produto.setCategoria(Ler.umaString());

                System.out.println("Insira a designação:");
                produto.setDesignacao(Ler.umaString());

                System.out.println("Insira o preço de venda:");
                produto.setPrecoVenda(Ler.umDouble());

                System.out.println("Qual o preço de compra ao fornecedor?");
                produto.setPrecoCompra(Ler.umDouble());

                System.out.println("Quanto stock existe?");
                produto.setStock(Ler.umInt());

                produtoExiste = true;
                break;
            }
        }

        if(!produtoExiste){
            System.out.println("Produto não encontrado!");
        }

        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("src\\project\\files\\produtos.dat"));
            os.writeInt(Produto.getUltimo());
            os.writeObject(produtos); // escrever o objeto no ficheiro
            os.flush(); // os dados são copiados de memória para o disco
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return produtos;
    }
    
    /**
     * Função que permite adicionar uma promoção a um produto
     * @param produtos ArrayList do tipo Produto
     * @return da ArrayList produtos, onde será guardado o produto com uma % de desconto
     */

    public ArrayList<Produto> adicionarPromo(ArrayList<Produto> produtos) throws Exception{
        int numero;
        double desconto = 0;
        boolean numerovalido = false;

        numero = verificarNumero();
        for (Produto i: produtos){
            if(i.getID()==numero){
                System.out.println("O produto foi encontrado, introduza a percentagem de desconto que quer aplicar: ");
                do{
                    numerovalido=true;
                    try{
                        desconto = Ler.umInt();
                    }
                    catch(Exception e){
                        System.out.println("Desconto invalido!");
                        numerovalido=false;
                    }
                }while(!numerovalido);

                i.setPrecoVenda(i.getPrecoVenda()*((100-desconto)/100));
                i.setDesconto(desconto);
                break;
            }
        }

        // atualizar ficheiro
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("src\\project\\files\\produtos.dat"));
            os.writeInt(Produto.getUltimo());
            os.writeObject(produtos); // escrever o objeto no ficheiro
            os.flush(); // os dados são copiados de memória para o disco
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return produtos;
    }

    /**
     * Função que permite remover uma promoção a um produto
     * @param produtos ArrayList do tipo Produto
     * @return da ArrayList produtos, onde será guardado o produto sem desconto
     */

    public ArrayList<Produto> removerPromo(ArrayList<Produto> produtos) throws Exception{
        int numero;
        double desconto = 0;
        boolean numerovalido = false;

        numero = verificarNumero();
        for (Produto i: produtos){
            if(i.getID() == numero){
                i.setPrecoVenda(i.getPrecoVenda()/((100-i.getDesconto())/100));
                i.setDesconto(0);
                System.out.println("O produto foi encontrado e o desconto removido!");
                break;
            }
        }

        // atualizar ficheiro
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("src\\project\\files\\produtos.dat"));
            os.writeInt(Produto.getUltimo());
            os.writeObject(produtos); // escrever o objeto no ficheiro
            os.flush(); // os dados são copiados de memória para o disco
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return produtos;
    }

    /**
     * Função utilizada noutras funções que permite ao user introduzir um input e que verifica se o mesmo é do tipo int > 0
     * @return do número
     */

    public int verificarNumero(){
        int n=0;
        boolean numerovalido=false;

        do{
            /*
             assumimos que o utilizador introduziu o input correcto, desta forma evitamos erros de sair
             do loop com um input errado caso o 'numerovalido=true;' fique dentro do 'try{}'
            */

            numerovalido=true;
            try{
                System.out.println("Introduza um ID valido: ");
                n=Ler.umInt();
                verificarNumeroInput(n);
            }
            catch(Exception e){
                System.out.println("ID invalido!");
                numerovalido=false;
            }

        }while(!numerovalido);

        System.out.println("O ID '"+n+"' existe!");

        return n;
    }

    /**
     * Função para mostrar uma mensagem de erro caso o input seja diferente de um input do tipo int > 0
     * @param n
     * @throws Exception
     */
    public void verificarNumeroInput(int n) throws Exception {
        if (n < 1){
            throw new Exception("Introduza um numero maior que zero");
        }
    }
}
