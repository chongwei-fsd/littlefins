package com.cw.littlefins_proj.util;

public final class Utils {
    /**
     * This helper method capitalises a string.
     * @param value is the string to be capitalised
     * @return the converted string
     */
    public static String capitalise(String value) {
        return value.substring(0,1).toUpperCase() + value.substring(1);
    }

    /**
     * This helper method extracts a substring after the last delimiter
     * @param value is the string to be extracted from
     * @param delimiter is the delimiter where the string after it is extracted
     * @return converted the substring value
     */
    public static String substringAtLastDelimiter(String value, String delimiter){
        return value.substring(value.lastIndexOf(".") + 1);
    }
}
