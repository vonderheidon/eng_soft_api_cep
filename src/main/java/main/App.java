package main;

import api.CepApiConsumer;

import javax.swing.*;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        var cepConsumer = new CepApiConsumer();

        var cep = JOptionPane.showInputDialog("Digite o CEP: ");

        var address = cepConsumer.getAddress(cep);

        var addressBuilder = new StringBuilder();

        JOptionPane.showMessageDialog(null,
                addressBuilder.append("Endereço: \n")
                        .append("Cidade: "+address.getLocalidade())
                        .append("\nUF: "+address.getUf())
                        .append("\nRua: "+address.getLogradouro())
                        .append("\nBairro: "+address.getBairro())
                        .append("\nDDD: "+address.getDdd())
                );
    }
}
