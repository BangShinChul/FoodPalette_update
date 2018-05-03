package logic;

import org.springframework.stereotype.Service;

@Service
public class ChangePwd {
	String user_email;
	String user_password;
	String user_password_check;
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
	public String getUser_password_check() {
		return user_password_check;
	}
	public void setUser_password_check(String user_password_check) {
		this.user_password_check = user_password_check;
	}
	
	
}
