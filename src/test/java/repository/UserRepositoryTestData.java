package repository;

import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryTestData {
    List<User> userList = new ArrayList<>();

    public UserRepositoryTestData() {
        User philip = new User(1, "Philip", "F", "phila@hotmail.com", "phila", "phila123", "admin");
        User fred = new User(2, "Fredrik", "J", "fredy@gmail.com", "fred", "fred123", "user");
        userList.add(philip);
        userList.add(fred);
    }

    public User getUserByFirstName(String username) {
        for (User oneUser: userList) {
            if(oneUser.getFirstName().equalsIgnoreCase(username)) {
                return oneUser;
            }
        }
        return null;
    }

}
