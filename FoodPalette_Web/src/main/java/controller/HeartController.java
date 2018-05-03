package controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import logic.CheckCatalogImpl;
import logic.DeleteCatalogImpl;
import logic.HeartUtil;
import logic.InsertCatalogImpl;
import logic.UpdateCatalogImpl;

@Controller
public class HeartController {

	//DB연동 DAO들
	@Autowired
	CheckCatalogImpl check;
	@Autowired
	InsertCatalogImpl insert;
	@Autowired
	UpdateCatalogImpl update;
	@Autowired
	DeleteCatalogImpl delete;
	
	
	//recipe_id 초기화면
	@RequestMapping(value="/heart/go_heart.html",method=RequestMethod.GET)
	public ModelAndView go_heart(String recipe_id, String page, String focus, String search_content, HttpSession session){
		
		System.out.println("-------고 하트 통과--------");
		System.out.println("받은 recipe_id : "+recipe_id);
		System.out.println("받은 page : "+page);
		System.out.println("전달받은 focus : "+focus);
		System.out.println("받은 검색어 : "+search_content);
		String rt_page = page.trim();
		String user_nick = (String)session.getAttribute("user_nick");
		String content = null;
		if(search_content!=null){
			content = search_content.trim();	
		}
		
		ModelAndView mav = new ModelAndView("home/AjaxHeart");
		
		//게시물의 총 하트수 가져오기
		int max_heart = check.getMaxHeart(recipe_id);
		mav.addObject("heart_count",max_heart);
		
		//로그인한 유저의 해당게시물 heart_check 가져오기
		HeartUtil util = new HeartUtil();
		util.setRecipe_id(recipe_id);
		util.setUser_nick(user_nick);
		String heart_check = check.getHeartCheck(util);
		mav.addObject("heart_check",heart_check);
		if(heart_check==null){
			//하트를 누른적이없는 상태일때 빈하트 출력
			//insert my_heart_info
			//update heart_info (+1)
			System.out.println("go_heart : 하트를 누른적이없는 상태일때 빈하트 출력");
			mav.addObject("heart_img","non_heart.png");
			mav.addObject("recipe_id",recipe_id);
			mav.addObject("PAGE",rt_page);
			mav.addObject("focus",focus);
			mav.addObject("SEARCH_CONTENT", content);
		}else{
			//하트를 눌렀던 상태일때 빨간하트 출력
			//delete my_heart_info
			//update heart_info (-1)
			System.out.println("go_heart : 하트를 눌렀던 상태일때 빨간하트 출력");
			mav.addObject("heart_img","in_heart.png");
			mav.addObject("recipe_id",recipe_id);
			mav.addObject("PAGE",rt_page);
			mav.addObject("focus",focus);
			mav.addObject("SEARCH_CONTENT", content);
		}
		return mav;
	}
	
	//하트 on/off 시
	@RequestMapping(value="/heart/update_heart.html",method=RequestMethod.GET)
	public ModelAndView on_heart(String recipe_id, String page, String focus, String search_content, HttpSession session){
		System.out.println("-------업데이트 하트 통과--------");
		System.out.println("전달받은 recipe_id : "+recipe_id);
		System.out.println("전달받은 page : "+page);
		System.out.println("전달받은 focus : "+focus);
		System.out.println("전달받은 검색어 : "+search_content);
		String msg = null;
		if(search_content!=null&&page.equals("search")){
			try{
				msg = URLEncoder.encode(search_content, "euc-kr");
			}catch(UnsupportedEncodingException e){
				e.printStackTrace();
			}	
		}
		
		ModelAndView mav = new ModelAndView();
		
		if(page.equals("total")){
			mav.setViewName("redirect:/list/recipe_list.html?FOCUS="+focus+"&FLAG=heart");
		}else if(page.equals("select")){
			mav.setViewName("redirect:/list/recipe_selected.html?id="+recipe_id);
		}else if(page.equals("search")){
			mav.setViewName("redirect:/list/recipe_search.html?FOCUS="+focus+"&FLAG=heart&search="+msg);
		}
	
		String user_nick = (String)session.getAttribute("user_nick");
		
		//하트check가져옴
		HeartUtil util = new HeartUtil();
		util.setRecipe_id(recipe_id);
		util.setUser_nick(user_nick);
		String heart_check = check.getHeartCheck(util);
		
		//게시물의 총 하트값 가져옴
		int max_heart = check.getMaxHeart(recipe_id);
		
		if(heart_check==null){
			//check가 없으면 
			//insert my_heart_info
			System.out.println("update_heart : 하트가 클릭되어지지 않은 상태였을때");
			util.setHeart_check("YES");
			insert.postMyHeart(util);
			
			//update heart_info (+1)
			util.setHeart(max_heart+1);
			update.updateHeartInfo(util);
			
			
		}else{
			//check가 있으면
			//delete my_heart_info
			System.out.println("update_heart : 하트가 클릭되어 있던 상태였을때");
			delete.deleteMyHeartInfo(util);
			//update heart_info (-1)
			util.setHeart(max_heart-1);
			update.updateHeartInfo(util);
			
		}
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value="/heart/update_heart_ajax.html",method=RequestMethod.GET)
	public String AjaxHeart(String recipe_id, String h_check, HttpSession session){
		
		System.out.println("Ajax 들어옴!");
		System.out.println("check : "+check);
		
		String user_nick = (String)session.getAttribute("user_nick");
		HeartUtil util = new HeartUtil();
		
		//하트check가져옴
		util.setRecipe_id(recipe_id);
		util.setUser_nick(user_nick);
		String heart_check = check.getHeartCheck(util);
		//게시물의 총 하트값 가져옴
		int max_heart = check.getMaxHeart(recipe_id);
		
		if(heart_check==null){
			//check가 없으면 
			//insert my_heart_info
			System.out.println("update_heart : 하트가 클릭되어지지 않은 상태였을때");
			util.setHeart_check("YES");
			insert.postMyHeart(util);
			
			//update heart_info (+1)
			util.setHeart(max_heart+1);
			update.updateHeartInfo(util);

		}else{
			//check가 있으면
			//delete my_heart_info
			System.out.println("update_heart : 하트가 클릭되어 있던 상태였을때");
			delete.deleteMyHeartInfo(util);
			//update heart_info (-1)
			util.setHeart(max_heart-1);
			update.updateHeartInfo(util);
		}
		
		//넘겨주기위해 한번 더 가져옴
		int return_max_heart = check.getMaxHeart(recipe_id);
		String return_val = String.valueOf(return_max_heart);
		return return_val;
	}

}
