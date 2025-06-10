package posProject;

public class OrderVO {

	//필드
	private int orderNo;
	private int tableId;
	private String menuName;
	private int count;
	private String payment;
	private String payDate;
	
	//생성자
	public OrderVO() {
		super();
	}
	
	public OrderVO(int orderNo, int tableId, String menuName, int count, String payment, String payDate) {
		super();
		this.orderNo = orderNo;
		this.tableId = tableId;
		this.menuName = menuName;
		this.count = count;
		this.payment = payment;
		this.payDate = payDate;
	}
	
	//메소드 gs
	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	
	//메소드 일반
	@Override
	public String toString() {
		return "OrderVO [orderNo=" + orderNo + ", tableId=" + tableId + ", menuName=" + menuName + ", count=" + count
				+ ", payment=" + payment + ", payDate=" + payDate + "]";
	}
	
}
	
