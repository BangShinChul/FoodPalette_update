package logic;

import org.springframework.stereotype.Service;

@Service
public class ListInfo {
	
	//레시피목록에 필요한 변수 선언
	private String user_nick;
	private String user_profile_img;
	private String recipe_posttime;
	private String recipe_posttime_cal;
	private String recipe_img1;
	private String recipe_content;
	private String recipe_hashtag;
	private String recipe_id;
	
	//레시피 상세목록에 필요한 변수 선언
	private String recipe_img2;
	private String recipe_img3;
	private String recipe_img4;
	private String recipe_img5;
	private String recipe_img6;
	private String recipe_img7;
	private String recipe_img8;
	private String recipe_img9;
	private String recipe_img10;
	private Integer recipe_img_order;
	
	//4.20수정
	//jung
	//스크랩기능에 필요한 변수선언
	private String clip_flag;
	
	//Getter, Setter
	public String getClip_flag() {
		return clip_flag;
	}
	public void setClip_flag(String clip_flag) {
		this.clip_flag = clip_flag;
	}
	public String getUser_nick() {
		return user_nick;
	}
	public void setUser_nick(String user_nick) {
		this.user_nick = user_nick;
	}
	public String getUser_profile_img() {
		return user_profile_img;
	}
	public void setUser_profile_img(String user_profile_img) {
		this.user_profile_img = user_profile_img;
	}
	public String getRecipe_posttime() {
		return recipe_posttime;
	}
	public void setRecipe_posttime(String recipe_posttime) {
		this.recipe_posttime = recipe_posttime;
	}
	public String getRecipe_posttime_cal() {
		return recipe_posttime_cal;
	}
	public void setRecipe_posttime_cal(String recipe_posttime_cal) {
		this.recipe_posttime_cal = recipe_posttime_cal;
	}
	public String getRecipe_img1() {
		return recipe_img1;
	}
	public void setRecipe_img1(String recipe_img1) {
		this.recipe_img1 = recipe_img1;
	}
	public String getRecipe_content() {
		return recipe_content;
	}
	public void setRecipe_content(String recipe_content) {
		this.recipe_content = recipe_content;
	}
	public String getRecipe_hashtag() {
		return recipe_hashtag;
	}
	public void setRecipe_hashtag(String recipe_hashtag) {
		this.recipe_hashtag = recipe_hashtag;
	}
	public String getRecipe_id() {
		return recipe_id;
	}
	public void setRecipe_id(String recipe_id) {
		this.recipe_id = recipe_id;
	}
	public String getRecipe_img2() {
		return recipe_img2;
	}
	public void setRecipe_img2(String recipe_img2) {
		this.recipe_img2 = recipe_img2;
	}
	public String getRecipe_img3() {
		return recipe_img3;
	}
	public void setRecipe_img3(String recipe_img3) {
		this.recipe_img3 = recipe_img3;
	}
	public String getRecipe_img4() {
		return recipe_img4;
	}
	public void setRecipe_img4(String recipe_img4) {
		this.recipe_img4 = recipe_img4;
	}
	public String getRecipe_img5() {
		return recipe_img5;
	}
	public void setRecipe_img5(String recipe_img5) {
		this.recipe_img5 = recipe_img5;
	}
	public String getRecipe_img6() {
		return recipe_img6;
	}
	public void setRecipe_img6(String recipe_img6) {
		this.recipe_img6 = recipe_img6;
	}
	public String getRecipe_img7() {
		return recipe_img7;
	}
	public void setRecipe_img7(String recipe_img7) {
		this.recipe_img7 = recipe_img7;
	}
	public String getRecipe_img8() {
		return recipe_img8;
	}
	public void setRecipe_img8(String recipe_img8) {
		this.recipe_img8 = recipe_img8;
	}
	public String getRecipe_img9() {
		return recipe_img9;
	}
	public void setRecipe_img9(String recipe_img9) {
		this.recipe_img9 = recipe_img9;
	}
	public String getRecipe_img10() {
		return recipe_img10;
	}
	public void setRecipe_img10(String recipe_img10) {
		this.recipe_img10 = recipe_img10;
	}
	public Integer getRecipe_img_order() {
		return recipe_img_order;
	}
	public void setRecipe_img_order(Integer recipe_img_order) {
		this.recipe_img_order = recipe_img_order;
	}

	
	
}
