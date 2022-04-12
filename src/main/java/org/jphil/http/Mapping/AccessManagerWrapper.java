package org.jphil.http.Mapping;
import org.jphil.core.security.AccessManager;
import org.jphil.core.security.RouteRole;
import org.jphil.handler.Handler;
import org.jphil.http.Request;
import org.jphil.http.Response;

import java.util.Set;

public class AccessManagerWrapper {
    private final AccessManager accessManager;
    private HandlerWrapper handler;

    public AccessManagerWrapper(AccessManager accessManager) {
        this.accessManager = accessManager;
        this.handler = null;
    }

    public void manage(Handler handler, Request request, Response response, Set<RouteRole> roleSet) {
        accessManager.manage(handler, request, response, roleSet);
    }


    public void setHandler(HandlerWrapper handler) {
        this.handler = handler;
    }

    @Override
    public String toString() {
        return "AccessManagerWrapper{" +
                "accessManager=" + accessManager +
                ", handler=" + handler +
                '}';
    }
}



