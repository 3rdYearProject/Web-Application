package login;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import configuration.Configuration;

@ManagedBean
@RequestScoped
public class Login {

	private String username;
	private String password;

	public void login() {

		FacesContext context = FacesContext.getCurrentInstance();
		Configuration config = new Configuration();
		username = config.getUserName();
		password = config.getPassword();

		if (this.username.equals(username) && this.password.equals(password)) {
			context.getExternalContext().getSessionMap().put("user", username);
			try {
				context.getExternalContext().redirect("index.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// Send an error message on Login Failure
			context.addMessage(null, new FacesMessage("Authentication Failed. Check username or password."));

		}
	}
	
	public void logout() {
    	FacesContext context = FacesContext.getCurrentInstance();
    	context.getExternalContext().invalidateSession();
        try {
			context.getExternalContext().redirect("login.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
