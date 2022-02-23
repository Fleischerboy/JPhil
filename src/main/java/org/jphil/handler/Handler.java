package org.jphil.handler;


import freemarker.template.TemplateException;
import org.jphil.http.request.Request;
import org.jphil.http.response.Response;

import java.io.IOException;

public interface Handler {

      /**
       *
       * @param request
       * @param response
       */
      void handle(Request request, Response response) throws TemplateException, IOException;
}
