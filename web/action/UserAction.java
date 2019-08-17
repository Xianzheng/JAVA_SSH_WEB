package mark.fang.platform.web.action;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import mark.fang.platform.domain.User;
import mark.fang.platform.service.UserService;
import mark.fang.platform.utils.MailUtils;
import mark.fang.platform.utils.UUIDUtils;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	/*private static String ValiCode = "";
	public String getValiCode() {
		return ValiCode;
	}

	public void setValiCode(String valiCode) {
		ValiCode = valiCode;
	}*/

	private User user = new User();
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	//for spring injection
	
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String regist() throws AddressException, MessagingException {
		
		
		System.out.println((String) request.getSession().getAttribute("regist_valicode"));
		String ReceivedCode = request.getParameter("regist_validation");
		if(!ReceivedCode.equalsIgnoreCase((String) request.getSession().getAttribute("regist_valicode"))) {
			this.addActionError("validation code is not correct");
			return "registFailed";
		}
		
		user.setUid(UUIDUtils.getId());
		user.setState(0);
		userService.regist(user);
		
		return "registSuccess";
	}
	
	public String login() {
		
		
		Cookie[] cookies=request.getCookies();
		String userName="";
		String passWord="";
		  if(cookies!=null && cookies.length!=0){
			  //System.out.println("if come to here");
			  for(int i=0;i<cookies.length;i++){
				  Cookie cookie = cookies[i];
				  //System.out.println("if come to here times?");
				  if(cookie.getName().equals("loginname")){
					  //System.out.println(cookie.getValue());
					  userName = cookie.getValue();
					  //checked="checked";
				  }
				  if(cookie.getName().equals("loginpassword")){
					  //System.out.println(cookie.getValue());
					  passWord= cookie.getValue();
				  }
			  }
			  User oldUser = new User();
			  oldUser.setUsername(userName);
			  oldUser.setPassword(passWord);
			  User existoldUser = userService.login(oldUser);
			  if(existoldUser != null) {
					
					ActionContext.getContext().getSession().put("existUser", existoldUser);
					return SUCCESS;
					
			  }
			  
			 			  
		  }
		  System.out.println(ActionContext.getContext().getSession().get("login_valicode"));
		User existUser = userService.login(user);
		//System.out.println(existUser.getEmail());
		//System.out.println(this.getValiCode());
		if(existUser == null) {
			this.addActionError("username or password wrong");
			return LOGIN;
		}else if(!user.getEmail().equals(existUser.getEmail())) {
			this.addActionError("email is not correct");
			return LOGIN;
		}else if(!request.getParameter("login_validation").equalsIgnoreCase((String) ActionContext.getContext().getSession().get("login_valicode"))) {
			this.addActionError("validation code is not correct");
			System.out.println("the required validation code is "+ActionContext.getContext().getSession().get("login_valicode"));
			System.out.println("you enter code is"+request.getParameter("login_validation"));
			return LOGIN;
		}
		else {
			//ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			ActionContext.getContext().getSession().put("existUser", existUser);
			Cookie cookieName=new Cookie("loginname", user.getUsername());
			Cookie cookiePassword=new Cookie("loginpassword", user.getPassword());
			
			cookieName.setMaxAge(60*60*60*24*100);
			cookieName.setPath(request.getContextPath());
			cookiePassword.setMaxAge(60*60*60*24*100);
			cookiePassword.setPath(request.getContextPath());
			response.addCookie(cookieName);
			response.addCookie(cookiePassword);
			
			return SUCCESS;
		}	
		
	}
	public String logout() {
		User existUser = userService.login(user);
		ActionContext.getContext().getSession().clear();
		return "removeSuccess";
	}
	
	public String login_validation() throws AddressException, MessagingException {
		String login_email = request.getParameter("login_email");
		HttpSession session = request.getSession();
		ActionContext.getContext().getSession().put("login_valicode", UUIDUtils.getCode().substring(0, 6));
		System.out.println("your login valicode is "+ActionContext.getContext().getSession().get("login_valicode"));
		MailUtils.sendMail(login_email, "Your Login Validation Code","your val_code is "+ActionContext.getContext().getSession().get("login_valicode"));
		return null;
	}
	
	public String regist_validation() throws AddressException, MessagingException {
		String regist_email = request.getParameter("regist_email"); 
		
		ActionContext.getContext().getSession().put("regist_valicode", UUIDUtils.getCode().substring(0, 6));
		/*HttpSession session = request.getSession();
		session.setAttribute("regist_valicode", UUIDUtils.getCode().substring(0, 6));*/
		System.out.println("your registration code is "+ActionContext.getContext().getSession().get("regist_valicode"));
		MailUtils.sendMail(regist_email, "Registration Validation Code","your registration code is "+ActionContext.getContext().getSession().get("regist_valicode"));
		return null;
		
	}
	
	

}
