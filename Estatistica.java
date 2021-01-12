package project;

import java.util.ArrayList;

public class Estatistica {

    /**
     * Função para calcular o lucro total das encomendas realizadas pelos clientes
     * @param encomendas ArrayList do tipo Produto;
     * @param produtos ArrayList do tipo Produto;
     * @return somatório dos lucros calculados;
     */

    public double getLucroTotal(ArrayList<Produto> encomendas, ArrayList<Produto> produtos) {
            double somatorioLucros = 0.0;
            for(Produto encomenda: encomendas){
                for(Produto produto : produtos){
                    if(produto.getID() == ((Encomenda)encomenda).getID()) {
                        somatorioLucros = somatorioLucros + ((((Encomenda) encomenda).getPrecoVenda() * (((Encomenda) encomenda).getQtd())) - (produto.getPrecoCompra() * (((Encomenda) encomenda).getQtd())));
                    }
                }
            }
            return somatorioLucros;
    }

    /**
     * Função para determinar qual o cliente que realizou mais encomendas
     * @param encomendas ArrayList do tipo Produto;
     * @param pessoas ArrayList do tipo Produto;
     * @return NIF do cliente que efectuou mais encomendas;
     */

    public int getClienteComMaisEncomendas(ArrayList<Produto> encomendas, ArrayList<Pessoa> pessoas){
        int count =  0, aux = 0, NIFpessoa = 0;

        for (Pessoa pessoa: pessoas) {
            count = 0;
            for (Produto encomenda : encomendas) {
                if(((Encomenda)encomenda).getNIFencomenda() == pessoa.getNIF()){
                    count++;
                }
            }
            if(aux < count){
                aux = count;
                NIFpessoa = pessoa.getNIF();
            }
        }
        return NIFpessoa;
    }

    /**
     * Função que calcula qual o produto mais vendido, de entre os que se encontram no catálogo
     * @param produtos ArrayList do tipo Produto;
     * @param encomendas ArrayList do tipo Produto;
     * @return Vai ser retornado o ID do produto que foi mais vendido;
     */

    public int getProdutoMaisVendido(ArrayList<Produto> produtos, ArrayList<Produto> encomendas){ // o segundo Arraylist do tipo produto é da encomenda
        int aux = 0, count = 0, IDproduto = 0;

        for (Produto produto: produtos) {
            for(Produto encomenda: encomendas){
                if(produto.getID() == ((Encomenda)encomenda).getID()){
                    count++;
                }
            }
            if(aux < count){
                aux = count;
                IDproduto = produto.getID();
            }
        }
        return IDproduto;
    }


    /**
     * Função que lista quais os produtos que se enconcontram em desconto
     * @param produtos ArrayList do tipo Produto;
     */

    public void getProdutosEmDesconto(ArrayList<Produto> produtos){
        ArrayList<Produto> produtosDesconto = new ArrayList<Produto>();
        for (Produto produto: produtos) {
            if(produto.getDesconto() != 0){
                produtosDesconto.add(produto);
            }
        }
        System.out.println(produtosDesconto.toString());
    }
}
