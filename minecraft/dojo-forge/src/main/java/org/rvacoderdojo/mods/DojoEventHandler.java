package org.rvacoderdojo.mods;

import java.util.logging.Logger;

import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DojoEventHandler {

	// Create a logger that will prefix all statements with the MODID + "DojoEventHandler"
	private static Logger logger = Logger.getLogger(DojoMod.MODID + "-" 
			+ DojoEventHandler.class.getName());
	
	// Makes dojo blocks unbreakable
	@SubscribeEvent
	public void onBlockBreakEvent (BreakEvent event) {
		if (event.state.getBlock() instanceof DojoBlock) {
			event.setCanceled(true);
			logger.info("Nobody breaks up the DOJO blocks!");
		}
	}
}
