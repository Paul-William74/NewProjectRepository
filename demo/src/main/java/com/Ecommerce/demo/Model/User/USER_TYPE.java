package com.Ecommerce.demo.Model.User;

import com.Ecommerce.demo.Exception.User.InvalidUserTypeException;
import lombok.Getter;

@Getter
public enum USER_TYPE {
    CUSTOMER("CUSTOMER"),
    ADMIN("ADMIN");

    private final String label;

    USER_TYPE(String label) {
        this.label = label;
    }

    public static USER_TYPE getUserType(String userType) throws InvalidUserTypeException {
        USER_TYPE[] userTypes = USER_TYPE.values();
        for(USER_TYPE user_type : userTypes)
            if(user_type.label.equals(userType))
                return user_type;
        throw new InvalidUserTypeException(userType + " Is Not Defined");
    }
}
