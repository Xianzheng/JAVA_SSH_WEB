package mark.fang.platform.domain;

import java.util.Date;

public class Media {
	private int id;
	private String pid;
	private String sid;
	private int rootid;
	private String title;
	private String detail;
	private String author;
	private int reviews;
	private int replies;
	private Date pdate;
	private String file;
	private String filetype;
	private String fileimage;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public int getRootid() {
		return rootid;
	}
	public void setRootid(int rootid) {
		this.rootid = rootid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getReviews() {
		return reviews;
	}
	public void setReviews(int reviews) {
		this.reviews = reviews;
	}
	public int getReplies() {
		return replies;
	}
	public void setReplies(int replies) {
		this.replies = replies;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	public String getSid() {
		return sid;
	}
	public String getFile() {
		return file;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	
	public String getFileimage() {
		return fileimage;
	}
	public void setFileimage(String fileimage) {
		this.fileimage = fileimage;
	}
	@Override
	public String toString() {
		return "Media [id=" + id + ", pid=" + pid + ", sid=" + sid + ", rootid=" + rootid + ", title=" + title
				+ ", detail=" + detail + ", author=" + author + ", reviews=" + reviews + ", replies=" + replies
				+ ", pdate=" + pdate + ", file=" + file + ", filetype=" + filetype + "]";
	}
	
}
