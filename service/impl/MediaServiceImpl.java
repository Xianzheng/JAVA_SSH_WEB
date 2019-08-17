package mark.fang.platform.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import mark.fang.platform.dao.MediaDao;
import mark.fang.platform.domain.Article;
import mark.fang.platform.domain.Media;
import mark.fang.platform.domain.PageBean;
import mark.fang.platform.service.MediaService;

public class MediaServiceImpl implements MediaService {
	
	MediaDao mediaDao;

	public void setMediaDao(MediaDao mediaDao) {
		this.mediaDao = mediaDao;
	}

	@Override
	public void postMedia(Media media) {
		// TODO Auto-generated method stub
		mediaDao.postMedia(media);
		
	}

	@Override
	public PageBean<Media> findByPage(Media media, DetachedCriteria detachedCriteria, Integer currPage,
			Integer pageSize) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		PageBean<Media> pageBean = new PageBean<Media>();
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		
		Integer totalCount=mediaDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		Double tc = totalCount.doubleValue();
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		Integer begin = (currPage-1)*pageSize;
		List<Media> list = mediaDao.findByPage(media,detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
		
	}

	@Override
	public Media findMedia(Media media) {
		// TODO Auto-generated method stub
		return mediaDao.findMedia(media);
	}
	
	
}
