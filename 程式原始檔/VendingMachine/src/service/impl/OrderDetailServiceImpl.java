package service.impl;

import java.util.List;

import dao.impl.OrderDetailDaoImpl;
import model.OrderDetail;
import service.OrderDetailService;

public class OrderDetailServiceImpl implements OrderDetailService {

	public static void main(String[] args) {
		/*
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrderId(10);
		orderDetail.setProductId(1);
		orderDetail.setProductName("p22");
		orderDetail.setPrice(100);
		orderDetail.setQty(120);
		orderDetailDaoImpl.add(orderDetail);
		*/
		
		//System.out.println(new OrderDetailServiceImpl().findAllOrderDetail());
		//System.out.println(new OrderDetailServiceImpl().findById(10));
		System.out.println(new OrderDetailServiceImpl().findByOrderId(10));
	}
	
	private static OrderDetailDaoImpl orderDetailDaoImpl = new OrderDetailDaoImpl();
	
	@Override
	public void addPorderDetail(OrderDetail orderDetail) {
		orderDetailDaoImpl.add(orderDetail);
	}

	@Override
	public List<OrderDetail> findAllOrderDetail() {
		return orderDetailDaoImpl.selectAll();
	}

	@Override
	public OrderDetail findById(int id) {
		return orderDetailDaoImpl.selectById(id);
	}

	@Override
	public List<OrderDetail> findByOrderId(int orderId) {
		return orderDetailDaoImpl.selectByOrderId(orderId);
	}

}
