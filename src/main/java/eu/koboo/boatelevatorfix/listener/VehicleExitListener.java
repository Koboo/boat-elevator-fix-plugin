package eu.koboo.boatelevatorfix.listener;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.BubbleColumn;
import org.bukkit.entity.Boat;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleExitEvent;

import java.util.Arrays;
import java.util.List;

public class VehicleExitListener implements Listener {

    private static final List<BlockFace> SIDE_FACES = Arrays.asList(
        BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST
    );

    @EventHandler
    public void onVehicleExit(VehicleExitEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (!(event.getVehicle() instanceof Boat)) {
            return;
        }
        Boat boat = (Boat) event.getVehicle();
        Block block = boat.getLocation().getBlock();
        if (!(block.getBlockData() instanceof BubbleColumn)) {
            return;
        }
        LivingEntity exited = event.getExited();
        if(!(exited instanceof Player)) {
            return;
        }
        Player player = (Player) exited;
        Block headBlock = player.getEyeLocation().getBlock();
        if(!(headBlock.getBlockData() instanceof BubbleColumn)) {
            return;
        }
        event.setCancelled(true);
    }
}
