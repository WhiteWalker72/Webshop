package domain.cart;

import domain.component.ComponentServices;
import dto.ProductDTO;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Integer, Integer> cart = new HashMap<>();

    public Cart() {


    }

    public void add(Integer product, Integer amount) {

        if(cart.containsKey(product))
            cart.put(product, cart.get(product) + amount);
        else
            cart.put(product, amount);
    }

    public void remove(Integer product) {

        if(cart.containsKey(product))
            cart.remove(product);
    }

    public Map<ProductDTO, Integer> getProducts() {

        Map<ProductDTO, Integer> products = new HashMap<>();

        for (Map.Entry<Integer, Integer> cartProduct : cart.entrySet()) {

            products.put(ComponentServices.getInstance().getProductDTO(cartProduct.getKey()), cartProduct.getValue());
        }

        return products;
    }
}
