package logic;

import org.springframework.stereotype.Service;

@Service
public class HeartUtil {

	//heart_info전용
	private String recipe_id;
	private Integer heart;
	private String user_nick;
	private String heart_check;
	
	public String getRecipe_id() {
		return recipe_id;
	}
	public void setRecipe_id(String recipe_id) {
		this.recipe_id = recipe_id;
	}
	public Integer getHeart() {
		return heart;
	}
	public void setHeart(Integer heart) {
		this.heart = heart;
	}
	public String getUser_nick() {
		return user_nick;
	}
	public void setUser_nick(String user_nick) {
		this.user_nick = user_nick;
	}
	public String getHeart_check() {
		return heart_check;
	}
	public void setHeart_check(String heart_check) {
		this.heart_check = heart_check;
	}
	
	
}
