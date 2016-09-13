package com.testerhome.android.auth;

/**
 * Created by Sun on 2016/9/12.
 */
public final class AuthConstants {
    public static final String BASE_URL = "http://testerhome.com";
    public static final String BASE_HTTPS_URL = "https://testerhome.com";
    public static final String BASE_URL_OVERRIDE = "https://testerhome.com/";

    public static final String ACCESS_TOKEN_URL = "https://testerhome.com/oauth/token";
    public static final String AUTHORIZATION_HTTPS_URL = "https://testerhome.com/oauth/authorize";

    //This is any string we want to use. This will be used for avoid CSRF attacks. You can generate one here: http://strongpasswordgenerator.com/
    public static final String STATE = "60zHfbLe30UfpIB";

    public static String CLIENT_ID = "6dbe4244";
    public static String SECRET = "3a20127eb087257ad7196098bfd8240746a66b0550d039eb2c1901c025e7cbea";
    public static String CALL_BACK_URL = "urn:ietf:wg:oauth:2.0:oob";

    /*---------------------------------------*/
    private static final String QUESTION_MARK = "?";
    private static final String AMPERSAND = "&";
    private static final String EQUALS = "=";


    public static String getAuthorizationUrl(){
        return AUTHORIZATION_HTTPS_URL + QUESTION_MARK
                + "response_type=code" + AMPERSAND
                + "client_id=" + CLIENT_ID + AMPERSAND
                + "state=" + STATE + "&"
                + "redirect_uri=" + CALL_BACK_URL;
    }
}
