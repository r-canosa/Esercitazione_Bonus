/**
 * Created by Riccardo Canosa 65372 on $DATE$.
 */

package com.example.esercitazionebonus;

import java.util.ArrayList;
import java.util.List;

public class UserFactory {

    public static List<User> UserList = new ArrayList<>();

    public static void AddUserToList(User user) {
        UserList.add(user);
    }

    public static void setUserList(List<User> userList) {
        UserList = userList;
    }

    public static List<User> getUserList() {
        return UserList;
    }

    public User searchUser(String username, String password) {
        User user = new User( null,null,null,null);
        for (int i = 0; i < UserList.size(); i++) {
            if ((UserList.get(i).username.equals(username)) && (UserList.get(i).password.equals(password)))
                user = UserList.get(i);
        }
        return user;
    }

    public void modifyPassword (User user, String password){
        UserFactory prov = new UserFactory();
        prov.getUserList();
        prov.searchUser(user.username, user.password).password=password;
    }
}
