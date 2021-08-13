package br.com.supernova.productcatalog.controller;

import br.com.supernova.productcatalog.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    //@Autowired
    //private ProductRepository repository;
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @PostMapping
    public String create(@RequestBody Product product) {
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(product.getId().toString())
                .withObject(product)
                .build();
        //String savedProduct = elasticsearchOperations.index(indexQuery);
        return "savedProduct";
    }

}
