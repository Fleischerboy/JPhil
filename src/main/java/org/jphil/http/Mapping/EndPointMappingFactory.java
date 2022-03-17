package org.jphil.http.Mapping;
import org.jphil.core.security.RouteRole;
import org.jphil.handler.Handler;
import org.jphil.http.HttpMethod;
import org.jphil.utils.AntPathMatcher;
import org.jphil.utils.PathMatcher;


import java.util.*;



public class EndPointMappingFactory {


    private static final Map<EndPointMapping, HandlerWrapper> endpointHandleMap = new HashMap<>();
    private static final Map<EndPointMapping, HandlerWrapper> interceptorMap = new HashMap<>();

    private static final PathMatcher pathMatcher = new AntPathMatcher();

    public static void addRoute(HttpMethod method, String path, Handler handler) {
        if (method == null || path.isEmpty() || handler == null) {
            return;
        }
        HandlerWrapper handlerWrapper = new HandlerWrapper(handler);
        endpointHandleMap.put(new EndPointMapping(method, path), handlerWrapper);
    }

    public static void addRoute(HttpMethod method, String path, Handler handler, RouteRole... roles) {
        Set<RouteRole> roleSet = new HashSet<>(Arrays.asList(roles));
        if (method == null || path.isEmpty() || handler == null) {
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

    /**
     * @param method    HTTP
     * @param path      request path
     * @param variables /book/{id} => /book/666 = {id:666}
     * @return
     */
    public static HandlerWrapper getHandlerWrapper(String method, String path, Map<String, String> variables) {
        List<String> matchedPaths = new ArrayList<>();
        for (Map.Entry<EndPointMapping, HandlerWrapper> entries : endpointHandleMap.entrySet()) {
            EndPointMapping mapping = entries.getKey();
            if (mapping.getMethod().toString().equals(method.toUpperCase())) {
                if (pathMatcher.match(mapping.getPath(), path)) {
                     matchedPaths.add(mapping.getPath());
                }
            }
        }
        if (!matchedPaths.isEmpty()) {
            String bestPath = "";
            if (matchedPaths.size() == 1) {
                bestPath = matchedPaths.get(0);
            }
            else {
                for (String onePath: matchedPaths) {
                    if(onePath.length() == bestPath.length()) {
                        if(compute(onePath, '*') < compute(bestPath,'*')){
                            bestPath = onePath;
                        }
                    }
                    if(bestPath.length()< onePath.length()) {
                        bestPath = onePath;
                    }
                }
            }
            variables.putAll(pathMatcher.extractUriTemplateVariables(bestPath,path));
            return endpointHandleMap.get(new EndPointMapping(getHttpMethod(method), bestPath));
        }
        return null;
    }


    public static HttpMethod getHttpMethod(String httpMethod) {
        String method = httpMethod.toUpperCase();
        switch (method) {
            case "GET":
                return HttpMethod.GET;
            case "POST":
                return HttpMethod.POST;
            case "PUT":
                return HttpMethod.PUT;
            case "DELETE":
                return HttpMethod.DELETE;
            case "BEFORE":
                return HttpMethod.BEFORE;
            case "AFTER":
                return HttpMethod.AFTER;
            default:
                return null;
        }
    }


    public static void printMap() {
        for (EndPointMapping i : endpointHandleMap.keySet()) {
            System.out.println("key: " + i + " value: " + endpointHandleMap.get(i));
        }
    }




}
