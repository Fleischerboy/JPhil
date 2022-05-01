package org.jphil.http.Mapping.Interceptor;
import org.jphil.handler.Handler;
import org.jphil.http.Mapping.HandlerWrapper;
import org.jphil.utils.AntPathMatcher;
import org.jphil.utils.PathMatcher;
import java.util.*;
import static org.jphil.utils.PathUtils.validateHandlerCreation;

public class InterceptorFactory {

    private static final List<InterceptorMapping> interceptorList = new ArrayList<>();


    private static final PathMatcher pathMatcher = new AntPathMatcher();


    public static void addInterceptor(Interceptor interceptor, String path, Handler handler) {
         path = validateHandlerCreation(interceptor, path, handler);
         HandlerWrapper handlerWrapper = new HandlerWrapper(handler);
         interceptorList.add(new InterceptorMapping(interceptor, path, handlerWrapper));

    }

    public static Stack<HandlerWrapper> getInterceptors(String path, Interceptor interceptor, Map<String, String> variables) {
        Stack<HandlerWrapper> interceptors = new Stack<>();
        for (InterceptorMapping oneInterceptor: interceptorList) {
            if(oneInterceptor.getInterceptor() == interceptor) {
                if(pathMatcher.match(oneInterceptor.getPath(), path)) {
                    variables.putAll(pathMatcher.extractUriTemplateVariables(oneInterceptor.getPath(), path));
                    HandlerWrapper hw = oneInterceptor.getHandlerWrapper();
                    interceptors.add(hw);
                }
            }
        }
        return interceptors;
    }




    public static void printInterceptorMap() {

    }


}
