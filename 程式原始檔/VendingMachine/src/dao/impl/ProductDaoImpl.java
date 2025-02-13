package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ProductDao;
import model.Product;
import util.DbConnection;

public class ProductDaoImpl implements ProductDao {

	public static void main(String[] args) {
		/*
		Product product = new Product("雪碧", 25, 11);	
		new ProductDaoImpl().add(product);
		*/
		/*
		List<Product> products=new ProductDaoImpl().selectAll();
		
		for (Product product : products) {
			System.out.println(product.getId() + "\t" + product.getName() + "\t" + product.getPrice() + "\t"
				+ product.getStockQty());
		}
		*/
		/*
		Product product=new ProductDaoImpl().selectById(2);
		System.out.println(product.getId() + "\t" + product.getName() + "\t" + product.getPrice() + "\t"
				+ product.getStockQty());
		*/
		/*
		Product product = new Product();
		product.setId(11);
		product.setName("雪碧1");
		product.setPrice(35);
		product.setStockQty(12);
		new ProductDaoImpl().update(product);
		*/
		//new ProductDaoImpl().delete(11);
		
		new ProductDaoImpl().updateQtyById(1, 2);
	}
	private static Connection conn = DbConnection.getDb();
	
	@Override
	public void add(Product product) {
		String sql = "insert into product (name, price, stock_qty)"
				+ "values (?, ?, ?)";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setInt(2, product.getPrice());
			preparedStatement.setInt(3, product.getStockQty());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Product> selectAll() {
		List<Product> products = new ArrayList();
		String sql = "select * from product ";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getInt("id"));
				product.setName(resultSet.getString("name"));
				product.setPrice(resultSet.getInt("price"));
				product.setStockQty(resultSet.getInt("stock_qty"));
				products.add(product);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return products;
	}

	@Override
	public Product selectById(int id) {
		Product product = null;
		String sql = "select * from product where id = ? ";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if  (resultSet.next()) {
				product = new Product();
				product.setId(resultSet.getInt("id"));
				product.setName(resultSet.getString("name"));
				product.setPrice(resultSet.getInt("price"));
				product.setStockQty(resultSet.getInt("stock_qty"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return product;
	}

	@Override
	public void update(Product product) {
		String sql = "update product set name=?, price=?, stock_Qty = ? where id = ?";
		try {
			PreparedStatement  preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setInt(2, product.getPrice());
			preparedStatement.setInt(3, product.getStockQty());
			preparedStatement.setInt(4, product.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(int id) {
		String sql = "delete from product where id = ?";
		try {
			PreparedStatement  preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateQtyById(int id, int shoppingQty) {
		String sql = "update product set stock_qty = stock_qty - ? where id = ?";
		try {
			PreparedStatement  preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, shoppingQty);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
