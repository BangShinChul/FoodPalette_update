package logic;

import org.springframework.stereotype.Service;

@Service
public class UserInfo {
	private String user_nick;
	private String user_name;
	private String user_email;
	private String user_password;
	private String user_question;
	private String user_answer;
	private String user_profile_img;
	private String user_fb_check;
	
	public String getUser_nick() {
		return user_nick;
	}
	public void setUser_nick(String user_nick) {
		this.user_nick = user_nick;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_question() {
		return user_question;
	}
	public void setUser_question(String user_question) {
		this.user_question = user_question;
	}
	public String getUser_answer() {
		return user_answer;
	}
	public void setUser_answer(String user_answer) {
		this.user_answer = user_answer;
	}
	public String getUser_profile_img() {
		return user_profile_img;
	}
	public void setUser_profile_img(String user_profile_img) {
		this.user_profile_img = user_profile_img;
	}
	public String getUser_fb_check() {
		return user_fb_check;
	}
	public void setUser_fb_check(String user_fb_check) {
		this.user_fb_check = user_fb_check;
	}
	
	
	
}
