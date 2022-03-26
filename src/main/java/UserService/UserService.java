package UserService;

public class UserService {
    private static UserService instance;

    private final ConnectionUserService connectionUserService= new MySqlConnectionUserService();

    private UserService(){
    }

    public static UserService getInstance(){
        UserService localInstance = instance;
        if(localInstance == null){
            synchronized (UserService.class){
                localInstance = instance;
                if (localInstance == null){
                    localInstance = instance = new UserService();
                }
            }
        }
        return localInstance;
    }

    public User getUser(String login, String password){
        return connectionUserService.getUser(login, password);
    }


}
