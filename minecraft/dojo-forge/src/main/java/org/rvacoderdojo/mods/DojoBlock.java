package org.rvacoderdojo.mods;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * This is an extension of the Minecraft "Block" class.  It 
 * defines our Dojo Block and registers the block for usage.
 */
public class DojoBlock extends Block {

    // This is the name used to register the block.
	private final String registeredName = "dojoBlock";
	
	public DojoBlock() {
		// Base our block off of a Wood material
		// If you open up the Material class (Ctrl-Click on the word 
		// "Material" in Eclipse)  You can see all the known material types.
		super(Material.wood);  

		// Tell Minecraft about our new block
		GameRegistry.registerBlock(this,  getRegisteredName());

		// This is the display name you see in the game.
		setUnlocalizedName(DojoMod.MODID + "_" + getRegisteredName());

		// This defines which tab the block will appear on in the Crafting Table.
		setCreativeTab(CreativeTabs.tabBlock);
	}

	public String getRegisteredName() {
		return registeredName;
	}

}
