/******
 * Student name: Doreen Vaserman
 * Student ID: 308223627
 * Student name: Nadav Spitzer
 * Student ID: 302228275
 */
package Collision;

import Enums.LevelEnum;
import Scene.Sounds;
import View.ViewManager;
import WorldObjects.BreakableCube;
import WorldObjects.Player;
import WorldObjects.World;

public class CollisionHandler {

    public void stopMovement() {

    }

    /****
     * handle breakable box.
     * decrease life or remove from world.
     * @param c the current box.
     */
    public static void vanish(BreakableCube c) {
        if (c.getHp() > 0) {
            c.decreaseHp();
        } else {
            World.getInstance().removeFromList(c);
        }
    }

    /****
     * teleport to the next level.
     */
    public static void nextLevel(){
        Sounds.makeSound("resources/sounds/portal.wav");
        Player.setLevel(LevelEnum.LEVEL_2);
        ViewManager.getInstance().drawBar();
        World.getInstance().moveToLevel2();
    }

    /****
     * handle loss.
     * decrease player's lives or stop game and show lose window.
     */
    public static void lose(){
        Sounds.makeSound("resources/sounds/pain.wav");
        Player.decreaseLives();
        ViewManager manager = ViewManager.getInstance();
        manager.drawBar();

    }
}
