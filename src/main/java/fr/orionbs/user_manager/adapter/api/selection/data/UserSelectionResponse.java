package fr.orionbs.user_manager.adapter.api.selection.data;

import lombok.Data;

@Data
public class UserSelectionResponse {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String actualStatus;
    private String lastEvent;
}
