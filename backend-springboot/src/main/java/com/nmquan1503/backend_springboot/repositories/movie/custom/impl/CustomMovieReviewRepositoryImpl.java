package com.nmquan1503.backend_springboot.repositories.movie.custom.impl;

import com.nmquan1503.backend_springboot.dtos.internal.MovieReviewStats;
import com.nmquan1503.backend_springboot.entities.movie.QMovieReview;
import com.nmquan1503.backend_springboot.repositories.movie.custom.CustomMovieReviewRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomMovieReviewRepositoryImpl implements CustomMovieReviewRepository {

    JPAQueryFactory queryFactory;

    @Override
    public MovieReviewStats findMovieReviewStatsByMovieId(Long movieId) {

        QMovieReview movieReview = QMovieReview.movieReview;

        MovieReviewStats stats = queryFactory
                .select(
                        Projections.constructor(
                                MovieReviewStats.class,
                                movieReview.movie.id,
                                movieReview.rating.avg(),
                                movieReview.count()
                        )
                )
                .from(movieReview)
                .where(movieReview.movie.id.eq(movieId))
                .groupBy(movieReview.movie.id)
                .fetchOne();
        if (stats == null) {
            return new MovieReviewStats(movieId, 0.0, 0L);
        }
        return stats;
    }

    @Override
    public Map<Long, MovieReviewStats> findListMovieReviewsStatsByMovieIds(List<Long> movieIds) {

        QMovieReview movieReview = QMovieReview.movieReview;

        List<MovieReviewStats> list = queryFactory
                .select(
                        Projections.constructor(
                                MovieReviewStats.class,
                                movieReview.movie.id,
                                movieReview.rating.avg(),
                                movieReview.count()
                        )
                )
                .from(movieReview)
                .where(movieReview.movie.id.in(movieIds))
                .groupBy(movieReview.movie.id)
                .fetch();
        Map<Long, MovieReviewStats> map = new HashMap<>();
        for (MovieReviewStats item : list) {
            map.put(item.getMovieId(), item);
        }
        for (Long id: movieIds) {
            if (!map.containsKey(id)) {
                map.put(id, new MovieReviewStats(id, 0.0, 0L));
            }
        }
        return map;
    }
}
