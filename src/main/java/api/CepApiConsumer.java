package api;


import model.Address;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class CepApiConsumer {
    public Address getAddress(String cep) {
        try {
            //configura a URL da api
            var url = "http://viacep.com.br/ws/".concat(cep).concat("/xml/");

            //cria o objeto responsavel pela request
            var request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url));

            //cria o client para o request
            var httpCLient = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(8))
                    .build();

            //cria o objeto response da request
            var response = httpCLient.send(request.build(), HttpResponse.BodyHandlers.ofString());

            // Verifica se a resposta foi bem-sucedida
            if (response.statusCode() != 200) {
                throw new RuntimeException("O CEP digitado é inválido.");
            }

            //tenta criar um objeto reader com o conteudo da resposta XML convertido para uma cadeia de caracteres
            var reader = new StringReader(response.body());

            //biblioteca que converte o formato XML em objeto Java
            var jaxbContext = JAXBContext.newInstance(Address.class);

            //cria um objeto unmarshaller que permite a desserialização do reader
            var unmarshaller = jaxbContext.createUnmarshaller();

            // Verifica se a tag <erro> está presente no XML
            if (response.body().contains("<erro>true</erro>")) {
                throw new RuntimeException("Esse CEP não existe.");
            }

            //retorna um objeto address com a restauração do unmarshaller
            return (Address) unmarshaller.unmarshal(reader);
        } catch (Exception ex) {
            throwError(ex.getMessage());
        }
        return null;
    }

    private void throwError(String msg) {
        JOptionPane.showMessageDialog(null, msg.concat("\nTente novamente."));
    }
}
