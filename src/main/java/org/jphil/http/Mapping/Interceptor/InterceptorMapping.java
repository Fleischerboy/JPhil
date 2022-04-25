package org.jphil.http.Mapping.Interceptor;

import java.util.Objects;

public class InterceptorMapping {

    private Interceptor interceptor;
    private String path;

    public InterceptorMapping(Interceptor interceptor, String path) {
        this.interceptor = interceptor;
        this.path = path;
    }

    public Interceptor getInterceptor() {
        return interceptor;
    }

    public void setInterceptor(Interceptor interceptor) {
        this.interceptor = interceptor;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InterceptorMapping that = (InterceptorMapping) o;
        return interceptor == that.interceptor && Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(interceptor, path);
    }

    @Override
    public String toString() {
        return "InterceptorMapping{" +
                "interceptor=" + interceptor +
                ", path='" + path + '\'' +
                '}';
    }


}
