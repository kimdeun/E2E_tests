package constants;

public class Json {
    public static String LOGIN_JSON = String.format("{" +
            "  \"login\": \"%s\"," +
            "  \"password\": \"%s\"" +
            "}", Credentials.USER_LOGIN, Credentials.USER_PASSWORD);

    public static String CHANGE_WORK_ORDER_STATE_TO_CONFIRMED = "{" +
            "  \"status\": \"confirmed\"" +
            "}";

    public static String CHANGE_WORK_ORDER_STATE_TO_PRODUCED = "{" +
            "  \"status\": \"produced\"" +
            "}";
}
