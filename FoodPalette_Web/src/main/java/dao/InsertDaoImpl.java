package dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import logic.Comment;
import logic.HeartUtil;
import logic.RecipeBook;
import logic.RecipeInfo;
import logic.UserInfo;

@Repository
public class InsertDaoImpl implements InsertDao {

	@Autowired
	private SqlSession session;
	
	public void createAccount(UserInfo userInfo) {
		session.insert("palette.insertAccount",userInfo);
	}
	
	//jung
	public void postRecipe(RecipeInfo recipeInfo) {
		session.insert("palette.postRecipe",recipeInfo);
	}

	public void postHeart(HeartUtil util) {
		session.insert("palette.insertHeartInfo", util);
	}

	public void postMyHeart(HeartUtil util) {
		session.insert("palette.insertMyHeartInfo", util);
	}

	public void postComment(Comment comment) {
		session.insert("palette.insertComment", comment);
	}
	
	//4.20 add
	//jung
	public void postRecipeBook(RecipeBook recipeBook) {
		session.insert("palette.insertRecipeBook", recipeBook);
	}

}
