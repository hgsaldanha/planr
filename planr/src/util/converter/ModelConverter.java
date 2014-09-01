package util.converter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.jboss.security.Base64Utils;

import util.base.Model;

@FacesConverter(forClass=Model.class)
public class ModelConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		Model obj = null;
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(Base64Utils.fromb64(string));
	        ObjectInputStream ois = new ObjectInputStream(bais);
	        obj  = (Model)ois.readObject();
	        ois.close();
	        EditableValueHolder comp = (EditableValueHolder) component;
	        comp.addValidator(context.getApplication().createValidator("modelValidator"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		String str = "";
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			oos.close();
			oos.reset();
			str = Base64Utils.tob64(baos.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
        return str;
	}

}
