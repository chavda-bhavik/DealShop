/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author bhavik
 */
@FacesValidator("phoneValidator")
public class phoneValidator implements Validator {
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String regex = "\\d{3}-\\d{3}-\\d{4}";
        System.out.println("phone vali called");
        String val = value.toString();
        if (value != null) {
            if(!val.matches(regex)) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Entered phone number is not valid.", null));
            }
        }
    }
}
