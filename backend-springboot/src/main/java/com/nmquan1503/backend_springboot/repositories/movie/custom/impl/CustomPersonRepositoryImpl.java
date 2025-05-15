package com.nmquan1503.backend_springboot.repositories.movie.custom.impl;

import com.nmquan1503.backend_springboot.entities.movie.*;
import com.nmquan1503.backend_springboot.repositories.movie.custom.CustomPersonRepository;
import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomPersonRepositoryImpl implements CustomPersonRepository {

    JPAQueryFactory queryFactory;

    @Override
    public List<Person> findActorsByMovieId(Long movieId) {
        QMovieActor movieActor = QMovieActor.movieActor;
        return queryFactory
                .select(movieActor.actor)
                .from(movieActor)
                .where(movieActor.movie.id.eq(movieId))
                .fetch();
    }

    @Override
    public List<Person> findDirectorsByMovieId(Long movieId) {
        QMovieDirector movieDirector = QMovieDirector.movieDirector;
        return queryFactory
                .select(movieDirector.director)
                .from(movieDirector)
                .where(movieDirector.movie.id.eq(movieId))
                .fetch();
    }
}
