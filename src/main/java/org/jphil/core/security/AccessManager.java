package org.jphil.core.security;

import org.jphil.handler.Handler;
import org.jphil.http.Request;
import org.jphil.http.Response;

import java.util.Set;

public interface AccessManager {

    /**
     *
     * @param handler
     * @param request
     * @param response
     * @param routeRoles
     */
    void manage(Handler handler, Request request, Response response, Set<RouteRole> routeRoles);
}
