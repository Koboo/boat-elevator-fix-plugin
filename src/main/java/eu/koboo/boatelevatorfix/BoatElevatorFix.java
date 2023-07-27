package eu.koboo.boatelevatorfix;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.BubbleColumn;
import org.bukkit.entity.Boat;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.plugin.PluginLoadOrder;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.plugin.ApiVersion;
import org.bukkit.plugin.java.annotation.plugin.Description;
import org.bukkit.plugin.java.annotation.plugin.LoadOrder;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import org.bukkit.plugin.java.annotation.plugin.Website;
import org.bukkit.plugin.java.annotation.plugin.author.Author;

@Plugin(name = "PROJECT_NAME", version = "PROJECT_VERSION")
@ApiVersion(ApiVersion.Target.v1_13)
@Author("PROJECT_GROUP")
@Description("PROJECT_DESCRIPTION")
@LoadOrder(PluginLoadOrder.POSTWORLD)
@Website("PROJECT_WEBSITE")
public class BoatElevatorFix extends JavaPlugin implements Listener {

  @Override
  public void onEnable() {
    Bukkit.getPluginManager().registerEvents(this, this);
    super.onEnable();
  }

  @Override
  public void onDisable() {
    super.onDisable();
  }

  @EventHandler
  public void onEject(VehicleExitEvent event) {
    if (event.isCancelled()) {
      return;
    }
    if (!(event.getVehicle() instanceof Boat)) {
      return;
    }
    Boat boat = (Boat) event.getVehicle();
    LivingEntity exited = event.getExited();
    Block eyeBlock = exited.getEyeLocation().getBlock();
    BlockData blockBlockData = eyeBlock.getBlockData();
    if (!(blockBlockData instanceof BubbleColumn)) {
      return;
    }
    event.setCancelled(true);
  }
}
