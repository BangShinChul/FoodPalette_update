package utils;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import logic.UserInfo;

//Validator : 항목체크를 해주는 객체

@Service
public class LoginValidator implements Validator{

	//클래스 생성방법 1
	//A abc = new A();
	
	//클래스 생성방법 2(메타데이터 사용)
	//Class a = Class.forName("leader.A"); ->leader패키지의 A라는 이름의 클래스를 찾아라 라는뜻
	//A o = (A)a.newInstance(); ->A클래스 타입의 새로운 인스턴스를 생성해라 그리고 그것을 변수o에 넣어라 라는뜻
	//이런식으로 일반적인 클래스생성법 말고도 Class를 사용하여 클래스를 생성할수도있다.
	
	//Class<?> : 명령어 클래스가 아니고 우리가 만든 클래스에대한 메타데이터를 가지고있는 클래스이다.
	//메타데이터 : 데이터의 데이터. ex)학생정보 테이블의 갯수,컬럼명 같은 데이터의 데이터를 메타데이터 라고한다.
	//내가 만든 클래스의 이름이 무엇인지, 무슨 메서드를 가지고있는지 등등 모든 데이터가 Class<?>에 들어있다.
	//<?>의 의미 : 제네릭안에 (어떤 이름의 클래스든)어떤 타입이 와도 괜찮다는뜻.
	public boolean supports(Class<?> arg0) {
		return User.class.isAssignableFrom(arg0);
	}//User클래스가 유효성검사의 대상인지 아닌지 검사함. 검사대상이면 true 아니면 false
	//User클래스의 인스턴스를 가지고 검사하는것이 아닌 그저 이름으로만 찾아서 검사하는것임
	//한마디로 클래스를 생성해서 검사하는것이 아님. 그렇기위해서 매개변수타입이 Class<?>인것임.

	//arg0에 항목체크대상(id,password)이 자동으로 들어간다.
	//위의 supports 메서드의 결과가 들어오는 것임.
	public void validate(Object arg0, Errors arg1) {
		UserInfo userInfo = (UserInfo)arg0;
		//로그인 시 입력받은 이메일과 패스워드가 일치하면 통과
		//페이스북 로그인 시 ~~??
		
		if(!StringUtils.hasLength(userInfo.getUser_email())){ //길이가 있는지 없는지 검사
			arg1.rejectValue("user_email", "error.required"); //에러메시지 출력
		}//이메일 입력하지 않은 경우
		if(!StringUtils.hasLength(userInfo.getUser_password())){
			arg1.rejectValue("user_password", "error.required");
		}//비밀번호를 입력하지 않은 경우
		
	}

}
