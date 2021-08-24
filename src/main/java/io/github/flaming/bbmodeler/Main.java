package io.github.flaming.bbmodeler;

import cn.nukkit.plugin.PluginBase;

public class Main extends PluginBase {

    private static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getServer().getCommandMap().register("blockbench", new CreationCommand());
    }

    public static Main getInstance() {
        return plugin;
    }
}
