package utils;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import logic.UserInfo;

@Service
public class AddAccountValidator implements Validator{

	public boolean supports(Class<?> arg0) {
		return User.class.isAssignableFrom(arg0);
	}
	
	public void validate(Object arg0, Errors arg1) {
		UserInfo user = (UserInfo)arg0;
//		user_nick 사용자이름 
//		user_name 이름
//		user_email 이메일
//		user_password 비밀번호
//		user_question 비밀번호찾기 질문
//		user_answer 비밀번호찾기 답
//		user_profile_img 프로필이미지(디폴트로하기)
//		user_fb_check 페이스북 계정유무

		
//		error.required.user_nick=사용자이름을 입력해주세요.
//		error.required.user_name=이름을 입력해주세요.
//		error.required.user_email=이메일을 입력해주세요.
//		error.required.user_password=비밀번호를 입력해주세요.
//		error.required.user_question=질문을 입력해주세요.
//		error.required.user_answer=질문의 답을 입력해주세요.
		
		//오류문에 값까지 출력할때 이거추가
		//new Object[]{user.getUser_email()}
		//new Object[]{user.getUser_nick()}
		
		//데이터 미입력시 나는 오류문
		if(!StringUtils.hasLength(user.getUser_nick())){ //길이가 있는지 없는지 검사
			arg1.rejectValue("user_nick", "error.required"); //에러메시지 출력
		}
		if(!StringUtils.hasLength(user.getUser_name())){
			arg1.rejectValue("user_name", "error.required");
		}
		if(!StringUtils.hasLength(user.getUser_email())){
			arg1.rejectValue("user_email", "error.required");
		}
		if(!StringUtils.hasLength(user.getUser_password())){
			arg1.rejectValue("user_password", "error.required");
		}
		
		if(arg1.hasErrors()){//arg1이 에러를 가지고 있는지 없는지 검사
			arg1.reject("error.input.user");
		}//입력폼에 문제가 있는 경우
	}
}
