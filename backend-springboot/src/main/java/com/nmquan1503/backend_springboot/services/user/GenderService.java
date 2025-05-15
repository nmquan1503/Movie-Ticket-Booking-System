package com.nmquan1503.backend_springboot.services.user;

import java.util.List;

import com.nmquan1503.backend_springboot.dtos.responses.user.GenderOptionResponse;
import org.springframework.stereotype.Service;

import com.nmquan1503.backend_springboot.entities.user.Gender;
import com.nmquan1503.backend_springboot.exceptions.GeneralException;
import com.nmquan1503.backend_springboot.exceptions.ResponseCode;
import com.nmquan1503.backend_springboot.mappers.user.GenderMapper;
import com.nmquan1503.backend_springboot.repositories.user.GenderRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenderService {
    
    GenderRepository genderRepository;

    GenderMapper genderMapper;

    public List<GenderOptionResponse> getAllGenderOptions() {
        List<Gender> genders = genderRepository.findAll();
        return genders.stream().map(
                genderMapper::toGenderOptionResponse
        ).toList();
    }

    public GenderOptionResponse getGenderOptionByGenderId(Byte genderId) {
        return genderMapper.toGenderOptionResponse(fetchGenderById(genderId));
    }

    public Gender fetchGenderById(Byte genderId) {
        return genderRepository.findById(genderId)
                .orElseThrow(() -> new GeneralException(ResponseCode.GENDER_NOT_FOUND));
    }

}
