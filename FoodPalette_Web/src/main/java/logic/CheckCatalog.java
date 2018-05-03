package logic;

import java.util.List;

public interface CheckCatalog {
	//회원가입시 이메일,사용자이름 중복확인용
	Integer checkEmail(String email);
	Integer checkNick(String nick);

	//로그인시 이메일과 비밀번호확인용
	String checkLogin(String email);
	//로그인 완료시 이메일의 사용자이름(닉네임) 가져오기용
	String getLoginUserNick(String email);
	
	//페이스북 계정확인용
	String checkFBLogin(String email);
	
	//비밀번호찾기 시 이메일의 질문가져오기용
	String selectQuestion(String email);
	//비밀번호찾기 시 이메일의 질문의답 가져오기용
	String checkAnswer(String email);
	
	//마이페이지 접속시 프로필사진 가져오기용
	String getProfileImg(String email);
	//마이페이지 비밀번호변경에서 이메일의 현재비밀번호 가져오기용
	String getCurrentPwd(String email);
	
	//Jung
	//업로드시 자기 레시피 카운트 가져오기용
	List<String> checkRecipeIdCount(String nick);
	
	//업로드시 전체 카운트 가져오기용
	Integer checkRecipeCountMax();
	
	//레시피 전체 목록 가져오기용
	List<ListInfo> getTotalRecipeList();

	//bang
	//마이페이지 나의게시물 
	//로그인한 유저의 게시한 총 게시물 수 가져오기용
	int getMaxRecipeNum(String nick);
	//로그인한 유저의 모든게시물의 첫번째 이미지들 가져오기용
	List<FirstImages> getFirstImgs(String nick);
	
	//jung
	//상세 레시피 가져오기용
	ListInfo getSelectedRecipe(String id);
	
	//bang
	//recipe_id의 총 하트갯수 가져오기용
	Integer getMaxHeart(String recipe_id);
	//로그인한 유저의 게시물 heart_check가져오기용
	String getHeartCheck(HeartUtil util);
	//게시물 상세에서 recipe_id의 총댓글갯수 가져오기용
	Integer getMaxComment(String recipe_id);
	//게시물 상세에서 recipe_id의 comment_order max값 가져오기용
	Integer getMaxCommentOrder(String recipe_id);
	//게시물 상세에서 댓글정보 가져오기용
	List<Comment> getComment(String recipe_id);
	//검색한 레시피 목록 가져오기용
	List<ListInfo> getSearchRecipeList(String search);
	
	//4.20 add
	//jung
	//레시피 북의 순서값 가져오기용
	Integer getMaxCilpOrder(String cilp_user);
	//레시피의 스크랩 여부 확인용
	Integer checkRecipeIsCliped(RecipeBookUtil recipeBookUtil);
	//유저가 스크랩한 레시피의 총 갯수 가져오기용
	Integer getMaxClipByUser(String clip_user);
	//스크랩한 레시피의 이미지 가져오기용
	List<RecipeBookUtil> getClipImgByUser(String clip_user);
	//유저가 올린 레시피 가져오기용
	RecipeInfo getRecipeTobeModify(String recipe_id);
}
