package mark.fang.platform.web.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.DetachedCriteria;

import mark.fang.platform.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import mark.fang.platform.domain.Article;
import mark.fang.platform.domain.PageBean;
import mark.fang.platform.domain.User;
import mark.fang.platform.service.ArticleService;
import mark.fang.platform.utils.UUIDUtils;

public class ArticleAction extends ActionSupport implements ModelDriven<Article> {
	
	private Article article = new Article(); //for modeldriven
	@Override
	public Article getModel() {
		// TODO Auto-generated method stub
		return article;
	}
	
	//for spring injection
	private ArticleService articleService;
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
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


	public String findAll() {
		//System.out.println("11");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
		PageBean<Article> pageBean = articleService.findByPage(article,detachedCriteria,currPage,pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		//System.out.println("22");
		return "findAll";
	}
	
	public String post() {
		User user= (User) ActionContext.getContext().getSession().get("existUser");
		
		article.setPid(UUIDUtils.getId());
		//System.out.println("name is "+user.getName());
		Date date = new Date(); 
		
		
			article.setAuthor(user.getName());
			article.setPdate(date);
		
		articleService.post(article);
		return "postSuccess";
		
	}
	
	public String findByPid() {//用于查找Theme下面的帖子
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
		PageBean<Article> pageBean = articleService.findByPid(article,detachedCriteria,currPage,pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findByPid";
	}
	
	public String deleteTheme() {//删除Theme以及下面的一个帖子
		List<Article> list = articleService.findByAllPid(article);
		articleService.deleteTheme(list);
		return "deleteThemeSuccess";
	}
	/*
	 * upload file
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


	public String reply() throws IOException {
		if(upload!=null) {
			String path="/home/mark_ffang/tomcat/webapps/upload";
			System.out.println(uploadFileName);
			if(!uploadFileName.contains(".")) {
				uploadFileName = uploadFileName+".jpg";
			}
			String[] buff1 = uploadFileName.split("[.]");
			if(buff1[1].equals("jpeg")) {
				buff1[1]="jpg";
			}
			uploadFileName=buff1[0]+"."+buff1[1];
			System.out.println(uploadFileName);
			String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
			String realPath= UploadUtils.getPath(uuidFileName);
			String url = path+realPath;
			File file = new File(url);

			if(!file.exists()) {
				file.mkdirs();
			}
			File dictFile= new File(url+"/"+uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			String[] buff = uuidFileName.split("[.]");
			String filetype = buff[1];//.doc,.docx,.zip,.pdf,.txt,.jpg,.heic
			if(!buff[1].equals("doc")&&!buff[1].equals("docx")&&!buff[1].equals("zip")&&!buff[1].equals("pdf")
					&&!buff[1].equals("txt")&&!buff[1].equals("jpg")&&!buff[1].equals("png")&&!buff[1].equals("heic")
					&&!buff[1].equals("jpeg")) {
				return "input";
			}
				
			article.setFile("/upload/"+realPath+"/"+uuidFileName);
			article.setFiletype(filetype);
			//System.out.println(fileType);
		}
		
		User user= (User) ActionContext.getContext().getSession().get("existUser");
		article.setSid(UUIDUtils.getId());
		Date date = new Date(); 
		article.setRootid(1);
		article.setAuthor(user.getName());
		article.setPdate(date);
		articleService.reply(article);

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
		PageBean<Article> pageBean = articleService.findByPid(article,detachedCriteria,currPage,pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		
		return "findByPid";
	}
	
	public String deleteReply() {
		System.out.println(article.getSid());
		Article article01 = articleService.findBySid(article);
		//System.out.print(article.toString());
		articleService.deleteSingle(article01);
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
		PageBean<Article> pageBean = articleService.findByPid(article,detachedCriteria,currPage,pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		
		return "findByPid";
	}

	
	

}
