package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import logic.CheckCatalogImpl;
import logic.UserInfo;
import utils.LoginValidator;

@Controller
public class LoginController {

	
	@Autowired
	LoginValidator validator;
		
	@Autowired
	CheckCatalogImpl check;
	
	@RequestMapping(value="/login/login.html",method=RequestMethod.GET)
	public ModelAndView loginWindow(){
		ModelAndView mav = new ModelAndView("home/login");
		mav.addObject(new UserInfo());
		return mav;
	}

	@RequestMapping(value="/login/home.html",method=RequestMethod.GET)
	public ModelAndView loginHome(){
		ModelAndView mav = new ModelAndView("home/homepage");
		return mav;
	}
	
	@RequestMapping(value="/login/do_login.html",method=RequestMethod.POST)
	public ModelAndView doLogin(UserInfo userInfo, BindingResult br, HttpSession session){
		
		ModelAndView mav = new ModelAndView();
		
		String email = userInfo.getUser_email();
		String password = userInfo.getUser_password();
		
		String fb_check = check.checkFBLogin(email);
		String user_nick = check.getLoginUserNick(email);
		
		System.out.println("doLogin 진입 : fb_check : "+fb_check);
		//아이디와 비밀번호 입력했는지 오류검색
		this.validator.validate(userInfo, br);//에러체크 메서드 실행
		
		if(br.getErrorCount() > 0){//에러가 있을 경우
			System.out.println("에러발견"+br.getAllErrors());
			mav.setViewName("home/login");
			mav.getModel().putAll(br.getModel());
			return mav;
		}
		if(fb_check.equals("YES")){
			System.out.println("페이스북 아이디로 일반 로그인시도를 한 경우");
			br.reject("error.login.fb_email");
			mav.setViewName("home/login");
			mav.getModel().putAll(br.getModel());
			return mav;
		}else if(fb_check.equals("NO")){
			System.out.println("일반 아이디로 일반 로그인시도를 한 경우");
			//아이디와 비밀번호를 가져와서 체크
			String pwd = check.checkLogin(email);
			if(!pwd.equals(password)){//아이디와 비밀번호가 맞지않을때
				System.out.println("일반계정의 이메일과 비밀번호가 맞지않을때");
				br.reject("error.login.user");//오류 출력
				mav.setViewName("home/login");
				mav.getModel().putAll(br.getModel());
				return mav;
			}

			if(pwd.equals(password)){//아이디와 비밀번호가 일치할때
				System.out.println("일반계정의 이메일과 비밀번호가 일치할때");
				mav.setViewName("home/homepage");
				session.setAttribute("loginUser", email); //로그인한 계정을 session에 올려놓는다.
				session.setAttribute("user_nick", user_nick);
				session.setAttribute("FB_CHECK", fb_check);
				mav.addObject("USER",email);//로그인 성공 매핑
			}
		}else if(fb_check.equals("EMPTY")){
			System.out.println("해당 이메일로 가입된 계정이 없을때");
			br.reject("error.notfind.user_email");
			mav.setViewName("home/login");
			mav.getModel().putAll(br.getModel());
			return mav;
		}
		
		return mav;
	}
	
	@RequestMapping(value="/login/fb_login.html", method=RequestMethod.POST)
	public ModelAndView FBloginCheck(UserInfo userInfo, HttpSession session){
		//Log
		System.out.println("FBloginCheck Start");
				     
		//모델값 저장
		String fb_user_name = userInfo.getUser_name();//user_name
		String fb_user_email = userInfo.getUser_email();//user_email
		fb_user_name = fb_user_name.replaceAll(" ", "");
		System.out.println("입력값 : "+fb_user_name+" / "+fb_user_email);
					      
		String user_nick = check.getLoginUserNick(fb_user_email);
		
		ModelAndView mav = new ModelAndView();
					      
		//이메일로 기존의 계정이 있는지 검색
		//페이스북 계정으로 로그인했는데 일반계정이메일과 겹칠경우
		//-> 일반계정으로 로그인시킨다.
		String msg = check.checkFBLogin(fb_user_email);
		
		System.out.println("FB로그인시도 이메일 : "+fb_user_email);
		System.out.println("DB체크결과  : "+msg);	      
		
		
		if(msg.equals("YES")){
			System.out.println("페이스북으로 로그인한 이메일이 이미 페이스북 계정으로 가입되어있을때");
			//페이스북으로 로그인한 이메일이 이미가입되어있고 그 이메일이 페이스북계정용 일 때 
			//페이스북계정으로 로그인 시킴
			mav.setViewName("home/homepage");
			session.setAttribute("loginUser", fb_user_email); //로그인한 계정을 session에 올려놓는다.
			session.setAttribute("user_nick", user_nick);
			session.setAttribute("FB_CHECK", "YES");
			return mav;
		}
		if(msg.equals("NO")){
			System.out.println("페이스북으로 로그인한 이메일이 이미 일반계정으로 가입되어있을때");
			//페이스북으로 로그인한 이메일이 이미가입되어있고 그 이메일이 일반계정용 일 때 
			//일반계정으로 로그인 시킴
			mav.setViewName("home/homepage");
			session.setAttribute("loginUser", fb_user_email); //로그인한 계정을 session에 올려놓는다.
			session.setAttribute("user_nick", user_nick);
			session.setAttribute("FB_CHECK", "NO");
			return mav;
		}
		if(msg.equals("EMPTY")){
			System.out.println("페이스북으로 로그인한 이메일이 DB에 없는경우(신규로그인)");
			//페이스북으로 로그인한 이메일이 DB에 없는경우(신규로그인)
			//닉네임 정하는 페이지로 이동
			//닉네임 정한 후 DB에 insert시킴
			mav.setViewName("home/first_fb_setNick");
			mav.addObject(userInfo);
			return mav;
		}
		return mav;
	}
	
}
