package org.jphil.annotation;

import org.jphil.http.HttpMethod;
/*
* this got never implemented, and I don't want to remove it. I might do it in the future.
 */
public @interface GetMapping {
    String path();

    HttpMethod httpMethod();

}
