package utils;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import logic.RecipeInfo;

@Service
public class PostValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return RecipeInfo.class.isAssignableFrom(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		//상세내용, 해쉬태그 검출 오류
		System.out.println("벨리테이트 들어옴");
		RecipeInfo recipeInfo = (RecipeInfo)arg0;
	
		/*//레시피내용 NotNull
		if(!StringUtils.hasLength(recipeInfo.getRecipe_content())){
			System.out.println("레시피 내용 판단한다.");
			arg1.rejectValue("recipe_content", "error.required");
		}*/
	}

}
