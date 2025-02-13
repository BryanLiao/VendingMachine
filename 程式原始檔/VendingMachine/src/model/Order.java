package model;
import java.sql.Timestamp;

public class Order {
	private int id;
	private int memberId;
	private Timestamp createdAt;

	public Order() {
		super();
	}

	public Order(int memberId) {
		super();
		this.memberId = memberId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
}
