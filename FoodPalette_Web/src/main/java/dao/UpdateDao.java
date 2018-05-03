package dao;

import logic.ChangePwd;
import logic.ChangePwd_Mypage;
import logic.HeartUtil;
import logic.RecipeInfo;
import logic.UpdateProfile;

public interface UpdateDao {
	//비밀번호 찾기 시 비밀번호 변경용
	void updatePassword(ChangePwd change);
	//마이페이지에서 비밀번호 변경용
	void updatePassword_Mypage(ChangePwd_Mypage change);
	
	//프로필이미지 변경용
	void updateProfile(UpdateProfile profile);
	//하트클릭시 heart_info update용
	void updateHeartInfo(HeartUtil util);
	
	//4.21
	//jung
	//레시피 변경용
	Integer updateRecipeInfo(RecipeInfo recipeInfo);
}
