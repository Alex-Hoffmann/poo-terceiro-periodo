package Lista1;

import javax.swing.JOptionPane;


public class MainUm {
    public static void main(String[] args) {

        olaMundo();


        boasVindas();


        mostrarOpcoes();
    }

    // ATV1 - Método que exibe a mensagem "Olá, Mundo!"
    public static void olaMundo() {
        JOptionPane.showMessageDialog(null, "Olá, Mundo!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
    }

    // ATV2 - Método que solicita o nome do usuário e exibe uma mensagem de boas-vindas
    public static void boasVindas() {
        String nome = JOptionPane.showInputDialog(null, "Digite seu nome:", "Entrada", JOptionPane.QUESTION_MESSAGE);
        if (nome != null && !nome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Bem-vindo, " + nome + "!", "Boas-vindas", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Nome não pode ser vazio.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ATV3 - Método que apresenta uma lista de opções e exibe uma mensagem com base na opção escolhida
    public static void mostrarOpcoes() {
        String[] opcoes = {"Opção 1", "Opção 2", "Opção 3"};
        int escolha = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Opções", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
        if (escolha != -1) {
            JOptionPane.showMessageDialog(null, "Você escolheu: " + opcoes[escolha], "Escolha", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma opção foi escolhida.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
