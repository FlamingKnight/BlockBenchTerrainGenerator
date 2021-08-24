package io.github.flaming.bbmodeler;

import cn.nukkit.block.BlockID;
import cn.nukkit.plugin.PluginBase;

import java.util.ArrayList;
import java.util.List;

public class BBTerrainGenerator extends PluginBase {

    private static BBTerrainGenerator plugin;
    public List<Integer> blackListedBlockIDs;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        try {
            blackListedBlockIDs = getConfig().getList("Ignored-Block-IDs");
        } catch (Exception e) {
            blackListedBlockIDs = new ArrayList<Integer>(){
                {
                    add(0);
                    add(BlockID.INVISIBLE_BEDROCK);
                    add(212);
                }
            };
            getLogger().error("Something went wrong while initializing the BlockBenchTerrainGenerator configuration!");
            e.printStackTrace();
        }
        getServer().getCommandMap().register("blockbench", new CreationCommand());
    }

    public static BBTerrainGenerator getInstance() {
        return plugin;
    }
}
