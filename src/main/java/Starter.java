import UserService.UserService;

import UserService.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Starter {
    public static void main(String[] args) {
        UserService userService = UserService.getInstance();
        User user1 = userService.getUser("nikita", "123");
        if(user1 != null){
            log.info("Пользователь найден : "+user1.getName());
        } else {
            log.info("Пользователь не найден");
        }
    }
}

