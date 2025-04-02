package eu.koboo.boatelevatorfix;


import eu.koboo.boatelevatorfix.listener.VehicleExitListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class BoatElevatorFixPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new VehicleExitListener(), this);
        super.onEnable();
    }
}
