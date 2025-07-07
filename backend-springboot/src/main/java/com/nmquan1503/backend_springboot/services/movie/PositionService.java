package com.nmquan1503.backend_springboot.services.movie;

import com.nmquan1503.backend_springboot.repositories.movie.PositionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PositionService {

    PositionRepository positionRepository;

    public boolean allUniquePositionIdsExist(List<Integer> ids) {
        return positionRepository.countByIdIn(ids) == ids.size();
    }

}
