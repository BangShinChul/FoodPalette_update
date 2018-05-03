package utils;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import logic.ChangePwd;

@Service
public class ChangePasswordValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return User.class.isAssignableFrom(arg0);
	}
	
	public void validate(Object arg0, Errors arg1) {
		ChangePwd change = (ChangePwd)arg0;
		
		if(!StringUtils.hasLength(change.getUser_password())){
			arg1.rejectValue("user_password", "error.required");
		}
		
		if(!StringUtils.hasLength(change.getUser_password_check())){
			arg1.rejectValue("user_password_check", "error.required");
		}
	}
	
}
