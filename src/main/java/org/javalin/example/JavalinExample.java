package org.javalin.example;

import io.javalin.Javalin;
import org.javalin.example.model.input.SimpleModel;
import org.javalin.example.model.output.Fault;
import org.javalin.example.model.output.Output;

public class JavalinExample {
    public static void main(String[] args) {
        try(Javalin app = Javalin.create().start(7070)) {
            /*1*/
            app.get("/", ctx -> ctx.result("Hello World"));
            /*2*/
            app.get("/path_1", ctx -> ctx.result("get path_1"));
            /*3*/
            app.post("path_1", ctx -> {
                SimpleModel simpleModel = ctx.bodyAsClass(SimpleModel.class);
                Output output = new Output(simpleModel.getName());
                ctx.json(output);
            });
            /*4*/
            app.get("/hello/{name}", ctx -> { // the {} syntax does not allow slashes ('/') as part of the parameter
                ctx.result("Hello: " + ctx.pathParam("name"));
            });

            /*5*/
            app.get("/bye", ctx -> ctx.result("Bye: " + ctx.header("name")));
            /*6*/
            app.get("/fault", ctx -> {
                Fault fault = new Fault(500, "500 status example");
                ctx.status(500).json(fault);
            });
        }
    }
}
