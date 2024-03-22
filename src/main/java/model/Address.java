package model;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "xmlcep")
public class Address {
    private String cep, logradouro, complemento,
            bairro, localidade, uf,
            ibge, gia, ddd, siafi;
}
