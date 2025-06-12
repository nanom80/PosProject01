package posproject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import posproject.vo.CategoryVO;

public class CategoryDAO {
	// field
	private Connection conn = null;
	PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String Driver = "com.mysql.cj.jdbc.Driver";
	private String Url = "jdbc:mysql://localhost:3306/pos_db";
	private String Id = "pos";
	private String Pw = "pos";

	// editor
	private void CategoryDAO() {

	}
	// method normal

	// connect database
	private void connect() {
		try {
			// 1. JDBC 드라이버 (MySQL) 로딩
			Class.forName(Driver);

			// 2. Connection 얻어오기
			this.conn = DriverManager.getConnection(Url, Id, Pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	} // connect end

	// resource close
	private void close() {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	} // resource close end

	// category insert
	public int categoryInsert(String categoryName, String categoryEmoji, String categoryDesc) {
		System.out.println("------------------------------");
		System.out.println(categoryName);
		System.out.println(categoryEmoji);
		System.out.println(categoryDesc);
		
		int count = -1;

		try {
			// 1. JDBC 드라이버 (MySQL) 로딩
			// 2. Connection 얻어오기
			this.connect();

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " insert into category ";
			query += " values (null, ?, ?, ?, '정상') ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, categoryName);
			pstmt.setString(2, categoryEmoji);
			pstmt.setString(3, categoryDesc);
			// 실행
			count = pstmt.executeUpdate();
			// 4.결과처리
			System.out.println(count + "건 등록 완료");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return count;
	} // category insert end

	// category delete
	public int categoryDelete(int categoryId) {
		int count = -1;

		try {
			// 1. JDBC 드라이버 (MySQL) 로딩
			// 2. Connection 얻어오기
			this.connect();

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " delete from category ";
			query += " where category_id = ? ";
			System.out.println(query);

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, categoryId);
			// 실행
			count = pstmt.executeUpdate();
			// 4.결과처리
			System.out.println(count + "건 삭제완료");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return count;
	} // category delete end

	// category dicoy delete
	public int categoryDicoyDelete(int categoryId) {
		int count = -1;
		System.out.println(categoryId);
		try {
			// 1. JDBC 드라이버 (MySQL) 로딩
			// 2. Connection 얻어오기
			this.connect();

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " update category ";
			query += " set	category_status = '삭제' ";
			query += " where category_id = ? ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, categoryId);

			// 실행
			count = pstmt.executeUpdate();
			// 4.결과처리
			System.out.println(count + "건 가짜 삭제 완료");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return count;
	}

	// category update
	public int categoryUpdate(String categoryName, String categoryEmoji, String categoryDesc, int categoryId) {
		int count = -1;

		try {
			// 1. JDBC 드라이버 (MySQL) 로딩
			// 2. Connection 얻어오기
			this.connect();

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " update category ";
			query += " set	category_name = ?, ";
			query += " 		category_emoji = ?, ";
			query += " 		category_desc = ? ";
			query += " where category_id = ? ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, categoryName);
			pstmt.setString(2, categoryEmoji);
			pstmt.setString(3, categoryDesc);
			pstmt.setInt(4, categoryId);
			// 실행
			count = pstmt.executeUpdate();
			// 4.결과처리
			System.out.println(count + "건 수정 완료");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return count;
	} // category update end

	// category select all (without staus)
	public List<CategoryVO> categorySelectAll() {
		List<CategoryVO> categoryList = new ArrayList<CategoryVO>();

		// 0. import java.sql.*;

		// 1. JDBC 드라이버 (MySQL) 로딩
		// 2. Connection 얻어오기
		this.connect();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " select category_id, ";
			query += "        category_name, ";
			query += "        category_emoji, ";
			query += "        category_desc ";
			query += " from category ";
			query += " where category_status != '삭제' ";

			// 바인딩
			pstmt = conn.prepareStatement(query);

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리 (java 리스트로 만든다)
			while (rs.next()) {

				int categoryId = rs.getInt("category_id");
				String categoryName = rs.getString("category_name");
				String categoryEmoji = rs.getString("category_emoji");
				String categoryDesc = rs.getString("category_desc");

				// 데이터 객체로 만들기(묶기)
				CategoryVO categoryVO = new CategoryVO(categoryId, categoryName, categoryEmoji, categoryDesc);

				// 묶은 데이터를 리스트에 달기
				categoryList.add(categoryVO);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		// 5. 자원정리
		this.close();

		return categoryList;

	}

	// category check status
	public CategoryVO categorySelectOne(int categoryId) {
		CategoryVO categoryVO = null;

		// 0. import java.sql.*;

		// 1. JDBC 드라이버 (MySQL) 로딩
		// 2. Connection 얻어오기
		this.connect();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " select category_status ";
			query += " from category ";
			query += " where category_id = ? ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, categoryId);

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리 (java 리스트로 만든다)
			rs.next();

			String categoryStatus = rs.getString("category_status");

			categoryVO = new CategoryVO(categoryStatus);

		} catch (

		SQLException e) {
			System.out.println("error:" + e);
		}

		// 5. 자원정리
		this.close();

		return categoryVO;
	}
}
