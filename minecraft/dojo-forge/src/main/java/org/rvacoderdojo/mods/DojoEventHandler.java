package org.rvacoderdojo.mods;

import java.util.logging.Logger;

import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DojoEventHandler {

	// Create a logger that will prefix all statements with the MODID + "DojoEventHandler"
	private static Logger logger = Logger.getLogger(DojoMod.MODID + "-" 
			+ DojoEventHandler.class.getName());
	
	/**
	 * An event handler that will get called every time ANY block breaks.
	 */
	@SubscribeEvent
	public void onBlockBreakEvent (BreakEvent event) {

		// Since this gets called for all block types, we need to 
		// figure out if the block that was broken is a DojoBlock
		if (event.state.getBlock() instanceof DojoBlock) {

			// If it was a "DojoBlock" tell Minecraft to "cancel" the event
			// (thereby making the block unbreakable)
			event.setCanceled(true);

			// This shows how to log statements out to the Launcher's console.
			logger.info("Nobody breaks up the DOJO blocks!");
		}
	}
}
