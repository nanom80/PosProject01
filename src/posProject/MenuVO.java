package posProject;

public class MenuVO {
	
	//필드
	private int menuId;
	private String categoryName;
	private String menuName;
	private int unitPrice;
	
	//생성자
	public MenuVO() {
		super();
	}
	
	public MenuVO(int menuId, String categoryName, String menuName, int unitPrice) {
		super();
		this.menuId = menuId;
		this.categoryName = categoryName;
		this.menuName = menuName;
		this.unitPrice = unitPrice;
	}
	
	//메소드 gs
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

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
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
		return "MenuVO [menuId=" + menuId + ", menuName=" + menuName + ", unitPrice=" + unitPrice + "]";
	}
	
}
