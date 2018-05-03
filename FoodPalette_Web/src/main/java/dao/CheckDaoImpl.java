package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import logic.Comment;
import logic.FirstImages;
import logic.HeartUtil;
import logic.ListInfo;
import logic.RecipeBookUtil;
import logic.RecipeInfo;

@Repository
public class CheckDaoImpl implements CheckDao {

	@Autowired
	private SqlSession session;

	public Integer checkEmail(String email) {
		System.out.println("이메일 중복 체크 DaoImpl");
		int check = session.selectOne("palette.checkEmail",email);
		return check;
	}

	public Integer checkNick(String nick) {
		System.out.println("닉네임 중복 체크 DaoImpl");
		int check = session.selectOne("palette.checkNick",nick);
		return check;
	}

	public String checkLogin(String email) {
		System.out.println("로그인 체크");
		String password = session.selectOne("palette.checkLogin",email);
		return password;
	}

	public String checkFBLogin(String email) {
		System.out.println("페이스북 로그인 체크");
		String check = session.selectOne("palette.fb_checkLogin",email);
		System.out.println("결과:"+check);
		String rt_value = "";
		
		if(check==null){
			//해당이메일로 일반계정도,페이스북계정도 존재하지않음
			rt_value="EMPTY";
		}else if(check.equals("YES")){
			//해당이메일로 페이스북계정이 이미존재
			rt_value="YES";
		}else if(check.equals("NO")){
			//해당이메일로 일반계정이 이미존재
			rt_value="NO";
		}
		System.out.println("리턴값:"+rt_value);
		
		return rt_value;
	}

	public String selectQuestion(String email) {
		String question = session.selectOne("palette.selectQuestion",email);
		return question;
	}

	public String checkAnswer(String email) {
		String answer = session.selectOne("palette.checkAnswer",email);
		return answer;
	}

	public String getLoginUserNick(String email) {
		String user_nick = session.selectOne("palette.getLoginUserNick",email);
		return user_nick;
	}

	public String getProfileImg(String email) {
		String user_profile_img = session.selectOne("palette.getLoginUserProfileImg", email);
		return user_profile_img;
	}

	public String getCurrentPwd(String email) {
		String current_pwd = session.selectOne("palette.getCurrentPwd", email);
		return current_pwd;
	}
	
	//jung
	
	public Integer checkRecipeCountMax() {
		System.out.println("전체 레시피 카운드 체크 DaoImpl");
		return session.selectOne("palette.checkRecipeCountMax");
	}

	public List<String> checkRecipeIdCount(String nick) {
		System.out.println("내 레시피 카운드 체크 DaoImpl");
		return session.selectList("palette.checkRecipeIdCount",nick);
	}

	public List<ListInfo> getTotalRecipeList() {
		System.out.println("레시피 토탈 리스트 체크 DaoImpl");
		return session.selectList("palette.getTotalRecipeList");
	}

	//bang
	public int getMaxRecipeNum(String nick) {
		System.out.println("로그인한 유저가 올린 레시피게시물 총 갯수 가져오기 DaoImpl");
		int max = session.selectOne("palette.getMaxRecipeNum", nick);
		return max;
	}

	public List<FirstImages> getFirstImgs(String nick) {
		System.out.println("로그인한 유저가 올린 레시피게시물들의 img1들 가져오기 DaoImpl");
		List<FirstImages> imgs = session.selectList("palette.getFirstImgs", nick);
		return imgs;
	}
	
	//jung
	public ListInfo getSelectedRecipe(String id) {
		System.out.println("선택된 레시피 상세목록 DaoImpl");
		return session.selectOne("palette.getSelectedRecipe", id);
	}

	//bang
	public Integer getMaxHeart(String recipe_id) {
		return session.selectOne("palette.getMaxHeart", recipe_id);
	}
	public String getHeartCheck(HeartUtil util) {
		System.out.println("파라미터값 : "+util.getRecipe_id()+" / "+util.getUser_nick());
		String heart_check = session.selectOne("palette.getHeartCheck", util);
		return heart_check;
	}
	public Integer getMaxComment(String recipe_id) {
		return session.selectOne("palette.getMaxComment", recipe_id);
	}

	public Integer getMaxCommentOrder(String recipe_id) {
		Integer maxOrder = session.selectOne("palette.getMaxCommentOrder", recipe_id);
		if(maxOrder==null){
			return 0;
		}
		return maxOrder;
	}

	public List<Comment> getComment(String recipe_id) {
		List<Comment> comment = session.selectList("palette.selectComment", recipe_id);
		return comment;
	}

	public List<ListInfo> getSearchRecipeList(String search) {
		System.out.println("레시피 검색 리스트 체크 DaoImpl");
		return session.selectList("palette.searchRecipe",search);
	}
	
	//4.20 add
	//jung
	public Integer getMaxCilpOrder(String cilp_user) {
		System.out.println("레시피 북 순서 DaoImpl");
		return session.selectOne("palette.getMaxCilpOrder",cilp_user);
	}

	public Integer checkRecipeIsCliped(RecipeBookUtil recipeBookUtil) {
		System.out.println("레시피 스크랩 여부 확인 DaoImpl");
		return session.selectOne("palette.checkRecipeIsCliped",recipeBookUtil);
	}

	public Integer getMaxClipByUser(String clip_user) {
		System.out.println("스크랩한 레시피 총 갯수 확인 DaoImpl");
		return session.selectOne("palette.getMaxClipByUser",clip_user);
	}

	public List<RecipeBookUtil> getClipImgByUser(String clip_user) {
		System.out.println("스크랩한 레시피 이미지 가져오기 DaoImpl");
		return session.selectList("palette.getClipImgByUser",clip_user);
	}

	public RecipeInfo getRecipeTobeModify(String recipe_id) {
		System.out.println("유저가 올린 레시피 가져오기 DaoImpl");
		return session.selectOne("palette.getRecipeTobeModify", recipe_id);
	}
	
	

}
