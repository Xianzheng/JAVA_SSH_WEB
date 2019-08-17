package mark.fang.platform.utils;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;  
import java.util.Calendar;  
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionContext;

import mark.fang.platform.domain.ProcessAvg; 

public class UUMedia {
	
	private final static String FFMPEG_PATH ="C:\\Users\\markf\\Downloads\\ffmpeg-20190730-a0c1970-win64-static\\bin\\ffmpeg";
	
	
	private static int checkContentType(String PATH) {
		
		String type = PATH.substring(PATH.lastIndexOf(".") + 1, PATH.length())  
                .toLowerCase();
		if(type.equals("avi")) {
			return 0;
		}else if(type.equals("mpg")) {
			return 0;
		}else if(type.equals("wmv")) {
			return 0;
		}else if(type.equals("3gp")) {
			return 0;
		}else if(type.equals("mov")) {
			return 0;
		}else if(type.equals("mp4")) {
			return 0;
		}else if(type.equals("asf")) {
			return 0;
		}else if(type.equals("asx")) {
			return 0;
		}else if(type.equals("flv")) {
			return 0;
		}else if(type.equals("wmv9")) {
			return 1;
		}else if(type.equals("rm")) {
			return 1;
		}else if(type.equals("rmvb")) {
			return 1;
		}
		return 9;
		
	}
	private static boolean checkfile(String path) {
		 File file = new File(path);  
	        if (!file.isFile()) {  
	            return false;  
	        }  
	        return true; 
	}
	public static boolean cutVideo(String uploadfilepath,String createfilepath,String startT, String endT) {
		if (!checkfile(uploadfilepath)) {  
            System.out.println(uploadfilepath + " is not file");  
            return false;  
		}
		List<String> commend = new ArrayList<String>(); 
		 commend.add(FFMPEG_PATH);
		 commend.add("-ss");//开始时间
		 commend.add(startT);
		 commend.add("-t");//持续时间
		 commend.add(endT);
		 commend.add("-i");
		 commend.add(uploadfilepath);//被切文件路径
		 commend.add("-c");
		 commend.add("copy");
		 commend.add(createfilepath);//生成文件路径
		 
		 try {
			 
			 ProcessBuilder builder = new ProcessBuilder(commend);  
             builder.command(commend);  
             Process p = builder.start(); 
             System.out.println("It processes to cut video");
             clear(p);
             p.waitFor();
              
             p.destroy();
             return true;
			 
		 }catch (Exception e) {
			 e.printStackTrace();
			 return false;
		 }
	}
	
	public static boolean convertToMP4(String uploadfilepath, String createfilepath) {
		if (!checkfile(uploadfilepath)) {  
            System.out.println(uploadfilepath + " is not file");  
            return false;  
		}
		
		List<String> commend = new ArrayList<String>(); 
		 commend.add(FFMPEG_PATH);
		 commend.add("-i");
		 commend.add(uploadfilepath);
		 //System.out.println("uploadfilepath is "+uploadfilepath);
		 commend.add("-vcodec");
		 commend.add("h264");
		 commend.add(createfilepath);
		 //System.out.println("createfilepath is "+createfilepath);
		 try {
			 
			 ProcessBuilder builder = new ProcessBuilder(commend);  
             builder.command(commend);  
             Process p = builder.start();
            
             System.out.println("It processes to convert to MP4");
             //clear(p);
             doWaitPro(p);
             p.waitFor();
             p.destroy();
             return true;
			 
		 }catch (Exception e) {
			 e.printStackTrace();
			 return false;
		 }
		
		
	}
		
	
	
	public static boolean getPhoto(String uploadfilepath, String createfilepath) {
		
		List<String> commend = new ArrayList<String>(); 
		 commend.add(FFMPEG_PATH);
		 commend.add("-i");
		 System.out.println("uploadfilepath is "+uploadfilepath);
		 commend.add(uploadfilepath);
		 commend.add("-y");
		 commend.add("-f");
		 commend.add("image2");
		 commend.add("-ss");
		 commend.add("8");
		 commend.add("-t");
		 commend.add("0.01");
		 commend.add("-s");
		 commend.add("800x500");
		 System.out.println("createfilepath is "+createfilepath);
		 commend.add(createfilepath);
		 try {
			 
			 ProcessBuilder builder = new ProcessBuilder(commend);  
             builder.command(commend);  
             Process p = builder.start();
             clear(p);
             System.out.println("正在弄图");
             p.waitFor();
             
             return true;
			 
		 }catch (Exception e) {
			 e.printStackTrace();
			 return false;
		 }
	}
	private static void clear(Process p) {
		final InputStream is1 = p.getInputStream();  
        final InputStream is2 = p.getErrorStream();
        
        new Thread() {  
       	    public void run() {  
       	       BufferedReader br1 = new BufferedReader(new InputStreamReader(is1));  
       	        try {  
       	            String line1 = null;  
       	            while ((line1 = br1.readLine()) != null) {  
       	                  if (line1 != null){}  
       	              }  
       	        } catch (IOException e) {  
       	             e.printStackTrace();  
       	        }  
       	        finally{  
       	             try {  
       	               is1.close();  
       	             } catch (IOException e) {  
       	                e.printStackTrace();  
       	            }  
       	          }  
       	        }  
       	     }.start(); 
       	     
	     new Thread() {   
	         public void  run() {   
	          BufferedReader br2 = new  BufferedReader(new  InputStreamReader(is2));   
	             try {   
	                String line2 = null ;   
	                while ((line2 = br2.readLine()) !=  null ) {   
	                     if (line2 != null){}  
	                }   
	              } catch (IOException e) {   
	                    e.printStackTrace();  
	              }   
	             finally{  
	                try {  
	                    is2.close();  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                }  
	              }  
	           }   
	         }.start();  
		
	}
	public static void doWaitPro(Process p){
		try {
			
			String errorMsg = readInputStream(p.getErrorStream(), "error");
			String outputMsg = readInputStream(p.getInputStream(), "out"); 
			   int c = p.waitFor();  
			   if (c != 0) {// 如果处理进程在等待  
				   System.out.println("处理失败：" + errorMsg);  
			   } else {  
				   System.out.println("COMPLETE" + outputMsg);  
			   }  
		} catch (IOException e) {
			// tanghui Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// tanghui Auto-generated catch block
			e.printStackTrace();
		}  
	}
	private static String readInputStream(InputStream is, String f) throws IOException {  
        // 将进程的输出流封装成缓冲读者对象  
        BufferedReader br = new BufferedReader(new InputStreamReader(is));  
        StringBuffer lines = new StringBuffer();// 构造一个可变字符串  
        long totalTime = 0;  
        int COMPLETE = 0;
        
        // 对缓冲读者对象进行每行循环  
        for (String line = br.readLine(); line != null; line = br.readLine()) {  
         lines.append(line);// 将每行信息字符串添加到可变字符串中  
         int positionDuration = line.indexOf("Duration:");// 在当前行中找到第一个"Duration:"的位置  
         int positionTime = line.indexOf("time=");  
         if (positionDuration > 0) {// 如果当前行中有"Duration:"  
          String dur = line.replace("Duration:", "");// 将当前行中"Duration:"替换为""  
          dur = dur.trim().substring(0, 8);// 将替换后的字符串去掉首尾空格后截取前8个字符  
          int h = Integer.parseInt(dur.substring(0, 2));// 封装成小时  
          int m = Integer.parseInt(dur.substring(3, 5));// 封装成分钟  
          int s = Integer.parseInt(dur.substring(6, 8));// 封装成秒  
          totalTime = h * 3600 + m * 60 + s;// 得到总共的时间秒数  
         }  
         if (positionTime > 0) {// 如果所用时间字符串存在  
          // 截取包含time=的当前所用时间字符串  
          String time = line.substring(positionTime, line  
            .indexOf("bitrate") - 1);  
          time = time.substring(time.indexOf("=") + 1, time.indexOf("."));// 截取当前所用时间字符串  
          int h = Integer.parseInt(time.substring(0, 2));// 封装成小时  
          int m = Integer.parseInt(time.substring(3, 5));// 封装成分钟  
          int s = Integer.parseInt(time.substring(6, 8));// 封装成秒  
          long hasTime = h * 3600 + m * 60 + s;// 得到总共的时间秒数 
          float t = (float) hasTime / (float) totalTime;// 计算所用时间与总共需要时间的比例  
          COMPLETE = (int) Math.ceil(t * 100);// 计算完成进度百分比  
          ActionContext.getContext().getSession().put("speed",String.valueOf(COMPLETE));
          System.out.println(f);
         }  
         	System.out.println("完成SESSION：" + ActionContext.getContext().getSession().get("speed") + "%"); 
         	
         	
        }  
        br.close();// 关闭进程的输出流  
        return lines.toString();  
   }

		   
		
	
	
	
}	

