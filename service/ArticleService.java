package mark.fang.platform.service;

import org.hibernate.criterion.DetachedCriteria;

import mark.fang.platform.domain.Article;
import mark.fang.platform.domain.PageBean;
import java.util.*;

public interface ArticleService {

	PageBean<Article> findByPage(Article article, DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	void post(Article article);

	PageBean<Article> findByPid(Article article, DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	void reply(Article article);

	List<Article> findByAllPid(Article article);

	void deleteTheme(List<Article> list);

	Article findBySid(Article article);

	void deleteSingle(Article article01);


}
