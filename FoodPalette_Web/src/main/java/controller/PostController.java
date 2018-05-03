package controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import logic.CheckCatalog;
import logic.Comment;
import logic.DeleteCatalog;
import logic.HeartUtil;
import logic.InsertCatalog;
import logic.RecipeInfo;
import logic.UpdateCatalog;
import logic.UploadUtil;
import utils.PostValidator;

@Controller
public class PostController{
	
	@Autowired
	private CheckCatalog checkCatalog;
	@Autowired
	private InsertCatalog insertCatalog;
	
	//jung
	@Autowired
	private UpdateCatalog updateCatalog;
	@Autowired
	private DeleteCatalog deleteCatalog;
	@Autowired
	private PostValidator postValidator;

	//jung
	//////////////////각종 메서드들
	//처음파일 NULL 오류처리 메서드
	private ModelAndView findFirstNullFile(RecipeInfo recipeInfo, BindingResult br){
		if(recipeInfo.getImg_food().get(0).getOriginalFilename().equals("")) {
			//log
			System.out.println("한장의 사진도 올리지 않은 경우");
			
			//페이지전환
			ModelAndView mav = new ModelAndView("home/homepage");
			br.reject("error.upload.not_frist_img");
			mav.addObject(recipeInfo);
			mav.addObject("BODY","postRecipe_img.jsp");
			return mav;
		}
		return null;
	}
	
	//그림파일 아닌 경우 오류처리 메서드
	private ModelAndView findNotImgFile(RecipeInfo recipeInfo, BindingResult br){
		List<MultipartFile> img_filter = recipeInfo.getImg_food();
		Iterator filter_it = img_filter.iterator();
		
		while(filter_it.hasNext()){
			MultipartFile imgs = (MultipartFile)filter_it.next();
			String fileName = imgs.getOriginalFilename();
			if(!fileName.contains(".jpg") && !fileName.contains(".gif") && !fileName.contains(".png") && !fileName.equals("")){
				//log
				System.out.println("이미지타입이 아닌 파일 업로드 경우");
				
				//페이지전환
				ModelAndView mav = new ModelAndView("home/homepage");
				br.reject("error.upload.not_img_file");
				recipeInfo.getImg_food().clear();
				mav.addObject(recipeInfo);
				mav.addObject("BODY","postRecipe_img.jsp");
				return mav;
			}
		}
		return null;
	}
	
	//그림파일중 5.0MB이상의 그림이 있는 경우 에러 처리 메서드
	private ModelAndView findImgMoreThanMaxSize(RecipeInfo recipeInfo, BindingResult br){
		List<MultipartFile> img_filter = recipeInfo.getImg_food();
		Iterator filter_it = img_filter.iterator();
		
		while(filter_it.hasNext()){
			MultipartFile imgs = (MultipartFile)filter_it.next();
			double fileSize = imgs.getSize()/1024.0d/1024.0d;
			System.out.println("이미지 사이즈 : "+fileSize);
			if(fileSize > 5.00d){
				//log
				System.out.println("이미지 파일 중 5MB가 넘는 파일이 있는 경우");
				
				//페이지전환
				ModelAndView mav = new ModelAndView("home/homepage");
				br.reject("error.upload.max_size_img");
				recipeInfo.getImg_food().clear();
				mav.addObject(recipeInfo);
				mav.addObject("BODY","postRecipe_img.jsp");
				return mav;
			}
		}
		return null;
	}
	
	//중간,끝에 NUll이 있는 경우 처리
	private Integer getSizeIfImgsHaveNull(RecipeInfo recipeInfo){
		//log
		System.out.println("이미지 파일 중 중간, 끝이 NULL인 경우");
	
		//이미지 배열값 계산
		List<MultipartFile> img_filter = recipeInfo.getImg_food();
		Iterator filter_it = img_filter.iterator();
		int count = 0;
		while(filter_it.hasNext()){
			MultipartFile imgs = (MultipartFile)filter_it.next();
			String fileName = imgs.getOriginalFilename();
			if(!fileName.equals("")){
				count++;
			}
		}
		
		System.out.println("이미지 배열 사이즈 : "+count);
		return count;
	}
	
	//해쉬태그, 레시피 내용 에러처리 메서드
	private ModelAndView findRecipeContentAndHashtagIsNull(RecipeInfo recipeInfo,BindingResult br, String flag){
		//데이터 저장
		String recipe_content = recipeInfo.getRecipe_content().trim();
		String recipe_hashtag = recipeInfo.getRecipe_hashtag().trim();
		Integer recipe_img_order = recipeInfo.getRecipe_img_order();
		
		
		//레시피내용, 해쉬태그 에러 처리
		if(recipe_content.equals("") || recipe_hashtag.equals("")){
		
			//이미지 배열
			String[] img_name = this.saveImgToArray(recipeInfo, "upload");
			
			//에러발생
			if(recipe_content.equals("") && recipe_hashtag.equals("")) br.reject("error.upload.all_input_null");
			else if(recipe_hashtag.equals("")) br.reject("error.upload.recipe_hashtag_null");
			else if(recipe_content.equals("")) br.reject("error.upload.recipe_content_null");
			
			//화면전환
			
			ModelAndView mav = new ModelAndView("home/homepage");
			mav.addObject(recipeInfo);
			mav.addObject("IMG_NAME",img_name);
			if(flag.equals("upload")) mav.addObject("BODY","postRecipe_content.jsp");
			else if(flag.equals("modify")) mav.addObject("BODY", "modifyRecipe_content.jsp");
			return mav;
		}
		return null;
	}
	
	//이미지 파일명 배열 저장 메서드
	private String[] saveImgToArray(RecipeInfo recipeInfo, String flag){
		String[] img_name = new String[recipeInfo.getRecipe_img_order()];
		System.out.println("수정할 이미지 파일명 "+recipeInfo.getRecipe_img1());
		int start = 0; if(flag.equals("upload")) start=24; 
		if(recipeInfo.getRecipe_img1()!=null) img_name[0] = (recipeInfo.getRecipe_img1().substring(start,recipeInfo.getRecipe_img1().length()));
		if(recipeInfo.getRecipe_img2()!=null) img_name[1] = (recipeInfo.getRecipe_img2().substring(start,recipeInfo.getRecipe_img2().length()));
		if(recipeInfo.getRecipe_img3()!=null) img_name[2] = (recipeInfo.getRecipe_img3().substring(start,recipeInfo.getRecipe_img3().length()));
		if(recipeInfo.getRecipe_img4()!=null) img_name[3] = (recipeInfo.getRecipe_img4().substring(start,recipeInfo.getRecipe_img4().length()));
		if(recipeInfo.getRecipe_img5()!=null) img_name[4] = (recipeInfo.getRecipe_img5().substring(start,recipeInfo.getRecipe_img5().length()));
		if(recipeInfo.getRecipe_img6()!=null) img_name[5] = (recipeInfo.getRecipe_img6().substring(start,recipeInfo.getRecipe_img6().length()));
		if(recipeInfo.getRecipe_img7()!=null) img_name[6] = (recipeInfo.getRecipe_img7().substring(start,recipeInfo.getRecipe_img7().length()));
		if(recipeInfo.getRecipe_img8()!=null) img_name[7] = (recipeInfo.getRecipe_img8().substring(start,recipeInfo.getRecipe_img8().length()));
		if(recipeInfo.getRecipe_img9()!=null) img_name[8] = (recipeInfo.getRecipe_img9().substring(start,recipeInfo.getRecipe_img9().length()));
		if(recipeInfo.getRecipe_img10()!=null) img_name[9] = (recipeInfo.getRecipe_img10().substring(start,recipeInfo.getRecipe_img10().length()));
		return img_name;
	}
	
	//이미지 파일 VO저장 매서드
	private RecipeInfo saveImgInValueObject(RecipeInfo recipeInfo){
		if(recipeInfo.getRecipe_img1()!=null) recipeInfo.setRecipe_img1(recipeInfo.getRecipe_img1().substring(24,recipeInfo.getRecipe_img1().length()));
		if(recipeInfo.getRecipe_img2()!=null) recipeInfo.setRecipe_img2(recipeInfo.getRecipe_img2().substring(24,recipeInfo.getRecipe_img2().length()));
		if(recipeInfo.getRecipe_img3()!=null) recipeInfo.setRecipe_img3(recipeInfo.getRecipe_img3().substring(24,recipeInfo.getRecipe_img3().length()));
		if(recipeInfo.getRecipe_img4()!=null) recipeInfo.setRecipe_img4(recipeInfo.getRecipe_img4().substring(24,recipeInfo.getRecipe_img4().length()));
		if(recipeInfo.getRecipe_img5()!=null) recipeInfo.setRecipe_img5(recipeInfo.getRecipe_img5().substring(24,recipeInfo.getRecipe_img5().length()));
		if(recipeInfo.getRecipe_img6()!=null) recipeInfo.setRecipe_img6(recipeInfo.getRecipe_img6().substring(24,recipeInfo.getRecipe_img6().length()));
		if(recipeInfo.getRecipe_img7()!=null) recipeInfo.setRecipe_img7(recipeInfo.getRecipe_img7().substring(24,recipeInfo.getRecipe_img7().length()));
		if(recipeInfo.getRecipe_img8()!=null) recipeInfo.setRecipe_img8(recipeInfo.getRecipe_img8().substring(24,recipeInfo.getRecipe_img8().length()));
		if(recipeInfo.getRecipe_img9()!=null) recipeInfo.setRecipe_img9(recipeInfo.getRecipe_img9().substring(24,recipeInfo.getRecipe_img9().length()));
		if(recipeInfo.getRecipe_img10()!=null) recipeInfo.setRecipe_img10(recipeInfo.getRecipe_img10().substring(24,recipeInfo.getRecipe_img10().length()));
		return recipeInfo;
	}

	
	////맵핑메서드////////////////////////////////////////////////////////////////////////
	@RequestMapping(value="/post/upload_content.html", method=RequestMethod.POST)
	public ModelAndView upload_all(RecipeInfo recipeInfo, BindingResult br, HttpSession session){
		//log
		System.out.println("내용업로드 메서드 통과");		
				
		//정보가져오기
		String recipe_content = recipeInfo.getRecipe_content().trim();
		String recipe_hashtag = recipeInfo.getRecipe_hashtag().trim();
		Integer recipe_img_order = recipeInfo.getRecipe_img_order();
		
		//세션가져오기
		String user_nick = (String)session.getAttribute("user_nick");
				
		//recipe_hastag 변형(해쉬태그 문자열 처리)
		char[] fix = recipe_hashtag.toCharArray();
		boolean text_flag = false;
		String hashtag_temp = "";
		System.out.println("정렬과정(초기) "+fix);
		for(int i=0;i<fix.length;i++){
			if(fix[i]!='#' && text_flag == false){
				hashtag_temp = hashtag_temp+ "#" + fix[i];
				text_flag = true;
			}else if(fix[i]!='#' && text_flag == true){
				hashtag_temp = hashtag_temp + fix[i];
			}else if(fix[i]=='#'){
				text_flag = false;
			}
		}	
		recipe_hashtag = hashtag_temp;
	
		//해쉬태그, 레시피 내용 에러처리
		ModelAndView find = findRecipeContentAndHashtagIsNull(recipeInfo, br, "upload");
		if(find!=null) return find;
			
				
		//Recipe_id 생성
		List<String> allRecipeIdByNick = checkCatalog.checkRecipeIdCount(user_nick);
		String recipe_id="";
		if(allRecipeIdByNick.size() == 0){
			System.out.println("recipeId NULL");
			recipe_id = user_nick+"_"+"1";
		}else{	
			System.out.println("recipeId NOT NULL");	
			Integer myCount[] = new Integer[allRecipeIdByNick.size()];
			Iterator it = allRecipeIdByNick.iterator();
			int cnt = 0;
	 		while(it.hasNext()){
				UploadUtil uu = (UploadUtil)it.next();
				String id = uu.getSelect_recipe_id();
				myCount[cnt] = Integer.valueOf(id.substring(id.indexOf("_")+1, id.length()));
				cnt++;			
			}
	 		
	 		Arrays.sort(myCount);
	 		Integer myMaxCount = myCount[myCount.length-1];		
			recipe_id = user_nick+"_"+(myMaxCount+1);
			}		
		
		//Recipe_count 생성
		Integer recipe_count = checkCatalog.checkRecipeCountMax();
		if(recipe_count == null) recipe_count = 1;
		else recipe_count++;
		
		//Recipe_posttime 생성
		DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyMMddHHmmss");
		String recipe_posttime = fmt.print(dt);	
		
	
		//log
		System.out.println("recipe_id : "+recipe_id);
		System.out.println("Recipe_posttime : " +recipe_posttime);
		System.out.println("recipe_count : " + recipe_count);
		System.out.println("recipe_content : "+recipe_content);
		System.out.println("recipe_hashtag : "+recipe_hashtag);
		System.out.println("recipe_img_order : "+recipe_img_order);
		
		
		//VO 저장
		//RecipeInfo recipeInfoSet = new RecipeInfo();
		recipeInfo.setRecipe_id(recipe_id);
		recipeInfo.setRecipe_count(recipe_count);
		recipeInfo.setRecipe_content(recipe_content);
		recipeInfo.setRecipe_hashtag(recipe_hashtag);
		recipeInfo.setRecipe_posttime(recipe_posttime);
		recipeInfo.setUser_nick(user_nick);
		recipeInfo.setRecipe_img_order(recipe_img_order);

		//DB연동
		this.insertCatalog.postRecipe(saveImgInValueObject(recipeInfo));
		
		//heart_info에 insert
		HeartUtil util = new HeartUtil();
		util.setHeart(0);
		util.setRecipe_id(recipe_id);
		this.insertCatalog.postHeart(util);
		
		//컨트롤러 이동(레시피 목록)
		return new ModelAndView("redirect:/list/recipe_list.html");
	}
	
	
	@RequestMapping(value="/post/upload_start.html", method=RequestMethod.GET)
	public ModelAndView create_model(){
		//log
		System.out.println("RecipeInfo 객체 생성");
		
		//객체생성
		ModelAndView mav = new ModelAndView("home/homepage");
		mav.addObject("BODY","postRecipe_img.jsp");
		mav.addObject(new RecipeInfo());
		return mav;
	}
	
	@RequestMapping(value="/post/upload_img.html", method=RequestMethod.POST)
	public ModelAndView upload_img(RecipeInfo recipeInfo, BindingResult br, HttpServletRequest request, HttpSession session){
		//log
		System.out.println("파일업로드 메서드 통과");
		
		//템플릿 설정
		ModelAndView mav = new ModelAndView("home/homepage");
		
		
		//처음파일 NULL 처리
		ModelAndView findFirstNullFileResult = findFirstNullFile(recipeInfo,br);
		if(findFirstNullFileResult!=null) return findFirstNullFileResult;
		
		//그림파일 아닌 경우 처리
		ModelAndView findNotImgFileResult = findNotImgFile(recipeInfo,br);
		if(findNotImgFileResult!=null) return findNotImgFileResult;
		
		//5MB이상 파일인 경우 처리
		ModelAndView findImgMoreThanMaxSizeResult = findImgMoreThanMaxSize(recipeInfo,br);
		if(findImgMoreThanMaxSizeResult!=null) return findImgMoreThanMaxSizeResult;
		
		//세션 가져오기
		String user_nick = (String)session.getAttribute("user_nick");
			
		//리스트 가져오기
		List<MultipartFile> img_list = recipeInfo.getImg_food();
		System.out.println("리스트 사이즈 : "+img_list.size());
		Iterator it = img_list.iterator();
		OutputStream out = null;
		String[] fileNames = new String[getSizeIfImgsHaveNull(recipeInfo)];
		int cnt = 0;

		//서버에 파일 저장
		while(it.hasNext()){
			MultipartFile imgs = (MultipartFile)it.next();
			String fileName = imgs.getOriginalFilename();
			
			if(!fileName.equals("")){//중간,마지막파일 NULL 처리
				//파일이름 재생성
				DateTime dt = new DateTime();
				DateTimeFormatter fmt = DateTimeFormat.forPattern("yyMMddHHmmss");
				String upload_time = fmt.print(dt);	
				fileName = upload_time +"_"+user_nick+"_"+fileName;
								
				//파일 저장
				String path = request.getSession().getServletContext().getRealPath("/upload/"+fileName);
				
				//한글처리
				/*try{
					fileNames[cnt] = URLEncoder.encode(fileName,"euc-kr");
					System.out.println("파일이름 : "+fileNames[cnt]);
				}catch(UnsupportedEncodingException e){
					e.printStackTrace();
				}*/
				
				fileNames[cnt] = fileName;
				cnt++;
				System.out.println("경로 : "+path);
				File file = new File(path);	
				try{
					out = new FileOutputStream(path);
					BufferedInputStream bis = new BufferedInputStream(imgs.getInputStream());
					byte[] buffer = new byte[8106];
					int read = 0;
					while((read=bis.read(buffer))>0){//읽은 내용 無 : -1
						out.write(buffer, 0, read);
					}//파일로 출력
					if(out != null) out.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
		//한글처리
		/*try{
			System.out.println(URLEncoder.encode(Arrays.toString(fileNames),"UTF-8"));
		}catch(Exception e){
			e.printStackTrace();
		}*/
		
		//페이지전환
		mav.addObject(recipeInfo);
		mav.addObject("IMG_NAME",fileNames);
		mav.addObject("BODY", "postRecipe_content.jsp");
		return mav;
	}
	
	
	//4.21
	//jung
	//레시피 수정 메서드
	@RequestMapping(value="/post/modify_cotent.html", method=RequestMethod.GET)
	public ModelAndView modify_content(String recipe_id, String TOTAL, String FOCUS, HttpSession session){
		//DB연동
		RecipeInfo recipeInfo = this.checkCatalog.getRecipeTobeModify(recipe_id);
		
		//FOCUS 값
		System.out.println("포커스 값 : "+FOCUS);
		
		
		//이미지 배열에 저장
		String fileNames[] = this.saveImgToArray(recipeInfo,"modify");
		
		//log
		System.out.println("수정할 recipe_id : "+recipeInfo.getRecipe_id());
		for(int i=0;i<fileNames.length;i++){
			System.out.println("수정할 img"+(i+1)+":"+fileNames[i]);
		}
			
		//화면전환
		ModelAndView mav = new ModelAndView("home/homepage");
		mav.addObject("BODY", "modifyRecipe_content.jsp");
		mav.addObject(recipeInfo); //기존내용 객체
		mav.addObject("IMG_NAME",fileNames); // 이미지파일용
		mav.addObject("TOTAL",TOTAL); //목록 vs 상세
		mav.addObject("RECIPE_ID",recipeInfo.getRecipe_id()); //레시피아이디
		mav.addObject("FOCUS",FOCUS); //포커스
		return mav;
	}
	
	//4.21
	//jung
	//레시피 수정 업로드 메서드
	@RequestMapping(value="/post/modify_upload.html",method=RequestMethod.POST)
	public ModelAndView modify_upload(RecipeInfo recipeInfo, BindingResult br, String recipe_id, String TOTAL, HttpSession session, String FOCUS){
		//log
		System.out.println("토탈페이지에서? : "+TOTAL);
		System.out.println("포커스 : "+FOCUS);
		System.out.println("레시피 아디 : "+recipeInfo.getRecipe_id());
		System.out.println("레시피 카운트 : "+recipeInfo.getRecipe_count());
		System.out.println("수정된 내용 : "+recipeInfo.getRecipe_content());
		System.out.println("수정된 해쉬태그 :"+recipeInfo.getRecipe_hashtag());
		System.out.println("수정된 이미지 내용 : "+recipeInfo.getRecipe_img1());
		System.out.println("레시피 이미지 순서 : "+recipeInfo.getRecipe_img_order());
		System.out.println("레시피게시시간 : "+recipeInfo.getRecipe_posttime());
		
		//해쉬태그, 레시피 내용 에러처리
		ModelAndView find = findRecipeContentAndHashtagIsNull(recipeInfo, br, "modify");
		if(find!=null){
			find.addObject("TOTAL",TOTAL); //목록 vs 상세
			find.addObject("RECIPE_ID",recipeInfo.getRecipe_id()); //레시피아이디
			return find;
		}
		
		//DB연동
		int update_result = this.updateCatalog.updateRecipeInfo(this.saveImgInValueObject(recipeInfo));
		if(update_result>0)System.out.println("레시피 업데이트 성공");
		
		//리스트로 화면 전환(focus 값 필요!)
		if(TOTAL.equals("YES")) return new ModelAndView("redirect:/list/recipe_list.html?FLAG=heart&FOCUS="+FOCUS);	
		else return new ModelAndView("redirect:/list/recipe_selected.html?id="+recipe_id);
	}
	
	//4.21
	//jung
	//레시피 삭제 매서드
	@RequestMapping(value="/post/delete_recipe.html",method=RequestMethod.GET)
	public ModelAndView deleteRecipe(String recipe_id){
		//log
		System.out.println("레시피 삭제(토탈) 메서드");
		System.out.println("삭제레시피아이디 : "+recipe_id);
		
		//DB연동
		int delete_result = this.deleteCatalog.deleteRcipe(recipe_id);
		if(delete_result>0) System.out.println("레시피 삭제 성공");
		
		//리스트로 화면전환
		return new ModelAndView("redirect:/list/recipe_list.html?FLAG='icon'");
	}	

}
