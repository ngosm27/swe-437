package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.*;

public class Assign9_NaderiNgoNguyenTran {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private conversion servlet;

    private StringWriter stringWriter;

    private PrintWriter writer;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);

        /* Step 1: Set up for the mock response writer */
        stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
    }

    @Test
    void doPostTest1() throws ServletException, IOException {
      
        /* Step 2: Set all the parameters as 100, as per our first test input */
        when(request.getParameter("F")).thenReturn("100");
        when(request.getParameter("C")).thenReturn("100");
        when(request.getParameter("in")).thenReturn("100");
        when(request.getParameter("cm")).thenReturn("100");
        when(request.getParameter("ft")).thenReturn("100");
        when(request.getParameter("m")).thenReturn("100");
        when(request.getParameter("mi")).thenReturn("100");
        when(request.getParameter("km")).thenReturn("100");
        when(request.getParameter("gal")).thenReturn("100");
        when(request.getParameter("L")).thenReturn("100");
        when(request.getParameter("oz")).thenReturn("100");
        when(request.getParameter("g")).thenReturn("100");
        when(request.getParameter("lb")).thenReturn("100");
        when(request.getParameter("kg")).thenReturn("100");

        // call doPost and get results
        servlet.doPost(request, response);
        writer.flush();
        String actualResponse = stringWriter.toString();

        // perform assertions
        assert(actualResponse.contains("name=\"F\" size=6 value=\"212.0\""));
        assert(actualResponse.contains("name=\"C\" size=6 value=\"37.78\""));
        assert(actualResponse.contains("name=\"in\" size=6 value=\"39.37\""));
        assert(actualResponse.contains("name=\"cm\" size=6 value=\"254.0\""));
        assert(actualResponse.contains("name=\"ft\" size=6 value=\"328.08\""));
        assert(actualResponse.contains("name=\"m\" size=6 value=\"30.48\""));
        assert(actualResponse.contains("name=\"mi\" size=6 value=\"62.14\""));
        assert(actualResponse.contains("name=\"km\" size=6 value=\"160.9\""));
        assert(actualResponse.contains("name=\"gal\" size=6 value=\"26.42\""));
        assert(actualResponse.contains("name=\"L\" size=6 value=\"378.5\""));
        assert(actualResponse.contains("name=\"oz\" size=6 value=\"3.53\""));
        assert(actualResponse.contains("name=\"g\" size=6 value=\"2835.0\""));
        assert(actualResponse.contains("name=\"lb\" size=6 value=\"220.5\""));
        assert(actualResponse.contains("name=\"kg\" size=6 value=\"45.36\""));
    }

    @Test
    void doPostTest2() throws ServletException, IOException {

        /* Step 3: Set all the parameters as empty, as per our first test input */
        when(request.getParameter("F")).thenReturn("");
        when(request.getParameter("C")).thenReturn("");
        when(request.getParameter("in")).thenReturn("");
        when(request.getParameter("cm")).thenReturn("");
        when(request.getParameter("ft")).thenReturn("");
        when(request.getParameter("m")).thenReturn("");
        when(request.getParameter("mi")).thenReturn("");
        when(request.getParameter("km")).thenReturn("");
        when(request.getParameter("gal")).thenReturn("");
        when(request.getParameter("L")).thenReturn("");
        when(request.getParameter("oz")).thenReturn("");
        when(request.getParameter("g")).thenReturn("");
        when(request.getParameter("lb")).thenReturn("");
        when(request.getParameter("kg")).thenReturn("");

        /* call doPost and get results */
        servlet.doPost(request, response);
        writer.flush();
        String actualResponse = stringWriter.toString();

        /* perform assertions */
        assert(actualResponse.contains("name=\"F\" size=6 value=\"\""));
        assert(actualResponse.contains("name=\"C\" size=6 value=\"\""));
        assert(actualResponse.contains("name=\"in\" size=6 value=\"\""));
        assert(actualResponse.contains("name=\"cm\" size=6 value=\"\""));
        assert(actualResponse.contains("name=\"ft\" size=6 value=\"\""));
        assert(actualResponse.contains("name=\"m\" size=6 value=\"\""));
        assert(actualResponse.contains("name=\"mi\" size=6 value=\"\""));
        assert(actualResponse.contains("name=\"km\" size=6 value=\"\""));
        assert(actualResponse.contains("name=\"gal\" size=6 value=\"\""));
        assert(actualResponse.contains("name=\"L\" size=6 value=\"\""));
        assert(actualResponse.contains("name=\"oz\" size=6 value=\"\""));
        assert(actualResponse.contains("name=\"g\" size=6 value=\"\""));
        assert(actualResponse.contains("name=\"lb\" size=6 value=\"\""));
        assert(actualResponse.contains("name=\"kg\" size=6 value=\"\""));
    }
}