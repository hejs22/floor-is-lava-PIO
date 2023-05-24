package front.main.java.com.pio.floorislavafront.ClientSocket;

public class StartConnection implements Runnable {

    private String ip;
    private int port;
    private String nickname;

    public StartConnection(String ip, int port, String nickname) {
        this.ip = ip;
        this.port = port;
        this.nickname = nickname;
    }

    @Override
    public void run() {
        new ClientApplication(ip, port, nickname);
    }

}
