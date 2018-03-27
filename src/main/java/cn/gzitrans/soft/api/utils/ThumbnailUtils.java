package cn.gzitrans.soft.api.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.imageio.ImageIO;

public class ThumbnailUtils {
	
    public static String cutImage(String srcPath, String savePath, int width, int height){
    	File targetFile = new File(savePath);
    	if(!targetFile.exists()){    
            targetFile.mkdirs();    
        }
		File srcFile = new File(srcPath);
		BufferedImage image = null;
		try {
			image = ImageIO.read(srcFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
		File destFile = new File(savePath, picPath);
	
		// 保存裁剪后的图片
		try {
			ImageIO.write(newImage.getSubimage(x, y, width, height), fileSufix,destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return picPath;
	}

}
