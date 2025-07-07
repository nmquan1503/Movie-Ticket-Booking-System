package com.nmquan1503.backend_springboot.services.movie;

import com.nmquan1503.backend_springboot.entities.movie.MovieScore;
import com.nmquan1503.backend_springboot.entities.movie.MovieReview;
import com.nmquan1503.backend_springboot.entities.movie.MovieViewHistory;
import com.nmquan1503.backend_springboot.entities.showtime.Showtime;
import com.nmquan1503.backend_springboot.entities.ticket.Ticket;
import com.nmquan1503.backend_springboot.repositories.movie.MovieScoreRepository;
import com.nmquan1503.backend_springboot.services.showtime.ShowtimeService;
import com.nmquan1503.backend_springboot.services.ticket.TicketService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MovieScoreService {

    MovieScoreRepository movieScoreRepository;

    MovieReviewService movieReviewService;
    MovieViewHistoryService movieViewHistoryService;
    TicketService ticketService;
    ShowtimeService showtimeService;

    double LAMBDA_RATING = 0.02;
    double LAMBDA_VIEW = 0.04;
    double LAMBDA_TICKET = 0.04;
    double LAMBDA_SHOWTIME = 0.1;

    double W_RATING = 0.15;
    double W_VIEW = 0.05;
    double W_TICKET = 0.2;
    double W_SHOWTIME = 0.6;


//    @Scheduled(cron = "0 0 0 * * *")
    public void updateScore() {
        List<MovieScore> scores = movieScoreRepository.findAll();
        double minRatingScore = 0;
        double maxRatingScore = 0;
        double minViewScore = 0;
        double maxViewScore = 0;
        double minTicketScore = 0;
        double maxTicketScore = 0;
        double minShowtimeScore = 0;
        double maxShowtimeScore = 0;
        for (MovieScore score : scores) {
            Long movieId = score.getMovie().getId();
            LocalDateTime now = LocalDateTime.now();
            if (score.getLastUpdated() == null) {
                score.setRatingScore(computeRatingScore(movieId, now));
                score.setViewScore(computeViewScore(movieId, now));
                score.setTicketScore(computeTicketScore(movieId, now));
                score.setShowtimeScore(computeShowtimeScore(movieId, now));
            }
            else {
                updateRatingScore(score, now);
                updateViewScore(score, now);
                updateTicketScore(score, now);
                updateShowtimeScore(score, now);
            }
            score.setLastUpdated(now);

            minRatingScore = Math.min(minRatingScore, score.getRatingScore());
            maxRatingScore = Math.max(maxRatingScore, score.getRatingScore());

            minViewScore = Math.min(minViewScore, score.getViewScore());
            maxViewScore = Math.max(maxViewScore, score.getViewScore());

            minTicketScore = Math.min(minTicketScore, score.getTicketScore());
            maxTicketScore = Math.max(maxTicketScore, score.getTicketScore());

            minShowtimeScore = Math.min(minShowtimeScore, score.getShowtimeScore());
            maxShowtimeScore = Math.max(maxShowtimeScore, score.getShowtimeScore());
        }

        for (MovieScore score : scores) {
            double normalizedRatingScore = maxRatingScore != minRatingScore
                    ? (score.getRatingScore() - minRatingScore) / (maxRatingScore - minRatingScore)
                    : 0;
            double normalizedViewScore = maxViewScore != minViewScore
                    ? (score.getViewScore() - minViewScore) / (maxViewScore - minViewScore)
                    : 0;
            double normalizedTicketScore = maxTicketScore != minTicketScore
                    ? (score.getTicketScore() - minTicketScore) / (maxTicketScore - minTicketScore)
                    : 0;
            double normalizedShowtimeScore = maxShowtimeScore != minShowtimeScore
                    ? (score.getShowtimeScore() - minShowtimeScore) / (maxShowtimeScore - minShowtimeScore)
                    : 0;

            double finalScore =
                    W_RATING * normalizedRatingScore +
                    W_VIEW * normalizedViewScore +
                    W_TICKET * normalizedTicketScore +
                    W_SHOWTIME * normalizedShowtimeScore;
            score.setFinalScore(finalScore);
        }
        movieScoreRepository.saveAll(scores);
    }

    public void updateScore(LocalDateTime time) {
        List<MovieScore> scores = movieScoreRepository.findAll();
        double minRatingScore = 0;
        double maxRatingScore = 0;
        double minViewScore = 0;
        double maxViewScore = 0;
        double minTicketScore = 0;
        double maxTicketScore = 0;
        double minShowtimeScore = 0;
        double maxShowtimeScore = 0;
        for (MovieScore score : scores) {
            Long movieId = score.getMovie().getId();
            LocalDateTime now = time;
            if (score.getLastUpdated() == null) {
                score.setRatingScore(computeRatingScore(movieId, now));
                score.setViewScore(computeViewScore(movieId, now));
                score.setTicketScore(computeTicketScore(movieId, now));
                score.setShowtimeScore(computeShowtimeScore(movieId, now));
            }
            else {
                updateRatingScore(score, now);
                updateViewScore(score, now);
                updateTicketScore(score, now);
                updateShowtimeScore(score, now);
            }
            score.setLastUpdated(now);

            minRatingScore = Math.min(minRatingScore, score.getRatingScore());
            maxRatingScore = Math.max(maxRatingScore, score.getRatingScore());

            minViewScore = Math.min(minViewScore, score.getViewScore());
            maxViewScore = Math.max(maxViewScore, score.getViewScore());

            minTicketScore = Math.min(minTicketScore, score.getTicketScore());
            maxTicketScore = Math.max(maxTicketScore, score.getTicketScore());

            minShowtimeScore = Math.min(minShowtimeScore, score.getShowtimeScore());
            maxShowtimeScore = Math.max(maxShowtimeScore, score.getShowtimeScore());
        }

        for (MovieScore score : scores) {
            double normalizedRatingScore = maxRatingScore != minRatingScore
                    ? (score.getRatingScore() - minRatingScore) / (maxRatingScore - minRatingScore)
                    : 0;
            double normalizedViewScore = maxViewScore != minViewScore
                    ? (score.getViewScore() - minViewScore) / (maxViewScore - minViewScore)
                    : 0;
            double normalizedTicketScore = maxTicketScore != minTicketScore
                    ? (score.getTicketScore() - minTicketScore) / (maxTicketScore - minTicketScore)
                    : 0;
            double normalizedShowtimeScore = maxShowtimeScore != minShowtimeScore
                    ? (score.getShowtimeScore() - minShowtimeScore) / (maxShowtimeScore - minShowtimeScore)
                    : 0;

            double finalScore =
                    W_RATING * normalizedRatingScore +
                            W_VIEW * normalizedViewScore +
                            W_TICKET * normalizedTicketScore +
                            W_SHOWTIME * normalizedShowtimeScore;
            score.setFinalScore(finalScore);
        }
        movieScoreRepository.saveAll(scores);
    }

    private double computeTimeOffset(LocalDateTime from, LocalDateTime to) {
        return ChronoUnit.SECONDS.between(from, to) / 86400.0;
    }

    private double computeRatingScore(Long movieId, LocalDateTime evaluatedTime) {
        List<MovieReview> reviews = movieReviewService.fetchByMovieId(movieId);
        if (reviews.isEmpty()) {
            return  0;
        }
        double score = 0;
        for (MovieReview review : reviews) {
            double timeOffsetSinceReview = computeTimeOffset(review.getCreationTime(), evaluatedTime);
            score += Math.exp(- LAMBDA_RATING * timeOffsetSinceReview) * review.getRating();
        }
        return score;
    }

    private void updateRatingScore(MovieScore score, LocalDateTime evaluatedTime) {
        double timeOffsetSinceLastUpdated = computeTimeOffset(score.getLastUpdated(), evaluatedTime);
        double newRatingScore = score.getRatingScore() * Math.exp(- LAMBDA_RATING * timeOffsetSinceLastUpdated);
        List<MovieReview> reviews = movieReviewService.fetchMovieReviewWithinPeriod(
                score.getMovie().getId(),
                score.getLastUpdated(),
                evaluatedTime
        );
        for (MovieReview review : reviews) {
            double timeOffsetSinceReview = computeTimeOffset(review.getCreationTime(), evaluatedTime);
            newRatingScore += Math.exp(- LAMBDA_RATING * timeOffsetSinceReview) * review.getRating();
        }
        score.setRatingScore(newRatingScore);
    }

    private double computeViewScore(Long movieId, LocalDateTime evaluatedTime) {
        List<MovieViewHistory> views = movieViewHistoryService.fetchByMovieId(movieId);
        if (views.isEmpty()) {
            return 0;
        }
        double score = 0;
        for (MovieViewHistory view : views) {
            double timeOffsetSinceView = computeTimeOffset(view.getStartTime(), evaluatedTime);
            score += Math.exp(- LAMBDA_VIEW * timeOffsetSinceView);
        }
        return score;
    }

    private void updateViewScore(MovieScore score, LocalDateTime evaluatedTime) {
        double timeOffsetSinceLastUpdated = computeTimeOffset(score.getLastUpdated(), evaluatedTime);
        double newViewScore = score.getViewScore() * Math.exp(- LAMBDA_VIEW * timeOffsetSinceLastUpdated);
        List<MovieViewHistory> views = movieViewHistoryService.fetchMovieViewHistoryWithinPeriod(
                score.getMovie().getId(),
                score.getLastUpdated(),
                evaluatedTime
        );
        for (MovieViewHistory view : views) {
            double timeOffsetSinceView = computeTimeOffset(view.getStartTime(), evaluatedTime);
            newViewScore += Math.exp(- LAMBDA_VIEW * timeOffsetSinceView);
        }
        score.setViewScore(newViewScore);
    }

    private double computeTicketScore(Long movieId, LocalDateTime evaluatedTime) {
        List<Ticket> tickets = ticketService.fetchByMovieId(movieId);
        if (tickets.isEmpty()) {
            return 0;
        }
        double score = 0;
        for (Ticket ticket : tickets) {
            double timeOffsetSinceBook = computeTimeOffset(ticket.getReservation().getStartTime(), evaluatedTime);
            score += Math.exp(- LAMBDA_TICKET * timeOffsetSinceBook);
        }
        return score;
    }

    private void updateTicketScore(MovieScore score, LocalDateTime evaluatedTime) {
        double timeOffsetSinceLastUpdated = computeTimeOffset(score.getLastUpdated(), evaluatedTime);
        double newTicketScore = score.getTicketScore() * Math.exp(- LAMBDA_TICKET * timeOffsetSinceLastUpdated);
        List<Ticket> tickets = ticketService.fetchByMovieIdAndTimeWithinPeriod(
                score.getMovie().getId(),
                score.getLastUpdated(),
                evaluatedTime
        );
        for (Ticket ticket : tickets) {
            double timeOffsetSinceBook = computeTimeOffset(ticket.getReservation().getStartTime(), evaluatedTime);
            newTicketScore += Math.exp(- LAMBDA_TICKET * timeOffsetSinceBook);
        }
        score.setTicketScore(newTicketScore);
    }

    private double computeShowtimeScore(Long movieId, LocalDateTime evaluatedTime) {
        List<Showtime> showtimes = showtimeService.fetchByMovieIdAndStartTimeGreaterThanEqual(movieId, evaluatedTime);
        if (showtimes.isEmpty()) {
            return 0;
        }
        double score = 0;
        for (Showtime showtime : showtimes) {
            double timeOffsetSinceStart = computeTimeOffset(showtime.getStartTime(), evaluatedTime);
            score += Math.exp(LAMBDA_SHOWTIME * timeOffsetSinceStart);
        }
        return score;
    }

    private void updateShowtimeScore(MovieScore score, LocalDateTime evaluatedTime) {
        double timeOffsetSinceLastUpdated = computeTimeOffset(score.getLastUpdated(), evaluatedTime);
        double newShowtimeScore = score.getShowtimeScore() * Math.exp(LAMBDA_SHOWTIME * timeOffsetSinceLastUpdated);
        List<Showtime> showtimes = showtimeService.fetchByMovieIdAndStartTimeWithinPeriod(
                score.getMovie().getId(),
                score.getLastUpdated(),
                evaluatedTime
        );
        for (Showtime showtime : showtimes) {
            double timeOffsetSinceStart = computeTimeOffset(showtime.getStartTime(), evaluatedTime);
            newShowtimeScore -= Math.exp(LAMBDA_SHOWTIME * timeOffsetSinceStart);
        }
        score.setShowtimeScore(newShowtimeScore);
    }

}
