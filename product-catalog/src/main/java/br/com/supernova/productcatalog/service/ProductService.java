package br.com.supernova.productcatalog.service;

import br.com.supernova.productcatalog.document.ProfileProductCatalog;
import br.com.supernova.productcatalog.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    public ProfileProductCatalog findById(String id) throws Exception {
        GetRequest getRequest = new GetRequest(INDEX, id);
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        Map<String, Object> resultMap = getResponse.getSource();

        return convertProfileDocument(resultMap);
    }

    public String updateProfile(ProfileProductCatalog product) throws Exception {
        ProfileProductCatalog productCatalog = findById(product.getId());
        UpdateRequest updateRequest = new UpdateRequest(INDEX, productCatalog.getId());

        updateRequest.doc(convertProfileDocumentToMap(product));
        UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);

        return  updateResponse.getResult().name();
    }

    public String deleteProductCatalog(String id) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(INDEX, id);
        DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);

        return deleteResponse.getResult().name();
    }

    public List<ProfileProductCatalog> findProductCatalog(String name) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(INDEX);

        SearchSourceBuilder search = new SearchSourceBuilder();
        MatchQueryBuilder match = QueryBuilders.matchQuery("name", name).operator(Operator.AND);

        search.query(match);
        searchRequest.source(search);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        return getSearchResult(searchResponse);
    }

    public List<ProfileProductCatalog> findAll() throws IOException {
        SearchRequest searchRequest = buildSearchRequest(INDEX);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        return getSearchResult(searchResponse);
    }
    private Map<String, Object> convertProfileDocumentToMap(ProfileProductCatalog document){
        return objectMapper.convertValue(document, Map.class);
    }

    private ProfileProductCatalog convertProfileDocument(Map<String, Object> documentMap) {
        return objectMapper.convertValue(documentMap, ProfileProductCatalog.class);
    }

    private List<ProfileProductCatalog> getSearchResult(SearchResponse response) {
        SearchHit[] hits = response.getHits().getHits();
        List<ProfileProductCatalog> productCatalogList = new ArrayList<>();

        for(SearchHit hit : hits){
            productCatalogList.add(
                    objectMapper.convertValue(
                            hit.getSourceAsMap(), ProfileProductCatalog.class
                    )
            );
        }
        return productCatalogList;
    }

    private SearchRequest buildSearchRequest(String index) {
        SearchRequest request = new SearchRequest();
        return  request.indices(index);
    }
}
