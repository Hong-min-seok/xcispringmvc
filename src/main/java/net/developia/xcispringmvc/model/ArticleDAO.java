package net.developia.xcispringmvc.model;


import java.sql.SQLException;
import java.util.List;

public interface ArticleDAO {

	void insertArticle(ArticleDTO articleDTO) throws SQLException;

	List<ArticleDTO> getArticleList(long startNum, long endNum) throws SQLException;

	void updateReadcount(long no) throws SQLException;

	ArticleDTO getDetail(long no) throws SQLException;

	// update�� �� ����Ǿ����� �ޱ� ���� int ��ȯ
	int updateArticle(ArticleDTO articleDTO) throws SQLException;

	int deleteArticle(ArticleDTO articleDTO) throws SQLException;

	long getTotalRecord() throws SQLException;
}
