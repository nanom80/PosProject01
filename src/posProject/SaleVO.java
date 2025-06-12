package posProject;

//데이터 담는 전용박스
public class SaleVO {

	//필드
	private String day; //날짜별 매출
	private String categoryName; //카테고리별 매출
	private int sale;
	
	//생성자
	public SaleVO() {	}
	
	public SaleVO(String day, int sale) {
		this.day = day;
		this.sale = sale;
	}
	
	public SaleVO(String day, String categoryName, int sale) {
		this.day = day;
		this.categoryName = categoryName;
		this.sale = sale;
	}

	//메소드gs

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getSale() {
		return sale;
	}

	public void setSale(int sale) {
		this.sale = sale;
	}

	@Override
	public String toString() {
		return "SaleVO [day=" + day + ", categoryName=" + categoryName + ", sale=" + sale + "]";
	}
	
	
	
}
