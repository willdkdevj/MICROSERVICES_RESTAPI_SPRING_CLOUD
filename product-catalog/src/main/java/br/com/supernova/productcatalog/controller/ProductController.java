package br.com.supernova.productcatalog.controller;

import br.com.supernova.productcatalog.document.ProfileProductCatalog;
import br.com.supernova.productcatalog.model.Product;
import br.com.supernova.productcatalog.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> create(@RequestBody ProfileProductCatalog document) throws IOException {
        String profileDocument = service.createProfileDocument(document);
        return ResponseEntity.ok().body(profileDocument);
    }

    @PutMapping("/up")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> update(@RequestBody ProfileProductCatalog document) throws Exception {
        String updateProfile = service.updateProfile(document);
        return ResponseEntity.ok().body(updateProfile);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProfileProductCatalog> findById(@PathVariable String id) throws Exception {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ProfileProductCatalog> findAll() throws IOException {
        List<ProfileProductCatalog> productCatalogs = service.findAll();
        return productCatalogs;
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<ProfileProductCatalog> searchDocument(@RequestParam(value = "name") String name) throws IOException {
        return service.findProductCatalog(name);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String, String>> delete(@PathVariable String id) throws Exception {
        String deleteProductCatalog = service.deleteProductCatalog(id);
        Map<String, String> deleteMap = new HashMap<>();
        deleteMap.put(deleteProductCatalog, HttpStatus.OK.name());
        return ResponseEntity.ok().body(deleteMap);
    }

}
