package ALEX_HOFFMANN.primeirob.prova;

import java.util.Scanner;

class primeiraprova {
    static class Cliente {
        String nome;
        String documentoIdentidade;

        public Cliente(String nome, String documentoIdentidade) {
            this.nome = nome;
            this.documentoIdentidade = documentoIdentidade;
        }
    }

    static class Quarto {
        int numero;
        boolean disponivel;
        double precoDiaria;

        public Quarto(int numero, double precoDiaria) {
            this.numero = numero;
            this.precoDiaria = precoDiaria;
            this.disponivel = true;
        }
    }

    static class Reserva {
        Cliente cliente;
        Quarto quarto;
        String dataInicio;
        String dataFim;

        public Reserva(Cliente cliente, Quarto quarto, String dataInicio, String dataFim) {
            this.cliente = cliente;
            this.quarto = quarto;
            this.dataInicio = dataInicio;
            this.dataFim = dataFim;
        }
    }

    static class Hotel {
        Cliente[] clientes = new Cliente[100];
        Quarto[] quartos = new Quarto[100];
        Reserva[] reservas = new Reserva[100];
        int indexCliente = 0;
        int indexQuarto = 0;
        int indexReserva = 0;

        public void cadastrarCliente(String nome, String documento) {
            if (indexCliente < 100) {
                clientes[indexCliente++] = new Cliente(nome, documento);
            } else {
                System.out.println("Número máximo de clientes atingido.");
            }
        }

        public void cadastrarQuarto(int numero, double precoDiaria) {
            if (indexQuarto < 100) {
                quartos[indexQuarto++] = new Quarto(numero, precoDiaria);
            } else {
                System.out.println("Número máximo de quartos atingido.");
            }
        }

        public void listarQuartos() {
            for (int i = 0; i < indexQuarto; i++) {
                System.out.println("Quarto: " + quartos[i].numero + ", Disponível: " + quartos[i].disponivel + ", Preço: " + quartos[i].precoDiaria);
            }
        }

        public void reservarQuarto(String documentoIdentidade, int numeroQuarto, String dataInicio, String dataFim) {
            Cliente cliente = null;
            for (int i = 0; i < indexCliente; i++) {
                if (clientes[i].documentoIdentidade.equals(documentoIdentidade)) {
                    cliente = clientes[i];
                    break;
                }
            }

            if (cliente != null) {
                for (int i = 0; i < indexQuarto; i++) {
                    if (quartos[i].numero == numeroQuarto && quartos[i].disponivel) {
                        reservas[indexReserva++] = new Reserva(cliente, quartos[i], dataInicio, dataFim);
                        quartos[i].disponivel = false;
                        System.out.println("Reserva realizada com sucesso!");
                        return;
                    }
                }
                System.out.println("Quarto não disponível.");
            } else {
                System.out.println("Cliente não encontrado.");
            }
        }

        public void realizarCheckout(int numeroQuarto) {
            for (int i = 0; i < indexQuarto; i++) {
                if (quartos[i].numero == numeroQuarto) {
                    quartos[i].disponivel = true;
                    System.out.println("Checkout realizado com sucesso!");
                    return;
                }
            }
            System.out.println("Quarto não encontrado.");
        }

        public double calcularTotalDiarias(int numeroQuarto, int dias) {
            for (int i = 0; i < indexQuarto; i++) {
                if (quartos[i].numero == numeroQuarto) {
                    return quartos[i].precoDiaria * dias;
                }
            }
            return 0;
        }

        public boolean verificarDisponibilidade(int numeroQuarto) {
            for (int i = 0; i < indexQuarto; i++) {
                if (quartos[i].numero == numeroQuarto) {
                    return quartos[i].disponivel;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();


        hotel.cadastrarCliente("Pedro", "123456789");
        hotel.cadastrarQuarto(101, 100.0);
        hotel.listarQuartos();

        hotel.reservarQuarto("123456789", 101, "2023-01-01", "2023-01-05");

        hotel.realizarCheckout(101);

        double total = hotel.calcularTotalDiarias(101, 4);
        System.out.println("Total a pagar: " + total);

        scanner.close();
    }
}
