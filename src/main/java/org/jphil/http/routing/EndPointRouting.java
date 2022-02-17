package org.jphil.http.routing;

public class EndPointRouting {

    private String method;
    private String path;

    public EndPointRouting(String method, String path) {
        this.method = method;
        this.path = path;
    }



    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
