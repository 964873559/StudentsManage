package action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

//所有action的父类
public class SuperAction extends ActionSupport implements ServletResponseAware,ServletContextAware,ServletRequestAware{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected HttpServletRequest request;//请求对象
	protected HttpServletResponse response;//响应对象
	protected HttpSession session;//会话对象
	protected ServletContext appilcation;//全局对象
	

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletContext(ServletContext appilcation) {
		this.appilcation = appilcation;
	}  
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = this.request.getSession();
	}
	
}
