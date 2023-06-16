package fr.orionbs.user_manager.adapter.api.selection.data;

import lombok.Data;

import java.util.UUID;

@Data
public class UserSelectionResponse {
    private UUID uuid;
    private String email;
    private String firstName;
    private String lastName;
    private String actualStatus;
    private String lastEvent;
}
