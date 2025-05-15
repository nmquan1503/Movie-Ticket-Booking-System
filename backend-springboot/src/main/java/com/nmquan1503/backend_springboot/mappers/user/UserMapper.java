package com.nmquan1503.backend_springboot.mappers.user;

import com.nmquan1503.backend_springboot.dtos.requests.user.UserCreationRequest;
import com.nmquan1503.backend_springboot.dtos.requests.user.UserUpdateRequest;
import com.nmquan1503.backend_springboot.dtos.responses.user.UserDetailResponse;
import com.nmquan1503.backend_springboot.dtos.responses.user.UserPreviewResponse;
import com.nmquan1503.backend_springboot.entities.user.User;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper {

    User toUser(UserCreationRequest request);

//    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);

    UserDetailResponse toUserDetailResponse(User user);

    UserPreviewResponse toUserPreviewResponse(User user);
}
