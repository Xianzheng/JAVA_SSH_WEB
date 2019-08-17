package mark.fang.platform.utils;

import java.util.UUID;

/**
 * file upload utils
 * @author markf
 *
 */
public class UploadUtils {
	/**
	 * solve the problem file repeat
	 * @param fileName
	 * @return
	 */
	public static String getUuidFileName(String fileName) {
		int idx = fileName.lastIndexOf(".");
		String extention = fileName.substring(idx);
		return UUID.randomUUID().toString().replace("-","")+extention;
	}
	/**
	 * 
	 * @param args
	 */
	public static String getPath(String uuidFileName) {
		int code1 = uuidFileName.hashCode();
		int d1 = code1 & 0xf;
		int code2 =  code1>>>4;
		int d2 = code2& 0xf;
		return "/"+d1+"/"+d2;
	}
	public static void main(String[] args) {
		System.out.println(getUuidFileName("aa.txt"));
	}
}
