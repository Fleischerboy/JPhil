package org.jphil.http.routing;

import org.jphil.http.Method;

import java.util.Objects;

public class EndPointRouting {

    private Method method;
    private String path;

    public EndPointRouting(Method method, String path) {
        this.method = method;
        this.path = path;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
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
        EndPointRouting that = (EndPointRouting) o;
        return method == that.method && Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, path);
    }

    @Override
    public String toString() {
        return "EndPointRouting{" +
                "method=" + method +
                ", path='" + path + '\'' +
                '}';
    }
}
