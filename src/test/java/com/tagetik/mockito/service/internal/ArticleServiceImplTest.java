package com.tagetik.mockito.service.internal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.tagetik.mockito.collaborator.Auditor;
import com.tagetik.mockito.collaborator.internal.ArticleDiscountCalculatorImpl;
import com.tagetik.mockito.dao.ArticleDao;
import com.tagetik.mockito.entity.Article;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ArticleServiceImplTest {

  private static final int AN_ID = 1;
  private static final String A_NAME = "anArticle";
  private static final double A_PRICE = 100.50d;
  private static final int ANOTHER_ID = 2;
  private static final String ANOTHER_NAME = "anotherName";
  private static final double ANOTHER_PRICE = 10.50d;
  private static final int A_THIRD_ID = 3;
  private static final String A_THIRD_NAME = "aThirdName";
  private static final double A_THIRD_PRICE = 9d;

  private ArticleServiceImpl sut;
  @Mock
  private ArticleDao articleDao;
  @Mock
  private Auditor auditor;

  @BeforeEach
  void setUp() {
    sut = new ArticleServiceImpl(articleDao, new ArticleDiscountCalculatorImpl(), auditor);
  }

  @Test
  void getPriceOfThreeArticles() {
    when(articleDao.findById(AN_ID)).thenReturn(new Article(AN_ID, A_NAME, A_PRICE));
    when(articleDao.findById(ANOTHER_ID)).thenReturn(new Article(ANOTHER_ID, ANOTHER_NAME, ANOTHER_PRICE));
    when(articleDao.findById(A_THIRD_ID)).thenReturn(new Article(A_THIRD_ID, A_THIRD_NAME, A_THIRD_PRICE));

    double result = sut.getPriceOf(Arrays.asList(AN_ID, ANOTHER_ID, A_THIRD_ID));

    verify(auditor, times(3)).audit(anyString());
    verify(auditor).audit("Read article 1 with name anArticle and price 100.5");
    verify(auditor).audit("Read article 2 with name anotherName and price 10.5");
    verify(auditor).audit("Read article 3 with name aThirdName and price 9.0");
    assertEquals((A_PRICE + ANOTHER_PRICE + A_THIRD_PRICE) / 2, result, "Unexpected price");
  }

  @Test
  void getPriceOfNoArticles() {
    double result = sut.getPriceOf(Collections.emptyList());
    verifyNoInteractions(articleDao);
    verifyNoInteractions(auditor);
    Assertions.assertEquals(0, result, "Expected total price zero");
  }

}