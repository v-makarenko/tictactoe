package ru.mai.julia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectConverter {
    // Собираем объект "список юзеров" из строки
    static List<User> usersStringToUsers(String usersString) {
        List<String> userStringList = Arrays.asList(usersString.split("\n"));
        List<User> userList = new ArrayList<>();
        for (String userString : userStringList) {
            if (!userList.isEmpty()) {
                userList.add(userStringToUser(userString));
            }
        }
        return userList;
    }

    // Собираем объект "юзер" из строки
    static User userStringToUser(String user) {
        String[] userParamArray = user.split(",");
        User resultUser = new User(userParamArray[0],
                Integer.parseInt(userParamArray[1]),
                Integer.parseInt(userParamArray[2]));
        return resultUser;
    }

    // получаем строку для объекта юзер
    static String userToString(User user) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(user.getUsername());
        stringBuilder.append(",");
        stringBuilder.append(user.getWinCount());
        stringBuilder.append(",");
        stringBuilder.append(user.getLoseCount());
        return stringBuilder.toString();
    }

    // получаем строку для объекта список юзеров
    static String usersToString(List<User> users) {
        StringBuilder stringBuilder = new StringBuilder();
        for (User user : users) {
            stringBuilder.append(userToString(user));
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}


