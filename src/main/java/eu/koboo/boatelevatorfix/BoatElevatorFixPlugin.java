package eu.koboo.boatelevatorfix;


import eu.koboo.boatelevatorfix.listener.VehicleExitListener;
import eu.koboo.boatelevatorfix.listener.VehicleUpdateListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class BoatElevatorFixPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new VehicleExitListener(), this);
        Bukkit.getPluginManager().registerEvents(new VehicleUpdateListener(), this);
        super.onEnable();
    }
}
