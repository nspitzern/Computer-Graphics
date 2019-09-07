package View;

import Scene.Sounds;
import WorldObjects.Player;
import com.jogamp.opengl.util.Animator;
import javax.swing.*;

/***
 * Singleton that manages the view windows.
 */
public class ViewManager {
    private static ViewManager viewManager;
    private JFrame game_frame;
    private JFrame menu_frame;
    private Animator animator;
    private JLabel LivesLabel;

    /**
     * Constructor.
     */
    private ViewManager(){ }

    /**
     * Returns the classes instance.
     * @return the classes current instance
     */
    public static ViewManager getInstance() {
        if(viewManager == null) {
            viewManager = new ViewManager();
        }
        return viewManager;
    }

    /****
     * sets the game frame.
     * @param frame a new frame.
     */
    public void setGameFrame(JFrame frame){
        this.game_frame = frame;
    }

    /***
     * sets the menu frame.
     * @param frame a new frame.
     */
    public void setMenuFrame(JFrame frame){
        this.menu_frame = frame;
    }

    /****
     * open the lose window.
     */
    public void lose() {
        showWindow("resources/pics/lose.jpg", "");
    }

    /****
     * open the win window.
     */
    public void win() {
        showWindow("resources/pics/win.jpg", "                 Winner, Great Job!");
    }

    /***
     * opens the main menu window.
     */
    public void mainMenu() {
        showWindow("resources/pics/temple.jpg","Can You Escape the Temple of Doom?");
    }

    /****
     * shows a window.
     * @param picPath the picture path.
     * @param labelText the label text.
     */
    private void showWindow(String picPath, String labelText) {
        Sounds.emptySounds();
        Menu menu = new Menu();
        menu.menu_window(picPath,labelText);
        if(game_frame != null){
            animator.stop();
            game_frame.dispose();
        }
    }

    /****
     * open the instructions window.
     */
    public void showInstructions() {
        Instructions instructions = new Instructions();
        instructions.instructions();
    }

    /****
     * Start a new game.
     */
    protected void startGame(){
        Sounds.emptySounds();
        Game game = new Game();
        game.game_window();
        if(menu_frame != null){
            menu_frame.dispose();
        }
    }

    /***
     * set the animator.
     * @param animator a new animator.
     */
    protected void setAnimator(Animator animator) {
        this.animator = animator;
    }

    /****
     * sets the number of lives in the lives label.
     * @param label the label.
     */
    public void setLivesLabel(JLabel label) {
        this.LivesLabel = label;
    }

    /****
     * draw the lives bar on the screen.
     */
    public void drawBar(){
        if (!this.LivesLabel.isVisible()) {
            this.LivesLabel.setVisible(true);
        }
        this.LivesLabel.setText("Level: "+Player.getLevel()+", Lives: " + Player.getLives());
    }
}
