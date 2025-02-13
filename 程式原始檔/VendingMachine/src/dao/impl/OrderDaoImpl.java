package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.OrderDao;
import model.Order;
import util.DbConnection;

public class OrderDaoImpl implements OrderDao {

	public static void main(String[] args) {
		/*
		Order order = new Order(5);
		new OrderDaoImpl().add(order);
		*/
		/*
		List<Order> orders=new OrderDaoImpl().selectAll();
		for (Order order : orders) {
			System.out.println("id:" + order.getId() + "\tmember_id:" + order.getMemberId() + 
					 "\tcreated_at:"+order.getCreatedAt());
		}
		*/
		/*
		Order order=new OrderDaoImpl().selectById(1);
		System.out.println("id:" + order.getId() + "\tmember_id:" + order.getMemberId() + 
				 "\tcreated_at:"+order.getCreatedAt());
		*/
		/*
		Order order=new OrderDaoImpl().selectLastOrderByMemberId(1);
		System.out.println("id:" + order.getId() + "\tmember_id:" + order.getMemberId() + 
				 "\tcreated_at:"+order.getCreatedAt());
		*/
		/*
		Order order = new Order();
		order.setId(1);
		order.setMemberId(8);
		new OrderDaoImpl().update(order);
		*/
		//new OrderDaoImpl().delete(2);
	}
	
	private static Connection conn = DbConnection.getDb();

	@Override
	public void add(Order order) {
		String sql = "insert into orders(member_id)"
				+ "values(?)";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, order.getMemberId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Order> selectAll() {
		List<Order> orders = new ArrayList();
		String sql = "select * from orders ";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Order order = new Order();
				order.setId(resultSet.getInt("id"));
				order.setMemberId(resultSet.getInt("member_id"));
				order.setCreatedAt(resultSet.getTimestamp("created_at"));
				orders.add(order);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return orders;
	}

	@Override
	public Order selectById(int id) {
		Order order = null;
		String sql = "select * from orders where id = ?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				order = new Order();
				order.setId(resultSet.getInt("id"));
				order.setMemberId(resultSet.getInt("member_id"));
				order.setCreatedAt(resultSet.getTimestamp("created_at"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return order;
	}

	@Override
	public Order selectLastOrderByMemberId(int member_id) {
		Order order = null;
		String sql = "select * from orders where member_id = ? order by id desc limit 1";
		
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, member_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				order = new Order();
				order.setId(resultSet.getInt("id"));
				order.setMemberId(resultSet.getInt("member_id"));
				order.setCreatedAt(resultSet.getTimestamp("created_at"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public void update(Order order) {
		String sql = "update orders set member_id = ? where id = ?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, order.getMemberId());
			preparedStatement.setInt(2, order.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(int id) {
		String sql = "delete from orders where id = ?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
