package posProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        System.out.println("자원정리");
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
            query = query.stripIndent().strip();

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

    // 조회 (전체 조회)
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
                           a.pay_date
                    from payment a
                        join menu b
                          on a.menu_id = b.menu_id
                    """;
            query = query.stripIndent().strip();

            pstmt = conn.prepareStatement(query);

            System.out.println(bindQuery(query));

            System.out.println();

            rs = pstmt.executeQuery();
            
            System.out.println("주문번호\t테이블\t메뉴\t수량\t지불여부\t결제일자");
            
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

            for (OrderVO orderVO : aList) {
                System.out.println(orderVO.getOrderNo() + "\t" +
                				   orderVO.getTableId() + "\t" +
                				   orderVO.getMenuName() + "\t" +
                                   orderVO.getCount() + "\t" +
                                   orderVO.getPayment() + "\t" +
                                   orderVO.getPayDate());
            }
            
            System.out.println(aList.size() + " 건이 조회되었습니다.");
            System.out.println();

        } catch (SQLException e) {
            System.out.println("error:" + e);
        }

        this.close();

        return aList;
    }
    
    // 메뉴조회
    public List<MenuVO> menuSelect() {

        List<MenuVO> aList = new ArrayList<>();

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
            
            System.out.println("번호\t카테고리\t메뉴\t가격");
            
            while (rs.next()) {
                int menuId = rs.getInt("menu_id");
                String categoryName = rs.getString("category_name");
                String menuName = rs.getString("menu_name");
                int unitPrice = rs.getInt("unit_price");
                
                MenuVO menuVO = new MenuVO(menuId, categoryName, menuName, unitPrice);

                aList.add(menuVO);
            }

            for (MenuVO menuVO : aList) {
                System.out.println(menuVO.getMenuId() + "\t" +
                				   menuVO.getCategoryName() + "\t" +
                				   menuVO.getMenuName() + "\t" +
                				   menuVO.getUnitPrice());
            }
            
            System.out.println();

        } catch (SQLException e) {
            System.out.println("error:" + e);
        }

        this.close();

        return aList;
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

