package mark.fang.platform.service;

import org.hibernate.criterion.DetachedCriteria;

import mark.fang.platform.domain.Article;
import mark.fang.platform.domain.Media;
import mark.fang.platform.domain.PageBean;

public interface MediaService {

	void postMedia(Media media);

	PageBean<Media> findByPage(Media media, DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	Media findMedia(Media media);

}
