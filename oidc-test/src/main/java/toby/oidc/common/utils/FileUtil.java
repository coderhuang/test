package toby.oidc.common.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public final class FileUtil {

	private FileUtil() {
	}

	public static final Charset BASE_CHARSET = Charset.forName("UTF-8");

	public static void write2File(String data, File file) throws IOException {

		ByteBuffer bBuf = ByteBuffer.wrap(data.getBytes(BASE_CHARSET));
		try (var raf = new RandomAccessFile(file, "rw"); var channel = raf.getChannel()) {

			bBuf.flip();
			channel.write(bBuf);
		}
	}

	public static String readFromFile(File file) throws FileNotFoundException, IOException {

		try (RandomAccessFile raf = new RandomAccessFile(file, "rw"); FileChannel fileChannel = raf.getChannel()) {

			
		}

		return null;
	}

}
