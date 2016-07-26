package jp.co.sakusaku.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jp.co.sakusaku.dto.TestDto;
import jp.co.sakusaku.entity.MstPrefectures;
import jp.co.sakusaku.entity.SqlTest;
import jp.co.sakusaku.form.TestForm;
import jp.co.sakusaku.repository.MstPrefecturesRepository;
import jp.co.sakusaku.repository.SqlTestRepository;

@Controller
public class IndexController {
	
	@Autowired
	private SqlTestRepository testRepository;
	@Autowired
	private MstPrefecturesRepository prefRepository;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping("/")
	public String index(Model model) {
		return "index";
	}
	
	@RequestMapping("/getAllDataForHtml")
	ModelAndView testData(ModelAndView mav) {
		List<SqlTest> list = testRepository.findAll();
		mav.setViewName("testThymeleaf");
		mav.addObject("messages", list);
		return mav;
	}
	
	@RequestMapping("/getAllPrefectures")
	ModelAndView getAllPrefectures(ModelAndView mav) {
		List<MstPrefectures> list = prefRepository.findAll();
		mav.setViewName("mstPrefectures");
		mav.addObject("messages", list);
		return mav;
	}
	
	@RequestMapping("/getAllPrefecture")
	ModelAndView getAllPrefecture(ModelAndView mav) {
		MstPrefectures pref = jdbcTemplate.queryForObject("SELECT * FROM mst_prefectures WHERE prefectures_cd = ?", new Object[]{"01"}, new RowMapper<MstPrefectures>(){
			@Override
			public MstPrefectures mapRow(ResultSet rs, int rowNum) throws SQLException {
				MstPrefectures pref = new MstPrefectures();
				pref.setId(rs.getString("prefectures_cd"));
				pref.setPrefecturesName(rs.getString("prefectures_name"));
				return pref;
			}
		});
		mav.setViewName("mstPrefecture");
		mav.addObject("messages", pref);
		return mav;
	}
	
	@RequestMapping("/send")
	ModelAndView sendQuery(TestForm form, ModelAndView mav) {
		mav.setViewName("sendTest");
		return mav;
	}
	
	@RequestMapping("/findSqlQuery")
	ModelAndView findSqlQuery(TestForm form, ModelAndView mav) {
		RowMapper<MstPrefectures> mapper = new BeanPropertyRowMapper<MstPrefectures>(MstPrefectures.class);
		List<MstPrefectures> pref = jdbcTemplate.query(form.getSqlQuery(), mapper);
		mav.setViewName("mstPrefectures");
		mav.addObject("messages", pref);
		return mav;
	}
	
	@RequestMapping(value="/getQueryResult",consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<MstPrefectures> findByQuery(@RequestBody TestDto dto) {
		RowMapper<MstPrefectures> mapper = new BeanPropertyRowMapper<MstPrefectures>(MstPrefectures.class);
		List<MstPrefectures> pref = jdbcTemplate.query(dto.getSqlQuery(), mapper);
		return pref;
	}

}
