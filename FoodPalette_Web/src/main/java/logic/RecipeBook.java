package logic;

import org.springframework.stereotype.Service;

@Service
public class RecipeBook {

	private String recipe_id;
	private Integer clip_order;
	private String clip_user;
	
	public String getRecipe_id() {
		return recipe_id;
	}
	public void setRecipe_id(String recipe_id) {
		this.recipe_id = recipe_id;
	}
	
	public Integer getClip_order() {
		return clip_order;
	}
	public void setClip_order(Integer clip_order) {
		this.clip_order = clip_order;
	}
	public String getClip_user() {
		return clip_user;
	}
	public void setClip_user(String clip_user) {
		this.clip_user = clip_user;
	}
	
	
}
