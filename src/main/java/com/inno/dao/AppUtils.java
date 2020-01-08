package com.inno.dao;

import com.inno.pojo.User;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class AppUtils {
    private static int REDIRECT_ID = 0;

    private static final Map<Integer, String> id_uri_map = new HashMap<Integer, String>();
    private static final Map<String, Integer> uri_id_map = new HashMap<String, Integer>();

    // Сохранить информацию пользователя в Session.
    public static void storeLoginedUser(HttpSession session, User loginedUser) {
        // На JSP можно получить доступ через ${loginedUser}
        session.setAttribute("loginedUser", loginedUser);
        System.out.println(loginedUser.toString());
    }

    // Получить информацию пользователя, сохраненную в Session.
    public static User getLoginedUser(HttpSession session) {
        if(session.getAttribute("loginedUser")!=null){
            System.out.println(session.getAttribute("loginedUser"));
            User loginedUser = (User) session.getAttribute("loginedUser");
            return loginedUser;
        }else{
            return null;
        }

    }

    public static int storeRedirectAfterLoginUrl(HttpSession session, String requestUri) {
        Integer id = uri_id_map.get(requestUri);

        if (id == null) {
            id = REDIRECT_ID++;

            uri_id_map.put(requestUri, id);
            id_uri_map.put(id, requestUri);
            return id;
        }

        return id;
    }

    public static String getRedirectAfterLoginUrl(HttpSession session, int redirectId) {
        String url = id_uri_map.get(redirectId);
        if (url != null) {
            return url;
        }
        return null;
    }
}
