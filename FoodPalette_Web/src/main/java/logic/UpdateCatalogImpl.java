package logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UpdateDao;

@Service
public class UpdateCatalogImpl implements UpdateCatalog {

	@Autowired
	UpdateDao updateDao;
	
	public void updatePassword(ChangePwd change) {
		updateDao.updatePassword(change);
	}

	public void updatePassword_Mypage(ChangePwd_Mypage change) {
		updateDao.updatePassword_Mypage(change);
	}

	public void updateProfile(UpdateProfile profile) {
		updateDao.updateProfile(profile);
	}

	public void updateHeartInfo(HeartUtil util) {
		System.out.println("하트 업데이트 완료!");
		updateDao.updateHeartInfo(util);
	}

	//4.21
	//jung
	public Integer updateRecipeInfo(RecipeInfo recipeInfo) {
		return updateDao.updateRecipeInfo(recipeInfo);
	}


	
}
