package com.nmquan1503.backend_springboot.repositories.movie.custom.impl;

import com.nmquan1503.backend_springboot.dtos.responses.movie.MovieListItemResponse;
import com.nmquan1503.backend_springboot.dtos.responses.movie.MoviePreviewResponse;
import com.nmquan1503.backend_springboot.entities.movie.*;
import com.nmquan1503.backend_springboot.entities.reservation.QReservation;
import com.nmquan1503.backend_springboot.entities.showtime.QShowtime;
import com.nmquan1503.backend_springboot.entities.ticket.QTicket;
import com.nmquan1503.backend_springboot.repositories.movie.custom.CustomMovieRepository;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomMovieRepositoryImpl implements CustomMovieRepository {

    JPAQueryFactory queryFactory;

    @Override
    public List<Movie> findTrendingMovies(int limit) {
        QMovie movie = QMovie.movie;
        QShowtime showtime = QShowtime.showtime;
        QMovieScore movieScore = QMovieScore.movieScore;
        List<Long> movieIds = queryFactory
                .selectDistinct(showtime.movie.id)
                .from(showtime)
                .where(showtime.startTime.between(LocalDateTime.now(), LocalDateTime.now().plusDays(14)))
                .fetch();
        return queryFactory
                .select(movie)
                .from(movie)
                .leftJoin(movieScore).on(movie.id.eq(movieScore.movie.id))
                .where(movie.id.in(movieIds))
                .orderBy(movieScore.finalScore.desc())
                .limit(limit)
                .fetch();
    }

    @Override
    public Page<Movie> findAllSortByFinalScore(Pageable pageable) {
        QMovie movie = QMovie.movie;
        QMovieScore score = QMovieScore.movieScore;

        List<Movie> movies = queryFactory
                .select(movie)
                .from(movie)
                .join(score).on(movie.id.eq(score.movie.id))
                .orderBy(score.finalScore.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(movie.count())
                .from(movie)
                .fetchOne();
        total = total == null ? 0 : total;

        return new PageImpl<>(movies, pageable, total);
    }

    @Override
    public Page<Movie> findNowShowingSortByFinalScore(Pageable pageable) {
        QMovie movie = QMovie.movie;
        QMovieScore movieScore = QMovieScore.movieScore;
        QShowtime showtime = QShowtime.showtime;

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneMonthAgo = now.minusMonths(1);
        LocalDateTime oneMonthLater = now.plusMonths(1);

        List<Long> movieIds = queryFactory
                .select(showtime.movie.id)
                .from(showtime)
                .where(showtime.startTime.between(oneMonthAgo, oneMonthLater))
                .groupBy(showtime.movie.id)
                .having(
                        Expressions.numberTemplate(Long.class,
                                "SUM(CASE WHEN {0} < {1} THEN 1 ELSE 0 END)", showtime.startTime, now
                        ).gt(0),
                        Expressions.numberTemplate(Long.class,
                                "SUM(CASE WHEN {0} > {1} THEN 1 ELSE 0 END)", showtime.startTime, now
                        ).gt(0)
                )
                .fetch();

        List<Movie> movies = queryFactory
                .select(movie)
                .from(movie)
                .join(movieScore).on(movie.id.eq(movieScore.movieId))
                .where(movie.id.in(movieIds))
                .orderBy(movieScore.finalScore.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(movies, pageable, movieIds.size());
    }

    @Override
    public Page<Movie> findUpComingSortByFinalScore(Pageable pageable) {
        QMovie movie = QMovie.movie;
        QMovieScore movieScore = QMovieScore.movieScore;
        QShowtime showtime = QShowtime.showtime;

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneMonthAgo = now.minusMonths(1);

        List<Long> movieIds = queryFactory
                .select(showtime.movie.id)
                .from(showtime)
                .where(showtime.startTime.goe(oneMonthAgo))
                .groupBy(showtime.movie.id)
                .having(
                        Expressions.numberTemplate(Long.class,
                                "SUM(CASE WHEN {0} > {1} THEN 1 ELSE 0 END)",
                                showtime.startTime, now
                        ).gt(0),

                        Expressions.numberTemplate(Long.class,
                                "SUM(CASE WHEN {0} BETWEEN {1} AND {2} THEN 1 ELSE 0 END)",
                                showtime.startTime, oneMonthAgo, now
                        ).eq(0L)
                )
                .fetch();

        List<Movie> movies = queryFactory
                .select(movie)
                .from(movie)
                .join(movieScore).on(movieScore.movie.id.eq(movie.id))
                .where(movie.id.in(movieIds))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(movieScore.finalScore.desc())
                .fetch();
        return new PageImpl<>(movies, pageable, movieIds.size());
    }

    @Override
    public List<Movie> findAllSortByFinalScore() {
        QMovie movie = QMovie.movie;
        QMovieScore score = QMovieScore.movieScore;
        return queryFactory
                .select(movie)
                .from(movie)
                .join(score).on(score.movie.id.eq(movie.id))
                .orderBy(score.finalScore.desc())
                .fetch();
    }

    @Override
    public List<Long> findIdsByListIds(List<Long> ids) {
        QMovie movie = QMovie.movie;
        return queryFactory
                .select(movie.id)
                .from(movie)
                .where(movie.id.in(ids))
                .fetch();
    }
}
