package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class conversionTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private conversion servlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        /*Step 1: Set up for the mock response writer*/

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
    }

    @Test
    void doPostTest1() throws ServletException, IOException {

        /*Step 2: Set all the parameters as 100, as per our first test input, then check if they match our expected output*/

        when(request.getParameter("F")).thenReturn("100");
        servlet.doPost(request, reponse);
        assertequals("37.78", stringWriter.toString().trim());

        when(request.getParameter("C")).thenReturn("100");
        servlet.doPost(request, reponse);
        assertequals("212.0", stringWriter.toString().trim());

        when(request.getParameter("in")).thenReturn("100");
        servlet.doPost(request, reponse);
        assertequals("254.0", stringWriter.toString().trim());

        when(request.getParameter("cm")).thenReturn("100");
        servlet.doPost(request, reponse);
        assertequals("39.37", stringWriter.toString().trim());

        when(request.getParameter("ft")).thenReturn("100");
        servlet.doPost(request, reponse);
        assertequals("30.48", stringWriter.toString().trim());

        when(request.getParameter("m")).thenReturn("100");
        servlet.doPost(request, reponse);
        assertequals("328.084", stringWriter.toString().trim());

        when(request.getParameter("mi")).thenReturn("100");
        servlet.doPost(request, reponse);
        assertequals("160.9", stringWriter.toString().trim());

        when(request.getParameter("km")).thenReturn("100");
        servlet.doPost(request, reponse);
        assertequals("62.14", stringWriter.toString().trim());

        when(request.getParameter("gal")).thenReturn("100");
        servlet.doPost(request, reponse);
        assertequals("378.5", stringWriter.toString().trim());

        when(request.getParameter("L")).thenReturn("100");
        servlet.doPost(request, reponse);
        assertequals("26.42", stringWriter.toString().trim());

        when(request.getParameter("oz")).thenReturn("100");
        servlet.doPost(request, reponse);
        assertequals("2835.0", stringWriter.toString().trim());

        when(request.getParameter("g")).thenReturn("100");
        servlet.doPost(request, reponse);
        assertequals("3.53", stringWriter.toString().trim());

        when(request.getParameter("lb")).thenReturn("100");
        servlet.doPost(request, reponse);
        assertequals("45.36", stringWriter.toString().trim());

        when(request.getParameter("kg")).thenReturn("100");
        servlet.doPost(request, reponse);
        assertequals("220.5", stringWriter.toString().trim());
    }

    @Test
    void doPostTest2() throws ServletException, IOException {
        /*Step 3: Set all the parameters as 100, as per our first test input, then check if they match our expected output*/

        when(request.getParameter("F")).thenReturn("0");
        servlet.doPost(request, reponse);
        assertequals("", stringWriter.toString().trim());

        when(request.getParameter("C")).thenReturn("0");
        servlet.doPost(request, reponse);
        assertequals("", stringWriter.toString().trim());

        when(request.getParameter("in")).thenReturn("0");
        servlet.doPost(request, reponse);
        assertequals("", stringWriter.toString().trim());

        when(request.getParameter("cm")).thenReturn("0");
        servlet.doPost(request, reponse);
        assertequals("", stringWriter.toString().trim());

        when(request.getParameter("ft")).thenReturn("0");
        servlet.doPost(request, reponse);
        assertequals("", stringWriter.toString().trim());

        when(request.getParameter("m")).thenReturn("0");
        servlet.doPost(request, reponse);
        assertequals("", stringWriter.toString().trim());

        when(request.getParameter("mi")).thenReturn("");
        servlet.doPost(request, reponse);
        assertequals("", stringWriter.toString().trim());

        when(request.getParameter("km")).thenReturn("0");
        servlet.doPost(request, reponse);
        assertequals("", stringWriter.toString().trim());

        when(request.getParameter("gal")).thenReturn("0");
        servlet.doPost(request, reponse);
        assertequals("", stringWriter.toString().trim());

        when(request.getParameter("L")).thenReturn("0");
        servlet.doPost(request, reponse);
        assertequals("", stringWriter.toString().trim());

        when(request.getParameter("oz")).thenReturn("0");
        servlet.doPost(request, reponse);
        assertequals("", stringWriter.toString().trim());

        when(request.getParameter("g")).thenReturn("0");
        servlet.doPost(request, reponse);
        assertequals("", stringWriter.toString().trim());

        when(request.getParameter("lb")).thenReturn("0");
        servlet.doPost(request, reponse);
        assertequals("", stringWriter.toString().trim());

        when(request.getParameter("kg")).thenReturn("0");
        servlet.doPost(request, reponse);
        assertequals("", stringWriter.toString().trim());
    }
}