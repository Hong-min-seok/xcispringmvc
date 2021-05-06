package net.developia.xcispringmvc.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JdbcTemplate_Before {


	/** 커넥션풀링 싱글톤으로 만들기 **********************************************/
	private DataSource dataSource;

	private static final JdbcTemplate_Before jdbcTemplate = new JdbcTemplate_Before();

	public static JdbcTemplate_Before getInstance() {
		return jdbcTemplate;
	}

	private JdbcTemplate_Before() {
		try {
			Context ctx = new InitialContext();

			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/xcispringmvc");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("JdbcTemplate 생성자에서 에러");
		}
	}
	/*******************************************************************************/
	
	public int update(String query, Object[] args) throws SQLException {
		
		try (Connection conn = dataSource.getConnection();
			 PreparedStatement ps = conn.prepareStatement(query)) {

/*			ps.setString(1, articleDTO.getTitle());
			ps.setString(2, articleDTO.getName());
			ps.setString(3, articleDTO.getPassword());
			ps.setString(4, articleDTO.getContent());*/
			
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i+1, args[i]);
				}
			
			return ps.executeUpdate();
			
			}
			
	}
	
	
}