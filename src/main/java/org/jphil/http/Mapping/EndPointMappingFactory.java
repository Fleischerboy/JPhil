package org.jphil.http.Mapping;
import org.jphil.core.security.RouteRole;
import org.jphil.handler.Handler;
import org.jphil.http.HttpMethod;

import java.util.*;

public class EndPointMappingFactory {

  private static Map<EndPointMapping, HandlerWrapper> endpointHandleMap = new HashMap<>();

  private static Map<EndPointMapping, HandlerWrapper> interceptorMap = new HashMap<>();



  public static void addRoute(HttpMethod method, String path, Handler handler) {
    if(method == null || path.isEmpty() || handler == null) {
        return;
    }
    HandlerWrapper handlerWrapper = new HandlerWrapper(handler);
    endpointHandleMap.put(new EndPointMapping(method, path), handlerWrapper);

  }

  public static void addRoute(HttpMethod method, String path, Handler handler, RouteRole... roles) {
      Set<RouteRole> roleSet = new HashSet<>(Arrays.asList(roles));
      if(method == null || path.isEmpty() || handler == null) {
          return;
      }
      HandlerWrapper handlerWrapper = new HandlerWrapper(handler);
      endpointHandleMap.put(new EndPointMapping(method, path, roleSet), handlerWrapper);
  }



  public static void addInterceptorRoute(HttpMethod method, String path, Handler handler) {

  }



  public static Map<EndPointMapping, HandlerWrapper> getEndpointHandleMap() {
    return endpointHandleMap;
  }


    public static void printMap() {
        for (EndPointMapping i : endpointHandleMap.keySet()) {
            System.out.println("key: " + i + " value: " + endpointHandleMap.get(i));
        }
    }




}
