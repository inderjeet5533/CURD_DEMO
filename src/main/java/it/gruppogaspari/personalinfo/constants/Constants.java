package it.gruppogaspari.personalinfo.constants;

public class Constants {

    public final static String PHONE_REGEX = "^(\\+\\d{1,2}\\s?)?1?\\-?\\.?\\s?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$";
    public final static String PHONE_ERROR_MSG = "must be valid 10 digits number";
    public static final String STRING_REGEX = "([a-zA-Z]+)";
    public static final String FIRSTNAME_ERROR_MSG = "FirstName must be valid characters";
    public static final String LASTNAME_ERROR_MSG = "LastName must be valid characters";
}
