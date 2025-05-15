package com.nmquan1503.backend_springboot.repositories.showtime.custom.impl;

import com.nmquan1503.backend_springboot.entities.showtime.QShowtime;
import com.nmquan1503.backend_springboot.repositories.showtime.custom.CustomShowtimeRepository;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomShowtimeRepositoryImpl implements CustomShowtimeRepository {

    JPAQueryFactory queryFactory;

    @Override
    public boolean isScheduleConflict(Integer roomId, LocalDateTime startTime, Short duration) {
        QShowtime showtime = QShowtime.showtime;

        DateTimeExpression<LocalDateTime> endTime = Expressions.dateTimeTemplate(
                LocalDateTime.class,
                "TIMESTAMPADD(MINUTE, {0}, {1})",
                Expressions.constant(duration),
                Expressions.constant(startTime)
        );

        DateTimeExpression<LocalDateTime> endTimeExisting = Expressions.dateTimeTemplate(
                LocalDateTime.class,
                "TIMESTAMPADD(MINUTE, {0}, {1})",
                showtime.movie.duration,
                showtime.startTime
        );

        Long count = queryFactory
                .select(showtime.count())
                .from(showtime)
                .where(showtime.room.id.eq(roomId)
                        .and(endTimeExisting.goe(startTime))
                        .and(showtime.startTime.loe(endTime)))
                .fetchOne();

        return count != null && count > 0;
    }
}
