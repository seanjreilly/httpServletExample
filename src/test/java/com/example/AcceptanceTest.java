package com.example;

import org.apache.catalina.startup.Tomcat;
import org.junit.Test;

import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class AcceptanceTest {
    @Test
    public void acceptanceTest() throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(12345);
        tomcat.addContext("/myApp", System.getProperty("java.io.tmpdir")).addApplicationListener("com.example.Application");
        tomcat.start();

        try {
            StringBuilder contents = new StringBuilder();
            char[] buffer = new char[512];
            Reader output = new InputStreamReader(new URL("http://localhost:12345/myApp/").openStream());
            for (int i = output.read(buffer); i != -1; i = output.read(buffer)) {
                contents.append(buffer, 0 , i);
            }

            output.close();
            assertEquals("Hello, Everyone!\n", contents.toString());

            /*
                or, call tomcat.getServer().await() if you want to mess with things manually
             */

        } finally {
            tomcat.stop();
        }
    }
}
