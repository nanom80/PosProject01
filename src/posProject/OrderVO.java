package posProject;

public class OrderVO {

	//필드
	private int orderNo;
	private int tableId;
	private String menuName;
	private int count;
	private String payment;
	private String payDate;
	private int menuId;
	private String categoryName;
	private int unitPrice;
	
	
	//생성자
	public OrderVO() {
		super();
	}
	
	public OrderVO(int menuId, String categoryName, String menuName, int unitPrice) {
		super();
		this.menuId = menuId;
		this.categoryName = categoryName;
		this.menuName = menuName;
		this.unitPrice = unitPrice;
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
	
	public OrderVO(int orderNo, int tableId, String menuName, int count, String payment, String payDate, int menuId,
			String categoryName, int unitPrice) {
		super();
		this.orderNo = orderNo;
		this.tableId = tableId;
		this.menuName = menuName;
		this.count = count;
		this.payment = payment;
		this.payDate = payDate;
		this.menuId = menuId;
		this.categoryName = categoryName;
		this.unitPrice = unitPrice;
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

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	//메소드 일반
	@Override
	public String toString() {
		return "OrderVO [orderNo=" + orderNo + ", tableId=" + tableId + ", menuName=" + menuName + ", count=" + count
				+ ", payment=" + payment + ", payDate=" + payDate + ", menuId=" + menuId + ", categoryName="
				+ categoryName + ", unitPrice=" + unitPrice + "]";
	}
	
}
	
