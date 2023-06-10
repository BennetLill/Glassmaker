import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.SkillTracker;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.impl.TaskScript;
import tasks.BankingTask;
import tasks.GlassmakingTask;

import java.awt.*;

// Every script needs a ScriptManifest so it can be seen in the script manager
@ScriptManifest(category = Category.CRAFTING, name = "GlassMaker", description = "Makes molten glass", author = "Cree", version = 1.0)
public class Glassmaker  extends TaskScript {

    @Override
    public void onStart() {
        // Start DreamBot's skill tracker for the mining skill, so we can later see how much experience we've gained
        SkillTracker.start(Skill.CRAFTING);

        // Now add our two tasks so the client knows what to do
        addNodes(new GlassmakingTask(), new BankingTask());
    }

    @Override
    public void onPaint(Graphics g) {
        String experienceGainedText = String.format(
                "IDK HOW TO DO PAINT!!!! Crafting Experience: %d (%d per hour)",
                SkillTracker.getGainedExperience(Skill.CRAFTING),
                SkillTracker.getGainedExperiencePerHour(Skill.CRAFTING)
        );
        g.drawString(experienceGainedText, 5, 35);
    }

}
