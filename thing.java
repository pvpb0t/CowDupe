package me.pvpb0t.callisto.impl.module.exploit;

import me.pvpb0t.callisto.api.command.Command;
import me.pvpb0t.callisto.api.module.Category;
import me.pvpb0t.callisto.api.module.Module;
import me.pvpb0t.callisto.api.setting.Setting;
import me.pvpb0t.callisto.api.util.logger.Logger;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumHand;

public class CowDupe extends Module {


    private final Setting factor = new Setting("Factor", this, 150, 10, 999);


    public CowDupe(String name, String description, Category category)
    {
        super(name, description, category);

        addSetting(factor);
    }

    @Override
    public void onEnable()
    {
        if (mc.player.inventory.getCurrentItem().getItem().equals(Items.SHEARS)) {
            for (int i = 0; i < factor.getIntegerValue(); ++i) {
                if (mc.pointedEntity == null) continue;
                mc.getConnection().sendPacket(new CPacketUseEntity(mc.pointedEntity, EnumHand.MAIN_HAND));
            }
            Logger.getLogger().printToChat("Finished shearing targeted entity.");

        } else {
            Logger.getLogger().printToChat("You need to hold shears to do the glitch.");
        }
        this.disable();
    }


}
