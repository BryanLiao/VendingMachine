package service;

import java.util.List;

import model.OrderDetail;

public interface OrderDetailService {
	// create
	void addPorderDetail(OrderDetail orderDetail);

	// read-->列印報表
	List<OrderDetail> findAllOrderDetail();
	
	List<OrderDetail> findByOrderId(int orderId);

	OrderDetail findById(int id);

	// update

	// delete
}
