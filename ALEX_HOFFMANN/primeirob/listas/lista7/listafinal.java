package ALEX_HOFFMANN.primeirob.listas.lista7;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


class Item {
    private String nome;
    private double preco;

    public Item(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Item: " + nome + ", Preço: R$" + preco;
    }
}


class Pedido {
    private String cliente;
    private Map<Item, Integer> itens;

    public Pedido(String cliente) {
        this.cliente = cliente;
        this.itens = new HashMap<>();
    }

    public void adicionarItem(Item item, int quantidade) {
        itens.put(item, quantidade);
    }

    public void listarItens() {
        System.out.println("Pedido do cliente: " + cliente);
        for (Map.Entry<Item, Integer> entry : itens.entrySet()) {
            System.out.println(entry.getKey() + " | Quantidade: " + entry.getValue());
        }
    }
}


class Empresa {
    private List<Item> itens;
    private List<Pedido> pedidos;

    public Empresa() {
        this.itens = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

    public void cadastrarItem(String nome, double preco) {
        Item novoItem = new Item(nome, preco);
        itens.add(novoItem);
    }

    public void listarItens() {
        if (itens.isEmpty()) {
            System.out.println("Nenhum item cadastrado.");
        } else {
            for (Item item : itens) {
                System.out.println(item);
            }
        }
    }

    public Item buscarItemPorNome(String nome) {
        for (Item item : itens) {
            if (item.getNome().equalsIgnoreCase(nome)) {
                return item;
            }
        }
        return null;
    }

    public void criarPedido(String cliente, String nomeItem, int quantidade) {
        Item item = buscarItemPorNome(nomeItem);
        if (item != null) {
            Pedido novoPedido = new Pedido(cliente);
            novoPedido.adicionarItem(item, quantidade);
            pedidos.add(novoPedido);
            System.out.println("Pedido criado com sucesso!");
        } else {
            System.out.println("Item não encontrado!");
        }
    }
}


public class listafinal {
    private static Scanner scanner = new Scanner(System.in);
    private Empresa empresa = new Empresa();

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Cadastrar item");
            System.out.println("2. Listar itens");
            System.out.println("3. Buscar item por nome");
            System.out.println("4. Criar pedido");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    cadastrarItem();
                    break;
                case 2:
                    listarItens();
                    break;
                case 3:
                    buscarItemPorNome();
                    break;
                case 4:
                    criarPedido();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void cadastrarItem() {
        System.out.print("Digite o nome do item: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o preço do item: ");
        double preco = scanner.nextDouble();
        scanner.nextLine();  // Consumir a quebra de linha

        empresa.cadastrarItem(nome, preco);
        System.out.println("Item cadastrado com sucesso!");
    }

    private void listarItens() {
        empresa.listarItens();
    }

    private void buscarItemPorNome() {
        System.out.print("Digite o nome do item a buscar: ");
        String nome = scanner.nextLine();

        Item item = empresa.buscarItemPorNome(nome);
        if (item != null) {
            System.out.println("Item encontrado: " + item);
        } else {
            System.out.println("Item não encontrado.");
        }
    }

    private void criarPedido() {
        System.out.print("Digite o nome do cliente: ");
        String cliente = scanner.nextLine();
        System.out.print("Digite o nome do item: ");
        String nomeItem = scanner.nextLine();
        System.out.print("Digite a quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();  // Consumir a quebra de linha

        empresa.criarPedido(cliente, nomeItem, quantidade);
    }

    public static void main(String[] args) {
        listafinal menu = new listafinal();
        menu.exibirMenu();
    }
}
