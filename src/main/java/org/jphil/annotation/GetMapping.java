package org.jphil.annotation;

import org.jphil.http.HttpMethod;

public @interface GetMapping {
    String path();

    HttpMethod httpMethod();

}
