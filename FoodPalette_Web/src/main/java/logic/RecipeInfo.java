package logic;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class RecipeInfo {
	//이미지 서버 업로드 변수
	private List<MultipartFile> img_food;
	
	//레시피 DB연동 변수
	private String recipe_id;
	private Integer recipe_count;
	private String recipe_content;
	private String recipe_hashtag;
	private Integer recipe_img_order;
	private String recipe_img1;
	private String recipe_img2;
	private String recipe_img3;
	private String recipe_img4;
	private String recipe_img5;
	private String recipe_img6;
	private String recipe_img7;
	private String recipe_img8;
	private String recipe_img9;
	private String recipe_img10;
	private String recipe_posttime;	
	private String user_nick;
	
	
	public List<MultipartFile> getImg_food() {
		return img_food;
	}
	public void setImg_food(List<MultipartFile> img_food) {
		this.img_food = img_food;
	}
	public String getRecipe_id() {
		return recipe_id;
	}
	public void setRecipe_id(String recipe_id) {
		this.recipe_id = recipe_id;
	}
	public Integer getRecipe_count() {
		return recipe_count;
	}
	public void setRecipe_count(Integer recipe_count) {
		this.recipe_count = recipe_count;
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
	public Integer getRecipe_img_order() {
		return recipe_img_order;
	}
	public void setRecipe_img_order(Integer recipe_img_order) {
		this.recipe_img_order = recipe_img_order;
	}
	public String getRecipe_img1() {
		return recipe_img1;
	}
	public void setRecipe_img1(String recipe_img1) {
		this.recipe_img1 = recipe_img1;
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
	public String getRecipe_posttime() {
		return recipe_posttime;
	}
	public void setRecipe_posttime(String recipe_posttime) {
		this.recipe_posttime = recipe_posttime;
	}
	public String getUser_nick() {
		return user_nick;
	}
	public void setUser_nick(String user_nick) {
		this.user_nick = user_nick;
	}
	

		

	
}
