package common;

import back.Position;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class Player implements Serializable {
    private final String nickname;
    private final ObjectOutputStream objectOutputStream;
    public Position position;
    private boolean isAlive;
    private boolean isMoving;
    private final int ID;


    public int getID() {
        return ID;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public Player(String nickname, int ID, ObjectOutputStream objectOutputStream){
        this.nickname = nickname;
        this.objectOutputStream = objectOutputStream;
        this.ID = ID;
    }

    public String getNickname() { return nickname; }

    public ObjectOutputStream getOutputStream() { return objectOutputStream; }
}