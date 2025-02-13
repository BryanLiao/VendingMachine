package service.impl;

import java.util.ArrayList;
import java.util.List;

import dao.impl.OrderDaoImpl;
import dao.impl.OrderDetailDaoImpl;
import model.Order;
import model.OrderDetail;
import service.OrderService;

public class OrderServiceImpl implements OrderService {

	public static void main(String[] args) {
		/*
		Order order = new Order();
		order.setMemberId(1);
		orderDaoImpl.add(order);
		*/
		
		//System.out.println(new OrderServiceImpl().findAllOrders());
	    //System.out.println(new OrderServiceImpl().findById(3));
		//System.out.println(new OrderServiceImpl().findLastOrderByMemberId(2));
		
		Order order = new Order();
		order.setMemberId(5);
		
		List<OrderDetail> orderDetails = new ArrayList();
        OrderDetail orderDetail = new OrderDetail(0, 1 , "紅茶", 15, 1);
        orderDetails.add(orderDetail);
        new OrderServiceImpl().addOrder(order, orderDetails);
		
	}

	private static OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
	
	@Override
	public void addOrder(Order order) {
		orderDaoImpl.add(order);
	}
	
	@Override
	public void addOrder(Order order, List<OrderDetail> orderDetails) {
		System.out.println("addOrder");
		orderDaoImpl.add(order);
		Order o = this.findLastOrderByMemberId(order.getMemberId());
		int orderId = o.getId();
		for (OrderDetail orderDetail : orderDetails) {
			System.out.print(orderDetail);
			orderDetail.setOrderId(orderId);
			new OrderDetailDaoImpl().add(orderDetail);
		}
		
	}

	@Override
	public List<Order> findAllOrders() {
		return orderDaoImpl.selectAll();
	}

	@Override
	public Order findLastOrderByMemberId(int member_id) {
		return orderDaoImpl.selectLastOrderByMemberId(member_id);
	}

	@Override
	public Order findById(int id) {
		return orderDaoImpl.selectById(id);
	}



	

	

}
