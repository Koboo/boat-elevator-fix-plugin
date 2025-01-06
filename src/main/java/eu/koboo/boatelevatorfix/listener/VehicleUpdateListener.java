package eu.koboo.boatelevatorfix.listener;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.BubbleColumn;
import org.bukkit.entity.Boat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.util.Vector;

public class VehicleUpdateListener implements Listener {

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
        boat.setVelocity(new Vector(0, 0, 0));
    }
}
