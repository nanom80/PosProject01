package posProject;

//데이터 담는 전용박스
public class SaleVO {

	//필드
	private String day; //날짜별 매출
	private String category_name; //카테고리별 매출
	private int sale;
	
	//생성자
	public SaleVO() {	}
	
	public SaleVO(String day, int sale) {
		this.day = day;
		this.sale = sale;
	}
	
	public SaleVO(String day, String category_name, int sale) {
		this.day = day;
		this.category_name = category_name;
		this.sale = sale;
	}

	//메소드gs

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public int getSale() {
		return sale;
	}

	public void setSale(int sale) {
		this.sale = sale;
	}

	@Override
	public String toString() {
		return "SaleVO [day=" + day + ", category_name=" + category_name + ", sale=" + sale + "]";
	}
	
	
}
