import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;


public class IsoCoreKeyBinding {


    static Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    private static volatile boolean moveUp = false;
    private static volatile boolean moveDown = false;
    private static volatile boolean moveLeft = false;
    private static volatile boolean moveRight = false;

    public IsoCoreKeyBinding(int mouseOctaveNum) {
    }

    public static Keys press(int key) {
        if (key == 62) {
            robot.keyPress(KeyEvent.VK_A); // left
            return Keys.KEY_A;
        }
        if (key == 64) {
            robot.keyPress(KeyEvent.VK_W); // up
            return Keys.KEY_A;
        }
        if (key == 65) {
            robot.keyPress(KeyEvent.VK_D); // right
            return Keys.KEY_D;
        }
        if (key == 60) {
            robot.keyPress(KeyEvent.VK_S); // down
            return Keys.KEY_S;
        }
        if (key == 67) {
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // attack/punch
            return Keys.Left_Mouse_Button;
        }
        if (key == 48) {
            pressDirection("down");
            return Keys.Down_Mouse_Movement;
        }
        if (key == 50) {
            pressDirection("up");
            return Keys.Up_Mouse_Movement;
        }
        if (key == 52) {
            pressDirection("left");
            return Keys.Left_Mouse_Movement;
        }
        if (key == 53) {
            pressDirection("right");
            return Keys.Right_Mouse_Movement;
        }
        return null;
    }

    public static void release(int key) {
        if (key == 62) robot.keyRelease(KeyEvent.VK_A);
        if (key == 64) robot.keyRelease(KeyEvent.VK_W);
        if (key == 65) robot.keyRelease(KeyEvent.VK_D);
        if (key == 60) robot.keyRelease(KeyEvent.VK_S);

        if (key == 67) robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        if (key == 48) releaseDirection("down");
        if (key == 50) releaseDirection("up");
        if (key == 52) releaseDirection("left");
        if (key == 53) releaseDirection("right");
    }

    private static void startMovement(String direction) {
        new Thread(() -> {
            try {
                while (true) {
                    if (direction.equals("up") && moveUp) {
                        Point p = MouseInfo.getPointerInfo().getLocation();
                        robot.mouseMove(p.x, p.y - 1);
                    }
                    if (direction.equals("down") && moveDown) {
                        Point p = MouseInfo.getPointerInfo().getLocation();
                        robot.mouseMove(p.x, p.y + 1);
                    }
                    if (direction.equals("left") && moveLeft) {
                        Point p = MouseInfo.getPointerInfo().getLocation();
                        robot.mouseMove(p.x - 1, p.y);
                    }
                    if (direction.equals("right") && moveRight) {
                        Point p = MouseInfo.getPointerInfo().getLocation();
                        robot.mouseMove(p.x + 1, p.y);
                    }

                    Thread.sleep(10); // Adjust speed (lower = faster)
                }
            } catch (InterruptedException ignored) {
            }
        }).start();
    }

    // Methods to call from MIDI/N keys
    public static void pressDirection(String dir) {
        switch (dir) {
            case "up" -> {
                if (!moveUp) {
                    moveUp = true;
                    startMovement("up");
                }
            }
            case "down" -> {
                if (!moveDown) {
                    moveDown = true;
                    startMovement("down");
                }
            }
            case "left" -> {
                if (!moveLeft) {
                    moveLeft = true;
                    startMovement("left");
                }
            }
            case "right" -> {
                if (!moveRight) {
                    moveRight = true;
                    startMovement("right");
                }
            }
        }
    }

    public static void releaseDirection(String dir) {
        switch (dir) {
            case "up" -> moveUp = false;
            case "down" -> moveDown = false;
            case "left" -> moveLeft = false;
            case "right" -> moveRight = false;
        }
    }
}
