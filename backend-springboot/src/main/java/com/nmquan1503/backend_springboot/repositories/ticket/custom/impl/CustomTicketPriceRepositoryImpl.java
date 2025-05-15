package com.nmquan1503.backend_springboot.repositories.ticket.custom.impl;

import com.nmquan1503.backend_springboot.entities.ticket.QTicketPrice;
import com.nmquan1503.backend_springboot.repositories.ticket.custom.CustomTicketPriceRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomTicketPriceRepositoryImpl implements CustomTicketPriceRepository {

    JPAQueryFactory queryFactory;

    @Override
    public Double findPriceByRoomTypeIdAndSeatTypeIdAndStartTime(Byte roomTypeId, Byte seatTypeId, LocalDateTime startTime) {
        QTicketPrice ticketPrice = QTicketPrice.ticketPrice;
        return queryFactory
                .select(ticketPrice.price)
                .from(ticketPrice)
                .where(ticketPrice.seatType.id.eq(seatTypeId)
                        .and(ticketPrice.roomType.id.eq(roomTypeId))
                        .and(ticketPrice.timeRangeStart.loe(startTime.toLocalTime()))
                        .and(ticketPrice.timeRangeEnd.goe(startTime.toLocalTime())))
                .fetchOne();
    }
}
