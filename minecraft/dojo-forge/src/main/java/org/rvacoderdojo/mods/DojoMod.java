package org.rvacoderdojo.mods;

import java.util.logging.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = DojoMod.MODID, version = DojoMod.VERSION)
public class DojoMod
{
	// Create a logger that will prefix all statements with the MODID + "DojoEventHandler"
	private static Logger logger = Logger.getLogger(DojoMod.MODID + "-" 
			+ DojoMod.class.getName());
	
    public static final String MODID = "dojomod";
    public static final String VERSION = "1.0";
    
    private DojoBlock dojoBlock;
    private DojoEventHandler dojoEvents;

    @EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {
    	logger.info("Running Pre Init to setup the DojoBlock");
    	
    	//blocks
    	dojoBlock = new DojoBlock();
    	
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	// Create the EventHandler
    	DojoEventHandler dojoEvents = new DojoEventHandler();
    	
    	// Register the event handler (Tell Minecraft to send these events to our handler)
    	MinecraftForge.EVENT_BUS.register(dojoEvents);
    	
    	// 5 Diamond pick axes from a diamond shape set of oak blocks
    	// (5 diamond pick axes from 4 pieces of wood?  What a bargain!)
    	GameRegistry.addRecipe(new ItemStack(Items.diamond_pickaxe, 5), 
    			new Object[] {
    		" A ",
    		"A A",
    		" A ",
    		'A', Blocks.log
    	});
    	
    	// 1 Diamond chest plate from a square shape oak blocks
    	GameRegistry.addRecipe (new ItemStack(Items.diamond_chestplate, 1),
    			new Object[] {
    		"AAA",
    		"A A",
    		"AAA", 
    		'A', Blocks.log
    	});
    	
    	// Create 10 dojo blocks when you use 5 oak blocks in an X pattern.
    	GameRegistry.addRecipe(new ItemStack(dojoBlock, 10), 
    			new Object[] {
    			"A A",
    			" A ",
    			"A A", 
    			'A', Blocks.log
    	});
    	
    	if (event.getSide() == Side.CLIENT) {
    		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
    		String modelResourceName = MODID + ":" + dojoBlock.getRegisteredName();
    		ModelResourceLocation resourceLocation = new ModelResourceLocation(modelResourceName, "inventory");
    		renderItem.getItemModelMesher().register(Item.getItemFromBlock(dojoBlock), 0, resourceLocation);
    	}
    }
};