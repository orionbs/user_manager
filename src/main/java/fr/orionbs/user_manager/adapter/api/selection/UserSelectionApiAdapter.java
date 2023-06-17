package fr.orionbs.user_manager.adapter.api.selection;

import fr.orionbs.user_manager.adapter.api.selection.data.UserSelectionResponse;
import fr.orionbs.user_manager.adapter.api.selection.mapper.UserSelectionMapper;
import fr.orionbs.user_manager.application.port.input.UserSelectionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserSelectionApiAdapter {

    private final UserSelectionUseCase selectionUseCase;
    private final UserSelectionMapper userSelectionMapper;

    @GetMapping(path = "users")
    @ResponseStatus(code = HttpStatus.OK)
    public List<UserSelectionResponse> getUsers() {
        return selectionUseCase.userSelection()
                .stream()
                .map(userSelectionMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "users/{userUuid}")
    @ResponseStatus(code = HttpStatus.OK)
    public UserSelectionResponse getUserByUuid(@PathVariable String userUuid) {
        return userSelectionMapper.toResponse(
                selectionUseCase.userSelectionById(userUuid)
        );
    }

}
