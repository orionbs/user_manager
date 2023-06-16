package fr.orionbs.user_manager.application.port.output;

public interface ExistUserPort {
    Boolean existUserByEmail(String email);
}
