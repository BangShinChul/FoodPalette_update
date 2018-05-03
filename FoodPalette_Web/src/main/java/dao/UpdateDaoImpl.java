package dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import logic.ChangePwd;
import logic.ChangePwd_Mypage;
import logic.HeartUtil;
import logic.RecipeInfo;
import logic.UpdateProfile;

@Repository
public class UpdateDaoImpl implements UpdateDao {

	@Autowired
	private SqlSession session;
	
	public void updatePassword(ChangePwd change) {
		session.update("palette.updatePassword",change);
	}

	
	public void updatePassword_Mypage(ChangePwd_Mypage change) {
		session.update("palette.updatePassword_mypage", change);
	}


	public void updateProfile(UpdateProfile profile) {
		session.update("palette.updateProfile", profile);
	}


	public void updateHeartInfo(HeartUtil util) {
		session.update("palette.updateHeartInfo",util);
	}
	
	//4.21
	//jung
	public Integer updateRecipeInfo(RecipeInfo recipeInfo) {
		return session.update("palette.updateReicpe", recipeInfo);
	}


}
