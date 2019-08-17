package mark.fang.platform.dao.impl;


import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import mark.fang.platform.dao.MediaDao;
import mark.fang.platform.domain.Article;
import mark.fang.platform.domain.Media;

@Transactional
public class MediaDaoImpl extends HibernateDaoSupport implements MediaDao {

	@Override
	public void postMedia(Media media) {
		// TODO Auto-generated method stub
		System.out.println("I come to here");
		System.out.println(media.toString());
		this.getHibernateTemplate().save(media);
	}

	

	@Override
	public List<Media> findByPage(Media media, DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
		// TODO Auto-generated method stub
		detachedCriteria.setProjection(null);
		return (List<Media>) this.getHibernateTemplate().findByCriteria(detachedCriteria,begin,pageSize);
	}



	@Override
	public Integer findCount(DetachedCriteria detachedCriteria) {
		// TODO Auto-generated method stub
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		// TODO Auto-generated method stub
		//System.out.println("list size is: "+list.size());
		if(list.size()>0) {
			//System.out.println("list record is: "+list.get(0).intValue());
			return list.get(0).intValue();
		}
		return null;
	}



	@Override
	public Media findMedia(Media media) {
		// TODO Auto-generated method stub
		List<Media> list = (List<Media>)  this.getHibernateTemplate().find("from Media where sid=?", media.getSid());
		if(list.size()>0)
			return list.get(0);
		return null;
	}


}
