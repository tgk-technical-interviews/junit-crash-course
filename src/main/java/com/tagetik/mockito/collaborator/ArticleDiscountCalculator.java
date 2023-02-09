package com.tagetik.mockito.collaborator;

import com.tagetik.mockito.entity.Article;

public interface ArticleDiscountCalculator {

  double applyDiscount(Article article);
}
