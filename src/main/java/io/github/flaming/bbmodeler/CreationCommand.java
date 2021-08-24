package io.github.flaming.bbmodeler;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.math.Vector3;
import cn.nukkit.utils.TextFormat;
import com.google.gson.Gson;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import io.github.flaming.bbmodeler.bbobjects.*;
import io.github.flaming.bbmodeler.math.Vector2i;
import io.github.flaming.bbmodeler.math.Vector3i;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.UUID;

public class CreationCommand extends Command {

    private final Gson gson = new Gson();

    public CreationCommand() {
        super("bbcreate");
        setDescription("A command to make a model out of the existing area around you!");
        commandParameters.put("default", new CommandParameter[]{
                CommandParameter.newType("ModelName", CommandParamType.STRING),
                CommandParameter.newType("Pos1", CommandParamType.BLOCK_POSITION),
                CommandParameter.newType("Pos2", CommandParamType.BLOCK_POSITION)
        });
        setPermission("blockbench.createmodel");
        //TODO: Idea, change it to a string as *,*,* or x,y,z?
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(TextFormat.RED + "You aren't a player! You should know how to use JSON");
            return false;
        }

        if(args.length == 0) {
            sender.sendMessage(TextFormat.RED + "Please specify a name for the model!");
            return false;
        }
        if(args.length == 1) {
            sender.sendMessage(TextFormat.RED + "Please specify position 1 as x,y,z!");
            return false;
        }
        if(args.length == 2) {
            sender.sendMessage(TextFormat.RED + "Please specify position 2 as x,y,z!");
            return false;
        }

        String modelName = args[0];
        String[] firstLocString = args[1].split(",");
        if(firstLocString.length <= 3) {
            sender.sendMessage(TextFormat.RED + "Something is wrong with your first position, make sure it follows the format x,y,z!");
            return false;
        }
        final Vector3i firstLocation;
        final Vector3i secondLocation;
        try {
            firstLocation = new Vector3i((int) Double.parseDouble(firstLocString[0]), (int) Double.parseDouble(firstLocString[1]), (int) Double.parseDouble(firstLocString[2]));
        } catch (Exception e) {
            sender.sendMessage(TextFormat.RED + "Something is wrong with your second position, make sure it follows the format x,y,z!");
            return false;
        }
        String[] secondLocString = args[2].split(",");
        if(secondLocString.length < 3) {
            sender.sendMessage(TextFormat.RED + "Something is wrong with your second position, make sure it follows the format x,y,z!");
            return false;
        }
        try {
            secondLocation = new Vector3i((int) Double.parseDouble(secondLocString[0]), (int) Double.parseDouble(secondLocString[1]), (int) Double.parseDouble(secondLocString[2]));
        } catch (Exception e) {
            sender.sendMessage(TextFormat.RED + "Something is wrong with your second position, make sure it follows the format x,y,z!");
            return false;
        }

        final Vector3i minLocation = new Vector3i(
                Math.min(firstLocation.x, secondLocation.x),
                Math.min(firstLocation.x, secondLocation.x),
                Math.min(firstLocation.x, secondLocation.x)
        );
        final Vector3i maxLocation = new Vector3i(
                Math.max(firstLocation.x, secondLocation.x),
                Math.max(firstLocation.x, secondLocation.x),
                Math.max(firstLocation.x, secondLocation.x)
        );

        Player player = (Player) sender;
        player.sendMessage(TextFormat.GREEN + "Starting to generate the BlockBench model! This may lag or take a while!");
        player.sendMessage(TextFormat.RED + "If it doesn't complete, you may not have enough dedicated RAM to run this!");
        player.sendMessage(TextFormat.RED + "Keep in mind, with current limitations there can only be 64 different types of blocks that appear in the blockbench model!");
        Main.getInstance().getServer().getScheduler().scheduleDelayedTask(Main.getInstance(), () -> {
            LinkedHashMap<Integer, Vector2i> uvLocations = new LinkedHashMap<>();
            int uvOffsetX = 0;
            int uvOffsetY = 0;
            ArrayList<BBLayer> layers = new ArrayList<>();
            for(int y = minLocation.y; y < maxLocation.y; y++) {
                BBLayer bbLayer = new BBLayer("BBLayer" + y, new int[]{0, 0, 0}, "", UUID.randomUUID(), true, false, false, true, 0, new ArrayList<>());
                for(int x = minLocation.x; x < maxLocation.x; x++) {
                    for(int z = minLocation.z; z < maxLocation.z; z++) {
                        Block block = player.getLevel().getBlock(new Vector3(x, y, z));
                        if(block.getId() != 0 && block.getId() != BlockID.INVISIBLE_BEDROCK) {
                            if(!uvLocations.containsKey(block.getId())) {
                                if(uvOffsetX < 224) {
                                    uvOffsetX += 32;
                                } else {
                                    uvOffsetX = 0;
                                    uvOffsetY += 32;
                                }
                                uvLocations.put(block.getId(), new Vector2i(uvOffsetX, uvOffsetY));
                            }
                            Vector2i offset = uvLocations.get(block.getId());
                            bbLayer.addCube(new BBCube(
                                    block.getName() + " " + x + " " + z, false,
                                    new Vector3i(x, y-minLocation.y, z), new Vector3i(x+1, y-minLocation.y+1, z+1),
                                    0, 0, false,
                                    new int[]{x, y, z}, new int[]{offset.x, offset.y}, BBFace.getOffsetMap(offset)
                            ));
                        }
                    }
                }
                if(!bbLayer.getTransientChildren().isEmpty()) layers.add(bbLayer);
            }

            ArrayList<BBTexture> bbTextures = new ArrayList<>();
            File bbModelsPath = new File("BlockBenchModels/");
            if(!bbModelsPath.exists()) bbModelsPath.mkdirs();

            try {
                BufferedImage bufferedImage = new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);
                File file = new File("BlockBenchModels/" + modelName + ".png");
                ImageIO.write(bufferedImage, "PNG", file);
                FileInputStream fileInputStreamReader = new FileInputStream(file);
                byte[] bytes = new byte[(int)file.length()];
                fileInputStreamReader.read(bytes);
                String source = Base64.encode(bytes);

                BBTexture bbTexture = new BBTexture(
                        file.getAbsolutePath(), modelName + ".png",
                        "", "",
                        "0", source
                );
                bbTextures.add(bbTexture);
            } catch (Exception e) {
                player.sendMessage(TextFormat.RED + "Something went wrong while generating the texture!");
                e.printStackTrace();
            }

            BBModel bbModel = new BBModel(
                    modelName, modelName,
                    getCubes(layers), layers, bbTextures
            );

            writeFile(new File("BlockBenchModels/"+modelName + ".bbmodel"), gson.toJson(bbModel, BBModel.class));
            player.sendMessage(TextFormat.GREEN + "" + TextFormat.BOLD + "Done generating BlockBench model! You can find it under BlockBenchModels/" + modelName + ".bbmodel");
        }, 1, true);
        return false;
    }

    public ArrayList<BBCube> getCubes(ArrayList<BBLayer> layers) {
        ArrayList<BBCube> cubes = new ArrayList<>();
        layers.forEach(layer -> cubes.addAll(layer.getTransientChildren()));
        return cubes;
    }

    public static void writeFile(File fileName, String toWrite) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            for(int i = 0; i < toWrite.length(); i++) {
                fileWriter.write(toWrite.charAt(i));
            }
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
