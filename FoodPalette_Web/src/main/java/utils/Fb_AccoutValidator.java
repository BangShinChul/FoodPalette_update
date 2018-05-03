package utils;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import logic.UserInfo;

@Service
public class Fb_AccoutValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return User.class.isAssignableFrom(arg0);
	}
	
	public void validate(Object arg0, Errors arg1) {
		UserInfo user = (UserInfo)arg0;
		//데이터 미입력시 나는 오류문
		if(!StringUtils.hasLength(user.getUser_nick())){ //길이가 있는지 없는지 검사
			arg1.rejectValue("user_nick", "error.required"); //에러메시지 출력
		}
	}
	
}
