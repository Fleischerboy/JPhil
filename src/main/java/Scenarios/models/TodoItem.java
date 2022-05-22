package Scenarios.models;

public class TodoItem {
    private int todoItemId;
    private TodoList todoListId;
    private String title;
    private boolean isDone;


    public TodoItem(int todoItemId, String title, boolean isDone, TodoList todoListId) {
        this.todoItemId = todoItemId;
        this.title = title;
        this.isDone = isDone;
        this.todoListId = todoListId;
    }

    public TodoList getTodoListId() {
        return todoListId;
    }

    public void setTodoListId(TodoList todoListId) {
        this.todoListId = todoListId;
    }

    public int getTodoItemId() {
        return todoItemId;
    }

    public void setTodoItemId(int todoItemId) {
        this.todoItemId = todoItemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "todoListId=" + todoListId +
                ", todoItemId=" + todoItemId +
                ", title='" + title + '\'' +
                ", isDone=" + isDone +
                '}';
    }
}
