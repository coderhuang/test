package toby.jwt.common.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import toby.jwt.common.dto.VerifyImageDto;

public final class VerifyImageGenerator {

	private VerifyImageGenerator() {
	}

	/**
	 * 根据图片的长、宽以及验证码的字符个数生成图片验证码对象
	 * 
	 * @param width
	 * @param height
	 * @param randSize
	 * @return
	 * @throws IOException
	 */
	public static VerifyImageDto creatImageVerify(int width, int height, int randSize) {
		// 图片验证码对象
		VerifyImageDto vo = new VerifyImageDto();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics graphics = image.getGraphics();
		// 生成随机类
		Random random = new Random();
		// 设定背景色
		graphics.setColor(getRandColor(200, 250));
		// 填充指定的矩形
		graphics.fillRect(0, 0, width, height);
		// 设定字体
		graphics.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		// 随机产生155条干扰线，使图像中的认证码不易被其他程序探测到
		graphics.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			graphics.drawLine(x, y, xl, yl);
		}
		// 取随机产生的验证码
		String sRand = "";
		for (int i = 0; i < randSize; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			// 将验证码显示到图像中
			graphics.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			graphics.drawString(rand, 13 * i + 6, 16);
		}
		// 释放此图形的上下文以及它使用的所有系统资源。
		graphics.dispose();
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

			ImageIO.write(image, "jpeg", baos);
			vo.setImage(baos.toByteArray());
			vo.setRandomString(sRand);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return vo;
	}

	/**
	 * 根据给定范围获得随机颜色
	 *
	 */
	public static Color getRandColor(int fc, int bc) {

		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);

		return new Color(r, g, b);
	}
}
