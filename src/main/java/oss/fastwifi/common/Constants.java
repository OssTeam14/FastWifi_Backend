package oss.fastwifi.common;

public final class Constants {
    public static final String PW_REGEXP = "^(?=.*[a-zA-z])(?=.*[0-9]).{8,16}$";
    public static final String MAIL_REGEXP = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$";
    public static final String KW_MAIL_REGEXP = "^[A-Za-z0-9._%+-]+@kw.ac.kr$";

    public static final String RANDOM_UID_BASE_STR = "fastWifi";
    public static final int RANDOM_UID_CODE_LENGTH = 4;
    public static final String REDIS_AUTO_PREFIX = "AUTO_MATCHING";
    public static final String REDIS_AUTO_SPLIT_REGEX = " ";
}