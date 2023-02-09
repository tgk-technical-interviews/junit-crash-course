package com.tagetik.mockito.collaborator.internal;

import com.tagetik.mockito.collaborator.ArticleDiscountCalculator;
import com.tagetik.mockito.entity.Article;

public class ArticleDiscountCalculatorImpl implements ArticleDiscountCalculator {

  public static final double DISCOUNT = 0.5;

  @Override
  public double applyDiscount(Article article) {
    return article.getPrice() * DISCOUNT;
  }
}
