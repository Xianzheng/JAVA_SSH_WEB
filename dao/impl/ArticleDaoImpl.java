package mark.fang.platform.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import mark.fang.platform.dao.ArticleDao;
import mark.fang.platform.domain.Article;
import mark.fang.platform.domain.PageBean;

@Transactional
public class ArticleDaoImpl extends HibernateDaoSupport implements ArticleDao {

	@Override
	public Integer findPidCount(Article article) {
		// TODO Auto-generated method stub
		
		List<Long> list = (List<Long>) this.getHibernateTemplate().find("from Article where pid=?", article.getPid());
		
		return list.size();
	}

	@Override
	public List<Article> findByPage(Article article,DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
		// TODO Auto-generated method stub
		detachedCriteria.setProjection(null);
		
		detachedCriteria.add(Restrictions.eq("rootid", article.getRootid()));
		
		return (List<Article>) this.getHibernateTemplate().findByCriteria(detachedCriteria,begin,pageSize);
	}

	@Override
	public void post(Article article) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(article);
	}

	@Override
	public List<Article> findByPid(Article article, DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
		// TODO Auto-generated method stub
		detachedCriteria.setProjection(null);
		detachedCriteria.add(Restrictions.eq("pid", article.getPid()));
		return (List<Article>) this.getHibernateTemplate().findByCriteria(detachedCriteria,begin,pageSize);
	}


	@Override
	public void reply(Article article) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(article);
	}

	@Override
	public List<Article> findByAllPid(Article article) {
		// TODO Auto-generated method stub
		return (List<Article>) this.getHibernateTemplate().find("from Article where pid=?", article.getPid());
	}

	@Override
	public void deleteTheme(List<Article> list) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().deleteAll(list);
	}

	@Override
	public Integer findRootCount(Article article) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		List<Long> list = (List<Long>) this.getHibernateTemplate().find("from Article where rootid=?",article.getRootid());
		
		return list.size();
	}

	@Override
	public Integer findCount(DetachedCriteria detachedCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Article findBySid(Article article) {
		// TODO Auto-generated method stub
		
		List<Article> list = (List<Article>) this.getHibernateTemplate().find("from Article where sid=?",article.getSid());
	
		if(list.size()>0)
			return list.get(0);
		return null;
	}

	@Override
	public void deleteSingle(Article article01) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(article01);
		
	}

}
