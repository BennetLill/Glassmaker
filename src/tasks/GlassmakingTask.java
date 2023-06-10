package tasks;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.Entity;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.Player;
import org.dreambot.api.wrappers.widgets.WidgetChild;

import static org.dreambot.api.methods.interactive.GameObjects.getTopObjectOnTile;

public class GlassmakingTask extends TaskNode {

    @Override
    public boolean accept() {
        return Inventory.contains("Soda ash") && Inventory.contains("Bucket of sand") && !isCrafting();
    }

    @Override
    public int execute() {
        if(!GameObjects.closest("Furnace").getSurroundingArea(3).contains(Players.getLocal().getTile())){
            walkToFurnace();
        }else{
            if(Widgets.getWidget(270).getChild(14)!=null && Widgets.getWidget(270).getChild(14).isVisible()){
                Widgets.getWidget(270).getChild(14).interact();
            }
            else{
                GameObjects.closest("Furnace").interact();
            }
        }
        return Calculations.random(500, 1000);
    }


    private int walkToFurnace() {
        GameObjects.closest("Furnace").interact();
        Sleep.sleepUntil(Walking::shouldWalk, () -> Players.getLocal().isMoving(), 1000, 100);
        return Calculations.random(300, 650);
    }

    private boolean isCrafting() {
        return Players.getLocal().isAnimating();
    }

}