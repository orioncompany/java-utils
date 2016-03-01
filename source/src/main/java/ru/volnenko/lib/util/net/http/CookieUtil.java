package ru.volnenko.lib.util.net.http;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by orion on 16.04.15.
 */
public final class CookieUtil {

    @Nullable
    private String getCookieValue(
            @NotNull final HttpServletRequest httpServletRequest,
            @NotNull final String cookieName
    ) {
        @NotNull
        final Cookie[] cookies = httpServletRequest.getCookies();
        for (final Cookie cookie: cookies) {
            if (cookie == null) {
                continue;
            }
            if (cookie.getName() == null) {
                continue;
            }
            if (cookieName.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public void removeCookies(
            @NotNull final HttpServletRequest httpServletRequest,
            @NotNull final HttpServletResponse httpServletResponse
    ) {
        @Nullable
        final Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (final Cookie cookie : cookies) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                httpServletResponse.addCookie(cookie);
            }
        }
    }

}
