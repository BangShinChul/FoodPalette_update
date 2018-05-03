package m_controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import logic.CheckCatalog;
import logic.Comment;
import logic.ListInfo;
import logic.RecipeBookUtil;

@Controller
public class Mobile_ListController {
	
	@Autowired
	private CheckCatalog checkCatalog; 

	////////////각종 메서드들
	//이미지 저장 메서드
	private String[] setImg(ListInfo li){
		int size = li.getRecipe_img_order();
		String[] imgs = new String[size];
		if(li.getRecipe_img1()!=null) imgs[0] = li.getRecipe_img1();
		if(li.getRecipe_img2()!=null) imgs[1] = li.getRecipe_img2();
		if(li.getRecipe_img3()!=null) imgs[2] = li.getRecipe_img3();
		if(li.getRecipe_img4()!=null) imgs[3] = li.getRecipe_img4();
		if(li.getRecipe_img5()!=null) imgs[4] = li.getRecipe_img5();
		if(li.getRecipe_img6()!=null) imgs[5] = li.getRecipe_img6();
		if(li.getRecipe_img7()!=null) imgs[6] = li.getRecipe_img7();
		if(li.getRecipe_img8()!=null) imgs[7] = li.getRecipe_img8();
		if(li.getRecipe_img9()!=null) imgs[8] = li.getRecipe_img9();
		if(li.getRecipe_img10()!=null) imgs[9] = li.getRecipe_img10();
		return imgs;
	}
	
	//4.21
	//jung
	//날짜 설정 메서드
	public ListInfo setTime(ListInfo listInfo){	
		//yymmdd
		String yymmdd = listInfo.getRecipe_posttime().substring(0,2)+". "+listInfo.getRecipe_posttime().substring(2,4)+". "
				+listInfo.getRecipe_posttime().substring(4,6);
		
		//게시 시간 설정
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyMMddHHmmss");
		DateTime post_time = fmt.parseDateTime(listInfo.getRecipe_posttime());
		System.out.println("포스트 타임 : "+post_time.toString());
	
		//현재 시간 설정
		DateTime present_time = new DateTime(); //현재시간
		System.out.println("현재 타임 : "+present_time.toString());
		
		//시간 차이 설정
		Duration duration = new Interval(post_time, present_time).toDuration();
		System.out.println("시간 차이 : " + duration.getStandardMinutes());

		//오늘인지여부 판단
		if(duration.getStandardDays() > 0){//오늘이 아닌 경우
			listInfo.setRecipe_posttime_cal(yymmdd);
			return listInfo;
		}
				
		//5시간이후여부 판단
		if(duration.getStandardHours()>5){//5시간 이후
			listInfo.setRecipe_posttime_cal(yymmdd);
			return listInfo;
		}else{//5시간 이전
			//10분후인지 여부
			if(duration.getStandardMinutes()>10){//10분후
				if(duration.getStandardHours() == 0){
					listInfo.setRecipe_posttime_cal("1시간전");
					return listInfo;
				}else{
					listInfo.setRecipe_posttime_cal(duration.getStandardHours()+"시간전");
					return listInfo;
				}
			}else{//10분전
				if(duration.getStandardMinutes() == 0){//지금
					listInfo.setRecipe_posttime_cal("방금"); 
					return listInfo;
				}else{//1~10분전
					listInfo.setRecipe_posttime_cal(duration.getStandardMinutes()+"분전");
					return listInfo;
				}
			}
		}
		
	}
	
	//댓글 날짜 설정 메서드
	public Comment setTime(Comment comment){	
		//yymmdd
		String yymmdd = comment.getComment_posttime().substring(0,2)+"."+comment.getComment_posttime().substring(2,4)+"."
				+comment.getComment_posttime().substring(4,6);
		
		
		//게시 시간 설정
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyMMddHHmmss");
		DateTime post_time = fmt.parseDateTime(comment.getComment_posttime());
		System.out.println("답글 게시 타임 : "+post_time.toString());
	
		//현재 시간 설정
		DateTime present_time = new DateTime(); //현재시간
		System.out.println("현재 타임 : "+present_time.toString());
		
		//시간 차이 설정
		Duration duration = new Interval(post_time, present_time).toDuration();
		System.out.println("시간 차이 : " + duration.getStandardMinutes());

		//오늘인지여부 판단
		if(duration.getStandardDays() > 0){//오늘이 아닌 경우
			comment.setComment_posttime_cal(yymmdd);
			return comment;
		}
				
		//5시간이후여부 판단
		if(duration.getStandardHours()>5){//5시간 이후
			comment.setComment_posttime_cal(yymmdd);
			return comment;
			//return yymmdd;
		}else{//5시간 이전
			//10분후인지 여부
			if(duration.getStandardMinutes()>10){//10분후
				if(duration.getStandardHours() == 0){
					comment.setComment_posttime_cal("1시간전");
					return comment;
					//return "1시간전";
				}else{
					comment.setComment_posttime_cal(duration.getStandardHours()+"시간전");
					return comment;
					//return duration.getStandardHours()+"시간전";
				}
			}else{//10분전
				if(duration.getStandardMinutes() == 0){//지금
					comment.setComment_posttime_cal("방금"); 
					return comment;
					//return "방금";
				}else{//1~10분전
					comment.setComment_posttime_cal(duration.getStandardMinutes()+"분전");
					return comment;
					//return duration.getStandardMinutes()+"분전";
				}
			}
		}
		
	}	
	
	
	///////////////////매핑 메서드
	@RequestMapping(value="/mobile_List/recipe_list.html", method=RequestMethod.GET)
	public ModelAndView recipeListShow(String FOCUS,String FLAG, HttpSession session){
		//log
		System.out.println("------모바일 토탈 리스트 메서드 통과-------");
		System.out.println("모바일 list_total에서 focus검사 : "+FOCUS);
		System.out.println("모바일 list_total에서 flag검사 : "+FLAG);
		
		
		//DB연동
		List<ListInfo> recpie_list = checkCatalog.getTotalRecipeList();
		System.out.println("레시피 갯수 : "+recpie_list.size());
		
		
		//jung
		Iterator it = recpie_list.iterator();
		int cnt = 0;
		while(it.hasNext()){
			ListInfo listInfo = (ListInfo)it.next();
			
			//Recipe_posttime_cal 생성 (객체를 던져라)
			listInfo = setTime(listInfo);
			
			//clip_flag 생성
			String clip_flag = "";
			String clip_user = (String)session.getAttribute("user_nick");
			RecipeBookUtil recipeBookUtil = new RecipeBookUtil();
			recipeBookUtil.setClip_user(clip_user);
			recipeBookUtil.setRecipe_id(listInfo.getRecipe_id());
			
			//DB연동
			Integer clip_result=this.checkCatalog.checkRecipeIsCliped(recipeBookUtil);
			if(clip_result>0) clip_flag = "YES";
			else clip_flag = "NO";
			System.out.println("clip_flag :"+clip_flag);
			
			//VO저장
			listInfo.setClip_flag(clip_flag);
			recpie_list.set(cnt, listInfo);
			
			cnt++;	
		}
		
		
		//화면전환
		ModelAndView mav = new ModelAndView("mobile_home/m_homepage");
		mav.addObject("LIST", recpie_list);
		mav.addObject("PAGE", "total");
		//mav.setViewName("home/listRecipe_total");
		if(FOCUS!=null&&FLAG.equals("heart")){
			mav.addObject("FOCUS",FOCUS);
		}
		if(FOCUS!=null&&FLAG.equals("icon")){
			mav.addObject("FOCUS",null);
		}
		return mav;
	}
	
	@RequestMapping(value="/mobile_List/recipe_selected.html", method=RequestMethod.GET)
	public ModelAndView selectedRecipeShow(String id, HttpSession session){
		//log
		System.out.println("---------------모바일 레시피 셀렉트 들어옴---------------");
		System.out.println("레시피id : "+id);
				
		//bang
		//댓글DB연동
		//해당게시물의 댓글정보(총댓글갯수,댓글게시자프로필&댓글게시자닉네임&댓글내용&댓글게시시간)
		//가져와서 다뿌려줌
		
		//총댓글갯수
		int comments = checkCatalog.getMaxComment(id);
		
		//댓글게시자정보(프로필,닉네임,내용,시간) 가져오기
		List<Comment> comment = checkCatalog.getComment(id);
		System.out.println("댓글 갯수 : "+comment.size());
		
		//댓글게시 날짜 정보 변경
		Iterator it = comment.iterator();
		int cnt = 0;
		while(it.hasNext()){
			Comment cmt = (Comment)it.next();
			cmt = this.setTime(cmt);
			comment.set(cnt, cmt);
			cnt++;
		}
		
		//레시피DB연동
		ListInfo selected_recipe = (ListInfo)checkCatalog.getSelectedRecipe(id);
		
		//날짜설정
		selected_recipe = this.setTime(selected_recipe);
		selected_recipe.setRecipe_id(id);
		
		//4.20 add
		//jung
		//clip_flag 생성
		String clip_flag = "";
		String clip_user = (String)session.getAttribute("user_nick");
		RecipeBookUtil recipeBookUtil = new RecipeBookUtil();
		recipeBookUtil.setClip_user(clip_user);
		recipeBookUtil.setRecipe_id(id);
	
		//DB연동
		Integer clip_result=this.checkCatalog.checkRecipeIsCliped(recipeBookUtil);
		if(clip_result>0) clip_flag = "YES";
		else clip_flag = "NO";
		System.out.println("clip_flag :"+clip_flag);

		//VO저장
		selected_recipe.setClip_flag(clip_flag);

		//화면전환
		ModelAndView mav = new ModelAndView("mobile_home/m_listRecipe_seleted");
//		ModelAndView mav = new ModelAndView();
		mav.addObject("imgs", setImg(selected_recipe));
		mav.addObject("recipe", selected_recipe);
		mav.addObject("comment_num",comments);
		mav.addObject("COMMENT",comment);
//		mav.addObject("BODY", "m_listRecipe_seleted.jsp");
		mav.addObject(new Comment());
		System.out.println("리턴합니다.");
		return mav;
		
	}
	
	//bang
	//검색시에 뿌려줄 list
	@RequestMapping(value="/mobile_List/recipe_search.html", method=RequestMethod.GET)
	public ModelAndView searchListShow(String FOCUS, String FLAG, HttpSession session, HttpServletRequest request){
		//log
		System.out.println("------검색 리스트 메서드 통과-------");
		System.out.println("list_search에서 focus검사 : "+FOCUS);
		System.out.println("list_search에서 flag검사 : "+FLAG);
		String content = null;
		content = request.getParameter("search");//공백제거
		
		System.out.println("검색할 컨텐츠 : ["+content+"]");
		//DB연동
		List<ListInfo> recpie_list = checkCatalog.getSearchRecipeList(content);
		System.out.println("레시피 갯수 : "+recpie_list.size());
		
		//Recipe_posttime_cal 생성
		Iterator it = recpie_list.iterator();
		int cnt = 0;
		while(it.hasNext()){
			ListInfo listInfo = (ListInfo)it.next();
			listInfo = setTime(listInfo);
			recpie_list.set(cnt, listInfo);
			cnt++;	
		}
		
		//화면전환
		ModelAndView mav = new ModelAndView("home/homepage");
		mav.addObject("LIST", recpie_list);
		mav.addObject("SEARCH_CONTENT", content);
		mav.addObject("PAGE", "search");
		mav.addObject("BODY","listRecipe_total.jsp");
		if(FOCUS!=null&&FLAG.equals("heart")){
			mav.addObject("FOCUS",FOCUS);
		}
		if(FOCUS!=null&&FLAG.equals("icon")){
			mav.addObject("FOCUS",null);
		}
		return mav;
	}
	
}
