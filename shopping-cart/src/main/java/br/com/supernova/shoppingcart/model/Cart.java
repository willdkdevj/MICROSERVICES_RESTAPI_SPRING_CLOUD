package br.com.supernova.shoppingcart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@RedisHash("cart")
public class Cart {

    @Id
    private Integer id;
    private List<Item> items;

}
