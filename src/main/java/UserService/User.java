package UserService;

import lombok.Getter;

@Getter
public class User {
    private long id;
    private String name;
    private String login;
    private String password;

    public User(long id, String name, String login, String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
    }


}
