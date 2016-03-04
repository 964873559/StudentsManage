package action;

import org.apache.struts2.interceptor.validation.SkipValidation;

import service.UsersDAO;
import service.impl.UsersDAOImpl;

import com.opensymphony.xwork2.ModelDriven;

import entity.Users;

public class UsersAction extends SuperAction implements ModelDriven<Users>{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Users user = new Users();
//用户登陆
	public String Login(){
		UsersDAO udo = new UsersDAOImpl();
		if(udo.usersLogin(user)){
			//保存登陆的用户名
			session.setAttribute("loginUserName", user.getUsername());
			return "login_success";
		}else{
			return "login_failure";
		}
	}

	@SkipValidation
//	用户注销
	public String Logout(){
		if(session.getAttribute("loginUserName")!=null){
			session.removeAttribute("loginUserName");
		}
		return "logout_success";
	}
	
//	表单验证
	
	@Override
	public void validate() {
		//用户名不能为空
		if("".equals(user.getUsername())){
			this.addFieldError("usernameError", "用户名不能为空!");
		}
		if(user.getPassword().length()<6){
			this.addFieldError("passwordError", "密码不能少于六位!");
		}
	}

	
	public Users getModel() {
		return this.user;
	}

}
