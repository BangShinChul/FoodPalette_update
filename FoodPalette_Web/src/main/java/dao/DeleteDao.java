package dao;

import logic.Comment;
import logic.HeartUtil;
import logic.RecipeBookUtil;

public interface DeleteDao {
	//My_Heart_info delete용
	void deleteMyHeartInfo(HeartUtil util);
	//Comment_info delete용
	void deleteComment(Comment comment);
	
	//4.20
	//jung
	//recipe_book_info delete용
	Integer deleteRecipeBook(RecipeBookUtil recipeBookUtil);
	//recipe_info delete용
	Integer deleteRcipe(String recipe_id);
}
