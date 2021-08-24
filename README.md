# Block Bench Terrain Generator
This is a plugin for Nukkit, made in Java, which adds a 
command for you to use, to create a BlockBench model to 
edit and texture, this plugin can be useful for some stuff 
such as map selection. 
BlockBench isn't the most optimized, so from my testings, 
a 30x30x20 (Length, Width, Height) area seemed to be usable 
with a GTX 1650 GPU and an Intel i7-9750H CPU.

# Usage
First, to create a block model, you will need to run the command `/bbcreate ModelName minx,miny,minz maxx,maxy,maxz` to create your model.
Once created, if everything goes successfully, you will be told that a new BlockBench model has been created under the `BlockBenchModels` folder.

After creating the model, you can double click it to open it, once opened, it will appear blank, that's since **no block textures are implemented**. You will need to select paint, then select the texture, then paint to your desire!
A note about painting, **each block has a 16x16 texture to paint in**. Selecting edit, then the texture or a block, will show its area in the texture PNG, any paintings applied to one block, will apply for all of the same type!

# Notes
The maximum amount of blocks in the texture can be 63