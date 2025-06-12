package posProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleDAO {

    // 필드
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://192.168.0.99:3306/pos_db";
    private String id = "pos";
    private String pw = "pos";
    
    //생성자
    public SaleDAO() {}
    
    //DB 연결
    private void connect() {
        System.out.println("DB연결");
        try {
            Class.forName(driver);
            this.conn = DriverManager.getConnection(url, id, pw);
        } catch (ClassNotFoundException e) {
            System.out.println("error: 드라이버 로딩 실패 - " + e);
        } catch (SQLException e) {
            System.out.println("error:" + e);
        }
    }    
    
    //자원정리
    private void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("error:" + e);
        }
        System.out.println("자원정리");    	
    }
    
    
    //생성
    public int saleInsert(int day, String categoryName) {

		int count = -1;

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		this.connect();

		try {
			
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " insert into food ";
			query += " values( ?, ?) ";
			System.out.println(query);

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, day); // 메소드의 파라미터
			pstmt.setString(2, categoryName); // 메소드의 파라미터

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 등록");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		this.close();
		
		return count;
	}
    
    // 조회
    public List<SaleVO> saleselect(){
    	System.out.println("aaaa");
    	List<SaleVO> sList = new ArrayList<> ();
        
    	this.connect();
    	
    	try {
    		String query ="""
						select day,
						       category_name,
						       sale
						from food a
						join category b
						on a.category_id = b.category_id
    				""";
    		query = query.stripIndent().strip();
    		
    		pstmt = conn.prepareStatement(query);
    		
    		System.out.println(query);

    		System.out.println();
    		
    		rs = pstmt.executeQuery();
    		
    		while(rs.next()) {    		
    			String day = rs.getString("day");
    			String categoryName = rs.getString("category_name");
    			int sale = rs.getInt("sale");
    			
    			SaleVO saleVo = new SaleVO(day, categoryName, sale);
    			
    			sList.add(saleVo);
    		}
    		for(SaleVO saleVO : sList) {
    			System.out.println("4.매출표확인 > 1.카테고리별 매출표 확인");
				System.out.println("");
				
				System.out.println("=======================================");
				System.out.println("	  <조회되었습니다>    ");
				
				
    			System.out.print(saleVO.getDay()+"\t");
    			System.out.print(saleVO.getCategory_name()+"\t");
    			System.out.println(saleVO.getSale());
    			
				
    		}
    		System.out.println(sList.size()+" 건이 조회되었습니다.");
    		System.out.println();
    	}catch (SQLException e) {
            System.out.println("error:" + e);
		}
    	
    	
    	this.close();
    	
    	return sList;
    }
    
    public List<SaleVO> Dayselect(){
    	List<SaleVO> sList = new ArrayList<> ();
        
    	this.connect();
    	
    	try {
    		String query ="""
						select day,
						       sale
						from food a
						join category b
						on a.category_id = b.category_id
    				""";
    		query = query.stripIndent().strip();
    		
    		pstmt = conn.prepareStatement(query);
    		
    		System.out.println(query);

    		System.out.println();
    		
    		rs = pstmt.executeQuery();
    		
    		while(rs.next()) {    		
    			String day = rs.getString("day");
    			//String categoryName = rs.getString("category_name");
    			int sale = rs.getInt("sale");
    			
    			//SaleVO saleVo = new SaleVO(day, categoryName, sale);
    			SaleVO saleVo = new SaleVO(day, sale);
    			
    			sList.add(saleVo);
    		}
    		for(SaleVO saleVO : sList) {
    			System.out.println("4.매출표확인 > 1.일별 매출표 확인");
				System.out.println("");
				System.out.println("날짜\t"+"        매출");
				
				System.out.print(saleVO.getDay()+"\t");
    			System.out.println(saleVO.getSale());
    			
				System.out.println("=======================================");
				System.out.println("	  <조회되었습니다>    ");
				
    		}
    		System.out.println(sList.size()+" 건이 조회되었습니다.");
    		System.out.println();
    	}catch (SQLException e) {
            System.out.println("error:" + e);
		}
    	
    	this.close();
    	
    	return sList;
    }

}