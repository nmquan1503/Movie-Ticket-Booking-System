package com.nmquan1503.backend_springboot.repositories.movie.custom.impl;

import com.nmquan1503.backend_springboot.entities.movie.Category;
import com.nmquan1503.backend_springboot.entities.movie.QMovieCategory;
import com.nmquan1503.backend_springboot.repositories.movie.custom.CustomMovieCategoryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomMovieCategoryRepositoryImpl implements CustomMovieCategoryRepository {

    JPAQueryFactory queryFactory;

    @Override
    public void deleteAllByMovieIdAndCategoryIdNotIn(Long movieId, List<Byte> categoryIds) {
        QMovieCategory movieCategory = QMovieCategory.movieCategory;

        queryFactory
                .delete(movieCategory)
                .where(movieCategory.movie.id.eq(movieId)
                        .and(movieCategory.category.id.notIn(categoryIds)))
                .execute();
    }
}
