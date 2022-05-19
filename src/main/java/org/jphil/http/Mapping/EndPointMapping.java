package org.jphil.http.Mapping;

import org.jphil.core.security.RouteRole;
import org.jphil.http.HttpMethod;

import java.util.Objects;
import java.util.Set;

public class EndPointMapping {

    private HttpMethod method;
    private String path;
    // A HashSet is a collection of items where every item is unique
    private Set<RouteRole> roleSet;


    public EndPointMapping(HttpMethod method, String path) {
        this.method = method;
        this.path = path;
    }


    public EndPointMapping(HttpMethod method, String path, Set<RouteRole> roleSet) {
        this.method = method;
        this.path = path;
        this.roleSet = roleSet;
    }

    public HttpMethod getMethod() {
        return method;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Set<RouteRole> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<RouteRole> roleSet) {
        this.roleSet = roleSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EndPointMapping that = (EndPointMapping) o;
        return method == that.method && Objects.equals(path, that.path) && Objects.equals(roleSet, that.roleSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, path, roleSet);
    }

    @Override
    public String toString() {
        return "EndPointMapping{" +
                "method=" + method +
                ", path='" + path + '\'' +
                ", roleSet=" + roleSet +
                '}';
    }
}
