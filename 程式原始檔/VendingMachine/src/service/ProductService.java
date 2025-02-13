package service;
import java.util.List;

import model.Product;
public interface ProductService {
	//create
	
	//read
	List<Product> findAllProducts();
	Product findProductById(int id);
	
	//update
	void updateProduct(Product product);
	
	void updateQtyById(int id, int shoppingQty);
	
	//delete
}
