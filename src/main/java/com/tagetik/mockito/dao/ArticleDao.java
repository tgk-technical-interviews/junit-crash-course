package com.tagetik.mockito.dao;

import com.tagetik.mockito.entity.Article;

public interface ArticleDao {

  Article findById(Integer id);

}
