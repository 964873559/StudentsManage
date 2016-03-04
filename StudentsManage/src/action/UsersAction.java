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
//�û���½
	public String Login(){
		UsersDAO udo = new UsersDAOImpl();
		if(udo.usersLogin(user)){
			//�����½���û���
			session.setAttribute("loginUserName", user.getUsername());
			return "login_success";
		}else{
			return "login_failure";
		}
	}

	@SkipValidation
//	�û�ע��
	public String Logout(){
		if(session.getAttribute("loginUserName")!=null){
			session.removeAttribute("loginUserName");
		}
		return "logout_success";
	}
	
//	����֤
	
	@Override
	public void validate() {
		//�û�������Ϊ��
		if("".equals(user.getUsername())){
			this.addFieldError("usernameError", "�û�������Ϊ��!");
		}
		if(user.getPassword().length()<6){
			this.addFieldError("passwordError", "���벻��������λ!");
		}
	}

	
	public Users getModel() {
		return this.user;
	}

}
