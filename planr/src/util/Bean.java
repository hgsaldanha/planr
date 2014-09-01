package util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class Bean {
	//TODO criar métodos específicos para cada tipo de mensagem. Ex: addInfo, addError, addWarning
	public void addMessage(Severity severity, String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity,message,""));
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}
	
	public void addMessage(Severity severity, String component, String message) {
		FacesContext.getCurrentInstance().addMessage(component, new FacesMessage(severity,message,""));
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}
	
	public void addMessage(Severity severity, String component, String message, String detail) {
		FacesContext.getCurrentInstance().addMessage(component, new FacesMessage(severity,message,detail));
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}
}
