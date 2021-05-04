package net.developia.xcispringmvc.service;

import java.util.List;

import net.developia.xcispringmvc.model.ArticleDTO;
import net.developia.xcispringmvc.model.PagingDTO;


public interface ArticleService {

	void insertArticle(ArticleDTO articleDTO) throws Exception;

	List<ArticleDTO> getArticleList(int page) throws Exception;

	ArticleDTO getDetail(long no, boolean updateReadcount) throws Exception;
	
	ArticleDTO getDetail(long no) throws Exception;

	void updateArticle(ArticleDTO articleDTO) throws Exception;

	void deleteArticle(Long no, String password) throws Exception;
	
	PagingDTO getPagingInfo(int page) throws Exception;
}
