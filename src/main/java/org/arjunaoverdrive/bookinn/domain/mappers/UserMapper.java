package org.arjunaoverdrive.bookinn.domain.mappers;

import org.arjunaoverdrive.bookinn.domain.entities.User;
import org.arjunaoverdrive.bookinn.web.payload.user.UpsertUserRequest;
import org.arjunaoverdrive.bookinn.web.payload.user.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User toUser(UpsertUserRequest request);

    default User toUser(Long id, UpsertUserRequest request){
        User user = toUser(request);
        user.setId(id);
        return user;
    }

    UserResponse toResponse(User user);
}
