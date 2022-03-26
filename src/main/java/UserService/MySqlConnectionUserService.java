package UserService;

import publicServices.ConnectionService;

import java.sql.*;

public class MySqlConnectionUserService implements ConnectionUserService{

    @Override
    public User getUser(String login, String password) {
        try {
            Connection connection = ConnectionService.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "Select * FROM users WHERE users.login = ? AND users.password = ? LIMIT 1 ;");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                return new User(rs.getInt("id"), rs.getString("name"), rs.getString("login"), rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
