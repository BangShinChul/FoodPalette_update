package m_controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import logic.ChangePwd_Mypage;
import logic.CheckCatalogImpl;
import logic.FirstImages;
import logic.InsertCatalogImpl;
import logic.ProfileUtil;
import logic.UpdateCatalogImpl;
import logic.UpdateProfile;
import logic.UserInfo;
import utils.ChangePasswordMyPageValidator;

@Controller
public class Mobile_MypageController {

	@Autowired
	ChangePasswordMyPageValidator pwd_validator;
	
	//DB연동 DAO들
	@Autowired
	CheckCatalogImpl check;
	@Autowired
	InsertCatalogImpl insert;
	@Autowired
	UpdateCatalogImpl update;
	
	@Autowired
	UpdateProfile update_profile;
	
	@RequestMapping(value="/mobile_Mypage/mypage.html",method=RequestMethod.GET)
	public ModelAndView goMypage(HttpSession session){
		//session에 올라가있는 user_email로 user_profile_img가져오기
		String email = (String)session.getAttribute("loginUser");
		String user_nick = (String)session.getAttribute("user_nick");
		String user_profile_img = check.getProfileImg(email);
		session.setAttribute("profile", user_profile_img);
	
		//나의게시물
		//참고
		//추가부분
//		int maxPid = crud.selectProductCnt();
//		int[] tr_index = new int[(maxPid/3+1)];
//		int max = maxPid/3+1;
//		for(int i=1;i<=max;i++){
//			tr_index[i-1] = i;
//		}
//		request.setAttribute("TR_INDEX", tr_index);
//		request.setAttribute("MAX", max);

		int maxPid = check.getMaxRecipeNum(user_nick);
		int[] tr_index = new int[(maxPid/3+1)];
		int max = maxPid/3+1;
		for(int i=1; i<=max; i++){
			tr_index[i-1] = i;
		}
		
		List<FirstImages> img1 = check.getFirstImgs(user_nick);
		System.out.println("list size : "+img1.size());
		Iterator it = img1.iterator();
		String[] firstImg = new String[img1.size()];
		String[] recipe_id = new String[img1.size()];
		int cnt = 0;
		while(it.hasNext()){
			FirstImages image = (FirstImages)it.next();
			System.out.println("값:"+image.getRecipe_img1());
			firstImg[cnt] = image.getRecipe_img1();
			recipe_id[cnt] = image.getRecipe_id();
			cnt++;
		}
		
		
		ModelAndView mav = new ModelAndView("mobile_home/m_mypage");
		mav.addObject("MAX",max);
		mav.addObject("MAXPID",maxPid);
		System.out.println("MAX : "+max);
		mav.addObject("TR_INDEX",tr_index);
		mav.addObject("FIRST_IMGS",firstImg);
		mav.addObject("RECIPE_ID",recipe_id);
		mav.addObject(new ProfileUtil());
		return mav;
	}
	
	//../mypage/update_profile.html
	@RequestMapping(value="/mobile_Mypage/update_profile.html",method=RequestMethod.POST)
	public ModelAndView updateMyProfile(ProfileUtil upload_profile, BindingResult br, HttpServletRequest request, HttpSession session){
		
		ModelAndView mav = new ModelAndView("mobile_home/m_mypage");
		
		System.out.println("프로필사진 업데이트 컨트롤러 들어옴");
		if(upload_profile.getUser_profile_img()==null){
			System.out.println("업로드받은 프로필이미지 null");
		}else{
			System.out.println("업로드받은 프로필이미지 null 아님");
		}
		
		if(br.getErrorCount() > 0){//에러가 있을 경우
			System.out.println("에러발견"+br.getAllErrors());
			mav.getModel().putAll(br.getModel());
			br.reject("error.input.user");
			mav.addObject("BODY","m_mypage.jsp");
			return mav;
		}
		
		//로그인되어있는 계정의 email과 nick을 session에서 가져오기
		String user_email = (String)session.getAttribute("loginUser");
		String user_nick = (String)session.getAttribute("user_nick");
		
		//파일처리
		MultipartFile upload_img = upload_profile.getUser_profile_img();
		String profile = user_nick+"_"+upload_img.getOriginalFilename();
		System.out.println("업데이트할 프로필이미지 : "+profile);
		
		//파일 확장자 (jpg,gif,png) 확인
		if(!profile.contains(".jpg") && !profile.contains(".gif") && !profile.contains(".png")){
			//확장자가 jpg, gif, png가 아닌 경우
			//오류 날림
			System.out.println("프로필이미지 확장자오류");
			br.reject("error.mypage.profile_type");
			mav.addObject("BODY","m_mypage.jsp");
			return mav;
		}
		
		//파일 저장(서버에 이미지파일 업로드)
        String path = request.getSession().getServletContext().getRealPath("/upload/"+profile);
        System.out.println("경로 : "+path);
        File file = new File(path);   
        try{
           OutputStream out = new FileOutputStream(path);
           BufferedInputStream bis = new BufferedInputStream(upload_img.getInputStream());
           byte[] buffer = new byte[8106];
           int read = 0;
           while((read=bis.read(buffer))>0){//읽은 내용 無 : -1
              out.write(buffer, 0, read);
           }//파일로 출력
           if(out != null) out.close();
        }catch(Exception e){
           e.printStackTrace();
        }
        
        //DB에 저장
        update_profile.setUser_email(user_email);
        update_profile.setUser_profile_img(profile);
        update.updateProfile(update_profile);
       
        
        //UnsupportedEncodingException
        //업데이트한 프로필이미지와 함께 마이페이지 다시불러줌
        try{
        	String user_profile_img = check.getProfileImg(user_email);
        	System.out.println("업로드할 이미지명(한글처리전) : "+user_profile_img);
    		session.setAttribute("profile", URLEncoder.encode(user_profile_img,"euc-kr"));
    		System.out.println("업로드할 이미지명(한글처리후) : "+URLEncoder.encode(user_profile_img,"euc-kr"));
    		mav.addObject("BODY","m_mypage.jsp");
    		
    		int maxPid = check.getMaxRecipeNum(user_nick);
    		int[] tr_index = new int[(maxPid/3+1)];
    		int max = maxPid/3+1;
    		for(int i=1; i<=max; i++){
    			tr_index[i-1] = i;
    		}
    		
    		List<FirstImages> img1 = check.getFirstImgs(user_nick);
    		System.out.println("list size : "+img1.size());
    		Iterator it = img1.iterator();
    		String[] firstImg = new String[img1.size()];
    		String[] recipe_id = new String[img1.size()];
    		int cnt = 0;
    		while(it.hasNext()){
    			FirstImages image = (FirstImages)it.next();
    			System.out.println("값:"+image.getRecipe_img1());
    			firstImg[cnt] = image.getRecipe_img1();
    			recipe_id[cnt] = image.getRecipe_id();
    			cnt++;
    		}
    		
    		mav.addObject("MAX",max);
    		System.out.println("MAX : "+max);
    		mav.addObject("MAXPID",maxPid);
    		mav.addObject("TR_INDEX",tr_index);
    		mav.addObject("FIRST_IMGS",firstImg);
    		mav.addObject("RECIPE_ID",recipe_id);
    		mav.addObject(new ProfileUtil());

        }catch(UnsupportedEncodingException e){
        	e.printStackTrace();
        }
		return mav;
	}
	
	@RequestMapping(value="/mobile_Mypage/go_changePwd.html",method=RequestMethod.GET)
	public ModelAndView go_changePassword(HttpSession session){
		//비밀번호 변경 페이지로 이동
		ModelAndView mav = new ModelAndView("mobile_home/m_homepage");
		mav.addObject("BODY","m_changePassword.jsp");
		mav.addObject(new ChangePwd_Mypage());
		return mav;
	}
	
	@RequestMapping(value="/mobile_Mypage/changePwd.html",method=RequestMethod.POST)
	public ModelAndView changePassword(ChangePwd_Mypage change,BindingResult br,HttpSession session){
		//마이페이지-비밀번호 변경
		System.out.println("마이페이지 - 비밀번호변경 컨트롤러 들어옴");
		ModelAndView mav = new ModelAndView("mobile_home/m_homepage");
		
		this.pwd_validator.validate(change, br);//비밀번호변경용 validator로 에러체크 메서드 실행
		
		if(br.getErrorCount() > 0){//에러가 있을 경우
			System.out.println("에러발견"+br.getAllErrors());
			mav.getModel().putAll(br.getModel());
			mav.addObject("BODY","m_changePassword.jsp");
			return mav;
		}
		
		//session에 올려져있는 로그인된 계정의 email과 
		//입력받은 현재비밀번호, 변경할 비밀번호, 비밀번호확인을 받아옴
		String user_email = (String)session.getAttribute("loginUser");
		String current_pwd = change.getCurrent_password();
		String change_pwd = change.getChange_password();
		String change_pwd_check = change.getChange_password_check();
		
		System.out.println("비밀번호를 변경할 email : "+user_email);
		System.out.println("현재 비밀번호 : "+current_pwd);
		System.out.println("변경할 비밀번호 : "+change_pwd);
		System.out.println("변경할 비밀번호 확인 : "+change_pwd_check);
		
		//email의 현재비밀번호가 맞는지 검사
		String check_current_pwd = check.getCurrentPwd(user_email);
		if(!check_current_pwd.equals(current_pwd)){
			//db검색한 현재비밀번호와 입력받은 현재비밀번호가 다르면
			System.out.println("db검색한 현재비밀번호와 입력받은 현재비밀번호가 다를 경우");
			br.reject("error.mypage.my_password");
			mav.getModel().putAll(br.getModel());
			mav.addObject("BODY","m_changePassword.jsp");
			return mav;
		}
		//변경할 비밀번호와 비밀번호확인이 맞는지 검사
		if(!change_pwd.equals(change_pwd_check)){
			//변경할 비밀번호와 비밀번호확인이 맞지 않으면
			System.out.println("변경할 비밀번호와 비밀번호확인이 맞지 않을 경우");
			br.reject("error.mypage.change_password");
			mav.getModel().putAll(br.getModel());
			mav.addObject("BODY","m_changePassword.jsp");
			return mav;
		}
		if(check_current_pwd.equals(current_pwd)&&
			change_pwd.equals(change_pwd_check)){
			//모든 폼을 정확히 입력했으면
			//새로입력한 비밀번호로 비밀번호 변경
			change.setUser_email(user_email);
			update.updatePassword_Mypage(change);
			
			System.out.println("모든 폼을 정확히 입력했을 경우");
			mav.addObject("BODY","m_changePassword_finish.jsp");
		}
		return mav;
	}
	
	
	@RequestMapping(value="/mobile_Mypage/logout.html",method=RequestMethod.GET)
	public ModelAndView logout(HttpSession session){
		//로그인된 정보의 session을 다 날려버림
		session.removeAttribute("loginUser");
		session.removeAttribute("user_nick");
		session.removeAttribute("FB_CHECK");
		session.removeAttribute("profile");
		
		ModelAndView mav = new ModelAndView("mobile_home/m_login");
		mav.addObject(new UserInfo());
		return mav;
	}
}
