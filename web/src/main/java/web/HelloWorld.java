package web;

import java.time.LocalDateTime;

import io.javalin.Javalin;
import web.vo.HelloWorldVO;

public class HelloWorld {

	public static void main(String[] args) {
		Javalin app = Javalin.create().start(7070);

		app.before("/", ctx -> System.err.println(ctx.path()));
		app.get("/", ctx -> ctx.result("hello world"));

		app.get("/oHo", ctx -> {
			HelloWorldVO vo = new HelloWorldVO();
			vo.setTime(LocalDateTime.now());
			vo.setMessage("oHo");
			ctx.json(vo);
		});

	}
}
