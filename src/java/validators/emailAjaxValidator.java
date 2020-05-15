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
@FacesValidator("emailAjaxValidator")
public class emailAjaxValidator implements Validator  {
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String EMAIL_REGIX = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
        
        String val = value.toString();
        if (value == null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Email input is required.", null));
        } else {
            Pattern pattern = Pattern.compile(EMAIL_REGIX);
            Matcher matcher = pattern.matcher(val);
            if(!matcher.matches()) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Email addres is not valid.", null));
            }
        }
    }
}
