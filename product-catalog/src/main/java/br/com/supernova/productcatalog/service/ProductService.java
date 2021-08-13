package br.com.supernova.productcatalog.service;

import br.com.supernova.productcatalog.document.ProfileProductCatalog;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class ProductService {
    private final String INDEX = "location_map";
    //private final String TYPE = "_doc";

    private RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(
                    new HttpHost("localhost", 9200, "http"),
                    new HttpHost("localhost", 9201, "http")));
    private ObjectMapper objectMapper;

    @Autowired
    public ProductService(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    public String createProfileDocument(ProfileProductCatalog document) throws IOException {
        UUID uuid = UUID.randomUUID();
        document.setId(uuid.toString());

        IndexRequest indexRequest = new IndexRequest(INDEX)
            .source(convertProfileDocumentToMap(document), XContentType.JSON);

        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);

        // Toda conexão deve ser encerrada após conclusão da tarefa
        client.close();
        return indexResponse.getResult().name();
    }

    private Map<String, Object> convertProfileDocumentToMap(ProfileProductCatalog document){
        return objectMapper.convertValue(document, Map.class);
    }
}
