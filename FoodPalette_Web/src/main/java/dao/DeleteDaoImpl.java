package dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import logic.Comment;
import logic.HeartUtil;
import logic.RecipeBookUtil;

@Repository
public class DeleteDaoImpl implements DeleteDao {

	@Autowired
	private SqlSession session;
	
	public void deleteMyHeartInfo(HeartUtil util) {
		session.delete("palette.deleteMyHeartInfo", util);
	}

	public void deleteComment(Comment comment) {
		System.out.println("deleteDao comment 들어옴");
		session.delete("palette.deleteComment", comment);
	}

	//4.20
	//jung
	public Integer deleteRecipeBook(RecipeBookUtil recipeBookUtil) {
		return session.delete("palette.deleteRecipeBook",recipeBookUtil);
	}

	public Integer deleteRcipe(String recipe_id) {
		return session.delete("palette.deleteRecipe", recipe_id);
	}

}
