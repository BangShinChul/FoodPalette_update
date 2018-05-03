package logic;

import org.springframework.stereotype.Service;

@Service
public class Comment {
	private String recipe_id; //게시물ID
	private String comment_text; //댓글내용
	private String comment_posttime; //댓글 게시시간
	private int comment_order; //댓글 게시한 순서
	private String user_nick; //댓글 게시자
	private String user_profile_img; //댓글게시자 프로필이미지
		
	//jung
	private String comment_posttime_cal; //계산한 시간

	
	public String getComment_posttime_cal() {
		return comment_posttime_cal;
	}
	public void setComment_posttime_cal(String comment_posttime_cal) {
		this.comment_posttime_cal = comment_posttime_cal;
	}
	public String getUser_profile_img() {
		return user_profile_img;
	}
	public void setUser_profile_img(String user_profile_img) {
		this.user_profile_img = user_profile_img;
	}
	
	public String getRecipe_id() {
		return recipe_id;
	}
	public void setRecipe_id(String recipe_id) {
		this.recipe_id = recipe_id;
	}
	public String getComment_text() {
		return comment_text;
	}
	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}
	public String getComment_posttime() {
		return comment_posttime;
	}
	public void setComment_posttime(String comment_posttime) {
		this.comment_posttime = comment_posttime;
	}
	public int getComment_order() {
		return comment_order;
	}
	public void setComment_order(int comment_order) {
		this.comment_order = comment_order;
	}
	public String getUser_nick() {
		return user_nick;
	}
	public void setUser_nick(String user_nick) {
		this.user_nick = user_nick;
	}
	
	
	
}
