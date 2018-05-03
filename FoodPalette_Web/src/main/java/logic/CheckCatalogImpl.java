package logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CheckDao;

@Service
public class CheckCatalogImpl implements CheckCatalog {

	@Autowired
	CheckDao checkDao;

	public Integer checkEmail(String email) {
		return checkDao.checkEmail(email);
	}

	public Integer checkNick(String nick) {
		return checkDao.checkNick(nick);
	}

	public String checkLogin(String email) {
		return checkDao.checkLogin(email);
	}

	public String checkFBLogin(String email) {
		return checkDao.checkFBLogin(email);
	}

	public String selectQuestion(String email) {
		return checkDao.selectQuestion(email);
	}

	public String checkAnswer(String email) {
		return checkDao.checkAnswer(email);
	}

	public String getLoginUserNick(String email) {
		return checkDao.getLoginUserNick(email);
	}

	public String getProfileImg(String email) {
		return checkDao.getProfileImg(email);
	}

	public String getCurrentPwd(String email) {
		return checkDao.getCurrentPwd(email);
	}	
	
	//jung
	public Integer checkRecipeCountMax() {
		return checkDao.checkRecipeCountMax();
	}

	public List<String> checkRecipeIdCount(String nick) {
		return checkDao.checkRecipeIdCount(nick);
	}

	public List<ListInfo> getTotalRecipeList() {
		return checkDao.getTotalRecipeList();
	}
	
	//bang
	public int getMaxRecipeNum(String nick) {
		return checkDao.getMaxRecipeNum(nick);
	}

	public List<FirstImages> getFirstImgs(String nick) {
		return checkDao.getFirstImgs(nick);
	}

	//jung
	public ListInfo getSelectedRecipe(String id) {
		
		return checkDao.getSelectedRecipe(id);
	}

	//bang
	public Integer getMaxHeart(String recipe_id) {
		return checkDao.getMaxHeart(recipe_id);
	}
	public String getHeartCheck(HeartUtil util) {
		return checkDao.getHeartCheck(util);
	}

	public Integer getMaxComment(String recipe_id) {
		return checkDao.getMaxComment(recipe_id);
	}

	public Integer getMaxCommentOrder(String recipe_id) {
		return checkDao.getMaxCommentOrder(recipe_id);
	}

	public List<Comment> getComment(String recipe_id) {
		return checkDao.getComment(recipe_id);
	}

	public List<ListInfo> getSearchRecipeList(String search) {
		return checkDao.getSearchRecipeList(search);
	}
	
	//4.20 add
	//jung
	public Integer getMaxCilpOrder(String cilp_user) {
		return checkDao.getMaxCilpOrder(cilp_user);
	}

	public Integer checkRecipeIsCliped(RecipeBookUtil recipeBookUtil) {
		return checkDao.checkRecipeIsCliped(recipeBookUtil);
	}

	public Integer getMaxClipByUser(String clip_user) {
		return checkDao.getMaxClipByUser(clip_user);
	}

	public List<RecipeBookUtil> getClipImgByUser(String clip_user) {
		return checkDao.getClipImgByUser(clip_user);
	}

	public RecipeInfo getRecipeTobeModify(String recipe_id) {
		return checkDao.getRecipeTobeModify(recipe_id);
	}

	
}
