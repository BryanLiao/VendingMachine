package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.OrderDetailDao;
import model.Order;
import model.OrderDetail;
import util.DbConnection;

public class OrderDetailDaoImpl implements OrderDetailDao {

	public static void main(String[] args) {
		//OrderDetail orderDetail = new OrderDetail(1, 2, "p1", 2, 3);
		//OrderDetail orderDetail = new OrderDetail(1, 3, "p3", 2, 3);
		//new OrderDetailDaoImpl().add(orderDetail);
		//List<OrderDetail> orderDetails=new OrderDetailDaoImpl().selectAll();
		/*
		List<OrderDetail> orderDetails=new OrderDetailDaoImpl().selectByOrderId(2); 
		for (OrderDetail orderDetail : orderDetails) {
			System.out.println("id:" + orderDetail.getId() + "\torder_id:" + orderDetail.getOrderId()
					+ "\tproduct_id:" + orderDetail.getProductId()
					+ "\tproduct_name:" + orderDetail.getProductName()
					+ "\tprice:" + orderDetail.getPrice()
					+ "\tqty:" + orderDetail.getQty()
					+ "\tcreated_at:" + orderDetail.getCreatedAt());
		}
		*/
		/*
		OrderDetail orderDetail=new OrderDetailDaoImpl().selectById(5);
		System.out.println("id:" + orderDetail.getId() + "\torder_id:" + orderDetail.getOrderId()
		+ "\tproduct_id:" + orderDetail.getProductId()
		+ "\tproduct_name:" + orderDetail.getProductName()
		+ "\tprice:" + orderDetail.getPrice()
		+ "\tqty:" + orderDetail.getQty()
		+ "\tcreated_at:" + orderDetail.getCreatedAt());
		*/
		/*
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setId(6);
		orderDetail.setProductId(31);
		orderDetail.setProductName("p31");
		orderDetail.setPrice(61);
		orderDetail.setQty(62);
		new OrderDetailDaoImpl().update(orderDetail);
		*/
		//new OrderDetailDaoImpl().delete(8);
	}

	private static Connection conn = DbConnection.getDb();

	@Override
	public void add(OrderDetail orderDetail) {
		String sql = "insert into order_detail(order_id, product_id, product_name, price, qty) values(?, ?, ?, ?, ?)";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, orderDetail.getOrderId());
			preparedStatement.setInt(2, orderDetail.getProductId());
			preparedStatement.setString(3, orderDetail.getProductName());
			preparedStatement.setInt(4, orderDetail.getPrice());
			preparedStatement.setInt(5, orderDetail.getQty());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<OrderDetail> selectAll() {
		List<OrderDetail> orderDetails = new ArrayList();
		String sql = "select * from order_detail ";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setId(resultSet.getInt("id"));
				orderDetail.setOrderId(resultSet.getInt("order_id"));
				orderDetail.setProductId(resultSet.getInt("product_id"));
				orderDetail.setProductName(resultSet.getString("product_name"));
				orderDetail.setPrice(resultSet.getInt("price"));
				orderDetail.setQty(resultSet.getInt("qty"));
				orderDetail.setCreatedAt(resultSet.getTimestamp("created_at"));
				orderDetails.add(orderDetail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orderDetails;
	}

	@Override
	public List<OrderDetail> selectByOrderId(int order_id) {
		List<OrderDetail> orderDetails = new ArrayList();
		String sql = "select * from order_detail where order_id = ?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, order_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setId(resultSet.getInt("id"));
				orderDetail.setOrderId(resultSet.getInt("order_id"));
				orderDetail.setProductId(resultSet.getInt("product_id"));
				orderDetail.setProductName(resultSet.getString("product_name"));
				orderDetail.setPrice(resultSet.getInt("price"));
				orderDetail.setQty(resultSet.getInt("qty"));
				orderDetail.setCreatedAt(resultSet.getTimestamp("created_at"));
				orderDetails.add(orderDetail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orderDetails;
	}

	@Override
	public OrderDetail selectById(int id) {
		OrderDetail orderDetail = null;
		String sql = "select * from order_detail where id = ?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				orderDetail = new OrderDetail();
				orderDetail.setId(resultSet.getInt("id"));
				orderDetail.setOrderId(resultSet.getInt("order_id"));
				orderDetail.setProductId(resultSet.getInt("product_id"));
				orderDetail.setProductName(resultSet.getString("product_name"));
				orderDetail.setPrice(resultSet.getInt("price"));
				orderDetail.setQty(resultSet.getInt("qty"));
				orderDetail.setCreatedAt(resultSet.getTimestamp("created_at"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderDetail;
	}

	@Override
	public void update(OrderDetail orderDetail) {
		String sql = "update order_detail set product_id = ?, product_name = ?, price = ?, qty = ? where id = ?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, orderDetail.getProductId());
			preparedStatement.setString(2, orderDetail.getProductName());
			preparedStatement.setInt(3, orderDetail.getPrice());
			preparedStatement.setInt(4, orderDetail.getQty());
			preparedStatement.setInt(5, orderDetail.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "delete from order_detail where id=?";
		try {
			PreparedStatement preparedstatement = conn.prepareStatement(sql);
			preparedstatement.setInt(1, id);
			preparedstatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
