package ru.mai.julia;

public class ObjectLocator {
    private static Server server;

    public static Server getServer() {
        return server;
    }

    public static void setServer(Server server) {
        ObjectLocator.server = server;
    }
}
