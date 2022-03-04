package org.jphil.handler;


import freemarker.template.TemplateException;
import org.jphil.http.Request;
import org.jphil.http.Response;

import java.io.IOException;

public interface Handler {

      /**
       *
       * @param request
       * @param response
       */
      void handle(Request request, Response response) throws TemplateException, IOException;
}
