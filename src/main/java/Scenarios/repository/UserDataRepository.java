package Scenarios.repository;

import Scenarios.models.TodoItem;
import Scenarios.models.TodoList;
import Scenarios.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserDataRepository implements UserInterface {

    List<User> userList = new ArrayList<>();


    public UserDataRepository() {
        List<TodoList> philipTodoLists = new ArrayList<>();
        List<TodoList> fredTodoLists = new ArrayList<>();
        User philip = new User(1,"Philip", "Fleischer", "phila@hotmail.com", "phila", "phila123", "admin", philipTodoLists);
        User fred = new User(2,"Fredrik", "Johnsen", "fredy@gmail.com", "fred", "fred123", "user", fredTodoLists);
        userList.add(philip);
        userList.add(fred);


        TodoList philipsTodoList1 = new TodoList(1, philip, "Monday");
        TodoList philipsTodoList2 = new TodoList(2, philip, "Work");
        TodoList philipsTodoList3 = new TodoList(3, philip, "Training");
        TodoList fredTodoList1 = new TodoList(4, fred, "Sunday");
        TodoList fredTodoList2 = new TodoList(5, fred, "Summer goals");
        TodoList fredTodoList3 = new TodoList(6, fred, "Workout");
        philipTodoLists.add(philipsTodoList1);
        philipTodoLists.add(philipsTodoList2);
        philipTodoLists.add(philipsTodoList3);
        fredTodoLists.add(fredTodoList1);
        fredTodoLists.add(fredTodoList2);
        fredTodoLists.add(fredTodoList3);

        List<TodoItem> philipTodoItems = new ArrayList<>();
        List<TodoItem> fredTodoItems = new ArrayList<>();
        philipTodoItems.add(new TodoItem(1,"go to store", true, philipsTodoList1));
        philipTodoItems.add(new TodoItem(2,"give the cat food", false, philipsTodoList1));
        philipTodoItems.add(new TodoItem(3,"talk to my brother", true, philipsTodoList1));
        philipTodoItems.add(new TodoItem(4,"send email", false, philipsTodoList2));
        philipTodoItems.add(new TodoItem(5,"talk to boss", true, philipsTodoList2));
        philipTodoItems.add(new TodoItem(6,"20 push-ups", false, philipsTodoList3));
        philipTodoItems.add(new TodoItem(7,"Run 20min", true, philipsTodoList3));

        fredTodoItems.add(new TodoItem(8,"sleep long", true, fredTodoList1));
        fredTodoItems.add(new TodoItem(9,"watch tv", false, fredTodoList1));
        fredTodoItems.add(new TodoItem(10,"relax", false, fredTodoList2));
        fredTodoItems.add(new TodoItem(11,"50 hangups", false, fredTodoList3));
        fredTodoItems.add(new TodoItem(12,"50 pushups", true, fredTodoList3));
    }

    @Override
    public User getSpecificUserById(int userId) {
        for (User oneUser: userList
             ) {
            if(oneUser.getUserId() == userId) {
                return oneUser;
            }
        }
        return null;
    }

    @Override
    public User getSpecificUserByUsername(String username) {
        for (User oneUser: userList
        ) {
            if(oneUser.getUserName().equals(username)) {
                return oneUser;
            }
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<User>(userList);
    }
}
