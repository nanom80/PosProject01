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
    private String url = "jdbc:mysql://localhost:3306/pos_db";
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
    
    // 조회
    public List<SaleVO> saleselect(){
    
    	List<SaleVO> sList = new ArrayList<> ();
        
    	this.connect();
    	
    	try {
    		String query ="""
    				select day,
    					   sale,
    					   oneDaySale
    				from food	   
    				""";
    		query = query.stripIndent().strip();
    		
    		pstmt = conn.prepareStatement(query);
    		
    		System.out.println(query);

    		System.out.println();
    		
    		rs = pstmt.executeQuery();
    		
    		while(rs.next()) {    		
    			int day = rs.getInt("day");
    			int sale = rs.getInt("sale");
    			int oneDaySale = rs.getInt("oneDaySale");
    			
    			SaleVO saleVo = new SaleVO(day, sale, oneDaySale);
    			
    			sList.add(saleVo);
    		}
    		for(SaleVO saleVO : sList) {
    			System.out.println(saleVO.getDay()+"\t");
    			System.out.println(saleVO.getSale()+"\t");
    			System.out.println(saleVO.getOneDaySale()+"\t");
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