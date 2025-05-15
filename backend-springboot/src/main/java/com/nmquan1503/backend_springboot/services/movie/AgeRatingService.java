package com.nmquan1503.backend_springboot.services.movie;

import com.nmquan1503.backend_springboot.entities.movie.AgeRating;
import com.nmquan1503.backend_springboot.exceptions.GeneralException;
import com.nmquan1503.backend_springboot.exceptions.ResponseCode;
import com.nmquan1503.backend_springboot.repositories.movie.AgeRatingRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AgeRatingService {

    AgeRatingRepository ageRatingRepository;

    AgeRating fetchAgeRatingById(Byte ageRatingId) {
        if (ageRatingId == null) {
            return null;
        }
        return ageRatingRepository.findById(ageRatingId)
                .orElseThrow(() -> new GeneralException(ResponseCode.AGE_RATING_NOT_FOUND));
    }

}
