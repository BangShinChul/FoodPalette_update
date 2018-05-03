package logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DeleteDao;

@Service
public class DeleteCatalogImpl implements DeleteCatalog {

	@Autowired
	DeleteDao deleteDao;
	
	public void deleteMyHeartInfo(HeartUtil util) {
		System.out.println("my_heart_info delete 완료!");
		deleteDao.deleteMyHeartInfo(util);
	}

	public void deleteComment(Comment comment) {
		System.out.println(comment.getRecipe_id()+"의"+
				comment.getUser_nick()+"이 작성한"+
				comment.getComment_order()+"번째 댓글 delete 완료!");
		deleteDao.deleteComment(comment);
	}
	
	//4.20
	//jung
	public Integer deleteRecipeBook(RecipeBookUtil recipeBookUtil) {
		return deleteDao.deleteRecipeBook(recipeBookUtil);
	}

	public Integer deleteRcipe(String recipe_id) {
		return deleteDao.deleteRcipe(recipe_id);
	}

}
