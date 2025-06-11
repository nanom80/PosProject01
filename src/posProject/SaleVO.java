package posProject;

//데이터 담는 전용박스
public class SaleVO {

	//필드
	private int day; //일별 날짜
	private int sale; //판매
	private int oneDaySale; //일별 매출
	
	//생성자
	public SaleVO() {	}
	
	public SaleVO(int day, int sale, int oneDaySale) {
		this.day = day;
		this.sale = sale;
		this.oneDaySale = oneDaySale;
	}

	//메소드gs
	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getSale() {
		return sale;
	}

	public void setSale(int sale) {
		this.sale = sale;
	}

	public int getOneDaySale() {
		return oneDaySale;
	}

	public void setOneDaySale(int oneDaySale) {
		this.oneDaySale = oneDaySale;
	}
	
	//메소드 일반
	@Override
	public String toString() {
		return "SaleVO [day=" + day + ", sale=" + sale + ", oneDaySale=" + oneDaySale + "]";
	}
}
