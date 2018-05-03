package logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.InsertDao;

@Service
public class InsertCatalogImpl implements InsertCatalog {

	@Autowired
	InsertDao insertDao;
	
	public void createAccount(UserInfo userInfo) {
		insertDao.createAccount(userInfo);
	}
	
	//Jung
	//레시피 업로드용
	public void postRecipe(RecipeInfo recipeInfo) {
		insertDao.postRecipe(recipeInfo);

	//bang
	}
	public void postHeart(HeartUtil util) {
		insertDao.postHeart(util);
	}

	public void postMyHeart(HeartUtil util) {
		System.out.println("my_heart_info에 insert 완료!");
		insertDao.postMyHeart(util);
	}
	
	public void postComment(Comment comment) {
		System.out.println(comment.getRecipe_id()+"에 댓글 게시 완료!");
		insertDao.postComment(comment);
	}
	
	//4.20 add
	//jung
	//My_Recipe_Book insert용
	public void postRecipeBook(RecipeBook recipeBook) {
		insertDao.postRecipeBook(recipeBook);
	}


}
