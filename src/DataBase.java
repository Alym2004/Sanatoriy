import java.util.List;

public class DataBase {
    private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User findByName(String userName) {
        for(User user : userList) {
            if(user.getName().equals(userName)) {
                return user;
            }
        }
        return null;
    }
}
