package controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import logic.CheckCatalog;
import logic.DeleteCatalog;
import logic.InsertCatalog;
import logic.RecipeBook;
import logic.RecipeBookUtil;


@Controller
public class RecipebookController {
	@Autowired
	private CheckCatalog checkCatalog;
	@Autowired
	private InsertCatalog insertCatalog;
	@Autowired
	private DeleteCatalog deleteCatalog;
	
	
	//4.21
	//jung
	@RequestMapping(value="/recipebook/clip_recipe.html", method=RequestMethod.GET)
	public ModelAndView cilpRecipe(String recipe_id, String TOTAL, HttpSession session, String FOCUS){
		//log
		System.out.println("포커스 값 : "+FOCUS);
		
		//clip_user 생성
		String clip_user = (String)session.getAttribute("user_nick");
		
		
		//clip_order 생성
		Integer clip_order = checkCatalog.getMaxCilpOrder(clip_user);
		if(clip_order == null){
			clip_order = 1;
		}else{
			clip_order++;
		}
		
		//log
		System.out.println("recipe_id : "+recipe_id);
		System.out.println("clip_user : "+clip_user);
		System.out.println("clip_order : "+clip_order);
		
		
		//VO저장
		RecipeBook recipeBook = new RecipeBook();
		recipeBook.setClip_order(clip_order);
		recipeBook.setClip_user(clip_user);
		recipeBook.setRecipe_id(recipe_id);
	
		//DB연동
		this.insertCatalog.postRecipeBook(recipeBook);
		
		//화면전환
		if(TOTAL.equals("YES")) return new ModelAndView("redirect:/list/recipe_list.html?FLAG=heart&FOCUS="+FOCUS);	
		else return new ModelAndView("redirect:/list/recipe_selected.html?id="+recipe_id);
	}
	
	@RequestMapping(value="/recipebook/my_clip_list.html",method=RequestMethod.GET)
	public ModelAndView myClipRecipeShow(HttpSession session){
		String clip_user = (String)session.getAttribute("user_nick");
		
		//리스트 초기 작업
		int maxClip = this.checkCatalog.getMaxClipByUser(clip_user);
		
		//행값(tr) 생성
		int[] tr_index = new int[(maxClip/3+1)];
		int max = maxClip/3+1;
		for(int i=1; i<=max; i++){
			tr_index[i-1] = i;
		}
		
		//스크랩 리스트 가져오기
		List<RecipeBookUtil> rbu = this.checkCatalog.getClipImgByUser(clip_user);
		Iterator it = rbu.iterator();
		String[] firstImg = new String[rbu.size()];
		int cnt = 0;
		while(it.hasNext()){
			RecipeBookUtil image = (RecipeBookUtil)it.next();
			System.out.println("스크랩 여부 이미지값:"+image.getRecipe_img1());
			System.out.println("스크랩 여부 레시피ID값:"+image.getRecipe_id());
			firstImg[cnt] = image.getRecipe_img1();
			cnt++;
		}
		
		ModelAndView mav = new ModelAndView("home/homepage");
		mav.addObject("BODY","recipeBook.jsp");
		mav.addObject("MAX",max);
		mav.addObject("TR_INDEX",tr_index);
		mav.addObject("FIRST_IMGS",firstImg);
	
		//4.21
		//jung
		mav.addObject("LIST",rbu);
		return mav;
	}
	
	@RequestMapping(value="recipebook/delete_clip_recipe",method=RequestMethod.GET)
	public ModelAndView deleteClip(String recipe_id, String TOTAL, HttpSession session){
		//clip_user 가져오기
		String clip_user = (String)session.getAttribute("user_nick");
		
		//VO 저장
		RecipeBookUtil recipeBookUtil = new RecipeBookUtil();
		recipeBookUtil.setClip_user(clip_user);
		recipeBookUtil.setRecipe_id(recipe_id);
		
		
		//DB연동
		Integer result = this.deleteCatalog.deleteRecipeBook(recipeBookUtil);
		
		if(result>0) System.out.println("스크랩한 레시피 삭제 완료");
		
		if(TOTAL.equals("YES")) return new ModelAndView("redirect:/list/recipe_list.html?FLAG='icon'");	
		else return new ModelAndView("redirect:/list/recipe_selected.html?id="+recipe_id);
	}
	
}
