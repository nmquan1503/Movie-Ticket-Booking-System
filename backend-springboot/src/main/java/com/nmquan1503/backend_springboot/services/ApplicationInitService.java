package com.nmquan1503.backend_springboot.services;

import com.nmquan1503.backend_springboot.dtos.internal.MovieReviewStats;
import com.nmquan1503.backend_springboot.entities.movie.Movie;
import com.nmquan1503.backend_springboot.entities.movie.MovieCategory;
import com.nmquan1503.backend_springboot.entities.movie.MovieReview;
import com.nmquan1503.backend_springboot.entities.movie.MovieViewHistory;
import com.nmquan1503.backend_springboot.entities.payment.PaymentMethod;
import com.nmquan1503.backend_springboot.entities.payment.PaymentTransaction;
import com.nmquan1503.backend_springboot.entities.payment.PaymentTransactionStatus;
import com.nmquan1503.backend_springboot.entities.reservation.Reservation;
import com.nmquan1503.backend_springboot.entities.reservation.ReservationSeat;
import com.nmquan1503.backend_springboot.entities.reservation.ReservationStatus;
import com.nmquan1503.backend_springboot.entities.showtime.Showtime;
import com.nmquan1503.backend_springboot.entities.theater.Seat;
import com.nmquan1503.backend_springboot.entities.ticket.Ticket;
import com.nmquan1503.backend_springboot.entities.ticket.TicketStatus;
import com.nmquan1503.backend_springboot.entities.user.User;
import com.nmquan1503.backend_springboot.repositories.movie.MovieCategoryRepository;
import com.nmquan1503.backend_springboot.repositories.movie.MovieRepository;
import com.nmquan1503.backend_springboot.repositories.movie.MovieReviewRepository;
import com.nmquan1503.backend_springboot.repositories.movie.MovieViewHistoryRepository;
import com.nmquan1503.backend_springboot.repositories.payment.PaymentTransactionRepository;
import com.nmquan1503.backend_springboot.repositories.reservation.ReservationRepository;
import com.nmquan1503.backend_springboot.repositories.reservation.ReservationSeatRepository;
import com.nmquan1503.backend_springboot.repositories.showtime.ShowtimeRepository;
import com.nmquan1503.backend_springboot.repositories.theater.SeatRepository;
import com.nmquan1503.backend_springboot.repositories.ticket.TicketRepository;
import com.nmquan1503.backend_springboot.repositories.user.UserRepository;
import com.nmquan1503.backend_springboot.services.movie.MovieReviewService;
import com.nmquan1503.backend_springboot.services.movie.MovieScoreService;
import com.nmquan1503.backend_springboot.utils.VnPayUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitService {

    PasswordEncoder passwordEncoder;
    MovieRepository movieRepository;
    MovieCategoryRepository movieCategoryRepository;
    UserRepository userRepository;
    MovieScoreService movieScoreService;
    MovieViewHistoryRepository movieViewHistoryRepository;
    ShowtimeRepository showtimeRepository;
    SeatRepository seatRepository;
    ReservationRepository reservationRepository;
    ReservationSeatRepository reservationSeatRepository;
    PaymentTransactionRepository paymentTransactionRepository;
    MovieReviewRepository movieReviewRepository;
    TicketRepository ticketRepository;
    MovieReviewService movieReviewService;


    @Transactional
    public void init() {
        Random random = new Random();
        List<User> users = userRepository.findAll();
        List<List<Integer>> ageCategoryList = Arrays.asList(
                Arrays.asList(2, 3, 4, 5, 6, 14),
                Arrays.asList(3, 4, 5, 6, 7, 9, 10, 14),
                Arrays.asList(6, 7, 8, 9, 11, 12, 14, 18),
                Arrays.asList(1, 7, 12, 15, 18, 19)
        );
        Map<Long, List<Integer>> favoriteCategory = new HashMap<>();
        Map<Long, List<Byte>> movieCategoryMap = createCategoryMap();
        int numberUser = 10000;
        LocalDateTime now = LocalDateTime.now();
        for (User user : users) {
            LocalDateTime day = LocalDateTime.now().plusDays(-370);
//                User user = User.builder()
//                        .id((long)i + 1)
//                        .fullName("Nguyen Van A")
//                        .phone(String.format("+84%09d", i))
//                        .email("test" + i + "@gmail.com")
//                        .password(passwordEncoder.encode("123456789"))
//                        .birthday(day.plusYears(-random.nextInt(15, 60)).plusDays(random.nextInt(365)).plusSeconds(random.nextInt(0, 100000)).toLocalDate())
//                        .gender(Gender.builder().id((byte)random.nextInt(1, 3)).build())
//                        .ward(Ward.builder().id(random.nextInt(1, 892)).build())
//                        .creationTime(day)
//                        .build();
//                user = userRepository.save(user);
//                users.add(user);
            double age = ChronoUnit.SECONDS.between(user.getBirthday().atStartOfDay(), now) / 86400.0;
            List<Integer> fav = new LinkedList<>();
            if (age <= 17) {
                for (int j = 0 ; j < 2; j++) {
                    int cateID = ageCategoryList.get(0).get(random.nextInt(ageCategoryList.get(0).size()));
                    if (fav.contains(cateID)) {
                        j--;
                        continue;
                    }
                    fav.add(cateID);
                }
            }
            else if (age <= 25) {
                for (int j = 0 ; j < 2; j++) {
                    int cateID = ageCategoryList.get(1).get(random.nextInt(ageCategoryList.get(1).size()));
                    if (fav.contains(cateID)) {
                        j--;
                        continue;
                    }
                    fav.add(cateID);
                }
            }
            else if (age <= 45) {
                for (int j = 0 ; j < 2; j++) {
                    int cateID = ageCategoryList.get(2).get(random.nextInt(ageCategoryList.get(2).size()));
                    if (fav.contains(cateID)) {
                        j--;
                        continue;
                    }
                    fav.add(cateID);
                }
            }
            else {
                for (int j = 0 ; j < 2; j++) {
                    int cateID = ageCategoryList.get(3).get(random.nextInt(ageCategoryList.get(3).size()));
                    if (fav.contains(cateID)) {
                        j--;
                        continue;
                    }
                    fav.add(cateID);
                }
            }
            favoriteCategory.put(
                    user.getId(),
                    fav
            );
        }
        Map<Long, Showtime> rateSchedule = new HashMap<>();
        for (User user : users) {
            rateSchedule.put(user.getId(), null);
        }
        for (LocalDateTime time = now.plusDays(-370).withHour(0).withMinute(0).withSecond(0).withNano(0); time.isBefore(now); time = time.plusDays(1)) {
            LocalDateTime start = LocalDateTime.now();
            movieScoreService.updateScore(time);
            long sec = ChronoUnit.SECONDS.between(start, LocalDateTime.now());
            try {
                FileWriter writer = new FileWriter("output.txt", true);
                writer.write("update time:" +sec +"\n");
            }
            catch (IOException e) {

            }
            List<Movie> movies = movieRepository.findAllSortByFinalScore();
            for (User user : users) {
                if (rateSchedule.get(user.getId()) != null) {
                    Showtime showtime = rateSchedule.get(user.getId());
                    if (!time.toLocalDate().equals(showtime.getStartTime().plusMinutes(showtime.getMovie().getDuration()).toLocalDate())) {
                        continue;
                    }
                    MovieReviewStats stats = movieReviewService.getStatsByMovieId(showtime.getMovie().getId());
                    List<Byte> cateIds = movieCategoryMap.get(showtime.getMovie().getId());
                    if (contain(cateIds, favoriteCategory.get(user.getId()))) {
                        if (stats.getCountRating() == 0) {
                            int rating = random.nextInt(3, 6);
                            String comment = getReviews().get(rating).get(random.nextInt(getReviews().get(rating).size()));
                            MovieReview movieReview = MovieReview.builder()
                                    .user(user)
                                    .movie(showtime.getMovie())
                                    .rating((byte)rating)
                                    .comment(comment)
                                    .creationTime(showtime.getStartTime().plusMinutes(showtime.getMovie().getDuration() + 30))
                                    .build();
                            movieReviewRepository.save(movieReview);
                            rateSchedule.put(user.getId(), null);
                        }
                        else if (stats.getAverageRating() < 3) {
                            int rating = random.nextInt(3, 5);
                            String comment = getReviews().get(rating).get(random.nextInt(getReviews().get(rating).size()));
                            MovieReview movieReview = MovieReview.builder()
                                    .user(user)
                                    .movie(showtime.getMovie())
                                    .rating((byte)rating)
                                    .comment(comment)
                                    .creationTime(showtime.getStartTime().plusMinutes(showtime.getMovie().getDuration() + 30))
                                    .build();
                            movieReviewRepository.save(movieReview);
                            rateSchedule.put(user.getId(), null);
                        }
                        else {
                            int rating = random.nextInt(4, 6);
                            String comment = getReviews().get(rating).get(random.nextInt(getReviews().get(rating).size()));
                            MovieReview movieReview = MovieReview.builder()
                                    .user(user)
                                    .movie(showtime.getMovie())
                                    .rating((byte)rating)
                                    .comment(comment)
                                    .creationTime(showtime.getStartTime().plusMinutes(showtime.getMovie().getDuration() + 30))
                                    .build();
                            movieReviewRepository.save(movieReview);
                            rateSchedule.put(user.getId(), null);
                        }
                    }
                    else {
                        if (stats.getCountRating() == 0) {
                            int rating = random.nextInt(1, 6);
                            String comment = getReviews().get(rating).get(random.nextInt(getReviews().get(rating).size()));
                            MovieReview movieReview = MovieReview.builder()
                                    .user(user)
                                    .movie(showtime.getMovie())
                                    .rating((byte)rating)
                                    .comment(comment)
                                    .creationTime(showtime.getStartTime().plusMinutes(showtime.getMovie().getDuration() + 30))
                                    .build();
                            movieReviewRepository.save(movieReview);
                            rateSchedule.put(user.getId(), null);
                        }
                        else if (stats.getAverageRating() < 3) {
                            int rating = random.nextInt(1, 4);
                            String comment = getReviews().get(rating).get(random.nextInt(getReviews().get(rating).size()));
                            MovieReview movieReview = MovieReview.builder()
                                    .user(user)
                                    .movie(showtime.getMovie())
                                    .rating((byte)rating)
                                    .comment(comment)
                                    .creationTime(showtime.getStartTime().plusMinutes(showtime.getMovie().getDuration() + 30))
                                    .build();
                            movieReviewRepository.save(movieReview);
                            rateSchedule.put(user.getId(), null);
                        }
                        else {
                            int rating = random.nextInt(3, 6);
                            String comment = getReviews().get(rating).get(random.nextInt(getReviews().get(rating).size()));
                            MovieReview movieReview = MovieReview.builder()
                                    .user(user)
                                    .movie(showtime.getMovie())
                                    .rating((byte)rating)
                                    .comment(comment)
                                    .creationTime(showtime.getStartTime().plusMinutes(showtime.getMovie().getDuration() + 30))
                                    .build();
                            movieReviewRepository.save(movieReview);
                            rateSchedule.put(user.getId(), null);
                        }
                    }
                    if (showtime.getStartTime().plusMinutes(showtime.getMovie().getDuration() + 30).isBefore(time.plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0))) {
                        time = showtime.getStartTime().plusMinutes(showtime.getMovie().getDuration() + 30);
                    }
                    else {
                        continue;
                    }
                }
                List<Integer> fav = favoriteCategory.get(user.getId());
                int countView = random.nextInt(10);
                for (int i = 0; i < countView; i++) {
                    int rateStrange = random.nextInt(100);
                    if (rateStrange < 5) {
                        for (Movie movie : movies) {
                            if (contain(movieCategoryMap.get(movie.getId()), fav)) {
                                continue;
                            }
                            int rateView = random.nextInt(100);
                            if (rateView < 40) {
                                MovieViewHistory view = MovieViewHistory.builder()
                                        .user(User.builder().id(user.getId()).build())
                                        .movie(movie)
                                        .startTime(time)
                                        .build();
                                movieViewHistoryRepository.save(view);
                                LocalDateTime nextTime = time.plusSeconds(random.nextInt(10000));
                                if (nextTime.isBefore(time.plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0))) {
                                    time = nextTime;
                                }
                                List<Showtime> showtimes = showtimeRepository.findByMovieIdAndStartTimeGreaterThanEqualAndStartTimeLessThan(
                                        movie.getId(),
                                        time.plusHours(5),
                                        time.plusDays(10)
                                );
                                for (Showtime showtime : showtimes) {
                                    if (showtime.getRoom().getBranch().getWard().getDistrict().equals(user.getWard().getDistrict())) {
                                        int rateBook = random.nextInt(100);
                                        if (rateBook < 10) {
                                            List<Seat> seats = seatRepository.findByShowtimeId(showtime.getId());
                                            List<Long> locked = seatRepository.findLockedSeatIdsByShowtimeId(showtime.getId());
                                            seats.removeIf(seat -> locked.contains(seat.getId()));
                                            if (!seats.isEmpty()) {
                                                Reservation reservation = Reservation.builder()
                                                        .showtime(showtime)
                                                        .user(user)
                                                        .startTime(time)
                                                        .endTime(time.plusMinutes(5))
                                                        .status(ReservationStatus.builder().id((byte)1).build())
                                                        .build();
                                                reservation = reservationRepository.save(reservation);
                                                ReservationSeat reservationSeat = ReservationSeat.builder()
                                                        .reservation(reservation)
                                                        .seat(seats.getFirst())
                                                        .build();
                                                reservationSeatRepository.save(reservationSeat);
                                                if (time.plusMinutes(1).isBefore(time.plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0)))time = time.plusMinutes(1);
                                                PaymentTransaction paymentTransaction = PaymentTransaction.builder()
                                                        .transactionId(VnPayUtil.generateTransactionId())
                                                        .reservation(reservation)
                                                        .amount(100000.0)
                                                        .time(time)
                                                        .method(PaymentMethod.builder().id((byte)3).build())
                                                        .status(PaymentTransactionStatus.builder().id((byte)2).build())
                                                        .build();
                                                paymentTransactionRepository.save(paymentTransaction);
                                                rateSchedule.put(user.getId(), showtime);
                                                Ticket ticket = Ticket.builder()
                                                        .reservation(reservation)
                                                        .status(TicketStatus.builder().id((byte)2).build())
                                                        .build();
                                                ticketRepository.save(ticket);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else {
                        for (Movie movie : movies) {
                            if (!contain(movieCategoryMap.get(movie.getId()), fav)) {
                                continue;
                            }
                            int rateView = random.nextInt(100);
                            if (rateView < 20) {
                                MovieViewHistory view = MovieViewHistory.builder()
                                        .user(User.builder().id(user.getId()).build())
                                        .movie(movie)
                                        .startTime(time)
                                        .build();
                                movieViewHistoryRepository.save(view);
                                LocalDateTime nextTime = time.plusSeconds(random.nextInt(10000));
                                if (nextTime.isBefore(time.plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0))) {
                                    time = nextTime;
                                }
                                List<Showtime> showtimes = showtimeRepository.findByMovieIdAndStartTimeGreaterThanEqualAndStartTimeLessThan(
                                        movie.getId(),
                                        time.plusHours(5),
                                        time.plusDays(10)
                                );
                                for (Showtime showtime : showtimes) {
                                    if (showtime.getRoom().getBranch().getWard().getDistrict().equals(user.getWard().getDistrict())) {
                                        int rateBook = random.nextInt(100);
                                        if (rateBook < 10) {
                                            List<Seat> seats = seatRepository.findByShowtimeId(showtime.getId());
                                            List<Long> locked = seatRepository.findLockedSeatIdsByShowtimeId(showtime.getId());
                                            seats.removeIf(seat -> locked.contains(seat.getId()));
                                            if (!seats.isEmpty()) {
                                                Reservation reservation = Reservation.builder()
                                                        .showtime(showtime)
                                                        .user(user)
                                                        .startTime(time)
                                                        .endTime(time.plusMinutes(5))
                                                        .status(ReservationStatus.builder().id((byte)1).build())
                                                        .build();
                                                reservation = reservationRepository.save(reservation);
                                                ReservationSeat reservationSeat = ReservationSeat.builder()
                                                        .reservation(reservation)
                                                        .seat(seats.getFirst())
                                                        .build();
                                                reservationSeatRepository.save(reservationSeat);
                                                if (time.plusMinutes(1).isBefore(time.plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0)))time = time.plusMinutes(1);
                                                PaymentTransaction paymentTransaction = PaymentTransaction.builder()
                                                        .transactionId(VnPayUtil.generateTransactionId())
                                                        .reservation(reservation)
                                                        .amount(100000.0)
                                                        .time(time)
                                                        .method(PaymentMethod.builder().id((byte)3).build())
                                                        .status(PaymentTransactionStatus.builder().id((byte)2).build())
                                                        .build();
                                                paymentTransactionRepository.save(paymentTransaction);
                                                rateSchedule.put(user.getId(), showtime);
                                                Ticket ticket = Ticket.builder()
                                                        .reservation(reservation)
                                                        .status(TicketStatus.builder().id((byte)2).build())
                                                        .build();
                                                ticketRepository.save(ticket);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                time = time.withHour(0).withMinute(0).withSecond(0).withNano(0);
            }
            sec = ChronoUnit.SECONDS.between(start, LocalDateTime.now());
            try {
                FileWriter writer = new FileWriter("output.txt", true);
                writer.write("1 day time:" +sec +"\n");
            }
            catch (IOException e) {

            }
        }
        random = null;
        users = null;
        ageCategoryList = null;
        favoriteCategory = null;
        movieCategoryMap = null;
        write();
    }

    private void write() {
        try {
            FileWriter writer = new FileWriter("output.txt");

            List<User> users = userRepository.findAll();
            LocalDateTime now = LocalDateTime.now();
            writer.write("INSERT INTO users(id, full_name, avatar_url, phone, email, password, birthday, gender_id, ward_id, specific_address, creation_time) VALUES\n");
            for (User user : users) {
                String record = "\t(" + user.getId() + ", " +
                        "'" + user.getFullName() + "', " +
                        "NULL" +
                        "'" + user.getPhone() + "', " +
                        "'" + user.getEmail() + "', " +
                        "'" + user.getPassword() + "', " +
                        "'" + getDayString(now, user.getCreationTime()) + "'" +
                        user.getGender().getId() + ", " +
                        user.getWard().getId() + ", " +
                        "NULL, " +
                        "'" + getDayString(now, user.getCreationTime()) + "'),\n";
                writer.write(record);
            }
            writer.write("\n\n\n");
            users = null;

            List<MovieReview> reviews = movieReviewRepository.findAll();
            writer.write("INSERT INTO movie_reviews(id, user_id, movie_id, rating, comment, creation_time) VALUES\n");
            for(MovieReview review : reviews) {
                String record = "\t(" + review.getId() + ", " +
                        review.getUser().getId() + ", " +
                        review.getMovie().getId() + ", " +
                        review.getRating() + ", " +
                        "'" + review.getComment() + "', " +
                        "'" + getDayString(now, review.getCreationTime()) + "'),\n";
                writer.write(record);
            }
            writer.write("\n\n\n");
            reviews = null;

            List<MovieViewHistory> views = movieViewHistoryRepository.findAll();
            writer.write("INSERT INTO movie_view_history(id, user_id, movie_id, start_time, end_time) VALUES\n");
            for (MovieViewHistory view : views) {
                String record = "\t(" + view.getId() + ", " +
                        view.getUser().getId() + ", " +
                        view.getMovie().getId() + ", " +
                        "'" + getDayString(now,view.getStartTime()) + "', " +
                        "'" + getDayString(now, view.getStartTime().plusSeconds(5)) +"'),\n";
                writer.write(record);
            }
            writer.write("\n\n\n");
            views=null;

            List<Reservation> reservations = reservationRepository.findAll();
            writer.write("INSERT INTO reservation(id, showtime_id, user_id, start_time, end_time, status_id) VALUES\n");
            for (Reservation reservation : reservations) {
                String record = "\t(" + reservation.getId() +", " +
                        reservation.getShowtime().getId() +", " +
                        reservation.getUser().getId() + ", " +
                        "'" + getDayString(now, reservation.getStartTime()) + "', " +
                        "'" + getDayString(now, reservation.getEndTime()) + "', " +
                        reservation.getStatus().getId()+"),\n";
                writer.write(record);
            }
            writer.write("\n\n\n");
            reservations = null;

            List<ReservationSeat> reservationSeats = reservationSeatRepository.findAll();
            writer.write("INSERT INTO reservation_seats(id, reservation_id, seat_id) VALUES\n");
            for (ReservationSeat reservationSeat : reservationSeats) {
                String record = "\t(" + reservationSeat.getId() + ", " +
                        reservationSeat.getReservation().getId() + ", " +
                        reservationSeat.getSeat().getId() + "),\n";
                writer.write(record);
            }
            writer.write("\n\n\n");
            reservationSeats = null;

            List<Ticket> tickets = ticketRepository.findAll();
            writer.write("INSERT INTO tickets(id, reservation_id, status_id) VALUES\n");
            for (Ticket ticket : tickets) {
                String record = "\t(" + ticket.getId() + ", " +
                        ticket.getReservation().getId() + ", " +
                        ticket.getStatus().getId() + "),\n";
                writer.write(record);
            }
            writer.write("\n\n\n");
            tickets = null;

            List<PaymentTransaction> transactions = paymentTransactionRepository.findAll();
            writer.write("INSERT INTO payment_transactions(id, reservation_id, payment_method_id, transaction_id, time, amount, status_id) VALUES\n");
            for (PaymentTransaction transaction : transactions) {
                String record = "\t(" + transaction.getId() + ", " +
                        transaction.getReservation().getId() + ", " +
                        transaction.getMethod().getId() + ", " +
                        "'" + transaction.getTransactionId() + "', " +
                        "'" + getDayString(now, transaction.getTime()) +"', " +
                        transaction.getAmount() +", " +
                        transaction.getStatus().getId() + "),\n";
                writer.write(record);
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    private String getDayString(LocalDateTime now, LocalDateTime time) {
        Duration duration= Duration.between(time, now);
        long totalSeconds = duration.getSeconds();
        long days = totalSeconds / 86400;
        long remainder = totalSeconds % 86400;
        long hours = remainder / 3600;
        remainder %= 3600;
        long minutes = remainder / 60;
        long seconds = remainder % 60;
        String timePart = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        String out = "CURRENT_DATE + INTERVAL '- " + days + " DAY' + INTERVAL '"+ timePart +"'";
        return out;
    }

    private Map<Long, List<Byte>> createCategoryMap() {
        List<MovieCategory> movieCategories = movieCategoryRepository.findAll();
        Map<Long, List<Byte>> movieCategoryMap = new HashMap<>();
        for (long i = 1; i <= 8965; i++) {
            movieCategoryMap.put(i, new LinkedList<>());
        }
        for (MovieCategory movieCategory : movieCategories) {
            Long movieId = movieCategory.getMovie().getId();
            movieCategoryMap.get(movieId).add(movieCategory.getCategory().getId());
        }
        return  movieCategoryMap;
    }

    private boolean contain(List<Byte> par, List<Integer> children) {
        for (Integer number : children) {
            int num = number;
            if (par.contains((byte)num)) {
                return true;
            }
        }
        return  false;
    }

    public static Map<Integer, List<String>> getReviews() {
        Map<Integer, List<String>> reviews = new HashMap<>();

        // 1 sao
        reviews.put(1, Arrays.asList(
                "Không hiểu tại sao phim lại tệ đến vậy.",
                "Thất vọng toàn tập, phí tiền mua vé.",
                "Cốt truyện lủng củng, xem mà bực mình.",
                "Diễn xuất quá tệ, nhân vật không có chiều sâu.",
                "Âm thanh chán, hình ảnh mờ nhạt.",
                "Tưởng hay mà hoá ra dở tệ.",
                "Phim kéo dài lê thê, buồn ngủ từ đầu tới cuối.",
                "Không cảm xúc, không ấn tượng.",
                "Kỹ xảo quá sơ sài, như phim học sinh làm.",
                "Phim dở nhất năm nay mình xem.",
                "Nội dung không logic chút nào.",
                "Plot twist nhảm nhí, vô lý vô cùng.",
                "Xem xong chỉ muốn đòi lại tiền.",
                "Phim quảng cáo hay hơn nội dung thật.",
                "Kịch bản như trẻ con viết vậy.",
                "Hiệu ứng kém như phim mấy chục năm trước.",
                "Âm thanh to quá mức, đau cả tai.",
                "Không có gì để nhớ ngoại trừ sự thất vọng.",
                "Tưởng kinh dị mà hóa ra hài nhạt nhẽo.",
                "Nhân vật chính diễn đơ như tượng.",
                "Cốt truyện rối rắm khó hiểu vô lý.",
                "Lời thoại giả trân, thiếu tự nhiên.",
                "Tốc độ phim quá chậm, xem mệt mỏi.",
                "Chỉ hợp để bật làm nền khi dọn nhà.",
                "Không nên lãng phí thời gian với phim này.",
                "Mình bỏ dở giữa chừng vì chán quá.",
                "Không khuyên ai đi xem phim này.",
                "Một thảm họa điện ảnh thực sự.",
                "Không có một điểm sáng nào.",
                "Từ âm nhạc, hình ảnh đến nội dung đều tệ.",
                "Không còn gì để nói nữa. 😤",
                "Tưởng bom tấn ai dè bom xịt.",
                "Thấy hối hận vì nghe bạn bè rủ đi xem.",
                "Mong đạo diễn đừng làm phần tiếp theo.",
                "Kỹ xảo làm bằng Paint à?",
                "Diễn viên chính cần học lại diễn xuất.",
                "Kịch bản phi logic từ đầu đến cuối.",
                "Không thể tưởng tượng được độ dở của nó.",
                "Cố gắng xem hết mà thật sự thất vọng.",
                "Nội dung lan man, chẳng biết muốn nói gì.",
                "Tưởng hấp dẫn mà cực kỳ nhạt nhẽo.",
                "Lồng tiếng cũng không cứu nổi phim.",
                "Không khuyến khích ai xem.",
                "Xem xong thấy mất niềm tin vào điện ảnh.",
                "Poster đẹp thôi, nội dung không có gì.",
                "Kết thúc nhạt nhẽo, thiếu cao trào.",
                "Nội dung rời rạc như ghép tạp nham.",
                "Diễn viên phụ còn tệ hơn diễn viên chính.",
                "Lãng phí 2 tiếng cuộc đời.",
                "Quá buồn ngủ, muốn ngủ luôn trong rạp.",
                "Âm thanh tạp âm nhiều vô lý."
        ));

        // 2 sao
        reviews.put(2, Arrays.asList(
                "Tạm ổn nhưng không đáng xem lại.",
                "Một vài cảnh hay nhưng tổng thể chưa đạt.",
                "Diễn xuất không đều, lúc hay lúc dở.",
                "Có tiềm năng nhưng chưa được khai thác tốt.",
                "Nội dung chưa sâu sắc, dễ quên.",
                "Nhân vật thiếu phát triển, xem hơi hụt hẫng.",
                "Phim bị lê thê ở giữa.",
                "Âm thanh ổn nhưng hình ảnh chưa đẹp.",
                "Thiếu điểm nhấn khiến phim mờ nhạt.",
                "Cốt truyện có sạn nhưng vẫn tạm chấp nhận.",
                "Lời thoại gượng gạo, thiếu tự nhiên.",
                "Dàn dựng cảnh quay chưa chỉn chu.",
                "Phần mở đầu khá ổn nhưng càng về sau càng chán.",
                "Không quá tệ nhưng chắc không xem lại.",
                "Một số chi tiết quá phi lý.",
                "Có nhiều tình tiết kéo dài không cần thiết.",
                "Nhịp phim không ổn định.",
                "Chưa có nhiều cảm xúc khi xem.",
                "Thiếu cao trào và bất ngờ.",
                "Một bộ phim dễ ngủ 😴.",
                "Không quá ấn tượng với kịch bản.",
                "Nhân vật phản diện mờ nhạt.",
                "Không tạo được sự kết nối với khán giả.",
                "Lồng ghép thông điệp chưa tự nhiên.",
                "Diễn viên phụ không nổi bật.",
                "Thiết kế bối cảnh sơ sài.",
                "Trang phục và hóa trang chưa hợp lý.",
                "Tưởng sẽ có cú twist hấp dẫn nhưng không có.",
                "Âm nhạc không phù hợp với phân cảnh.",
                "Phim có nhiều lỗi kỹ thuật nhỏ.",
                "Quảng cáo hay hơn nội dung chính.",
                "Cái kết làm mình hụt hẫng.",
                "Thiếu sự sáng tạo trong xây dựng nhân vật.",
                "Kỹ xảo nhìn khá giả tạo.",
                "Cần chỉnh sửa lại kịch bản.",
                "Một vài cảnh đối thoại khá chán.",
                "Nội dung dễ đoán, thiếu bất ngờ.",
                "Thông điệp phim chưa rõ ràng.",
                "Tuyệt đối không phải phim dở nhất, nhưng chưa đủ tốt.",
                "Có thể xem nếu quá rảnh.",
                "Không đủ hấp dẫn để giới thiệu bạn bè.",
                "Cần đầu tư thêm phần âm thanh ánh sáng.",
                "Tiếc vì tiềm năng chưa được khai thác hết.",
                "Nhân vật nữ chính thiếu sức hút.",
                "Chuyển cảnh hơi gượng ép.",
                "Phim phù hợp cho thiếu nhi hơn là người lớn.",
                "Đạo diễn có lẽ chưa đủ kinh nghiệm.",
                "Không rõ phim muốn gửi gắm điều gì.",
                "Một trải nghiệm xem phim tạm chấp nhận."
        ));

        reviews.put(3, Arrays.asList(
                "Phim ở mức trung bình, không quá tệ cũng không quá hay.",
                "Nội dung ổn nhưng chưa thực sự cuốn hút.",
                "Có thể xem một lần cho biết.",
                "Cốt truyện dễ đoán nhưng có vài chi tiết thú vị.",
                "Hiệu ứng tốt nhưng kịch bản chưa thật sự nổi bật.",
                "Diễn viên làm tròn vai nhưng chưa bùng nổ.",
                "Nội dung dễ xem, giải trí ổn.",
                "Kỹ xảo tương đối ổn, không có gì đặc sắc.",
                "Tình tiết hợp lý nhưng hơi chậm.",
                "Lời thoại tự nhiên, nhưng chưa có chiều sâu.",
                "Bối cảnh đẹp nhưng thiếu điểm nhấn.",
                "Một bộ phim giải trí cuối tuần hợp lý.",
                "Thông điệp có nhưng chưa ấn tượng.",
                "Cái kết tạm ổn nhưng thiếu bất ngờ.",
                "Mạch phim trôi chảy, xem tạm được.",
                "Nhân vật phụ khá thú vị, giúp kéo lại phần nào.",
                "Nhạc nền ổn, dễ chịu.",
                "Trang phục nhân vật khá đẹp.",
                "Hình ảnh tốt, màu sắc bắt mắt.",
                "Không quá ấn tượng nhưng cũng không dở.",
                "Tình cảm nhân vật xây dựng tương đối ổn.",
                "Có vài cảnh gây cười nhẹ nhàng.",
                "Chuyển cảnh mượt mà.",
                "Không có lỗi lớn nhưng thiếu sự xuất sắc.",
                "Kỹ xảo nhìn ổn, hợp thời.",
                "Một bộ phim xem giải trí tạm ổn.",
                "Tuyệt đối không phải thảm họa.",
                "Tình tiết ổn định, không bị lan man.",
                "Xem xong cảm giác nhẹ nhàng.",
                "Không tạo cảm giác chán nản khi xem.",
                "Nếu rảnh có thể xem thử.",
                "Đạo diễn làm tròn vai trò nhưng chưa có dấu ấn riêng.",
                "Phim phù hợp để xem với gia đình.",
                "Nhân vật chính diễn khá ổn.",
                "Một số cảnh quay đẹp, chăm chút.",
                "Cốt truyện không mới nhưng cách kể chuyện ổn.",
                "Phim thiếu sự đột phá.",
                "Dễ xem, phù hợp với nhiều đối tượng.",
                "Không có chi tiết gây khó chịu.",
                "Kết thúc hợp lý dù hơi an toàn.",
                "Có vài cảnh đáng nhớ.",
                "Phim ổn định từ đầu đến cuối.",
                "Mạch phim hơi chậm ở giữa nhưng cuối khá ổn.",
                "Một vài cảnh hành động làm tốt.",
                "Thông điệp nhân văn nhưng thể hiện chưa sâu.",
                "Đáng xem khi không có gì khác hấp dẫn hơn.",
                "Không tiếc tiền vé nhưng cũng không quá vui.",
                "Nhân vật phản diện chưa đủ ác nhưng tròn vai.",
                "Dựng cảnh chỉnh chu, đầu tư ổn.",
                "Nội dung có vài twist nhẹ nhàng."
        ));
        reviews.put(4, Arrays.asList(
                "Phim khá hay, đáng để xem.",
                "Diễn xuất tốt, cốt truyện hấp dẫn.",
                "Hiệu ứng hình ảnh và âm thanh rất ổn.",
                "Nội dung có chiều sâu, cuốn hút người xem.",
                "Một bộ phim đáng xem trong năm nay.",
                "Nhân vật được xây dựng có chiều sâu.",
                "Kỹ xảo mượt mà, mãn nhãn.",
                "Bối cảnh hoành tráng, đẹp mắt.",
                "Cái kết làm mình hài lòng.",
                "Kịch bản logic, mạch phim chặt chẽ.",
                "Có nhiều cảnh quay nghệ thuật, ấn tượng.",
                "Nhạc nền rất phù hợp với mạch cảm xúc.",
                "Cảm xúc được đẩy lên cao trào hợp lý.",
                "Đạo diễn có tư duy rất tốt.",
                "Xem phim mà cuốn từ đầu đến cuối.",
                "Tuy có vài lỗi nhỏ nhưng tổng thể rất ổn.",
                "Nhân vật phản diện xây dựng rất có sức nặng.",
                "Một vài cảnh làm mình nổi da gà.",
                "Khung hình đẹp, chăm chút từng chi tiết.",
                "Rất đáng đồng tiền bát gạo.",
                "Cốt truyện vừa kịch tính vừa nhân văn.",
                "Hình ảnh sống động, tạo chiều sâu không gian.",
                "Nhiều phân cảnh xúc động, chạm đến cảm xúc người xem.",
                "Xứng đáng nằm trong top phim năm nay.",
                "Dàn diễn viên diễn rất tròn vai.",
                "Nhiều triết lý sâu sắc cài cắm khéo léo.",
                "Cái kết bất ngờ, làm mình suy ngẫm.",
                "Nội dung đủ mới mẻ để thu hút.",
                "Kịch bản có sự đầu tư chỉnh chu.",
                "Không có điểm trừ đáng kể.",
                "Tốc độ phim rất hợp lý, không bị lê thê.",
                "Hiệu ứng âm thanh sống động, chân thực.",
                "Lời thoại tự nhiên, gần gũi.",
                "Tuyệt vời để xem cùng bạn bè, gia đình.",
                "Một tác phẩm rất chỉn chu.",
                "Cảm giác không hối tiếc khi bỏ thời gian xem.",
                "Phim cân bằng tốt giữa giải trí và nghệ thuật.",
                "Không khí phim cuốn hút từ những phút đầu tiên.",
                "Khắc họa nhân vật rất thành công.",
                "Dễ dàng nhập tâm vào câu chuyện phim.",
                "Tình tiết hợp lý, khó đoán trước.",
                "Thông điệp nhân văn, ý nghĩa.",
                "Trang phục, bối cảnh đầu tư kỹ lưỡng.",
                "Những cảnh hành động rất đã mắt.",
                "Đầy cảm xúc nhưng không sến.",
                "Cốt truyện giữ được nhịp độ rất tốt.",
                "Tình cảm, hành động, kịch tính đủ cả.",
                "Một bộ phim có giá trị xem lại nhiều lần.",
                "Kỹ xảo đỉnh cao, rất đẹp mắt."
        ));
        reviews.put(5, Arrays.asList(
                "Xuất sắc! Mọi thứ đều hoàn hảo.",
                "Cực kỳ ấn tượng, nên xem ngay lập tức.",
                "Diễn xuất, kịch bản, hình ảnh đều tuyệt vời.",
                "Một siêu phẩm điện ảnh, không thể bỏ lỡ.",
                "Top 10 phim hay nhất mà tôi từng xem.",
                "Xứng đáng đạt giải thưởng quốc tế.",
                "Không có chỗ nào để chê.",
                "Mỗi phân cảnh đều là nghệ thuật.",
                "Diễn viên nhập vai xuất thần.",
                "Kịch bản quá thông minh và lôi cuốn.",
                "Âm thanh vòm khiến trải nghiệm cực đã.",
                "Hiệu ứng hình ảnh đỉnh cao.",
                "Cảm xúc dâng trào từ đầu tới cuối.",
                "Nội dung sâu sắc, giàu ý nghĩa nhân văn.",
                "Mọi khía cạnh đều được trau chuốt tỉ mỉ.",
                "Một trải nghiệm điện ảnh không thể quên.",
                "Kỹ xảo vượt mong đợi.",
                "Từng khung hình như tranh vẽ.",
                "Cốt truyện cực kỳ hấp dẫn và bất ngờ.",
                "Thật sự xứng đáng từng đồng vé.",
                "Bộ phim khiến tôi muốn xem lại ngay lập tức.",
                "Mạch phim hoàn hảo, không một lỗ hổng.",
                "Bối cảnh tuyệt đẹp, chân thực tới từng chi tiết.",
                "Hiếm có bộ phim nào đạt đến đẳng cấp như vậy.",
                "Nhạc phim lay động lòng người.",
                "Một tác phẩm nghệ thuật đỉnh cao.",
                "Không thể tin đây chỉ là một bộ phim giải trí.",
                "Đạo diễn quá xuất sắc, khéo léo trong từng chi tiết.",
                "Diễn viên chính thể hiện đẳng cấp ngôi sao.",
                "Hòa quyện hoàn hảo giữa nghệ thuật và thương mại.",
                "Một bộ phim xuất sắc từ đầu đến cuối.",
                "Từng nhân vật đều có chiều sâu và ấn tượng.",
                "Kết thúc làm mình bồi hồi mãi không quên.",
                "Xem xong vẫn còn dư âm cảm xúc.",
                "Không hề có điểm trừ nào để phàn nàn.",
                "Đây là định nghĩa của từ 'tuyệt tác'.",
                "Mỗi cảnh quay đều khiến tôi trầm trồ.",
                "Cả rạp vỗ tay sau khi kết thúc phim.",
                "Thật khó để tìm ra bộ phim nào hay hơn.",
                "Hội tụ đủ mọi yếu tố tuyệt vời nhất của điện ảnh.",
                "Cảnh hành động mãn nhãn, cực kỳ cuốn hút.",
                "Tình tiết đan xen cực kỳ hợp lý, thông minh.",
                "Nhân vật chính quá xuất sắc, diễn rất thật.",
                "Không khí phim khiến người xem đắm chìm hoàn toàn.",
                "Một bộ phim sẽ đi vào lịch sử điện ảnh.",
                "Tôi ước có thể xem lại lần đầu một lần nữa.",
                "Hết sức khâm phục đội ngũ sản xuất.",
                "Đây là lý do tại sao tôi yêu điện ảnh.",
                "Một hành trình cảm xúc tuyệt vời."
        ));

        return reviews;
    }

}
