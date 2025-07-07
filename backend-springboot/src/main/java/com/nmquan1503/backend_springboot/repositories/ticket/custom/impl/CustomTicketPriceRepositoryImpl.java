package com.nmquan1503.backend_springboot.repositories.ticket.custom.impl;

import com.nmquan1503.backend_springboot.entities.ticket.QTicketPrice;
import com.nmquan1503.backend_springboot.repositories.ticket.custom.CustomTicketPriceRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomTicketPriceRepositoryImpl implements CustomTicketPriceRepository {

    JPAQueryFactory queryFactory;

    @Override
    public double findCurrentTicketPrice() {
        QTicketPrice ticketPrice = QTicketPrice.ticketPrice;
        LocalDate today = LocalDate.now();
        byte dayOfWeek = (byte)(today.getDayOfWeek().getValue() % 7);
        Double price = queryFactory
                .select(ticketPrice.price)
                .from(ticketPrice)
                .where(ticketPrice.dayOfWeek.eq(dayOfWeek)
                        .and(ticketPrice.timeRangeStart.loe(LocalTime.now()))
                        .and(ticketPrice.timeRangeEnd.goe(LocalTime.now())))
                .fetchOne();
        return price == null ? 0 : price;
    }
}
