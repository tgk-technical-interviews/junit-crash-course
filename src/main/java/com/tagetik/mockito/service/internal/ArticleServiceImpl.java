package com.tagetik.mockito.service.internal;

import com.tagetik.mockito.collaborator.ArticleDiscountCalculator;
import com.tagetik.mockito.collaborator.Auditor;
import com.tagetik.mockito.dao.ArticleDao;
import com.tagetik.mockito.entity.Article;
import com.tagetik.mockito.service.ArticleService;
import java.util.List;

public class ArticleServiceImpl implements ArticleService {

  public static final String TEMPLATE = "Read article %s with name %s and price %s";
  private final ArticleDao articleDao;
  private final ArticleDiscountCalculator articleDiscountCalculator;
  private final Auditor auditor;

  public ArticleServiceImpl(ArticleDao articleDao, ArticleDiscountCalculator articleDiscountCalculator, Auditor auditor) {
    this.articleDao = articleDao;
    this.articleDiscountCalculator = articleDiscountCalculator;
    this.auditor = auditor;
  }

  @Override
  public double getPriceOf(List<Integer> articleIds) {
    double total = 0;
    for (int i = 0; i < articleIds.size(); i++) {
      Integer articleId = articleIds.get(i);
      Article article = articleDao.findById(articleId);
      auditor.audit(String.format(TEMPLATE, article.getId(), article.getName(), article.getPrice()));
      double priceOfCurrentArticle = articleDiscountCalculator.applyDiscount(article);
      total += priceOfCurrentArticle;
    }
    return total;
  }

  //  private Article findById(Integer articleId){
  //    Article result = articleDao.findById(articleId);
  //    auditor.audit(String.format(TEMPLATE, result.getId(), result.getName(), result.getPrice()));
  //    return result;
  //  }
  //
  //    @Override
  //    public double getPriceOf(List<Integer> articleIds) {
  //      return articleIds.stream()
  //               .map(this::findById)
  //               .map(articleDiscountCalculator::applyDiscount)
  //               .reduce(Double::sum)
  //               .orElseGet(() -> 0d);
  //    }

}
