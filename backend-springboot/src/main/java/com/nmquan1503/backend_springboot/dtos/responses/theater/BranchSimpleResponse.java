package com.nmquan1503.backend_springboot.dtos.responses.theater;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BranchSimpleResponse {

    Short id;
    String name;
}
