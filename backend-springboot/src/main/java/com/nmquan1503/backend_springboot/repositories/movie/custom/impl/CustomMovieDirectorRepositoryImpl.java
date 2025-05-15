package com.nmquan1503.backend_springboot.repositories.movie.custom.impl;

import com.nmquan1503.backend_springboot.entities.movie.QMovieDirector;
import com.nmquan1503.backend_springboot.repositories.movie.custom.CustomMovieDirectorRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomMovieDirectorRepositoryImpl implements CustomMovieDirectorRepository {

    JPAQueryFactory queryFactory;

    @Override
    public void deleteAllByMovieIdAndDirectorIdNotIn(Long movieId, List<Long> directorIds) {
        QMovieDirector movieDirector = QMovieDirector.movieDirector;

        queryFactory
                .delete(movieDirector)
                .where(movieDirector.movie.id.eq(movieId)
                        .and(movieDirector.director.id.notIn(directorIds)))
                .execute();
    }
}
