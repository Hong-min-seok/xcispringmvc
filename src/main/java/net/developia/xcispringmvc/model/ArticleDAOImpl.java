package net.developia.xcispringmvc.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleDAOImpl implements ArticleDAO {

	//*******************************************************************************
	@Autowired
	private DataSource dataSource;
	//private static final JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
	//아래처럼 바뀜 *****************************************************************
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*
	private ArticleDAOImpl() {
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/xcispring01");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/

	
	@Override
	public void insertArticle(ArticleDTO articleDTO) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into t_board(no, title, name, password, content) ");
		sql.append("values(seq_board.nextval, ?, ? ,? ,?) ");

		Object[] args = {
				articleDTO.getTitle(),
				articleDTO.getName(),
				articleDTO.getPassword(),
				articleDTO.getContent()
		};
		
		jdbcTemplate.update(sql.toString(), args);
	}
	
	public long getTotalRecord() throws SQLException {
		long recordCount = 0;

		StringBuffer sql_count = new StringBuffer();
		sql_count.append("select count(*) as cnt from t_board ");

		try (Connection conn = dataSource.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql_count.toString());
				
			ResultSet rs = ps.executeQuery()) {
			
			if (rs.next()) {
				recordCount = rs.getLong("cnt");
			}
		}

		return recordCount;
	}

	@Override
	public List<ArticleDTO> getArticleList(long startNum, long endNum) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("select B.* ");
		sql.append("from (select rownum as rnum, A.* ");
		sql.append("      from (select no, title, name, regdate, readcount from t_board ");
		sql.append("            order by no desc) A ");
		sql.append("     ) B ");
		sql.append("where rnum between ? and ? ");
		
		List<ArticleDTO> list = new ArrayList<>();
		try(Connection conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString())) {
			
			ps.setLong(1, startNum);
			ps.setLong(2, endNum);
			
			try(ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					ArticleDTO articleDTO = new ArticleDTO();
					articleDTO.setNo(rs.getLong("no"));
					articleDTO.setTitle(rs.getString("title"));
					articleDTO.setName(rs.getString("name"));
					articleDTO.setRegdate(rs.getDate("regdate"));
					articleDTO.setReadcount(rs.getInt("readcount"));
					list.add(articleDTO);
				}
			}
			
		}
		return list;
	}

	@Override
	public void updateReadcount(long no) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE t_board SET ");
		sql.append("	   readcount = readcount + 1 ");
		sql.append("WHERE no = ? ");
		
		Object[] args = {no};
		jdbcTemplate.update(sql.toString(), args);
		
	}

	@Override
	public ArticleDTO getDetail(long no) throws SQLException {
		StringBuffer sql = new StringBuffer();

		sql.append("select no, title, name, regdate, readcount, content from t_board ");
		sql.append("where no = ? ");
		
		ArticleDTO articleDTO = new ArticleDTO();
		
		try(Connection conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString())) {
			ps.setLong(1, no);
			try(ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					articleDTO = new ArticleDTO();
					articleDTO.setNo(rs.getLong("no"));
					articleDTO.setTitle(rs.getString("title"));
					articleDTO.setName(rs.getString("name"));
					articleDTO.setRegdate(rs.getDate("regdate"));
					articleDTO.setReadcount(rs.getInt("readcount"));
					articleDTO.setContent(rs.getString("content"));
				}
			}
			
		}
		return articleDTO;
	}

	@Override
	public int updateArticle(ArticleDTO articleDTO) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE t_board SET ");
		sql.append("	   title=?, name=?, content=? ");
		sql.append("WHERE no=? AND password=? ");
		
		/*
		 * try(Connection conn = dataSource.getConnection(); PreparedStatement pstmt =
		 * conn.prepareStatement(sql.toString())) { pstmt.setString(1,
		 * articleDTO.getTitle()); pstmt.setString(2, articleDTO.getName());
		 * pstmt.setString(3, articleDTO.getContent()); pstmt.setLong(4,
		 * articleDTO.getNo()); pstmt.setString(5, articleDTO.getPassword()); return
		 * pstmt.executeUpdate(); }
		 */
		
		Object[] args = {
				articleDTO.getTitle(),
				articleDTO.getName(),
				articleDTO.getContent(),
				articleDTO.getNo(),
				articleDTO.getPassword(),
		};
		return jdbcTemplate.update(sql.toString(), args);
	}

	@Override
	public int deleteArticle(Long no, String password) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM t_board ");
		sql.append("WHERE no=? AND password=?");
		
		Object[] args = {
				no,
				password
		};
		return jdbcTemplate.update(sql.toString(), args);
	}
}
