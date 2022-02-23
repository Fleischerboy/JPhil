package org.jphil.http.routing;
import jakarta.servlet.http.HttpServletRequest;
import org.jphil.handler.Handler;
import org.jphil.handler.HandlerWrapper;
import org.jphil.http.Method;

import java.util.HashMap;
import java.util.Map;

public class EndPointRoutingFactory {

  private static final Map<EndPointRout, HandlerWrapper> endpointHandleMap = new HashMap<>();



  public static void addRoute(Method method, String path, Handler handler) {
    if(method == null || path.isEmpty() || handler == null) {
      return;
   }
    if(!path.startsWith("/")) {
      return;
  }
  HandlerWrapper hw = new HandlerWrapper(handler);
  endpointHandleMap.put(new EndPointRout(method, path), hw);

  }



  public static boolean pathMatcher(HttpServletRequest req) {
    for (EndPointRout entry : endpointHandleMap.keySet()) {
      if(entry.getMethod().toString().equals(req.getMethod()) && entry.getPath().equals(req.getRequestURI())) {
        return true;
      }

    }

    return false;
  }

  public static HandlerWrapper getHandlerWrapper(HttpServletRequest req) {
    for (Map.Entry<EndPointRout, HandlerWrapper> entry : endpointHandleMap.entrySet()) {
      EndPointRout mapping = entry.getKey();
      if (mapping.getMethod().toString().equals(req.getMethod())) {
          if(mapping.getPath().equals(req.getRequestURI())) {
            EndPointRout reqMap = new EndPointRout(mapping.getMethod(), mapping.getPath());
            HandlerWrapper requestMap = endpointHandleMap.get(reqMap);
            if (requestMap != null) {
              return requestMap;
            }

          }
      }
    }
    return null;
  }


  public static Map<EndPointRout, HandlerWrapper> getEndpointHandleMap() {
    return endpointHandleMap;
  }




  public static void printMap() {
    for (EndPointRout i : endpointHandleMap.keySet()) {
      System.out.println("key: " + i + " value: " + endpointHandleMap.get(i));
    }
  }
}
