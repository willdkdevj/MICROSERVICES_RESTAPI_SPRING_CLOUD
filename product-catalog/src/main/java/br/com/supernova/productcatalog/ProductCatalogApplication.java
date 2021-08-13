package br.com.supernova.productcatalog;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ProductCatalogApplication {

	//private static final String DEMILITER = ",";

	@Autowired
	private RestHighLevelClient highLevelClient;

	//@Autowired
	//private ProductRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogApplication.class, args);
	}

	//@PreDestroy
	//public void deleteIndex(){
	//	elasticOperations.indexOps(Product.class).delete();
	//}

	//@PostConstruct
	//public void buildIndex(){
	//	elasticOperations.indexOps(Product.class).refresh();
	//	repository.deleteAll();
	//	repository.saveAll(prepareDataset());
	//}

	//private Collection<Product> prepareDataset(){
	//	Resource resource = new ClassPathResource("product-catalog.csv");
	//	List<Product> productList = new ArrayList<Product>();

	//	try(InputStream input = resource.getInputStream();
	//		Scanner scanner = new Scanner(resource.getInputStream());) {
	//		int linha = 0;
	//		while (scanner.hasNextLine()) {
	//			++linha;
	//			String novaLinha = scanner.nextLine();
	//			if (linha == 1) continue;
	//			Optional<Product> product = rowToProductMapper(novaLinha);
	//			if (product.isPresent()) productList.add(product.get());
	//		}
	//	} catch(Exception ex) {
	//		log.error("File read error {}", ex);
	//	}
	//	return productList;
	//}

	//private Optional<Product> rowToProductMapper(final String line){
	//	try(
	//			Scanner rowScanner = new Scanner(line)) {
	//			rowScanner.useDelimiter(DEMILITER);
	//			while(rowScanner.hasNext()){
	//				String name = rowScanner.next();
	//				String amount = rowScanner.next();
	//				return Optional.of(Product.builder()
	//									.name(name)
	//									.amount(amount)
	//									.build());
	//			}
	//	}
	//	return Optional.of(null);
	//}
	//}
}
