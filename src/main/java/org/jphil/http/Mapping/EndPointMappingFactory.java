package org.jphil.http.Mapping;
import org.jphil.core.security.AccessManager;
import org.jphil.core.security.RouteRole;
import org.jphil.handler.Handler;
import org.jphil.http.HttpMethod;
import org.jphil.utils.AntPathMatcher;
import org.jphil.utils.PathMatcher;
import java.util.*;
import static org.jphil.utils.PathUtils.validateHandlerCreation;


public class EndPointMappingFactory {


    private static final Map<EndPointMapping, HandlerWrapper> endpointHandleMap = new HashMap<>();


    private static AccessManagerWrapper accessManagerWrapper;


    private static final PathMatcher pathMatcher = new AntPathMatcher();


    public static void addRoute(HttpMethod method, String path, Handler handler) {
        path = validateHandlerCreation(method, path, handler);
        HandlerWrapper handlerWrapper = new HandlerWrapper(handler);
        endpointHandleMap.put(new EndPointMapping(method, path), handlerWrapper);
    }



    public static void addRoute(HttpMethod method, String path, Handler handler, RouteRole... roles) {
        Set<RouteRole> roleSet = new HashSet<>(Arrays.asList(roles));
        path = validateHandlerCreation(method, path, handler);
        HandlerWrapper handlerWrapper = new HandlerWrapper(handler);
        endpointHandleMap.put(new EndPointMapping(method, path, roleSet), handlerWrapper);
    }



   public static void setAccessManager(AccessManager accessManager) {
        if(accessManager != null) {
            accessManagerWrapper = new AccessManagerWrapper(accessManager);
        }
   }




    public static Map<EndPointMapping, HandlerWrapper> getEndpointHandleMap() {
        return endpointHandleMap;
    }


    /**
     * @param method    HTTP
     * @param path      request path
     * @param variables /book/{id} => /book/666 = {id:666}
     * @param roleSet Role.ADMIN, role.USER, role.ANYONE etc. as many roles you want!
     * @return
     */
    public static HandlerWrapper getHandlerWrapper(String method, String path, Map<String, String> variables, Set<RouteRole> roleSet) {
        List<String> matchedPaths = new ArrayList<>();
        for (Map.Entry<EndPointMapping, HandlerWrapper> entries : endpointHandleMap.entrySet()) {
            EndPointMapping mapping = entries.getKey();
            if (mapping.getMethod().toString().equals(method.toUpperCase())) {
                if (pathMatcher.match(mapping.getPath(), path)) {
                     matchedPaths.add(mapping.getPath());
                     if(mapping.getRoleSet() != null) {
                         roleSet.addAll(mapping.getRoleSet());
                     }
                }
            }
        }
        if (!matchedPaths.isEmpty()) {
            String bestPath = getBestPath(matchedPaths);
            variables.putAll(pathMatcher.extractUriTemplateVariables(bestPath, path));
            if(!(roleSet.isEmpty())) {
                return endpointHandleMap.get(new EndPointMapping(getHttpMethod(method), bestPath, roleSet));
            }
            else {
                return endpointHandleMap.get(new EndPointMapping(getHttpMethod(method), bestPath));
            }
        }
        return null;
    }

    private static String getBestPath(List<String> matchedPaths) {
        String bestPath = "";
        if (matchedPaths.size() == 1) {
            bestPath = matchedPaths.get(0);
        }
        else {
            for (String onePath: matchedPaths) {
                if(onePath.length() == bestPath.length()) {
                    if(measure(onePath, '*') < measure(bestPath,'*')){
                        bestPath = onePath;
                    }
                }
                if(bestPath.length() < onePath.length()) {
                    bestPath = onePath;
                }
            }
        }
        return bestPath;
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
            default:
                return null;
        }
    }


    public static AccessManagerWrapper getAccessManagerWrapper() {
        return accessManagerWrapper;
    }

    public static void printMap() {
        for (EndPointMapping i : endpointHandleMap.keySet()) {
            System.out.println("key: " + i + " value: " + endpointHandleMap.get(i));
        }
    }


    public static int measure(String str, char ch){
        int count = 0;
        for(char oneChar : str.toCharArray()){
            if(oneChar==ch){
                count++;
            }
        }
        return count;
    }



}
