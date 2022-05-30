package scenarios.models;

public class TodoList {
    private int todoListId;
    private User user;
    private String title;


    public TodoList(int todoListId, User user, String title) {
        this.todoListId = todoListId;
        this.user = user;
        this.title = title;
    }


    public int getTodoListId() {
        return todoListId;
    }

    public void setTodoListId(int todoListId) {
        this.todoListId = todoListId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "TodoList{" +
                "todoListId=" + todoListId +
                ", user=" + user +
                ", title='" + title + '\'' +
                '}';
    }
}
