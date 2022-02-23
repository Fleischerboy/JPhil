package org.jphil.http.routing;
import jakarta.servlet.http.HttpServletRequest;
import org.jphil.handler.Handler;
import org.jphil.handler.HandlerWrapper;
import org.jphil.http.Method;

import java.util.HashMap;
import java.util.Map;

public class EndPointRoutingFactory {

  private static Map<EndPointRouting, HandlerWrapper> endpointHandleMap = new HashMap<>();



  public static void addRoute(Method method, String path, Handler handler) {

  }




  public static boolean pathMatcher(HttpServletRequest req) {
  return false;
  }

  public static HandlerWrapper getHandlerWrapper(HttpServletRequest req) {
    return null;
  }


  public static Map<EndPointRouting, HandlerWrapper> getEndpointHandleMap() {
    return endpointHandleMap;
  }




}
