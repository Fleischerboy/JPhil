package Scenarios.repository;

import Scenarios.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserInterface {

    User getSpecificUserById(int userId);

    User getSpecificUserByUsername(String username);

    List<User> getAllUsers();
}
