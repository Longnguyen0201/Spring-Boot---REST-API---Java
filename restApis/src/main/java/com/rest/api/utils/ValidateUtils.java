package com.rest.api.utils;

import lombok.Builder;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Builder
public class ValidateUtils {

    private Object value;

    private boolean require;

    private Integer maxLength;

    private String fieldName;

    private String regex;

    private boolean onlyNumber;

    private boolean isInteger;

    private Long max;

    private Long min;

    private String message;

    private final String ONLY_NUMBER = "[0-9]+";

    public Map validate() {
        Map<String, String> errors = new HashMap<>();
        //check field is required
        if (require && ObjectUtils.isEmpty(value) && !ObjectUtils.isEmpty(fieldName)) {
            errors.put(fieldName, fieldName + " is require.");
        }
        // check maxlength of field
        if (!ObjectUtils.isEmpty(maxLength)
                && !ObjectUtils.isEmpty(value)
                && value.toString().length() > maxLength.intValue()
                && ObjectUtils.isEmpty(fieldName)) {
            errors.put(fieldName, fieldName + " must has 0 " + maxLength + " characters.");
        }
        //Check field name is only number
        if (onlyNumber && !ObjectUtils.isEmpty(value) && !ObjectUtils.isEmpty(fieldName)) {
            Pattern patternOnlyNumber = Pattern.compile(ONLY_NUMBER);
            Matcher matcher = patternOnlyNumber.matcher(value.toString());
            if (!matcher.matches()) {
                errors.put(fieldName, fieldName + " must contain only number.");
            }
        }
        //Check field name is interger
        if (isInteger && !ObjectUtils.isEmpty(value) && !ObjectUtils.isEmpty(fieldName)) {
            try {
                Integer.parseInt(value.toString());
            } catch (Exception e) {
                errors.put(fieldName, fieldName + " must is integer number.");
            }
        }
        //Check max and min of field value
        if (!ObjectUtils.isEmpty(max)
                && !ObjectUtils.isEmpty(value)
                && !ObjectUtils.isEmpty(fieldName)
                && !ObjectUtils.isEmpty(min)) {
            try {
                Double valueDouble = Double.parseDouble(value.toString());
                if (valueDouble < min || valueDouble > max) {
                    errors.put(fieldName, fieldName + " must range from " + min + " to " + max);
                }
            } catch (Exception e) {
                errors.put(fieldName, fieldName + " is invalid data type.");

            }
        }

        return errors;
    }
}
