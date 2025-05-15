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
import org.springframework.data.domain.Page;
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
    public List<MoviePreviewResponse> getTrendingMovieSummaries(int number) {
//        QShowtime showtime = QShowtime.showtime;
//        QReservation reservation = QReservation.reservation;
//        QTicket ticket = QTicket.ticket;
//        NumberExpression<Double> averageRatingScore = movie.averageRating;
//        NumberExpression<Double> totalTicketsBookedScore = ticket.count().castToNum(Double.class);
//        NumberExpression<Double> daysSinceReleaseScore = Expressions.numberTemplate(
//                Long.class,
//                "DATEDIFF(CURRENT_DATE, {0})",
//                movie.releasedDate
//        ).castToNum(Double.class);
//
//        NumberExpression<Double> hotScore = averageRatingScore.multiply(1)
//                .add(totalTicketsBookedScore.multiply(1))
//                .add(daysSinceReleaseScore.multiply(-2));
//
//        return queryFactory
//                .select(Projections.constructor(
//                        MovieSummaryResponse.class,
//                        movie.id,
//                        movie.title,
//                        movie.posterURL,
//                        Projections.constructor(
//                                AgeRatingSummaryResponse.class,
//                                movie.ageRating.id,
//                                movie.ageRating.code
//                        )
//                ))
//                .from(movie)
//                .leftJoin(movie.showtimes, showtime)
//                .leftJoin(showtime.reservations, reservation)
//                .leftJoin(reservation.tickets, ticket)
////                .orderBy(hotScore.desc())
//                .limit(number)
//                .fetch();
        return null;
    }

    @Override
    public List<MoviePreviewResponse> getNowShowingMovieSummaries() {
//        QShowtime pastShowtime = new QShowtime("past");
//        QShowtime futureShowtime = new QShowtime("future");
//        LocalDateTime now = LocalDateTime.now();
//        return queryFactory
//                .selectDistinct(Projections.constructor(
//                        MovieSummaryResponse.class,
//                        movie.id,
//                        movie.title,
//                        movie.thumbnailURL,
//                        movie.rating
//                ))
//                .from(movie)
//                .where(
//                        JPAExpressions.selectOne()
//                                .from(pastShowtime)
//                                .where(pastShowtime.movie.eq(movie)
//                                        .and(pastShowtime.startTime.loe(now)))
//                                .exists(),
//                        JPAExpressions.selectOne()
//                                .from(futureShowtime)
//                                .where(futureShowtime.movie.eq(movie)
//                                        .and(futureShowtime.startTime.after(now)))
//                                .exists()
//                )
//                .fetch();
        return null;
    }

    @Override
    public List<MoviePreviewResponse> getComingSoonMovieSummaries() {
//        QShowtime showtime = QShowtime.showtime;
//        LocalDateTime now = LocalDateTime.now();
//        return queryFactory
//                .selectDistinct(Projections.constructor(
//                        MovieSummaryResponse.class,
//                        movie.id,
//                        movie.title,
//                        movie.thumbnailURL,
//                        movie.rating
//                ))
//                .from(movie)
//                .join(movie.showtimes, showtime)
//                .where(showtime.startTime.after(now))
//                .fetch();
        return null;
    }

    @Override
    public Page<MovieListItemResponse> findMovieCatalogs(Pageable pageable) {
//        QCategory category = QCategory.category;
//        QMovieCategory movieCategory = QMovieCategory.movieCategory;
//        QAgeRating ageRating = QAgeRating.ageRating;
//        List<MovieCatalogResponse> content = queryFactory
//                .select(Projections.constructor(
//                        MovieCatalogResponse.class,
//                        movie.id,
//                        movie.title,
//                        movie.posterURL,
//                        movie.releasedDate,
//                        movie.duration,
//                        movie.averageRating,
//                        movie.ratingCount,
//                        Projections.list(
//                                Projections.constructor(
//                                        CategorySummaryResponse.class,
//                                        category.id,
//                                        category.name
//                                )
//                        ),
//                        Projections.constructor(
//                                AgeRatingSummaryResponse.class,
//                                movie.ageRating.id,
//                                movie.ageRating.code
//                        )
//                ))
//                .from(movie)
//                .leftJoin(movie.movieCategories,movieCategory)
//                .leftJoin(movieCategory.category, category)
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//
//
//
//
//
//
//        Long total = queryFactory
//                .select(movie.count())
//                .from(movie)
//                .fetchOne();
//        if (total == null) total = 0L;
//
//        return new PageImpl<>(content, pageable, total);
        return null;
    }

    @Override
    public List<Movie> findTrendingMovies(int limit) {
        QMovie movie = QMovie.movie;
        QShowtime showtime = QShowtime.showtime;
        QReservation reservation = QReservation.reservation;
        QTicket ticket = QTicket.ticket;

        QMovie movieSub = new QMovie("movieSub");
        QShowtime showtimeSub = new QShowtime("showtimeSub");

        NumberExpression<Long> ticketCount = ticket.count();
        NumberExpression<Long> daysSinceRelease = Expressions.numberTemplate(
                Long.class,
                "DATEDIFF(CURDATE(), {0})",
                movie.releasedDate
        );
        NumberExpression<Double> score = movie.averageRating.multiply(2.0)
                .add(ticketCount.multiply(0.01))
                .add(daysSinceRelease.multiply(-0.1));

        return queryFactory
                .select(movie)
                .from(movie)
                .join(movie, showtime.movie)
                .leftJoin(showtime, reservation.showtime)
                .leftJoin(reservation, ticket.reservation)
                .where(movie.id.in(
                        JPAExpressions
                                .selectDistinct(movieSub.id)
                                .from(movieSub)
                                .join(movieSub, showtimeSub.movie)
                                .where(
                                        showtimeSub.startTime.between(
                                                LocalDateTime.now(),
                                                LocalDateTime.now().plusDays(14)
                                        )
                                )
                ))
                .groupBy(movie.id)
                .orderBy(score.desc())
                .limit(limit)
                .fetch();
    }
}
