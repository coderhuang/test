package querdsl.process;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
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
	void testProcessInfo() throws IOException {
		
		var process = new ProcessBuilder(VS_CODE_EXE_PATH).start();
		assertNotNull(process);
		
		var processHandle = process.toHandle();
		var processInfo = processHandle.info();
		System.err.println(processInfo.command().orElse(""));
	}
}
