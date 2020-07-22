package querdsl.process;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;

class ProcessTest {

	private static final String VS_CODE_EXE_PATH = "D:\\Program Files\\Microsoft VS Code\\Code.exe";

	@Test
	void testNewProcess() throws IOException, InterruptedException, ExecutionException {

		var process = new ProcessBuilder(VS_CODE_EXE_PATH).start();
		assertNotNull(process);
		System.err.println(process.pid());
		System.err.println(process.onExit().thenApply(p -> p.exitValue()).get());
	}

	@Test
	void testProctessInfo() throws IOException, InterruptedException, ExecutionException {

		var process = new ProcessBuilder(VS_CODE_EXE_PATH).start();
		assertNotNull(process);

		var processHandle = process.toHandle();
		System.err.println(processHandle.pid());

		var processInfo = processHandle.info();
		System.err.println(processInfo.command().orElse(""));
		System.err.println(processInfo.user().orElse(""));
		System.err.println(processInfo.startInstant().orElse(Instant.now()).toString());
		System.err.println(processInfo.totalCpuDuration().orElse(Duration.ofMillis(0L)));
		
		var onExit = processHandle.onExit();
		onExit.get();
		onExit.thenAccept(ph -> {
			System.out.printf("PID %d  %s terminated%n", ph.pid(), "啊哈");
		});
	}

	@Test
	void testAllProcessInfo() {

		var streamOfProcess = ProcessHandle.allProcesses();
		assertNotNull(streamOfProcess);
		streamOfProcess.filter(ph -> ph.info().command().isPresent()).limit(10).forEach(ph -> {

			var processInfo = ph.info();
			System.err.println(processInfo.command().orElse(""));
			System.err.println(processInfo.user().orElse(""));
			System.err.println(processInfo.startInstant().orElse(Instant.now()).toString());
			System.err.println(processInfo.totalCpuDuration().orElse(Duration.ofMillis(0L)));
			
			System.err.println("---------------神秘分割线-----------------------------------------");
		});
	}
}
