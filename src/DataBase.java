import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private List<User> userList;
    private List<Client> clientList;

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

    public List<User> getAllClients() {
        List<User> clients = new ArrayList<>();
        for(User user : userList) {
            if(user.getRole().equals(Role.CLIENT)) {
                clients.add(user);
            }
        }
        return clients;
    }

//    public List<Client> getClientList() {
//        return clientList;
//    }
//
//    public void setClientList(List<Client> clientList) {
//        this.clientList = clientList;
//    }
//
//    public Client findClientByName(String clientName) {
//        for(Client client : clientList) {
//            if(client.getName().equals(clientName)) {
//                return client;
//            }
//        }
//        return null;
//    }
}
