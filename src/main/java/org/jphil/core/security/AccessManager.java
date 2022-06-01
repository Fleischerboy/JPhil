package org.jphil.core.security;

import org.jphil.handler.Handler;
import org.jphil.http.Request;
import org.jphil.http.Response;

import java.util.Set;

/**
 * The interface for access manager is a way of implementing endpoint security management
 */
@FunctionalInterface
public interface AccessManager {
    void manage(Handler handler, Request request, Response response, Set<RouteRole> routeRoles);
}
