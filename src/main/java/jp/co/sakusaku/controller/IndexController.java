package jp.co.sakusaku.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.co.sakusaku.entity.SqlTest;
import jp.co.sakusaku.repository.SqlTestRepository;

@Controller
public class IndexController {
	
	@Autowired
	private SqlTestRepository repository;
	
	@RequestMapping("/")
	public String index(Model model) {
		return "index";
	}
	
	@RequestMapping("/getAllDataForHtml")
	ModelAndView testData(ModelAndView mav) {
		List<SqlTest> list = repository.findAll();
		mav.setViewName("testThymeleaf");
		mav.addObject("messages", list);
		return mav;
	}

}
