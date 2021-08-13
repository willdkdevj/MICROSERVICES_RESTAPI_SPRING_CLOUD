package br.com.supernova.productcatalog.document;

import br.com.supernova.productcatalog.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProfileProductCatalog {
    private String id;
    private String firstName;
    private String lastName;
    private List<Product> products;
    private Location location;
}
