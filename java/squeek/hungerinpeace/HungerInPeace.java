package squeek.hungerinpeace;

import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import squeek.applecore.api.hunger.ExhaustionEvent;
import squeek.applecore.api.hunger.HealthRegenEvent;
import squeek.applecore.api.hunger.StarvationEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = ModInfo.MODID, version = ModInfo.VERSION, dependencies = "required-after:AppleCore")
public class HungerInPeace
{
	public static final Logger Log = LogManager.getLogger(ModInfo.MODID);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		ModConfig.init(event.getSuggestedConfigurationFile());
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onExhausted(ExhaustionEvent.Exhausted event)
	{
		if (event.player.worldObj.difficultySetting != EnumDifficulty.PEACEFUL)
			return;

		if (event.player.getFoodStats().getSaturationLevel() <= 0)
			event.deltaHunger = -1;
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onPeacefulRegen(HealthRegenEvent.PeacefulRegen event)
	{
		if (!ModConfig.DISABLE_HEALTH_REGEN)
			return;

		event.setCanceled(true);
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onStarve(StarvationEvent.Starve event)
	{
		if (event.starveDamage != 0f)
			return;

		if (event.player.worldObj.difficultySetting != EnumDifficulty.PEACEFUL)
			return;

		if (event.player.getHealth() <= ModConfig.MIN_HEALTH_FROM_STARVATION)
			return;

		event.starveDamage = 1f;
	}
}
