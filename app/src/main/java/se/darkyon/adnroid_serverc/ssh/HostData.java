package se.darkyon.adnroid_serverc.ssh;

public class HostData {
    public String ip;
    public String user;
    public int port;

    public HostData(String ip, String user, int port) {
        this.ip = ip;
        this.user = user;
        this.port = port;
    }
}
