package se.darkyon.adnroid_serverc.ssh;

public class HostData {
    public String ip;
    public String user;
    public String pass;
    public int port;

    public HostData(String ip, String user,String pass, int port) {
        this.ip = ip;
        this.user = user;
        this.pass = pass;
        this.port = port;
    }
}
