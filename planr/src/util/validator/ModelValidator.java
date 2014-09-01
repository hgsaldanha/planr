package util.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="modelValidator")
public class ModelValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object obj) throws ValidatorException {
		
	}

}
