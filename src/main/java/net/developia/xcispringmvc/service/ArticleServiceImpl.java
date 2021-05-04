package net.developia.xcispringmvc.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.developia.xcispringmvc.model.ArticleDAO;
import net.developia.xcispringmvc.model.ArticleDTO;
import net.developia.xcispringmvc.model.PagingDTO;


@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDAO articleDAO;

	@Override
	public void insertArticle(ArticleDTO articleDTO) throws Exception {

		articleDAO.insertArticle(articleDTO);

	}
	
	public PagingDTO getPagingInfo(int page) throws Exception {
		return new PagingDTO(page, articleDAO.getTotalRecord());
	}

	@Override
	public List<ArticleDTO> getArticleList(int page) throws Exception {
		
		PagingDTO pagingDTO = new PagingDTO(page);
		
		return articleDAO.getArticleList(pagingDTO.getStartNum(), pagingDTO.getEndNum());
	}
	
	@Override
	public ArticleDTO getDetail(long no) throws Exception {
		return getDetail(no, true);
	}

	@Override
	public ArticleDTO getDetail(long no, boolean updateReadcount) throws Exception {
		try {
			
			if(updateReadcount)	articleDAO.updateReadcount(no);
			
			ArticleDTO articleDTO = articleDAO.getDetail(no);
			
			if(articleDTO == null) {
				throw new RuntimeException("상세보기 실패");
			}
			return articleDTO;
			
		} finally {}
	}

	@Override
	public void updateArticle(ArticleDTO articleDTO) throws Exception {
		
		if(articleDAO.updateArticle(articleDTO) == 0) {
			throw new RuntimeException("글이 없거나 비밀번호가 틀립니다.");
		}
		
	}

	@Override
	public void deleteArticle(Long no, String password) throws Exception {
		if(articleDAO.deleteArticle(no, password) == 0) {
			throw new RuntimeException("글이 없거나 비밀번호가 틀립니다.");
		}
	}

	

}
