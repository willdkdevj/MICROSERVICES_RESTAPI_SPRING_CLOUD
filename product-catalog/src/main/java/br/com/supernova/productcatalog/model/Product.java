package br.com.supernova.productcatalog.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@Document(indexName = "product")
@TypeAlias("product")
public class Product {

    @Id
    @Field(type = FieldType.Long, name = "code")
    private Long id;

    @Field(type = FieldType.Text, name = "product")
    private String name;

    @Field(type = FieldType.Integer, name = "amount")
    private Integer amount;
}
