package mark.fang.platform.web.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.media.jfxmediaimpl.MediaUtils;

import mark.fang.platform.domain.Media;
import mark.fang.platform.domain.PageBean;
import mark.fang.platform.domain.ProcessAvg;
import mark.fang.platform.domain.User;
import mark.fang.platform.service.MediaService;
import mark.fang.platform.utils.UUIDUtils;
import mark.fang.platform.utils.UUMedia;
import mark.fang.platform.utils.UploadUtils;

public class MediaAction extends ActionSupport implements ModelDriven<Media> {
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	private Media media = new Media();
	@Override
	public Media getModel() {
		// TODO Auto-generated method stub
		return media;
	}
	
	private MediaService mediaService;
	
	public void setMediaService(MediaService mediaService) {
		this.mediaService = mediaService;
	}
	private Integer currPage=1;
	
	public void setCurrPage(Integer currPage) {
		if(currPage==null)
			currPage=1;
		this.currPage = currPage;
	}
	
	private Integer pageSize=3;
	

	public void setPageSize(Integer pageSize) {
		if(pageSize==null)
			pageSize=3;
		this.pageSize = pageSize;
	}
	
	/*
	 * upload image file
	 */
	private String uploadFileName;
	private File upload;
	private String uploadContentType;
	
	
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}


	public void setUpload(File upload) {
		this.upload = upload;
	}


	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	/*
	 * upload media file
	 */
	private String uploadMediaFileName;
	private File uploadMedia;
	private String uploadMediaContentType;
	
	
	
	public void setUploadMediaFileName(String uploadMediaFileName) {
		this.uploadMediaFileName = uploadMediaFileName;
	}


	public void setUploadMedia(File uploadMedia) {
		this.uploadMedia = uploadMedia;
	}


	public void setUploadMediaContentType(String uploadMediaContentType) {
		this.uploadMediaContentType = uploadMediaContentType;
	}


	public String postMedia() throws IOException {
		
		if(upload!=null) {
			String path="C:/Users/markf/Downloads/apache-tomcat-9.0.11-windows-x64/apache-tomcat-9.0.11/webapps/upload";
			String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
			String realPath= UploadUtils.getPath(uuidFileName);
			//System.out.println(realPath);
			String url = path+realPath;
			File file = new File(url);
			if(!file.exists()) {
				file.mkdirs();
			}
			File dictFile= new File(url+"/"+uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			String[] buff = uuidFileName.split("[.]");
			String filetype = buff[1];
			media.setFileimage("/upload/"+realPath+"/"+uuidFileName);
	
			//System.out.println(fileType);
		}
		if(uploadMedia!=null) {
			String path="C:/Users/markf/Downloads/apache-tomcat-9.0.11-windows-x64/apache-tomcat-9.0.11/webapps/upload";
			String uuidFileName = UploadUtils.getUuidFileName(uploadMediaFileName);
			String realPath= UploadUtils.getPath(uuidFileName);
			String url = path+realPath;
			File file = new File(url);
			if(!file.exists()) {
				file.mkdirs();
			}
			File dictFile= new File(url+"/"+uuidFileName);
			String afterCutName = UploadUtils.getUuidFileName(uuidFileName);
			String mp4FilePath = UUIDUtils.getCode()+".mp4";
			String imgFilePath = UUIDUtils.getCode()+".jpg";
			System.out.println("url is "+url);
			System.out.println("uuidFileName is "+uuidFileName);
			System.out.println("realPath is "+realPath);
			
			FileUtils.copyFile(uploadMedia, dictFile);//复制上传文件到dictFile
			
			UUMedia.cutVideo(url+"/"+uuidFileName, url+"/"+afterCutName, "00:01:00", "00:00:30");//切割dictFile 到 url+"/"+afterCutName
			
			 
			UUMedia.convertToMP4(url+"/"+afterCutName, url+"/"+mp4FilePath);//转换url+"/"+afterCutName文件成url+"/"+mp4FilePath
			
			UUMedia.getPhoto(url+"/"+mp4FilePath, url+"/"+imgFilePath);//从afterCutName文件成url+"/"+mp4FilePath截一张图做封面
			
			dictFile.delete();//删除原始上传文件
			
			File cuted_file = new File(url+"/"+afterCutName);
			cuted_file.delete();//删除原始切割过的文件
			
			
			String[] buff = mp4FilePath.split("[.]");
			String filetype = buff[1];
			media.setFile("/upload"+realPath+"/"+mp4FilePath);
			media.setFiletype(filetype);
			media.setFileimage("/upload"+realPath+"/"+imgFilePath);
			//System.out.println(fileType);
			
		}
		User user= (User) ActionContext.getContext().getSession().get("existUser");
		media.setSid(UUIDUtils.getId());
		Date date = new Date(); 
		media.setRootid(1);
		media.setAuthor(user.getName());
		media.setPdate(date);
		mediaService.postMedia(media);
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Media.class);
		PageBean<Media> pageBean = mediaService.findByPage(media,detachedCriteria,currPage,pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		//System.out.println("22");
		ActionContext.getContext().getSession().remove("speed");
		
		return "findAllMedia";
		
	}
	
	public String findAllMedia() {
		//System.out.println("11");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Media.class);
		PageBean<Media> pageBean = mediaService.findByPage(media,detachedCriteria,currPage,pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		//System.out.println("22");
		return "findAllMedia";
	}
	
	public String playMedia() {
		Media media01 = mediaService.findMedia(media);
		ActionContext.getContext().getValueStack().push(media01);
		return "playMedia";
	}
	
	
	
	

}
