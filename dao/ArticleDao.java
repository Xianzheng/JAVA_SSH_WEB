package mark.fang.platform.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import mark.fang.platform.domain.Article;
import mark.fang.platform.domain.PageBean;

public interface ArticleDao {

	Integer findRootCount(Article article);

	List<Article> findByPage(Article article, DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);

	void post(Article article);

	List<Article> findByPid(Article article, DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);


	void reply(Article article);

	void deleteTheme(List<Article> list);

	List<Article> findByAllPid(Article article);

	Integer findCount(DetachedCriteria detachedCriteria);

	Integer findPidCount(Article article);

	Article findBySid(Article article);

	void deleteSingle(Article article01);

}
