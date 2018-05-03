package dao;

import logic.Comment;
import logic.HeartUtil;
import logic.RecipeBook;
import logic.RecipeInfo;
import logic.UserInfo;

public interface InsertDao {
	//계정 DB에 insert용
	void createAccount(UserInfo userInfo);
	
	//레시피 insert용
	void postRecipe(RecipeInfo recipeInfo); 
	//Heart_info insert용
	void postHeart(HeartUtil util);
	//My_Heart_info insert용
	void postMyHeart(HeartUtil util);
	//Comment_info insert용
	void postComment(Comment comment);
	
	//4.20 add
	//jung
	//My_Recipe_Book insert용
	void postRecipeBook(RecipeBook recipeBook);
}
