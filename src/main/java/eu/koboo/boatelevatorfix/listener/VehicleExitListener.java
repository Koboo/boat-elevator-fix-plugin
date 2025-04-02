package eu.koboo.boatelevatorfix.listener;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.BubbleColumn;
import org.bukkit.entity.Boat;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.util.Vector;

public class VehicleExitListener implements Listener {

    @EventHandler
    public void onVehicleExit(VehicleExitEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (!(event.getVehicle() instanceof Boat)) {
            return;
        }
        Boat boat = (Boat) event.getVehicle();
        LivingEntity exited = event.getExited();
        if (!(exited instanceof Player)) {
            return;
        }
        Block boatBlock = boat.getLocation().getBlock();
        if (!(boatBlock.getBlockData() instanceof BubbleColumn)) {
            return;
        }
        Player player = (Player) exited;
        Block playerBlock = player.getLocation().getBlock();
        if (!(playerBlock.getBlockData() instanceof BubbleColumn)) {
            return;
        }
        Vector velocity = boat.getVelocity();
        double heightVelocity = velocity.getY();
        if (heightVelocity == 0.0) {
            // Boat stopped travelling upwards, since we are still in a bubble column
            // we need to allow ejection by the player himself.
            return;
        }
        event.setCancelled(true);
    }
}
