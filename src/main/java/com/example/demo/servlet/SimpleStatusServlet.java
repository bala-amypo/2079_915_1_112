package com.example.demo.servlet;

import jakarta.servlet.http.*;
import java.io.IOException;

public class SimpleStatusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().write("Digital Credential Verification Engine is running");
    }
}
