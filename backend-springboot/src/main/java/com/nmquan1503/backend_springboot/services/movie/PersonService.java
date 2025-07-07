package com.nmquan1503.backend_springboot.services.movie;

import com.nmquan1503.backend_springboot.dtos.responses.movie.PersonPreviewResponse;
import com.nmquan1503.backend_springboot.entities.movie.Person;
import com.nmquan1503.backend_springboot.exceptions.GeneralException;
import com.nmquan1503.backend_springboot.exceptions.ResponseCode;
import com.nmquan1503.backend_springboot.mappers.movie.PersonMapper;
import com.nmquan1503.backend_springboot.repositories.movie.PersonRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PersonService {

    PersonRepository personRepository;

    PersonMapper personMapper;

//    List<PersonPreviewResponse> getActorPreviewsByMovieId(Long movieId) {
//        return personRepository.findActorsByMovieId(movieId)
//                .stream().map(personMapper::toPersonPreviewResponse).toList();
//    }
//
//    List<PersonPreviewResponse> getDirectorPreviewsByMovieId(Long movieId) {
//        return personRepository.findDirectorsByMovieId(movieId)
//                .stream().map(personMapper::toPersonPreviewResponse).toList();
//    }

    List<PersonPreviewResponse> getPersonPreviewsByIds(List<Long> personIds) {
        List<Person> people = getPersonByIds(personIds);
        return people.stream().map(
                personMapper::toPersonPreviewResponse
        ).toList();
    }

    List<Person> getPersonByIds(List<Long> personIds) {
        if (personIds == null) {
            return null;
        }
        List<Person> people = personRepository.findAllById(personIds);
        if (people.size() < personIds.size()) {
            throw new GeneralException(ResponseCode.PERSON_NOT_FOUND);
        }
        return people;
    }

    public boolean allUniquePersonIdsExist(List<Long> ids) {
        return personRepository.countByIdIn(ids) == ids.size();
    }

}
