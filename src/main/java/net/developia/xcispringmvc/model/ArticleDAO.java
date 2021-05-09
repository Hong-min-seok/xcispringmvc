package net.developia.xcispringmvc.model;


import java.sql.SQLException;
import java.util.List;

public interface ArticleDAO {

	void insertArticle(ArticleDTO articleDTO) throws SQLException;

	List<ArticleDTO> getArticleList(long startNum, long endNum) throws SQLException;

	void updateReadcount(long no) throws SQLException;

	ArticleDTO getDetail(long no) throws SQLException;

	// update가 잘 수행되었는지 받기 위해 int 반환
	int updateArticle(ArticleDTO articleDTO) throws SQLException;

	int deleteArticle(ArticleDTO articleDTO) throws SQLException;

	long getTotalRecord() throws SQLException;
}
