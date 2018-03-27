package cn.gzitrans.soft.api.test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class PythonTestMain {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		String result = "";
		String filePath = "E://upload_image/";
		String simplifyFilePath = "E://simplify_image/";
		
		StringBuffer orderString = new StringBuffer();
		orderString.append("python ");
		orderString.append("C:/sketchsimplify/simplify.py ");
		orderString.append(filePath);
		orderString.append("timg.jpg ");
		orderString.append(simplifyFilePath + " ");
		orderString.append("simplify" + Math.random()*1000 + ".jpg");
        try {
			//Process process = Runtime.getRuntime().exec("python /home/jia/fireevacuation/my.py " + filename);
        	Process proc = Runtime.getRuntime().exec(orderString.toString());
        	InputStreamReader ir = new InputStreamReader(proc.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            result = input.readLine();
            input.close();
            ir.close();
        } catch (IOException e) {
            System.out.println("调用python脚本并读取结果时出错：" + e.getMessage());
        }
        System.out.println(result);
    }

}
