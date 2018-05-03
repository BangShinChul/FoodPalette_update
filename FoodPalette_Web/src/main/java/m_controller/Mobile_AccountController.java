package m_controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import logic.ChangePwd;
import logic.CheckCatalogImpl;
import logic.InsertCatalogImpl;
import logic.UpdateCatalogImpl;
import logic.UserInfo;
import utils.AddAccountValidator;
import utils.AddAccountValidator_second;
import utils.ChangePasswordValidator;
import utils.Fb_AccoutValidator;
import utils.FindPasswordValidator;
import utils.FindPasswordValidator_second;

@Controller
public class Mobile_AccountController {

	//Validator들
	@Autowired
	AddAccountValidator validator;
	@Autowired
	AddAccountValidator_second s_validator;
	@Autowired
	Fb_AccoutValidator fb_validator;
	@Autowired
	FindPasswordValidator pwd_validator;
	@Autowired
	FindPasswordValidator_second s_pwd_validator;
	@Autowired
	ChangePasswordValidator change_pwd_validator;
	
	//DB연동 DAO들
	@Autowired
	CheckCatalogImpl check;
	@Autowired
	InsertCatalogImpl insert;
	@Autowired
	UpdateCatalogImpl update;
	
	@RequestMapping(value="/mobile_Account/input.html",method=RequestMethod.GET)
	public ModelAndView goAddAccount(){
		System.out.println("가입하기 폼 들어옴");
		ModelAndView mav = new ModelAndView("mobile_home/m_addAccount_first");
		mav.addObject(new UserInfo());
		return mav;
	}
	
	@RequestMapping(value="/mobile_Account/input_first.html",method=RequestMethod.POST)
	public ModelAndView AddAccount_first(UserInfo userInfo, BindingResult bindingResult){
		
		System.out.println("----------모바일 가입하기 첫번째----------");
		System.out.println("모바일 가입하기 첫번째폼 들어옴");
		this.validator.validate(userInfo, bindingResult);//에러체크 메서드 실행
		
		System.out.println("모바일 inputFirst에서 받은 이메일 : "+userInfo.getUser_email());
		System.out.println("모바일 inputFirst에서 받은 성명 : "+userInfo.getUser_name());
		System.out.println("모바일 inputFirst에서 받은 사용자이름 : "+userInfo.getUser_nick());
		System.out.println("모바일 inputFirst에서 받은 비밀번호 : "+userInfo.getUser_password());
		
		if(bindingResult.getErrorCount() > 0){//에러가 있을 경우
			System.out.println("에러발견"+bindingResult.getAllErrors());
			
			ModelAndView mav = new ModelAndView("mobile_home/m_addAccount_first");
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		ModelAndView mav = new ModelAndView();
		//입력한 이메일 중복확인
		int checkEmail = check.checkEmail(userInfo.getUser_email()); 
		//입력한 사용자이름 중복확인
		int checkNick = check.checkNick(userInfo.getUser_nick());
			
		if(checkEmail > 0){ //이메일이 중복인경우
			System.out.println("이메일 중복 if문");
			mav.setViewName("mobile_home/m_addAccount_first");
			bindingResult.rejectValue("user_email", "error.double", new Object[]{userInfo.getUser_email()}, "이메일이 중복됩니다.");
			//properties의 오류문을 BindingResult를통해 뿌려줌. 디폴트 메시지는 무시해도됨.
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		if(checkNick > 0){ //닉네임이 중복인경우
			System.out.println("닉네임 중복 if문");
			mav.setViewName("home/addAccount_first");
			bindingResult.rejectValue("user_nick", "error.double", new Object[]{userInfo.getUser_nick()}, "사용자이름이 중복됩니다.");
			//properties의 오류문을 BindingResult를통해 뿌려줌. 디폴트 메시지는 무시해도됨.
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		
		mav.setViewName("mobile_home/m_addAccount_second");
		mav.addObject(userInfo);
		
		return mav;
	}
	

	@RequestMapping(value="/mobile_Account/input_second.html",method=RequestMethod.POST)
	public ModelAndView AddAccount_second(UserInfo userInfo, BindingResult bindingResult){
		
		//이전페이지에서 데이터제대로 넘어오는지확인
		System.out.println("----------모바일 가입하기 두번째----------");
		System.out.println("모바일 가입하기 두번째폼 들어옴");
		System.out.println("user_email : "+userInfo.getUser_email());
		System.out.println("user_name : "+userInfo.getUser_name());
		System.out.println("user_nick : "+userInfo.getUser_nick());
		System.out.println("user_password : "+userInfo.getUser_password());
		System.out.println("user_question: "+userInfo.getUser_question());
		System.out.println("user_answer: "+userInfo.getUser_answer());
		
		this.s_validator.validate(userInfo, bindingResult);//두번째 validator로 에러체크 메서드 실행
		
		if(bindingResult.getErrorCount() > 0){//에러가 있을 경우
			System.out.println("에러발견"+bindingResult.getAllErrors());	
			ModelAndView mav = new ModelAndView("mobile_home/m_addAccount_second");
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		//질문과 답변 모두 입력했을경우
		//프로필이미지는 default로 설정하고 페이스북계정유무는 NO으로 셋팅
		userInfo.setUser_profile_img("default_user.png");
		userInfo.setUser_fb_check("NO");
		insert.createAccount(userInfo);
			
		ModelAndView mav = new ModelAndView("mobile_home/m_addAccount_finish");
		mav.addObject("NAME",userInfo.getUser_nick());
		return mav;
	}
	
	@RequestMapping(value="/mobile_Account/fb_nick.html", method=RequestMethod.POST)
	public ModelAndView set_FB_Nick(UserInfo fb_user, BindingResult br, HttpSession session){
		
		ModelAndView mav = new ModelAndView();
		this.fb_validator.validate(fb_user, br);//fb용 validator로 에러체크 메서드 실행
		
		if(br.getErrorCount() > 0){//에러가 있을 경우
			System.out.println("에러발견"+br.getAllErrors());	
			mav.setViewName("mobile_home/m_first_fb_setNick");
			mav.getModel().putAll(br.getModel());
			return mav;
		}
		
		
		//이전페이지에서 데이터제대로 넘어오는지확인
		System.out.println("---이전페이지에서 넘어온 & 입력받은 데이터---");
		System.out.println("user_email : "+fb_user.getUser_email());
		System.out.println("user_name : "+fb_user.getUser_name());
		System.out.println("user_nick : "+fb_user.getUser_nick());
		
		System.out.println("---널값으로 들어갈 데이터---");
		System.out.println("user_password : "+fb_user.getUser_password());
		System.out.println("user_question: "+fb_user.getUser_question());
		System.out.println("user_answer: "+fb_user.getUser_answer());
		
		System.out.println("---입력해줘야할 데이터---");
		System.out.println("user_fb_check: "+fb_user.getUser_fb_check());
		System.out.println("user_profile_img : "+fb_user.getUser_profile_img());
		
		
		
		//데이터 제대로 넘어오면
		//입력받은 사용자이름 중복확인 후 중복안되면 계정정보 user_info에 insert
		//중복이면 오류문 날리고 다시페이지 출력
		
		
		int check_fb_nick = check.checkNick(fb_user.getUser_nick());
		if(check_fb_nick > 0){ //닉네임이 중복인경우
			System.out.println("닉네임 중복 if문");
			mav.setViewName("mobile_home/m_first_fb_setNick");
			br.rejectValue("user_nick", "error.double", new Object[]{fb_user.getUser_nick()}, "사용자이름이 중복됩니다.");
			//properties의 오류문을 BindingResult를통해 뿌려줌. 디폴트 메시지는 무시해도됨.
			mav.getModel().putAll(br.getModel());
			return mav;
		}
		
		//중복이 아닌경우->프로필이미지까지 넣어주고 fb_check YES로하고 insert
		fb_user.setUser_profile_img("default_user.png");
		fb_user.setUser_fb_check("YES");
		
		
		System.out.println("---(입력 후)입력해줘야할 데이터---");
		System.out.println("user_fb_check: "+fb_user.getUser_fb_check());
		System.out.println("user_profile_img : "+fb_user.getUser_profile_img());
		
		insert.createAccount(fb_user); //계정데이터 삽입
		
//		mav.setViewName("mobile_home/m_homepage");
		mav.setViewName("redirect:/mobile_List/recipe_list.html?FOCUS=null&FLAG=icon");
		session.setAttribute("loginUser", fb_user.getUser_email()); //로그인한 계정을 session에 올려놓는다.
		mav.addObject("USER",fb_user.getUser_email());//로그인 성공 매핑
		
		return mav;
	}
	
	
	//비밀번호찾기 폼
	@RequestMapping(value="/mobile_Account/findPassword.html", method=RequestMethod.GET)
	public ModelAndView go_FindPassword(){
		ModelAndView mav = new ModelAndView("mobile_home/m_findPassword_first");
		mav.addObject(new UserInfo());
		return mav;
	}
	
	//비밀번호찾기 폼
	@RequestMapping(value="/mobile_Account/findPassword_first.html", method=RequestMethod.POST)
	public ModelAndView findPassword_fisrt(UserInfo userInfo, BindingResult br){
		//입력받은 이메일로 가입되어있는 계정이 있는지 검사
		//있을때 - 이 계정의 fb_check가 YES인경우 - 해당 이메일은 페이스북 계정으로 가입되어 있습니다. 출력
		//있을때 - 이 계정의 fb_check가 NO인경우 - 질문과답변 페이지로 이동
		//없을때 - 해당 이메일로 가입된 계정이 없습니다. 출력
		
		System.out.println("모바일 비밀번호찾기 전달받은 이메일 : "+userInfo.getUser_email());
		
		ModelAndView mav = new ModelAndView();
		this.pwd_validator.validate(userInfo, br);//pwd용 validator로 에러체크 메서드 실행
		
		if(br.getErrorCount() > 0){//에러가 있을 경우
			System.out.println("에러발견"+br.getAllErrors());
			mav.setViewName("mobile_home/m_findPassword_first");
			mav.getModel().putAll(br.getModel());
			return mav;
		}
		
		//이메일체크 
		String msg = check.checkFBLogin(userInfo.getUser_email());
		System.out.println("리턴받은값 :"+msg);
		if(msg.equals("YES")){
			//해당 이메일은 페이스북 계정으로 가입되어 있습니다. 출력
			System.out.println("msg:YES");
			br.reject("error.double.facebook_user");
			mav.setViewName("mobile_home/m_findPassword_first");
			mav.getModel().putAll(br.getModel());
			return mav;
		}
		if(msg.equals("NO")){
			//해당이메일의 질문을 가져오고 set해준담에 페이지이동
			System.out.println("msg:NO");
			String question = check.selectQuestion(userInfo.getUser_email());
			userInfo.setUser_question(question);
			//질문과답변 페이지로 이동
			mav.setViewName("mobile_home/m_findPassword_second");
			mav.addObject(userInfo);
			return mav;
		}
		if(msg.equals("EMPTY")){
			//해당 이메일로 가입된 계정이 없습니다. 출력
			System.out.println("msg:EMPTY");
			br.reject("error.notfind.user_email");
			mav.setViewName("mobile_home/m_findPassword_first");
			mav.getModel().putAll(br.getModel());
			return mav;
		}
		return mav;
	}
		
	
	//비밀번호찾기 폼
	@RequestMapping(value="/mobile_Account/findPassword_second.html", method=RequestMethod.POST)
	public ModelAndView findPassword_second(UserInfo userInfo, BindingResult br){
		//질문과 답이 정확한지 검사
		//틀리면 오류날림
		//맞으면 비밀번호 변경창으로 이동
		
		System.out.println("전달받은 이메일 : "+userInfo.getUser_email());
		System.out.println("전달받은 질문 : "+userInfo.getUser_question());
		System.out.println("전달받은 답변 : "+userInfo.getUser_answer());
		
		ModelAndView mav = new ModelAndView();
		this.s_pwd_validator.validate(userInfo, br);//pwd용 validator로 에러체크 메서드 실행
		
		if(br.getErrorCount() > 0){//에러가 있을 경우
			System.out.println("에러발견"+br.getAllErrors());	
			mav.setViewName("mobile_home/m_findPassword_second");
			mav.getModel().putAll(br.getModel());
			return mav;
		}
		
		//이메일로 질문의 답을 검색
		String answer = check.checkAnswer(userInfo.getUser_email());

		if(userInfo.getUser_answer().equals(answer)){
			//입력받은 질문과 검색결과가 같으면 다음페이지로 이동	
			mav.setViewName("mobile_home/m_findPassword_third");
			ChangePwd changePwd = new ChangePwd();
			changePwd.setUser_email(userInfo.getUser_email());	
			mav.addObject(changePwd);
			return mav;
		}else{
			//입력받은 질문과 검색결과가 다르면 오류날림	
			br.reject("error.fail.answer");
			mav.setViewName("mobile_home/m_findPassword_second");
			mav.getModel().putAll(br.getModel());
		}
		return mav;
	}
	
	//비밀번호찾기 폼-마지막 비밀번호변경
	@RequestMapping(value="/mobile_Account/findPassword_third.html", method=RequestMethod.POST)
	public ModelAndView findPassword_third(ChangePwd changePwd, BindingResult br){
		//비밀번호와 비밀번호확인이 일치하는지 검사
		//일치하면 해당이메일의 user_password 변경
		//일치하지않으면 오류날림
		
		ModelAndView mav = new ModelAndView();
		this.change_pwd_validator.validate(changePwd, br);//비밀번호변경용 validator로 에러체크 메서드 실행
		if(br.getErrorCount() > 0){//에러가 있을 경우
			System.out.println("에러발견"+br.getAllErrors());	
			mav.setViewName("mobile_home/m_findPassword_third");
			mav.getModel().putAll(br.getModel());
			return mav;
		}
		
		System.out.println("전달받은 이메일 : "+changePwd.getUser_email());
		System.out.println("전달받은 비밀번호 : "+changePwd.getUser_password());
		System.out.println("전달받은 비밀번호확인 : "+changePwd.getUser_password_check());
		
		String pwd = changePwd.getUser_password();
		String pwd_check = changePwd.getUser_password_check();
		
		if(pwd.equals(pwd_check)){
			//비밀번호와 비밀번호확인이 일치하는 경우
			//이메일 계정의 비밀번호를 변경 후 페이지전환
			update.updatePassword(changePwd);
			mav.setViewName("mobile_home/m_findPassword_finish");
		}else{
			//비밀번호와 비밀번호확인이 일치하지 않은경우
			//오류문날림
			br.reject("error.same.user_password");
			mav.setViewName("mobile_home/m_findPassword_third");
			mav.getModel().putAll(br.getModel());
			return mav;
		}
		return mav;
	}
}
