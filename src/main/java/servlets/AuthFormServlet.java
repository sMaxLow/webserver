package servlets;

import templater.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class AuthFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, Object> authFormVariables = createMapVariables(req);
        authFormVariables.put("message", "");

        resp.getWriter().println(PageGenerator.instance().getPage("src/templates/auth_form.html", authFormVariables));
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, Object> authFormVariables = createMapVariables(req);

        String message = req.getParameter("message");
        resp.setContentType("text/html;charset=utf-8");

        if(message == null || message.isEmpty())
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        else
            resp.setStatus(HttpServletResponse.SC_OK);

        authFormVariables.put("message",message == null ? "": message);

        resp.getWriter().println(PageGenerator.instance().getPage("src/templates/auth_form.html", authFormVariables));
    }

    private Map<String, Object> createMapVariables (HttpServletRequest request) {
        Map<String, Object> authFormVariables = new HashMap<>();
        authFormVariables.put("method", request.getMethod());
        authFormVariables.put("URL", request.getRequestURI());
        authFormVariables.put("pathInfo", request.getPathInfo());
        authFormVariables.put("sessionId", request.getSession().getId());
        authFormVariables.put("parameters", request.getParameterMap().toString());
        return authFormVariables;
    }
}
