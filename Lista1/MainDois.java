package Lista1;

import javax.swing.JOptionPane;


class CalculadoraException extends Exception {
    public CalculadoraException(String mensagem) {
        super(mensagem);
        JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}

public class MainDois {
    public static void main(String[] args) {
        try {
            calcular();
        } catch (CalculadoraException e) {

        }
    }


    public static void calcular() throws CalculadoraException {
        String[] operacoes = {"Adição", "Subtração", "Multiplicação", "Divisão"};
        int escolha = JOptionPane.showOptionDialog(null, "Escolha uma operação:", "Calculadora",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, operacoes, operacoes[0]);

        if (escolha == -1) {
            throw new CalculadoraException("Nenhuma operação foi escolhida.");
        }

        try {
            double num1 = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o primeiro número:", "Entrada", JOptionPane.QUESTION_MESSAGE));
            double num2 = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o segundo número:", "Entrada", JOptionPane.QUESTION_MESSAGE));

            double resultado = 0;
            switch (escolha) {
                case 0:
                    resultado = num1 + num2;
                    break;
                case 1:
                    resultado = num1 - num2;
                    break;
                case 2:
                    resultado = num1 * num2;
                    break;
                case 3:
                    if (num2 == 0) {
                        throw new CalculadoraException("Divisão por zero não permitida.");
                    }
                    resultado = num1 / num2;
                    break;
                default:
                    throw new CalculadoraException("Operação inválida.");
            }

            JOptionPane.showMessageDialog(null, "Resultado: " + resultado, "Resultado", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            throw new CalculadoraException("Entrada inválida. Por favor, insira números.");
        }
    }
}
