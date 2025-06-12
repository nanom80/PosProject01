package posproject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import posproject.TextUtil;
import posproject.vo.CategoryVO;
import posproject.vo.OrderVO;

public class OrderDAO {

    // 필드
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/pos_db";
    private String id = "pos";
    private String pw = "pos";

    // 생성자
    public OrderDAO() {}

    // DB연결
    private void connect() {
        //System.out.println("DB연결");
        try {
            Class.forName(driver);
            this.conn = DriverManager.getConnection(url, id, pw);
        } catch (ClassNotFoundException e) {
            System.out.println("error: 드라이버 로딩 실패 - " + e);
        } catch (SQLException e) {
            System.out.println("error:" + e);
        }
    }

    // 자원정리
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
        //System.out.println("자원정리");
    }

    // 입력
    public int orderInsert(int count, String payment, String payDate, int tableId, int menuId) {

        int cnt = -1;

        this.connect();

        try {
            String query = """
                    insert into payment
                    values(null, ?, ?, ?, ?, ?)
                    """;
            //query = query.stripIndent().strip();

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, count);
            pstmt.setString(2, payment);
            pstmt.setString(3, payDate);
            pstmt.setInt(4, tableId);
            pstmt.setInt(5, menuId);

            System.out.println(bindQuery(query, count, payment, payDate, tableId, menuId));

            // 실행
            cnt = pstmt.executeUpdate(); // 수정!

            System.out.println(cnt + " 건이 입력되었습니다.");
            System.out.println();

        } catch (SQLException e) {
            System.out.println("error:" + e);
        }

        this.close();

        return cnt;
    }

    // 조회 (주문 전체 조회)
    public List<OrderVO> orderSelect() {

        List<OrderVO> aList = new ArrayList<>();

        this.connect();

        try {
            String query = """
                    select a.order_no,
                           a.table_id,
                           b.menu_name,
                           a.count,
                           a.payment,
                           date_format(a.pay_date,'%Y-%m-%d') pay_date
                    from payment a
                        join menu b
                          on a.menu_id = b.menu_id
                    order by a.pay_date,
                    		a.table_id, a.order_no
                    """;
            query = query.stripIndent().strip();

            pstmt = conn.prepareStatement(query);

            System.out.println(bindQuery(query));

            System.out.println();

            rs = pstmt.executeQuery();
            
            //System.out.println("주문번호\t테이블\t메뉴\t수량\t지불여부\t결제일자");
            
            while (rs.next()) {
                int orderNo = rs.getInt("order_no");
                int tableId = rs.getInt("table_id");
                String menuName = rs.getString("menu_name");
                int count = rs.getInt("count");
                String payment = rs.getString("payment");
                String payDate = rs.getString("pay_date");
                
                OrderVO orderVO = new OrderVO(orderNo, tableId, menuName, count, payment, payDate);

                aList.add(orderVO);
            }
            
            OrderVO ordervo = new OrderVO();
            
            final int widthOrderNo = 8;
            final int widthTableId = 8;
    		final int widthMenuName = 15;
    		final int widthCount = 8;
    		final int widthPayment = 8;
    		final int widthpayDate = 10;

    		String line = "-".repeat(widthOrderNo + widthTableId + widthMenuName + widthCount + widthPayment + widthpayDate + 5);
    		
    		System.out.println(line);
    		//System.out.println("|      |              |        |                                        |");
    		System.out.println("|" + TextUtil.padRightDisplay("주문번호", widthOrderNo) + "|"
    				+ TextUtil.padRightDisplay("테이블", widthTableId) + "|"
    				+ TextUtil.padRightDisplay("메뉴", widthMenuName)+ "|"
    				+ TextUtil.padRightDisplay("수량", widthCount)+ "|"
    				+ TextUtil.padRightDisplay("지불여부", widthPayment)+ "|"
    				+ TextUtil.padRightDisplay("결제일자", widthpayDate) + "|");
    		//System.out.println("|______|______________|________|________________________________________|");
    		System.out.println(line);

    		for (OrderVO vo : aList) {
    			System.out.println("|" + TextUtil.padRightDisplay(String.valueOf(vo.getOrderNo()), widthOrderNo) + "|"
    					+ TextUtil.padRightDisplay(vo.getTableId(), widthTableId) + "|"
    					+ TextUtil.padRightDisplay(vo.getMenuName(), widthMenuName) + "|"
    					+ TextUtil.padRightDisplay(vo.getCount(), widthCount) + "|"
    					+ TextUtil.padRightDisplay(vo.getPayment(), widthPayment) + "|"
    					+ TextUtil.padRightDisplay(vo.getPayDate(), widthpayDate) + "|"
    					);
    		}
    		
    		/*
            for (OrderVO orderVO : aList) {
                System.out.println(orderVO.getOrderNo() + "\t" +
                				   orderVO.getTableId() + "\t" +
                				   orderVO.getMenuName() + "\t" +
                                   orderVO.getCount() + "\t" +
                                   orderVO.getPayment() + "\t" +
                                   orderVO.getPayDate());
            }
            */
    		
            System.out.println(aList.size() + " 건이 조회되었습니다.");
            System.out.println();

        } catch (SQLException e) {
            System.out.println("error:" + e);
        }

        this.close();

        return aList;
    }
    
    // 메뉴조회
    public List<OrderVO> menuSelect() {
    	
    	
        List<OrderVO> aList = new ArrayList<>();

        this.connect();

        try {
            String query = """
                    select a.menu_id,
					    b.category_name,
					    a.menu_name,
					    a.unit_price
					from menu a
					join category b
					  on a.category_id = b.category_id
					where menu_show != '숨기기'
					  and menu_delete != '삭제됨'
                    """;
            query = query.stripIndent().strip();

            pstmt = conn.prepareStatement(query);
            
            System.out.println();

            rs = pstmt.executeQuery();
            
            //System.out.println("번호\t카테고리\t메뉴\t가격");
            
            while (rs.next()) {
                int menuId = rs.getInt("menu_id");
                String categoryName = rs.getString("category_name");
                String menuName = rs.getString("menu_name");
                int unitPrice = rs.getInt("unit_price");
                
                OrderVO orderVO = new OrderVO(menuId, categoryName, menuName, unitPrice);

                aList.add(orderVO);
            }
            
            final int widthId = 6;
    		final int widthName = 20;
    		final int widthEmoji = 20;
    		final int widthDesc = 15;

    		String line = "-".repeat(widthId + widthName + widthEmoji + widthDesc + 5);

    		System.out.println("[3.카테고리 관리]");
    		System.out.println(line);
    		//System.out.println("|      |              |        |                                        |");
    		System.out.println("|" + TextUtil.padRightDisplay("번호", widthId) + "|"
    				+ TextUtil.padRightDisplay("카테고리명", widthName) + "|" + TextUtil.padRightDisplay("메뉴명", widthEmoji)
    				+ "|" + TextUtil.padRightDisplay("단가", widthDesc) + "|");
    		//System.out.println("|______|______________|________|________________________________________|");
    		System.out.println(line);

    		for (OrderVO ordervo : aList) {
    			System.out.println("|" + TextUtil.padRightDisplay(String.valueOf(ordervo.getMenuId()), widthId) + "|"
    					+ TextUtil.padRightDisplay(ordervo.getCategoryName(), widthName) + "|"
    					+ TextUtil.padRightDisplay(ordervo.getMenuName(), widthEmoji) + "|"
    					+ TextUtil.padRightDisplay(ordervo.getUnitPrice(), widthDesc) + "|");
    		}
    		
//            for (OrderVO orderVO : aList) {
//                System.out.println(orderVO.getMenuId() + "\t" +
//                		orderVO.getCategoryName() + "\t" +
//                		orderVO.getMenuName() + "\t" +
//                		orderVO.getUnitPrice());
//            }
            
            System.out.println();

        } catch (SQLException e) {
            System.out.println("error:" + e);
        }

        this.close();

        return aList;
    }
    
    // 계산
    public int orderUpdate(int tableId) {
    	
    	int cnt = -1;

        this.connect();

        try {
        	String query1 = """
	                select sum(b.unit_price * a.count) as sum_price
	                from payment a
	                join menu b
	                  on a.menu_id = b.menu_id
	                where a.payment = 'N'
	                  and a.table_id = ?
                    """;
            //query1 = query1.stripIndent().strip();

            pstmt = conn.prepareStatement(query1);
            pstmt.setInt(1, tableId);
            
            System.out.println();

            rs = pstmt.executeQuery();
            
            int totalPrice = 0;
            if (rs.next()) {
                totalPrice = rs.getInt("sum_price");
                System.out.println("총 결제 금액: " + totalPrice + "원");
            }
            
            System.out.println();
            
            String query2 = """
                    update payment
					set payment = 'Y'
					where table_id = ?
					  and payment = 'N'
                    """;
            //query2 = query2.stripIndent().strip();

            pstmt = conn.prepareStatement(query2);
            pstmt.setInt(1, tableId);

            System.out.println(bindQuery(query2, tableId));

            // 실행
            cnt = pstmt.executeUpdate();

            System.out.println(cnt + " 건이 계산 되었습니다.");
            System.out.println();

        } catch (SQLException e) {
            System.out.println("error:" + e);
        }

        this.close();

        return cnt;
    }
    
    // 주문 삭제 
    public int orderDelete(int orderNo) {
    	
        int cnt = -1;
        
        this.connect();
        
        try {
            String query = """
                    update payment
					set payment = 'X'
					where order_no = ?
					  and payment = 'N'
                    """;
            //query = query.stripIndent().strip();

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, orderNo);

            System.out.println(bindQuery(query, orderNo));

            // 실행
            cnt = pstmt.executeUpdate(); // 수정!

            System.out.println(cnt + " 건이 삭제되었습니다.");
            System.out.println();

        } catch (SQLException e) {
            System.out.println("error:" + e);
        }

        this.close();

        return cnt;
    }
    
    // Helper method for binding query for print
    public static String bindQuery(String query, Object... params) {
        for (Object param : params) {
            String value;
            if (param instanceof String) {
                value = "'" + param.toString().replace("'", "''") + "'";
            } else {
                value = String.valueOf(param);
            }
            query = query.replaceFirst("\\?", value);
        }
        return query;
    }
}

