package org.rvacoderdojo.mods;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class DojoBlock extends Block {

	private final String registeredName = "dojoBlock";
	
	public DojoBlock() {
		super(Material.wood);
		GameRegistry.registerBlock(this,  getRegisteredName());
		setUnlocalizedName(DojoMod.MODID + "_" + getRegisteredName());
		setCreativeTab(CreativeTabs.tabBlock);
	}

	public String getRegisteredName() {
		return registeredName;
	}

}
