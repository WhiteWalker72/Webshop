package persistence;

import java.sql.SQLException;
import java.util.ArrayList;

import domain.Aanbieding.Aanbieding;
import domain.product.Product;

public interface ProductDAO {

    // Get product by ID
    Product getProduct(int productID) throws SQLException;

    // Get alle producten
    ArrayList<Product> getAllProducts() throws SQLException;

    // Get products by category
    ArrayList<Product> getProductsByCategoryID(int categoryID) throws SQLException;

    // Get categoryID by name
    int getCategoryIDbyName(String nameCategory) throws SQLException;

    // Get product by name
    Product getProductbyName(String nameProduct) throws SQLException;

    // Get bijbehorende aanbiedingen
    ArrayList<Aanbieding> getProductsPerAanbieding(int aanbiedingID) throws SQLException;

    void updateProduct(Product product) throws SQLException;

    void deleteProductbyID(int productid) throws SQLException; // moet nog een exception gemaakt worden voor als product niet bestaat
}
