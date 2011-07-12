package com.example;

import org.apache.catalina.startup.Tomcat;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
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
            assertEquals("Hello, Everyone!\n", retrieveUrlContents("http://localhost:12345/myApp/"));

            /*
                or, call tomcat.getServer().await() if you want to mess with things manually
             */

        } finally {
            tomcat.stop();
        }
    }

    private String retrieveUrlContents(String serverUrl) throws IOException {
        Reader output = new InputStreamReader(new URL(serverUrl).openStream());
        try {
            char[] buffer = new char[512];
            StringBuilder contents = new StringBuilder();
            for (int i = output.read(buffer); i != -1; i = output.read(buffer)) {
                contents.append(buffer, 0 , i);
            }
            return contents.toString();
        } finally {
            output.close();
        }
    }
}
