package fr.orionbs.user_manager.adapter.api.selection.mapper;

import fr.orionbs.user_manager.adapter.api.selection.data.UserSelectionResponse;
import fr.orionbs.user_manager.domain.model.*;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class UserSelectionMapper {

    public UserSelectionResponse toResponse(User user) {
        UserSelectionResponse userSelectionResponse = new UserSelectionResponse();
        userSelectionResponse.setId(user.getId());
        userSelectionResponse.setEmail(user.getEmail());
        userSelectionResponse.setFirstName(user.getFirstName());
        userSelectionResponse.setLastName(user.getLastName());
        userSelectionResponse.setActualStatus(
                user.getStatuses()
                        .stream()
                        .max(Comparator.comparing(Status::getMilestone))
                        .map(Status::getStatusEnum)
                        .map(StatusEnum::toString)
                        .orElse("UNKNOWN")
        );
        userSelectionResponse.setLastEvent(
                user.getEvents()
                        .stream()
                        .max(Comparator.comparing(Event::getMilestone))
                        .map(Event::getEventEnum)
                        .map(EventEnum::toString)
                        .orElse("UNKNOWN")
        );
        userSelectionResponse.setActualAuthority(
                user.getAuthorities()
                        .stream()
                        .max(Comparator.comparing(Authority::getMilestone))
                        .map(Authority::getAuthorityEnum)
                        .map(AuthorityEnum::toString)
                        .orElse("UNKNOWN")
        );
        return userSelectionResponse;
    }

}
