package mark.fang.platform.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import mark.fang.platform.domain.Article;
import mark.fang.platform.domain.Media;

public interface MediaDao {

	void postMedia(Media media);

	List<Media> findByPage(Media media, DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);

	Integer findCount(DetachedCriteria detachedCriteria);

	Media findMedia(Media media);

}
