package dao;

import java.util.List;

import model.Product;

public interface ProductDao {
	// create
		void add(Product product);

	// read
	List<Product> selectAll();// select * from product
	
	Product selectById(int id);

	// update
	void update(Product product);
	
	void updateQtyById(int id, int shoppingQty);

	// delete
	void delete(int id);
}
