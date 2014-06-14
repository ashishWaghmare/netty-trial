package com.ashish; 
 
import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.platform.Verticle;

import java.util.Map;

public class MMServer extends Verticle {

    public void start() {

        int port = 8080;
        String stringPortNo = System.getenv("OPENSHIFT_VERTX_PORT");
        if (stringPortNo != null) {
            port = Integer.valueOf(stringPortNo);
        }
                System.out.println("Port set to: " + port);
        String ip = System.getenv("OPENSHIFT_VERTX_IP");
        if (ip == null) {
            ip = "127.0.0.1";
        }
        System.out.println("IP Address set to: " + ip);
        vertx.createHttpServer().requestHandler(new Handler<HttpServerRequest>() {
            public void handle(HttpServerRequest req) {
                System.out.println("Got request: " + req.uri());
                System.out.println("Headers are: ");
                for (Map.Entry<String, String> entry : req.headers()) {
                    System.out.println(entry.getKey() + ":" + entry.getValue());
                }
                req.response().headers().set("Content-Type", "text/html; charset=UTF-8");
                req.response().end("<html><body><h1>Hello from Ashish Waghmare. My first Vert.x Application!</h1></body></html>");
            }
        }).listen(port, ip);
    }

}
