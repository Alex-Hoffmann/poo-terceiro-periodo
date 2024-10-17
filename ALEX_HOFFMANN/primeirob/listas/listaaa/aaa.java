package ALEX_HOFFMANN.primeirob.prova;

import java.util.Scanner;

public class ProvaUm {
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

    static class Hotel {
        Quarto[] quartos = new Quarto[100];
        int indexQuarto = 0;

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

        public void reservarQuarto(int numeroQuarto) {
            for (int i = 0; i < indexQuarto; i++) {
                if (quartos[i].numero == numeroQuarto && quartos[i].disponivel) {
                    quartos[i].disponivel = false;
                    System.out.println("Reserva realizada com sucesso!");
                    return;
                }
            }
            System.out.println("Quarto não disponível.");
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
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();

        hotel.cadastrarQuarto(101, 100.0);
        hotel.cadastrarQuarto(102, 150.0);
        hotel.listarQuartos();

        hotel.reservarQuarto(101);
        hotel.realizarCheckout(101);

        double total = hotel.calcularTotalDiarias(102, 3); // Calcula total para 3 dias
        System.out.println("Total a pagar: " + total);

        scanner.close();
    }
}
