package com.nmquan1503.backend_springboot.services.movie;

import com.nmquan1503.backend_springboot.entities.movie.Language;
import com.nmquan1503.backend_springboot.exceptions.GeneralException;
import com.nmquan1503.backend_springboot.exceptions.ResponseCode;
import com.nmquan1503.backend_springboot.repositories.movie.LanguageRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LanguageService {

    LanguageRepository languageRepository;

    Language fetchLanguageById(Byte languageId) {
        if (languageId == null) {
            return null;
        }
        return languageRepository.findById(languageId)
                .orElseThrow(() -> new GeneralException(ResponseCode.LANGUAGE_NOT_FOUND));
    }

}
