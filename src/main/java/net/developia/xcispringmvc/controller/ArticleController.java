package net.developia.xcispringmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import net.developia.xcispringmvc.model.ArticleDTO;
import net.developia.xcispringmvc.model.PagingDTO;
import net.developia.xcispringmvc.service.ArticleService;

@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@RequestMapping(value = "/list")
	public ModelAndView list(@RequestParam(defaultValue = "1") int page) {
		ModelAndView mav = new ModelAndView();

		try {
			List<ArticleDTO> list = articleService.getArticleList(page);
			PagingDTO pagingDTO = articleService.getPagingInfo(page);
			
			mav.addObject("pagingDTO", pagingDTO);
			mav.setViewName("list");
			mav.addObject("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mav;
	}

	@RequestMapping(value = "/detail")
	public ModelAndView detail(@RequestParam(required = true) Long no) {

		try {
			ArticleDTO articleDTO = articleService.getDetail(no);
			return new ModelAndView("detail", "articleDTO", articleDTO);
		} catch (Exception e) {
			e.printStackTrace();
			ModelAndView mav = new ModelAndView("result.jsp");
			mav.addObject("msg", e.getMessage());
			mav.addObject("url", "list");
			return mav;
		}
	}

	@RequestMapping(value = "/insert")
	public String insert() {
		return "insert";
	}

	@RequestMapping(value = "/insertAction")
	public ModelAndView insertAction(ArticleDTO articleDTO) {
		ModelAndView mav = new ModelAndView();
		try {
			articleService.insertArticle(articleDTO);
			mav.setViewName("redirect:list");
		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("/WEB-INF/views/result.jsp");
			mav.addObject("msg", "글 등록에 실패하였습니다.");
			mav.addObject("url", "javascript:history.back();");
		}
		return mav;
	}

	@RequestMapping(value = "/update")
	public ModelAndView update(@RequestParam(required = true) Long no) {

		try {
			ArticleDTO articleDTO = articleService.getDetail(no, false);
			return new ModelAndView("update", "articleDTO", articleDTO);
		} catch (Exception e) {
			e.printStackTrace();
			ModelAndView mav = new ModelAndView("result");
			mav.addObject("msg", e.getMessage());
			mav.addObject("url", "list");
			return mav;
		}
	}

	@RequestMapping(value = "/updateAction")
	public ModelAndView updateAction(@ModelAttribute ArticleDTO articleDTO) {
		ModelAndView mav = new ModelAndView();

		try {
			articleService.updateArticle(articleDTO);
			mav.setViewName("result");
			mav.addObject("msg", "게시물이 수정되었습니다.");
			mav.addObject("url", "detail?no=" + articleDTO.getNo());
		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("result");
			mav.addObject("msg", e.getMessage());
			mav.addObject("url", "javascript:history.back()");
		}

		return mav;
	}
	
	@RequestMapping(value = "/delete")
	public ModelAndView delete(@RequestParam(required = true) Long no) {
		return new ModelAndView("delete", "no", no);
	}
	
	@RequestMapping(value = "/deleteAction")
	public ModelAndView deleteAction(@ModelAttribute ArticleDTO articleDTO) {
		Long no = articleDTO.getNo();
		String password = articleDTO.getPassword();
		ModelAndView mav = new ModelAndView();
		
		try {
			articleService.deleteArticle(no, password);
			mav.setViewName("result");
			mav.addObject("msg", "게시물이 삭제되었습니다.");
			mav.addObject("url", "list");
		}catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("result");
			mav.addObject("msg", e.getMessage());
			mav.addObject("url", "javascript:history.back()");
		}
		
		return mav;
	}

}
