package com.nmquan1503.backend_springboot.repositories.movie.custom.impl;

import com.nmquan1503.backend_springboot.entities.movie.QMovieActor;
import com.nmquan1503.backend_springboot.repositories.movie.custom.CustomMovieActorRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomMovieActorRepositoryImpl implements CustomMovieActorRepository {

    JPAQueryFactory queryFactory;

    @Override
    public void deleteAllByMovieIdAndActorIdNotIn(Long movieId, List<Long> actorIds) {
        QMovieActor movieActor = QMovieActor.movieActor;

        queryFactory
                .delete(movieActor)
                .where(movieActor.movie.id.eq(movieId)
                        .and(movieActor.actor.id.notIn(actorIds)))
                .execute();
    }
}
