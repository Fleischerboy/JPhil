package org.jphil.handler;

import org.jphil.http.Request;
import org.jphil.http.Response;

/**
 * interface for endpoint actions
 */
@FunctionalInterface
public interface Handler {
    void handle(Request request, Response response);
}
