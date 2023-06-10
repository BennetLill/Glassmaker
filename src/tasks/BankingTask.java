package tasks;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.Player;

public class BankingTask extends TaskNode {



    @Override
    public boolean accept() {
        return !Inventory.contains("Soda ash") && !Inventory.contains("Bucket of sand");
    }

    @Override
    public int execute() {
        if (Bank.open()) {

            Bank.depositAllItems();
            Calculations.random(300, 600);
            if(Bank.contains("Bucket of sand", "Soda ash")){
                Bank.withdraw("Bucket of sand", 14);
                Calculations.random(300, 600);
                Bank.withdraw("Soda ash", 14);
                Calculations.random(500, 700);
            }
        } else {
            Sleep.sleepUntil(Walking::shouldWalk, () -> Players.getLocal().isMoving(), 1000, 100);
            return 100;
        }
        return Calculations.random(300, 600);
    }
}
