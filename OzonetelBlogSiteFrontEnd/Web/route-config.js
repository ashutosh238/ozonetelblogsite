var myApp=angular.module("myApp", ['ngRoute','ngCookies']);

myApp.config(
			function($routeProvider)
			{
				alert("i am route configuration file")
				
				$routeProvider
			
				.when("/",{templateUrl : "/index.html"})
				.when("/login",	{templateUrl : "c_user/login.html"})
				.when("/loggedin",{templateUrl : "welcomepage.html"})
				.when("/logout", {templateUrl : "LogoutConfirmation.html"})
				
				.when("/register",{templateUrl : "c_user/register.html"})
				.when("/addBlog",{templateUrl : "c_blog/addBlog.html"})
				.when("/allBlog",{templateUrl : "c_blog/showBlog.html"})
				.when("/myBlog", {templateUrl : "c_blog/myBlog.html"})
				.when("/editprofile", {templateUrl : "c_user/editProfile.html"})
				.when("/blogcomment", {templateUrl : "c_blog/blogcomment.html"})
				
				.when("/contactus",	{templateUrl : "c_contactUs/ContactUs.html"})
				.when("/aboutus",	{templateUrl : "c_aboutUs/AboutUs.html"})
				
				
				
			});

myApp.run(function($rootScope,$cookieStore)
		
{
	console.log('I am in run function');
	console.log($rootScope.CurrentUser);
	
	if($rootScope.CurrentUser==undefined)
		{
		$rootScope.CurrentUser=$cookieStore.get('userDetail');
		}
});