package logic;

import org.springframework.stereotype.Service;

@Service
public class RecipeBookUtil {
	private String recipe_id;
	private String clip_user;
	private String recipe_img1;
	
	public String getRecipe_img1() {
		return recipe_img1;
	}
	public void setRecipe_img1(String recipe_img1) {
		this.recipe_img1 = recipe_img1;
	}
	public String getRecipe_id() {
		return recipe_id;
	}
	public void setRecipe_id(String recipe_id) {
		this.recipe_id = recipe_id;
	}
	public String getClip_user() {
		return clip_user;
	}
	public void setClip_user(String clip_user) {
		this.clip_user = clip_user;
	}
	
	
}
