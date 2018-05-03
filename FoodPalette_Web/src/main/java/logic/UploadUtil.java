package logic;

import org.springframework.stereotype.Service;

@Service
public class UploadUtil {
	private String select_recipe_id;

	public String getSelect_recipe_id() {
		return select_recipe_id;
	}

	public void setSelect_recipe_id(String select_recipe_id) {
		this.select_recipe_id = select_recipe_id;
	}


}
