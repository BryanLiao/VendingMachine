package service.impl;

import java.util.List;

import dao.impl.ProductDaoImpl;
import model.Product;
import service.ProductService;

public class ProductServiceImpl implements ProductService {

	public static void main(String[] args) {
		//System.out.println(new ProductServiceImpl().findAllProducts());
	    //System.out.println(new ProductServiceImpl().findProductById(2));
		/*
		Product product = new Product();
		product.setId(1);
		product.setName("紅茶1");
		product.setPrice(101);
		product.setStockQty(50);
	    new ProductServiceImpl().updateProduct(product);
		*/
	}

	private static ProductDaoImpl productDaoImpl =  new ProductDaoImpl();
	
	@Override
	public List<Product> findAllProducts() {
		return productDaoImpl.selectAll();
	}

	@Override
	public Product findProductById(int id) {
		return productDaoImpl.selectById(id);
	}

	@Override
	public void updateProduct(Product product) {
		productDaoImpl.update(product);
	}

	@Override
	public void updateQtyById(int id, int shoppingQty) {
		productDaoImpl.updateQtyById(id, shoppingQty);
	}
	
	

}
