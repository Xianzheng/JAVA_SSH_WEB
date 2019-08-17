package mark.fang.platform.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import mark.fang.platform.dao.ArticleDao;
import mark.fang.platform.domain.Article;
import mark.fang.platform.domain.PageBean;
import mark.fang.platform.service.ArticleService;

public class ArticleServiceImpl implements ArticleService {
	/*
	 * For spring injection
	 */
	private ArticleDao articleDao;

	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	@Override
	public PageBean<Article> findByPage(Article article,DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		// TODO Auto-generated method stub
		PageBean<Article> pageBean = new PageBean<Article>();
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		
		Integer totalCount=articleDao.findRootCount(article);
		pageBean.setTotalCount(totalCount);
		Double tc = totalCount.doubleValue();
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		Integer begin = (currPage-1)*pageSize;
		List<Article> list = articleDao.findByPage(article,detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
		
	}

	@Override
	public void post(Article article) {
		// TODO Auto-generated method stub
		articleDao.post(article);
		
	}

	@Override
	public PageBean<Article> findByPid(Article article, DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		// TODO Auto-generated method stub
		PageBean<Article> pageBean = new PageBean<Article>();
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		
		Integer totalCount=articleDao.findPidCount(article);
		pageBean.setTotalCount(totalCount);
		Double tc = totalCount.doubleValue();
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		Integer begin = (currPage-1)*pageSize;
		List<Article> list = articleDao.findByPid(article,detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
		
	}

	@Override
	public void reply(Article article) {
		// TODO Auto-generated method stub
		articleDao.reply(article);
		
	}

	@Override
	public List<Article> findByAllPid(Article article) {
		// TODO Auto-generated method stub
		return articleDao.findByAllPid(article);
	}

	@Override
	public void deleteTheme(List<Article> list) {
		// TODO Auto-generated method stub
		articleDao.deleteTheme(list);
	}

	@Override
	public Article findBySid(Article article) {
		// TODO Auto-generated method stub
		return articleDao.findBySid(article);
	}

	@Override
	public void deleteSingle(Article article01) {
		// TODO Auto-generated method stub
		articleDao.deleteSingle(article01);
	}
	
}
