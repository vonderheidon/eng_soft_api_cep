package main;

import api.CepApiConsumer;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException, JAXBException {
        var cepConsumer = new CepApiConsumer();

        var cep = JOptionPane.showInputDialog("Digite o CEP: ");

        var address = cepConsumer.getAddress(cep);

        if (address != null) {
            var addressBuilder = new StringBuilder();

            if (address.getLogradouro().isEmpty() && address.getBairro().isEmpty()) {
                addressBuilder.append("Essa localidade possui CEP único.\n")
                        .append("Cidade: "+address.getLocalidade())
                        .append("\nUF: "+address.getUf())
                        .append("\nDDD: "+address.getDdd());

            } else {
                addressBuilder.append("Cidade: "+address.getLocalidade())
                        .append("\nUF: "+address.getUf())
                        .append("\nRua: "+address.getLogradouro())
                        .append("\nBairro: "+address.getBairro())
                        .append("\nDDD: "+address.getDdd());
            }
            JOptionPane.showMessageDialog(null, addressBuilder);
        }
    }
}
