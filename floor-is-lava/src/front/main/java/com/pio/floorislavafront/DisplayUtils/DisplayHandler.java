package front.main.java.com.pio.floorislavafront.DisplayUtils;

import common.FieldType;
import common.Packet;
import common.Player;
import common.PlayerData;
import front.main.java.com.pio.floorislavafront.FloorIsLavaController;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

import static common.GlobalSettings.*;
import static front.main.java.com.pio.floorislavafront.FloorIsLavaApp.getPrimaryStage;

public class DisplayHandler {
    private static ArrayList<PlayerData> previousPlayerData;

    private static final String SPRITE_IMAGE_BASE = "src/front/main/resources/com/pio/floorislavafront/images/sprites/";
    private static final String STATS_IMAGE_BASE = "src/front/main/resources/com/pio/floorislavafront/images/stats/";
    private static final ArrayList<InputStream> SAFE_ZONE_SPRITE_IMAGE = new ArrayList<>();
    private static final ArrayList<InputStream> LAVA_SPRITE_IMAGE = new ArrayList<>();
    private static final ArrayList<InputStream> HOLE_SPRITE_IMAGE = new ArrayList<>();
    private static final ArrayList<InputStream> FLOOR_SPRITE_IMAGE = new ArrayList<>();
    private static final InputStream BOOST_SPEED_IMAGE;
    private static final InputStream BOOST_GHOST_IMAGE;
    private static final InputStream PLAYER_SPRITE_RED_IMAGE;
    private static final InputStream PLAYER_SPRITE_BLACK_IMAGE;
    private static final InputStream PLAYER_SPRITE_PURPLE_IMAGE;
    private static final InputStream PLAYER_SPRITE_BLUE_IMAGE;
    public static String actualInstancePlayerName;

    private static final String BASE_SOUND_EFFECTS_PATH = "sound-effects/";
    private static final AudioClip hurt = new AudioClip(FloorIsLavaController.class.getResource(BASE_SOUND_EFFECTS_PATH.concat("hurt.mp3")).toExternalForm());

    private static final ArrayList<AudioClip> playersSteps = new ArrayList();
    private static final ArrayList<AudioClip> playersFastSteps =  new ArrayList();

    public static void initSounds() {
        playersSteps.add(new AudioClip(FloorIsLavaController.class.getResource(BASE_SOUND_EFFECTS_PATH.concat("footsteps1.mp3")).toExternalForm()));
        playersSteps.add(new AudioClip(FloorIsLavaController.class.getResource(BASE_SOUND_EFFECTS_PATH.concat("footsteps2.mp3")).toExternalForm()));
        playersSteps.add(new AudioClip(FloorIsLavaController.class.getResource(BASE_SOUND_EFFECTS_PATH.concat("footsteps3.mp3")).toExternalForm()));
        playersSteps.add(new AudioClip(FloorIsLavaController.class.getResource(BASE_SOUND_EFFECTS_PATH.concat("footsteps4.mp3")).toExternalForm()));

        playersFastSteps.add(new AudioClip(FloorIsLavaController.class.getResource(BASE_SOUND_EFFECTS_PATH.concat("boosted-footsteps1.mp3")).toExternalForm()));
        playersFastSteps.add(new AudioClip(FloorIsLavaController.class.getResource(BASE_SOUND_EFFECTS_PATH.concat("boosted-footsteps2.mp3")).toExternalForm()));
        playersFastSteps.add(new AudioClip(FloorIsLavaController.class.getResource(BASE_SOUND_EFFECTS_PATH.concat("boosted-footsteps3.mp3")).toExternalForm()));
        playersFastSteps.add(new AudioClip(FloorIsLavaController.class.getResource(BASE_SOUND_EFFECTS_PATH.concat("boosted-footsteps4.mp3")).toExternalForm()));
    }

    static {
        try {
            BOOST_GHOST_IMAGE = new FileInputStream(SPRITE_IMAGE_BASE.concat("ghost.png"));
            BOOST_SPEED_IMAGE = new FileInputStream(SPRITE_IMAGE_BASE.concat("speed.png"));

            PLAYER_SPRITE_RED_IMAGE = new FileInputStream(SPRITE_IMAGE_BASE.concat("red.png"));
            PLAYER_SPRITE_PURPLE_IMAGE = new FileInputStream(SPRITE_IMAGE_BASE.concat("purple.png"));
            PLAYER_SPRITE_BLACK_IMAGE = new FileInputStream(SPRITE_IMAGE_BASE.concat("black.png"));
            PLAYER_SPRITE_BLUE_IMAGE = new FileInputStream(SPRITE_IMAGE_BASE.concat("blue.png"));

            FLOOR_SPRITE_IMAGE.add(new FileInputStream(SPRITE_IMAGE_BASE.concat("floor1.png")));
            FLOOR_SPRITE_IMAGE.add(new FileInputStream(SPRITE_IMAGE_BASE.concat("floor2.png")));
            FLOOR_SPRITE_IMAGE.add(new FileInputStream(SPRITE_IMAGE_BASE.concat("floor3.png")));
            FLOOR_SPRITE_IMAGE.add(new FileInputStream(SPRITE_IMAGE_BASE.concat("floor4.png")));

            SAFE_ZONE_SPRITE_IMAGE.add(new FileInputStream(SPRITE_IMAGE_BASE.concat("safe_zone1.png")));
            SAFE_ZONE_SPRITE_IMAGE.add(new FileInputStream(SPRITE_IMAGE_BASE.concat("safe_zone2.png")));
            SAFE_ZONE_SPRITE_IMAGE.add(new FileInputStream(SPRITE_IMAGE_BASE.concat("safe_zone3.png")));
            SAFE_ZONE_SPRITE_IMAGE.add(new FileInputStream(SPRITE_IMAGE_BASE.concat("safe_zone4.png")));

            LAVA_SPRITE_IMAGE.add(new FileInputStream(SPRITE_IMAGE_BASE.concat("lava1.png")));
            LAVA_SPRITE_IMAGE.add(new FileInputStream(SPRITE_IMAGE_BASE.concat("lava2.png")));
            LAVA_SPRITE_IMAGE.add(new FileInputStream(SPRITE_IMAGE_BASE.concat("lava3.png")));
            LAVA_SPRITE_IMAGE.add(new FileInputStream(SPRITE_IMAGE_BASE.concat("lava4.png")));

            HOLE_SPRITE_IMAGE.add(new FileInputStream(SPRITE_IMAGE_BASE.concat("hole1.png")));
            HOLE_SPRITE_IMAGE.add(new FileInputStream(SPRITE_IMAGE_BASE.concat("hole2.png")));
            HOLE_SPRITE_IMAGE.add(new FileInputStream(SPRITE_IMAGE_BASE.concat("hole3.png")));
            HOLE_SPRITE_IMAGE.add(new FileInputStream(SPRITE_IMAGE_BASE.concat("hole4.png")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static final ImagePattern PLAYER_SPRITE_BLUE = new ImagePattern(new Image(PLAYER_SPRITE_BLUE_IMAGE));
    private static final ImagePattern PLAYER_SPRITE_RED = new ImagePattern(new Image(PLAYER_SPRITE_RED_IMAGE));
    private static final ImagePattern PLAYER_SPRITE_BLACK = new ImagePattern(new Image(PLAYER_SPRITE_BLACK_IMAGE));
    private static final ImagePattern PLAYER_SPRITE_PURPLE = new ImagePattern(new Image(PLAYER_SPRITE_PURPLE_IMAGE));
    private static final ImagePattern BOOST_SPEED = new ImagePattern(new Image(BOOST_SPEED_IMAGE));
    private static final ImagePattern BOOST_GHOST = new ImagePattern(new Image(BOOST_GHOST_IMAGE));
    private static final ArrayList<ImagePattern> FLOOR_SPRITES = new ArrayList<>();
    private static final ArrayList<ImagePattern> SAFE_ZONE_SPRITES = new ArrayList<>();
    private static final ArrayList<ImagePattern> LAVA_SPRITES = new ArrayList<>();
    private static final ArrayList<ImagePattern> HOLE_SPRITES = new ArrayList<>();

    public static void initTextures() {
        LAVA_SPRITE_IMAGE.forEach((texture) -> LAVA_SPRITES.add(new ImagePattern(new Image(texture))));
        HOLE_SPRITE_IMAGE.forEach((texture) -> HOLE_SPRITES.add(new ImagePattern(new Image(texture))));
        SAFE_ZONE_SPRITE_IMAGE.forEach((texture) -> SAFE_ZONE_SPRITES.add(new ImagePattern(new Image(texture))));
        FLOOR_SPRITE_IMAGE.forEach((texture) -> FLOOR_SPRITES.add(new ImagePattern(new Image(texture))));
    }

    private static ImagePattern getRandomTextureFromTexturesList(ArrayList<ImagePattern> textures) {
        int random = (int) Math.floor(Math.random() * textures.size());
        return textures.get(random);
    }

    private static ImagePattern getTextureFromTexturesList(ArrayList<ImagePattern> textures, int seed) {
        return textures.get(seed % textures.size());
    }

    private static void buildPlayerSprite(FieldType field, int col, int row, AnchorPane playersMap, ArrayList<PlayerData> playerData, String currentPlayerNickname) {
        ImageView sprite = new ImageView();
        Label name = new Label();
        int playerId = 0;

        switch (field) {
            case PLAYER_0:
                sprite.setImage(PLAYER_SPRITE_BLUE.getImage());
                playerId = 0;
                break;

            case PLAYER_1:
                sprite.setImage(PLAYER_SPRITE_RED.getImage());
                playerId = 1;
                break;

            case PLAYER_2:
                sprite.setImage(PLAYER_SPRITE_BLACK.getImage());
                playerId = 2;
                break;

            case PLAYER_3:
                sprite.setImage(PLAYER_SPRITE_PURPLE.getImage());
                playerId = 3;
                break;
        }

        name.setText(playerData.get(playerId).getNickname());
        double spriteWidth = 2 * SQUARE_SIZE;
        double spriteHeight = 3 * SQUARE_SIZE;
        double minNameWidth = 200;
        double baseLeftAnchor = (playersMap.getWidth() / 2 - (SQUARE_SIZE * WIDTH) / 2);

        if (currentPlayerNickname.equals(playerData.get(playerId).getNickname())) {
            Ellipse highlight = new Ellipse();
            highlight.setRadiusX(spriteWidth);
            highlight.setRadiusY(spriteHeight / 4);
            highlight.setFill(Color.YELLOW);
            highlight.setOpacity(0.65);
            playersMap.getChildren().add(highlight);

            AnchorPane.setBottomAnchor(highlight, (HEIGHT - row - 0.5) * SQUARE_SIZE * 1.0);
            AnchorPane.setLeftAnchor(highlight, baseLeftAnchor + (col - 0.5) * SQUARE_SIZE - spriteWidth / 2);
        }

        sprite.setFitHeight(spriteHeight);
        sprite.setFitWidth(spriteWidth);

        playersMap.getChildren().add(sprite);
        playersMap.getChildren().add(name);


        AnchorPane.setBottomAnchor(sprite, (HEIGHT - row) * SQUARE_SIZE * 1.0);
        AnchorPane.setLeftAnchor(sprite, baseLeftAnchor + (col - 0.5) * SQUARE_SIZE);

        name.setMinWidth(minNameWidth);
        name.setTextFill(Color.WHITE);
        name.setStyle("-fx-alignment: center;");

        AnchorPane.setBottomAnchor(name, (HEIGHT - row) * SQUARE_SIZE * 1.0 + SQUARE_SIZE + spriteHeight);
        AnchorPane.setLeftAnchor(name, baseLeftAnchor + (col + 0.5) * SQUARE_SIZE - name.getMinWidth() / 2);
    }

    private static Rectangle createSquare(FieldType fieldType, int col, int row) {
        Rectangle square = new Rectangle(SQUARE_SIZE, SQUARE_SIZE);

        switch (fieldType) {
            case PLAYER_0 -> square.setFill(PLAYER_SPRITE_BLUE);
            case PLAYER_1 -> square.setFill(PLAYER_SPRITE_RED);
            case PLAYER_2 -> square.setFill(PLAYER_SPRITE_BLACK);
            case PLAYER_3 -> square.setFill(PLAYER_SPRITE_PURPLE);
            case WALL -> square.setFill(Color.YELLOW);
            case BORDER -> square.setFill(Color.BLACK);
            case SAFE_ZONE -> square.setFill(getTextureFromTexturesList(SAFE_ZONE_SPRITES, col * 2 + row * 3));
            case FLOOR -> square.setFill(getTextureFromTexturesList(FLOOR_SPRITES, col * 2 + row * 3));
            case LAVA -> square.setFill(getRandomTextureFromTexturesList(LAVA_SPRITES));
            case HOLE -> square.setFill(getRandomTextureFromTexturesList(HOLE_SPRITES));
            case BOOST_SPEED -> square.setFill(BOOST_SPEED);
            case BOOST_GHOST -> square.setFill(BOOST_GHOST);
        }
        return square;
    }

    private static String buildDisplayedMessage(ArrayList<PlayerData> playerData, boolean isWaitingForPlayers, String winnerNickname) {
        int check = 0;

        for (int i=0; i < playerData.size(); i++)
        {
            PlayerData data = playerData.get(i);
            if (data.isAlive())
            {
                check++;
            }
        }

        if (check==0) return "Wszyscy nie żyją!";
        if (winnerNickname!=null) return ("Gracz " + winnerNickname + " wygrał rundę!");
        if (isWaitingForPlayers) {
            return "Oczekiwanie na graczy...";
        } else {
            return "Spróbuj przeżyć!";
        }
    }

    public static void displayMessage(String message) {
        Scene currentScene = getPrimaryStage().getScene();
        String containerId = "gameMessage";
        Node container = currentScene.lookup("#" + containerId);
        if (container instanceof Label) {
            Label timerLabel = (Label) container;
            timerLabel.setText(message);
        }
    }

    public static void displayTimer(int time){
        Scene currentScene = getPrimaryStage().getScene();
        String containerId = "timer";
        Node container = currentScene.lookup("#" + containerId);
        if (container instanceof Label)
        {
            Label timerLabel = (Label) container;
            timerLabel.setText(String.valueOf(time));
        }

    }

    public static void setHeartImage(Node container, boolean isAlive) {
        Image heart;
        if (isAlive) {
            heart = new Image("file:" + STATS_IMAGE_BASE.concat("alive.png"));
        }
        else {
            heart = new Image("file:" + STATS_IMAGE_BASE.concat("dead.png"));
        }
        if (container instanceof ImageView)
        {
            ImageView view = (ImageView) container;
            view.setImage(heart);
        }
    }

    public static void setConnectImage(Node container, boolean isConnected) {
        Image connect;
        if (isConnected) {
            connect = new Image("file:" + STATS_IMAGE_BASE.concat("online.png"));
        }
        else {
            connect = new Image("file:" + STATS_IMAGE_BASE.concat("offline.png"));
        }

        if (container instanceof ImageView)
        {
            ImageView view = (ImageView) container;
            view.setImage(connect);
        }
    }

    public static void setWinsLabel(Node container, int wins) {
        if (container instanceof Label)
        {
            Label winsLabel = (Label) container;
            winsLabel.setText(String.valueOf(wins));
        }
    }

    public static void setNameLabel(Node container, String username) {
        if (container instanceof Label)
        {
            Label nameLabel = (Label) container;
            nameLabel.setText(String.valueOf(username));
        }
    }

    public static void displayPlayerData(ArrayList<PlayerData> playerData){
        Scene currentScene = getPrimaryStage().getScene();
        Node container;
        String imageHeart;
        String imageConnect;
        String nameLabel;
        String winsLabel;

        for (int i=0; i< playerData.size(); i++)
        {
            PlayerData data = playerData.get(i);
            int id = data.getID();

            if (id != -1) {
                imageHeart = "P" + id + "_heart";
                imageConnect = "P" + id + "_connect";
                nameLabel = "P" + id + "_name";
                winsLabel = "P" + id + "_wins";
            }
            else {
                imageHeart = "P" + i + "_heart";
                imageConnect = "P" + i + "_connect";
                nameLabel = "P" + i + "_name";
                winsLabel = "P" + i + "_wins";
            }


            container = currentScene.lookup("#" + imageHeart);
            setHeartImage(container, data.isAlive());

            container = currentScene.lookup("#" + imageConnect);
            setConnectImage(container, data.isConnected());

            container = currentScene.lookup("#" + nameLabel);
            setNameLabel(container, data.getNickname());

            container = currentScene.lookup("#" + winsLabel);
            setWinsLabel(container, data.getGamesWon());
        }

    }

    public static void displayActualInstancePowerups(ArrayList<PlayerData> playerData) {
        for (int i=0; i < playerData.size(); i++)
        {
            PlayerData data = playerData.get(i);

            if (Objects.equals(data.getNickname(), actualInstancePlayerName)) {
                Scene currentScene = getPrimaryStage().getScene();
                String containerId = "gamescene";
                Node container = currentScene.lookup("#" + containerId);

                if (container instanceof AnchorPane) {
                    Node labelcont1 = container.lookup("#countSpeed");
                    Node labelcont2 = container.lookup("#countGhost");
                    Node iconcont1 = container.lookup("#iconSpeed");
                    Node iconcont2 = container.lookup("#iconGhost");

                    if (labelcont1 instanceof Label && labelcont2 instanceof Label && iconcont1 instanceof ImageView && iconcont2 instanceof ImageView){
                        Label countSpeed = (Label) labelcont1;
                        Label countGhost = (Label) labelcont2;
                        ImageView iconSpeed = (ImageView) iconcont1;
                        ImageView iconGhost = (ImageView) iconcont2;

                        if (data.getSpeedRounds() != 0) {
                            iconSpeed.setOpacity(100);
                        } else {
                            iconSpeed.setOpacity(0);
                        }
                        if (data.getGhostRounds() != 0) {
                            iconGhost.setOpacity(100);
                        } else {
                            iconGhost.setOpacity(0);
                        }

                        countSpeed.setText(String.valueOf(data.getSpeedRounds()));
                        countGhost.setText(String.valueOf(data.getGhostRounds()));
                    }
                }
                return;
            }
        }
    }

    public static void playFootsteps(int id, boolean speed, boolean ghost) {
       if (id < 0) return;
        if (speed) {
            playersFastSteps.get(id).play();
        } else {
            playersSteps.get(id).play();
        }
    }

    public static void updateFootstepsSound(int id, boolean speed, boolean ghost) {
        if (id < 0) return;
        playersSteps.get(id).stop();
        playersFastSteps.get(id).stop();
        playFootsteps(id, speed, ghost);
    }

    public static void playDeathSound() {
        hurt.play();
    }

    public static void handleMovementStop(int id) {
        if (id < 0) return;
        playersSteps.get(id).stop();
        playersFastSteps.get(id).stop();
    }

    private static void handleSoundEffects(ArrayList<PlayerData> playerData) {
        if (previousPlayerData == null || previousPlayerData.isEmpty()) return;
        playerData.forEach(currentPlayer -> {
            Optional<PlayerData> maybePlayerData = previousPlayerData.stream().filter(player -> Objects.equals(player.getNickname(), currentPlayer.getNickname())).findFirst();
            if (maybePlayerData.isPresent()) {
                PlayerData previousCurrentPlayerData = maybePlayerData.get();

                boolean previousAlive = previousCurrentPlayerData.isAlive();
                boolean previousMoving = previousCurrentPlayerData.isMoving();
                int previousSpeed = previousCurrentPlayerData.getSpeedRounds();
                int previousGhost = previousCurrentPlayerData.getGhostRounds();

                 if (!previousMoving && currentPlayer.isMoving()) {
                    playFootsteps(currentPlayer.getID(), currentPlayer.getSpeedRounds() > 0, currentPlayer.getGhostRounds() > 0);
                } else if (!currentPlayer.isMoving()) {
                    handleMovementStop(currentPlayer.getID());
                 } else if (previousGhost != currentPlayer.getGhostRounds() || previousSpeed != currentPlayer.getSpeedRounds()) {
                    updateFootstepsSound(currentPlayer.getID(), currentPlayer.getSpeedRounds() > 0, currentPlayer.getGhostRounds() > 0);
                }

                if (!currentPlayer.isAlive() && previousAlive) {
                    playDeathSound();
                }
            }
        });
    }

    public static void gameHandler(FieldType[][] map, Packet packet) {
        Scene currentScene = getPrimaryStage().getScene();
        String containerId = "gamescene";
        Node container = currentScene.lookup("#" + containerId);

        ArrayList<PlayerData> playerData = packet.getPlayerData();

        displayTimer(packet.getTimer());
        displayPlayerData(playerData);
        displayActualInstancePowerups(playerData);
        displayMessage(buildDisplayedMessage(playerData, packet.isWaitingForPlayers(), packet.getWinnerNickname()));

        handleSoundEffects(playerData);

        if (container instanceof AnchorPane) {
            AnchorPane myContainer = (AnchorPane) container;

            Node mapNode = container.lookup("#mymap");
            if (mapNode instanceof GridPane) {
                GridPane writtenMap = (GridPane) mapNode;
                myContainer.getChildren().remove(writtenMap);
            }

            GridPane myMap = new GridPane();
            myMap.setId("mymap");

            Pane playersMap = new GridPane();
            playersMap.setId("playersMap");

            myContainer.getChildren().add(myMap);
            myContainer.getChildren().add(playersMap);

            AnchorPane.setBottomAnchor(myMap, 10.0);
            AnchorPane.setLeftAnchor(myMap, (myContainer.getWidth() / 2 - (SQUARE_SIZE * WIDTH) / 2));
            myMap.setPrefSize(WIDTH * SQUARE_SIZE, HEIGHT * SQUARE_SIZE);

            for (int row = 0; row < HEIGHT; row++) {
                for (int col = 0; col < WIDTH; col++) {
                    Rectangle square = createSquare(map[row][col], col, row);
                    myMap.add(square, col, row);
                    if (PLAYER_FIELDS.contains(map[row][col])) {
                        buildPlayerSprite(map[row][col], col, row, myContainer, playerData, packet.getReceiverNickname());
                    }
                }
            }

        } else {
            System.err.println("Nie znaleziono kontenera o ID: " + containerId);
        }

        previousPlayerData = playerData;
    }
}