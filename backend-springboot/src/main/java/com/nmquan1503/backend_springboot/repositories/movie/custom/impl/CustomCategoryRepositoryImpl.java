package com.nmquan1503.backend_springboot.repositories.movie.custom.impl;

import com.nmquan1503.backend_springboot.entities.movie.Category;
import com.nmquan1503.backend_springboot.entities.movie.QMovieCategory;
import com.nmquan1503.backend_springboot.repositories.movie.custom.CustomCategoryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomCategoryRepositoryImpl implements CustomCategoryRepository {

    JPAQueryFactory queryFactory;

    @Override
    public List<Category> findByMovieId(Long movieId) {
        QMovieCategory movieCategory = QMovieCategory.movieCategory;
        return queryFactory
                .select(movieCategory.category)
                .from(movieCategory)
                .where(movieCategory.movie.id.eq(movieId))
                .fetch();
    }
}
