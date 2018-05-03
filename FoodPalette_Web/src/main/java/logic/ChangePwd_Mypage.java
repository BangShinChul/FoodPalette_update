package logic;

import org.springframework.stereotype.Service;

@Service
public class ChangePwd_Mypage {
	private String user_email;//비밀번호를 변경할 이메일
	private String current_password;//현재 비밀번호
	private String change_password;//변경할 비밀번호
	private String change_password_check;//변경할 비밀번호 확인
	
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getCurrent_password() {
		return current_password;
	}
	public void setCurrent_password(String current_password) {
		this.current_password = current_password;
	}
	public String getChange_password() {
		return change_password;
	}
	public void setChange_password(String change_password) {
		this.change_password = change_password;
	}
	public String getChange_password_check() {
		return change_password_check;
	}
	public void setChange_password_check(String change_password_check) {
		this.change_password_check = change_password_check;
	}

}
