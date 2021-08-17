package br.com.supernova.productcatalog.controller;

import br.com.supernova.productcatalog.document.ProfileProductCatalog;
import br.com.supernova.productcatalog.model.Product;
import br.com.supernova.productcatalog.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @GetMapping("/test")
    public String test(){
        return "Tudo bem por aqui, e por aí? Espero que esteja!";
    }

    @PostMapping
    public String create(@RequestBody ProfileProductCatalog document) throws IOException {
        String profileDocument = service.createProfileDocument(document);
        return profileDocument;
    }

    @GetMapping("/{id}")
    public ProfileProductCatalog findById(@PathVariable String id) {
        return null;
    }
}
