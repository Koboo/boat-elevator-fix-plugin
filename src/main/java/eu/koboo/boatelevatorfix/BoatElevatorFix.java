package eu.koboo.boatelevatorfix;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.BubbleColumn;
import org.bukkit.entity.Boat;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleBlockCollisionEvent;
import org.bukkit.event.vehicle.VehicleCollisionEvent;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.event.vehicle.VehicleUpdateEvent;
import org.bukkit.plugin.PluginLoadOrder;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.plugin.ApiVersion;
import org.bukkit.plugin.java.annotation.plugin.Description;
import org.bukkit.plugin.java.annotation.plugin.LoadOrder;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import org.bukkit.plugin.java.annotation.plugin.Website;
import org.bukkit.plugin.java.annotation.plugin.author.Author;
import org.bukkit.util.Vector;

@Plugin(name = "PROJECT_NAME", version = "PROJECT_VERSION")
@ApiVersion(ApiVersion.Target.v1_13)
@Author("PROJECT_GROUP")
@Description("PROJECT_DESCRIPTION")
@LoadOrder(PluginLoadOrder.POSTWORLD)
@Website("PROJECT_WEBSITE")
public class BoatElevatorFix extends JavaPlugin implements Listener {

    private static final List<BlockFace> SIDE_FACES = Arrays.asList(
        BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST);

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        super.onEnable();
    }

    @EventHandler
    public void onVehicleExit(VehicleExitEvent event) {
        if (event.isCancelled()) {
            System.out.println("Cancelled");
            return;
        }
        if (!(event.getVehicle() instanceof Boat)) {
            System.out.println("not a boat");
            return;
        }
        Boat boat = (Boat) event.getVehicle();
        LivingEntity exited = event.getExited();
        Block block = boat.getLocation().getBlock();
        boolean isBubbleColumn = true;
        if (!(block.getBlockData() instanceof BubbleColumn)) {
            isBubbleColumn = false;
            for (BlockFace sideFace : SIDE_FACES) {
                Block sideBlock = block.getRelative(sideFace);
                if (!(sideBlock.getBlockData() instanceof BubbleColumn)) {
                    continue;
                }
                isBubbleColumn = true;
            }
        }
        if (!isBubbleColumn) {
            System.out.println("Not a column");
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onCollision(VehicleCollisionEvent event) {
        if (event.getVehicle() instanceof Boat) {
            System.out.println("Collosion boat");
        }
    }

    @EventHandler
    public void onBlockCollision(VehicleBlockCollisionEvent event) {
        if (event.getVehicle() instanceof Boat) {
            System.out.println("block Collosion boat");
        }
    }

    @EventHandler
    public void onDamage(VehicleDamageEvent event) {
        if (event.getVehicle() instanceof Boat) {
            System.out.println("Damage boat");
        }
    }

    @EventHandler
    public void onEntityCollision(VehicleEntityCollisionEvent event) {
        if (event.getVehicle() instanceof Boat) {
            System.out.println("Entity Collision boat");
        }
    }

    @EventHandler
    public void onUpdate(VehicleUpdateEvent event) {
        if (!(event.getVehicle() instanceof Boat)) {
            return;
        }
        Boat boat = (Boat) event.getVehicle();
        Block block = boat.getLocation().getBlock();
        if (!(block.getBlockData() instanceof BubbleColumn)) {
            return;
        }
        Block above = block.getRelative(BlockFace.UP);
        if(above.getBlockData() instanceof BubbleColumn) {
            return;
        }
        //System.out.println("update boat " + boat.getVelocity());
    }

    @EventHandler
    public void onMove(VehicleMoveEvent event) {
        if (!(event.getVehicle() instanceof Boat)) {
            return;
        }
        Boat boat = (Boat) event.getVehicle();
        Block block = boat.getLocation().getBlock();
        if (!(block.getBlockData() instanceof BubbleColumn)) {
            return;
        }
        Block above = block.getRelative(BlockFace.UP);
        if(above.getBlockData() instanceof BubbleColumn) {
            return;
        }
        System.out.println("move boat " + boat.getVelocity());
        boat.setVelocity(new Vector(0, 0, 0));
    }
}
