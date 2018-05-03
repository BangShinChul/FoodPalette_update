package utils;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import logic.ChangePwd_Mypage;

@Service
public class ChangePasswordMyPageValidator {

	public boolean supports(Class<?> arg0) {
		return ChangePwd_Mypage.class.isAssignableFrom(arg0);
	}
	
	public void validate(Object arg0, Errors arg1) {
		ChangePwd_Mypage change = (ChangePwd_Mypage)arg0;
		
		if(!StringUtils.hasLength(change.getCurrent_password())){
			arg1.rejectValue("current_password", "error.required");
		}
		
		if(!StringUtils.hasLength(change.getChange_password())){
			arg1.rejectValue("change_password", "error.required");
		}
		
		if(!StringUtils.hasLength(change.getChange_password_check())){
			arg1.rejectValue("change_password_check", "error.required");
		}
	}
	
}
