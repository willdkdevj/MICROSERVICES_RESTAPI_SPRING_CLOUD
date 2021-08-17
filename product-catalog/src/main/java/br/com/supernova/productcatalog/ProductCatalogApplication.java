package br.com.supernova.productcatalog;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ProductCatalogApplication {

	@Autowired
	private RestHighLevelClient highLevelClient;


	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogApplication.class, args);
	}

}
