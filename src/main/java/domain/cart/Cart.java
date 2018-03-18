package domain.cart;

import domain.component.ComponentServices;
import domain.component.Product;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Product, Integer> cart = new HashMap<>();

    public Cart() {

    }

    public void add(Integer productId, Integer amount) {
        Map.Entry<Product, Integer> prodEntry = getProductAmountById(productId);

        if (prodEntry == null) {
            cart.put(ComponentServices.getInstance().getProduct(productId), amount);
        } else {
            cart.put(prodEntry.getKey(), prodEntry.getValue() + amount);
        }
    }

    public void edit(Integer productId, Integer amount) {
        Map.Entry<Product, Integer> prodEntry = getProductAmountById(productId);

        if (prodEntry != null) {
            cart.put(prodEntry.getKey(), amount);
        }
    }

    public void remove(Integer productId) {
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            if (entry.getKey().getId() == productId) {
                cart.remove(entry.getKey());
                return;
            }
        }
    }

    public Map<Product, Integer> getProducts() {
        return cart;
    }

    private Map.Entry<Product, Integer> getProductAmountById(int productId) {
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            if (entry.getKey().getId() == productId) {
                return entry;
            }
        }
        return null;
    }

}
