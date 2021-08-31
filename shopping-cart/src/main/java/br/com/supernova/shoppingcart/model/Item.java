package br.com.supernova.shoppingcart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@NoArgsConstructor
@AllArgsConstructor
@Data
@RedisHash
public class Item {

    @Id
    private Integer productId;
    private Integer amount;

}
