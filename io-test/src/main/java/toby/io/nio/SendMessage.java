package toby.io.nio;

public class SendMessage {

	public static void main(String[] args) throws InterruptedException {

		int port = 3333;
		var thread1 = new Thread(() -> {

			try {
				new NioServer().launch(port);
			} catch (Exception e) {

				e.printStackTrace();
			}
		});
		thread1.start();

		var thread2 = new Thread(() -> {

			try {
				new NioClient().launch("localhost", port);
			} catch (Exception e) {

				e.printStackTrace();
			}
		});
		thread2.start();

		thread1.join();
		thread2.join();
		System.err.println("10->all is done!");
	}
}
