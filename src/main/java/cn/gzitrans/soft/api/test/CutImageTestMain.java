package cn.gzitrans.soft.api.test;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

public class CutImageTestMain {
	
	public String cutImage(String srcPath, int width, int height)throws IOException {
		File srcFile = new File(srcPath);
		BufferedImage image = ImageIO.read(srcFile);
		int srcWidth = image.getWidth(null);
		int srcHeight = image.getHeight(null);
		int newWidth = 0, newHeight = 0;
		int x = 0, y = 0;
		double scale_w = (double) width / srcWidth;
		double scale_h = (double) height / srcHeight;
		// 按原比例缩放图片
		if (scale_w < scale_h) {
		newHeight = height;
		newWidth = (int) (srcWidth * scale_h);
		x = (newWidth - width) / 2;
		} else {
		newHeight = (int) (srcHeight * scale_w);
		newWidth = width;
		y = (newHeight - height) / 2;
		}
		BufferedImage newImage = new BufferedImage(newWidth, newHeight,
		BufferedImage.TYPE_INT_RGB);
		newImage.getGraphics().drawImage(image.getScaledInstance(newWidth, newHeight,Image.SCALE_SMOOTH), 0, 0, null);
		// 保存缩放后的图片
		String fileSufix = srcFile.getName().substring(srcFile.getName().lastIndexOf(".") + 1);
	
		String string = UUID.randomUUID().toString();
		
		String picPath = string + "." + fileSufix;
	
		File destFile = new File("E://thumbnail_image/", picPath);
	
		// 保存裁剪后的图片
		ImageIO.write(newImage.getSubimage(x, y, width, height), fileSufix,destFile);
		return picPath;
	}


			public static void main(String[] args) throws Exception {
			 String name = "E://simplify_image/simplify654.8222304732112wx0842e27bf63e20e8.o6zAJs160CbKOOqQR-uszvfA9BiM.93d1ea6a0bdeb3ecb5313f5a5bc6581c.jpg";
			 CutImageTestMain i = new CutImageTestMain();
			 String cutImage = i.cutImage(name, 375, 375);
			 System.out.println(cutImage);
			}

}
