package br.com.arhoffmann.controller;

import javax.swing.*;

public class MenuGeral {
    public void menuController() throws Exception {
        MenuConvenio menuConvenio = new MenuConvenio();
        MenuBoleto menuBoleto = new MenuBoleto();

        while (true) {
            String[] options = {"Listar Convênios", "Consultar Boleto", "Sair"};

            Integer choice = JOptionPane.showOptionDialog(
                    null, "Escolha uma opção do menu: ", "Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                    options,
                    options[0]
            );

            switch (choice) {
                case 0 -> {
                    System.out.println("Consultando Convênios Patron...");
                    menuConvenio.opcoesMenuConvenio();
                }
                case 1 -> {
                    System.out.println("Consultar Boleto Patron");
                    menuBoleto.opcoesMenuBoleto();
                }
                case 2 -> {
                    System.out.println("Saindo Patron...");
                    System.exit(0);
                }
                default -> JOptionPane.showMessageDialog(
                        null, "Desculpe, opção invalida", "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}
