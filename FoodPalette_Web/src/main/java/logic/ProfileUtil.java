package logic;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProfileUtil {
	private MultipartFile user_profile_img;

	public MultipartFile getUser_profile_img() {
		return user_profile_img;
	}

	public void setUser_profile_img(MultipartFile user_profile_img) {
		this.user_profile_img = user_profile_img;
	}
	
}
