package org.jphil.http.routing;
import org.jphil.handler.Handler;
import org.jphil.handler.HandlerWrapper;
import org.jphil.http.HttpMethod;
import java.util.HashMap;
import java.util.Map;

public class EndPointRoutingFactory {

  private static Map<EndPointRouting, HandlerWrapper> endpointHandleMap = new HashMap<>();




  public static void addRoute(HttpMethod method, String Path, Handler handler) {

  }


  public static void addBeforeHandlerWithRoutePath(HttpMethod before, String path, Handler handler) {

  }

  public static void addBeforeHandler(Handler handler) {

  }



  public static void addAfterHandlerWithRoutePath(HttpMethod after, String path, Handler handler) {

  }


  public static void addAfterHandler(Handler handler) {

  }





  public static Map<EndPointRouting, HandlerWrapper> getEndpointHandleMap() {
      return endpointHandleMap;
  }
}
