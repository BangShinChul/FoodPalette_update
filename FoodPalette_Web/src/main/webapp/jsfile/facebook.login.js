/**
 * facebook login javascript
 */

//Facebook SDK
 window.fbAsyncInit = function() {
    FB.init({
      appId      : '1760401840939627',
      xfbml      : true,
      version    : 'v2.8'
    });
    FB.AppEvents.logPageView();
  };

  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "//connect.facebook.net/en_US/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));
  
//변수 설정
var user_token;
var flag = 0;
var userId;
var userName;
var userBirthday;
var userEmail;
var userGender;
var logoutUserName;
var loginStatus;

//로그인
function loginFB(){
	FB.login(function(response){
		if(response.status == 'connected'){
			console.log("로그인성공")
			user_token = response.authResponse.accessToken;
			getFacebookUserInfo();
			setInterval(function(){
				if(flag == 1){
					viewInfo();
				}
			},500);
		}else if(response.status='not_authorized'){
			alret("로그인 실패!")
		}
	},{scope : 'public_profile, email'});
}

//로그인 사용자 정보 저장
function getFacebookUserInfo(){
	FB.api('/me','GET',{fields:'id,email,name'},
			function(response) {
					userName = response.name;				
					userEmail = response.email;
					flag = 1;
					console.log("로그값 : "+flag);
			  }
		);
}

//로그아웃
function logoutFB(){
	//logoutUserName = userName;
	FB.logout(function(response){
	});
	
}

//쿠기로드
function getCookie(){
	FB.Event.subscribe('auth.statusChange', function(response) {
		document.write("토큰 : " + user_token+"<br/>");
		//document.getElementById("btn_login").value = logoutUserName+"님 다시 로그인";
		loginStatus = 1;
	
	});
}

//자동 Submit
function viewInfo(){
	console.log("이름값 : "+userName);
	var fb_name = document.getElementById("fb_name");
	var fb_email = document.getElementById("fb_email");
	var fb_form = document.getElementById("fb_form");
	fb_name.setAttribute("value", userName);
	fb_email.setAttribute("value", userEmail);
	fb_form.submit();
}

//로그아웃 로그
function logoutView(){
	document.write("토큰 : " + user_token+"<br/>");
	document.write("로그아웃 이름 :" + logoutUserName+"<br/>");
}
