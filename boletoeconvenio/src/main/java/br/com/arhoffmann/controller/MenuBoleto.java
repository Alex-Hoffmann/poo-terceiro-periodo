package br.com.arhoffmann.controller;

import br.com.arhoffmann.service.BoletoService;

import javax.swing.*;

public class MenuBoleto {
    public void opcoesMenuBoleto() throws Exception {
        try {
            String externalTerminal = JOptionPane.showInputDialog("Informe o external terminal: ");
            if (externalTerminal == null) {
                JOptionPane.showMessageDialog(null, "Identificador externalTerminal não informado!", "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            String externalNSUInput = JOptionPane.showInputDialog("Informe o external NSU: ");
            if (externalNSUInput == null) {
                JOptionPane.showMessageDialog(null, "Identificador externalNSU não informado!", "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            Long externalNSU;

            try {
                externalNSU = Long.parseLong(externalNSUInput);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "O identificador external NSU deve ser numérico!", "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            String typeInput = JOptionPane.showInputDialog("Informe o external NSU: ");
            if (typeInput == null) {
                JOptionPane.showMessageDialog(null, "O type deve ser numerico!", "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            Integer type;

            try {
                type = Integer.parseInt(typeInput);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "O type deve ser numerico!", "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            String digitable = JOptionPane.showInputDialog("Informe o codigo de barras do boleto:");
            if (digitable == null) {
                JOptionPane.showMessageDialog(null, "O codigo de barras do boleto não informado", "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            String value = BoletoService.consultBoleto(externalTerminal, externalNSU, type, digitable);

            JOptionPane.showMessageDialog(null, formatValue(value), "Consulta de Boleto",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao consultar o boleto",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private String formatValue(String value) {
        return value.replace(",", ",\n").replace("{", "{\n")
                .replace("}", "\n}");
    }
}
