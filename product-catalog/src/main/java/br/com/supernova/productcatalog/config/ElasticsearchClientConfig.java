package br.com.supernova.productcatalog.config;

import br.com.supernova.productcatalog.model.Product;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchCustomConversions;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author William Derek Dias
 *
 */

//@EnableElasticsearchRepositories(basePackages = "br.com.supernova.productcatalog.repository")
//@ComponentScan(basePackages = { "br.com.supernova.productcatalog" })
@Configuration
public class ElasticsearchClientConfig extends AbstractElasticsearchConfiguration {

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {

        final ClientConfiguration clientConfiguration =
                ClientConfiguration
                        .builder()
                        .connectedTo("localhost:9200", "localhost:9300")
                        .build();

        return RestClients
                .create(clientConfiguration)
                .rest();
    }

    //@Bean
    //@Override
    //public ElasticsearchCustomConversions elasticsearchCustomConversions() {
//        return new ElasticsearchCustomConversions(
  //              Arrays.asList(new AddressToMap(), new MapToAddress()));
    //}

    //@WritingConverter
    //static class AddressToMap implements Converter<Product, Map<String, Object>> {

      //  @Override
        //public Map<String, Object> convert(Product source) {
          //  LinkedHashMap<String, Object> target = new LinkedHashMap<>();
          //  target.put("name", source.getName());
            //target.put("amount", source.getAmount());

            //return target;
        //}
    //}

    //@ReadingConverter
    //static class MapToAddress implements Converter<Map<String, Object>, Product> {

//        @Override
  //      public Product convert(Map<String, Object> source) {
    //        Product product = new Product();
      //      product.setName(source.get("name").toString());
        //    product.setName(source.get("amount").toString());

          //  return product;
        //}
    //}

}