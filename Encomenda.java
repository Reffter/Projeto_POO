package project;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import myinputs.Ler;

public class Encomenda extends Produto {
    private static int ultimo = 0;
    private int IDencomenda = 0;
    private int qtd = 0;
    private String estado;
    private int NIFencomenda = 0;

    public Encomenda(Produto p, int qtd, int NIFencomenda){
        ultimo++;
        super.setDesignacao(p.getDesignacao());
        super.setPrecoVenda(p.getPrecoVenda());
        super.setID(p.getID());
        this.IDencomenda = ultimo;
        this.qtd = qtd;
        this.estado = "Não entregue";
        this.NIFencomenda = NIFencomenda;
    }

    public Encomenda(){}

    public int getIDencomenda() {
        return IDencomenda;
    }
    public void setIDencomenda(int IDencomenda) {
        this.IDencomenda = IDencomenda;
    }

    public int getQtd() {
        return qtd;
    }
    public void setQtd(int qnt) {
        this.qtd = qtd;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNIFencomenda() {
        return NIFencomenda;
    }
    public void setNIFencomenda(int NIFencomenda) {
        this.NIFencomenda = NIFencomenda;
    }

    public static void setUltimo(int ultimo) {
        Encomenda.ultimo = ultimo;
    }

    public static int getUltimo() {
        return ultimo;
    }


    /**
     * Função que permite alterar o estado de uma encomenda efetuada
     * @param encomendas ArrayList do tipo Produto
     * @return Vai ser retornada a encomenda cujo o estado foi alterado.
     */

    public ArrayList<Produto> alterarEstado(ArrayList<Produto> encomendas) {
        int lerIDencomenda = 0;
        System.out.println("Qual o ID de encomenda a alterar o estado?");
        lerIDencomenda = Ler.umInt();

        for (Produto encomenda : encomendas) {
            if (((Encomenda) encomenda).getIDencomenda() == lerIDencomenda) {
                System.out.println("Introduza o estado da encomenda:");
                ((Encomenda) encomenda).setEstado(Ler.umaString());
                System.out.println("Estado alterado!");

                try {
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("src\\project\\files\\encomendas.dat"));
                    os.writeInt(Encomenda.getUltimo());
                    os.writeObject(encomendas); // escrever o objeto no ficheiro
                    os.flush(); // os dados são copiados de memória para o disco
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

                return encomendas;
            }
        }
        System.out.println("Estado não alterado!\n");
        return encomendas;
    }


    /**
     * Função que permite ao cliente realizar uma encomenda
     * @param produtos ArrayList do tipo Produto
     * @param encomendas ArrayList do tipo Produto
     * @param NIF - NIF necessário para atribuir a encomenda realizada ao cliente que a realizou
     * @return A função vai retornar um Array que contêm as encomendas efetuadas pelo cliente
     */

    public ArrayList<ArrayList<Produto>> realizarEncomenda(ArrayList<Produto> produtos, ArrayList<Produto> encomendas , int NIF){
        System.out.println("Qual o ID do produto?");
        super.setID(Ler.umInt());

        System.out.println("Qual a quantidade a encomendar?");
        this.qtd = Ler.umInt();

        boolean encomendaRealizada = false;

        for (Produto produto: produtos) {
            if(produto.getID() == super.getID() && qtd <= produto.getStock()){
                encomendas.add(new Encomenda(produto, this.qtd, NIF));
                produto.setStock(produto.getStock() - qtd);
                System.out.println("Encomenda realizada!");
                encomendaRealizada = true;
                break;
            }
        }

        if(!encomendaRealizada)
            System.out.println("Encomenda não efetuada!");

        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("src\\project\\files\\encomendas.dat"));
            os.writeInt(Encomenda.getUltimo());
            os.writeObject(encomendas); // escrever o objeto no ficheiro

            os = new ObjectOutputStream(new FileOutputStream("src\\project\\files\\produtos.dat"));
            os.writeInt(Produto.getUltimo());
            os.writeObject(produtos); // escrever o objeto no ficheiro

            os.flush(); // os dados são copiados de memória para o disco
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        ArrayList<ArrayList<Produto>> array = new ArrayList<ArrayList<Produto>>();
        array.add(encomendas);
        array.add(produtos);
        return array;
    }

    @Override
    public String toString() {
            return IDencomenda + " | " + getDesignacao() + " | " + qtd + " | " + getPrecoVenda() + " | " + NIFencomenda + " | " + getEstado();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Encomenda encomenda = (Encomenda) o;
        return qtd == encomenda.qtd && estado == encomenda.estado && NIFencomenda == encomenda.NIFencomenda && IDencomenda == encomenda.IDencomenda;
    }
}
