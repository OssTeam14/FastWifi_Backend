package oss.fastwifi.auth.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class TokenCookieService {
    private final static String cookieKey = "accessToken";

    public void addCookie(HttpServletResponse response, String value){
        Cookie cookie = new Cookie(cookieKey,value);
        response.addCookie(cookie);
    }

    public void expireCookie(HttpServletResponse response){
        Cookie cookie = new Cookie(cookieKey,null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
