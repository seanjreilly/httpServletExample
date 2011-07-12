package com.example;

import org.junit.Test;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class HelloWorldServletTest {

    @Test
    public void doGet_shouldSayHello() throws Exception {
        //setup
        Servlet servlet = new HelloWorldServlet("World");

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getMethod()).thenReturn("GET");

        HttpServletResponse response = mock(HttpServletResponse.class);
        StringWriter output = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(output));

        //execute
        servlet.service(request, response);

        //verify
        verify(response).setContentType("text/plain");
        assertEquals("Hello, World!\n", output.toString());
    }
}