package org.jphil.http.Mapping.Interceptor;
import org.jphil.handler.Handler;
import org.jphil.http.Mapping.HandlerWrapper;
import org.jphil.utils.AntPathMatcher;
import org.jphil.utils.PathMatcher;
import java.util.*;
import static org.jphil.utils.PathUtils.validateHandlerCreation;

public class InterceptorFactory {

    private static final Map<InterceptorMapping, HandlerWrapper> interceptorMap = new HashMap<>();


    private static Handler beforeHandler;

    private static Handler afterHandler;

    private static final PathMatcher pathMatcher = new AntPathMatcher();


    public static void addInterceptor(Interceptor interceptor, String path, Handler handler) {
         path = validateHandlerCreation(interceptor, path, handler);
         HandlerWrapper handlerWrapper = new HandlerWrapper(handler);
         interceptorMap.put(new InterceptorMapping(interceptor, path), handlerWrapper);

    }

    public static Stack<HandlerWrapper> getInterceptors(String path, Interceptor interceptor, Map<String, String> variables) {
        Stack<HandlerWrapper> interceptors = new Stack<>();
        for (Map.Entry<InterceptorMapping, HandlerWrapper> entries : interceptorMap.entrySet()) {
            InterceptorMapping mapping = entries.getKey();
            if(mapping.getInterceptor() == interceptor) {
                if(pathMatcher.match(mapping.getPath(), path)) {
                    HandlerWrapper interceptorHandler = interceptorMap.get(new InterceptorMapping(interceptor, mapping.getPath()));
                    variables.putAll(pathMatcher.extractUriTemplateVariables(mapping.getPath(), path));
                    interceptors.add(interceptorHandler);
                }
            }
        }
        return interceptors;
    }



    public static void setBeforeHandler(Handler beforeHandler) {
        InterceptorFactory.beforeHandler = beforeHandler;
    }

    public static void setAfterHandler(Handler afterHandler) {
        InterceptorFactory.afterHandler = afterHandler;
    }



    public static void printInterceptorMap() {
        for (InterceptorMapping i : interceptorMap.keySet()) {
            System.out.println("key: " + i + " value: " + interceptorMap.get(i));
        }
    }


}
