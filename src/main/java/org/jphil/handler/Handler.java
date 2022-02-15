package org.jphil.handler;


import org.jphil.http.request.Request;
import org.jphil.http.response.Response;

public interface Handler {

      /**
       *
       * @param request
       * @param response
       */
      void handle(Request request, Response response);
}
