package squeek.hungerinpeace;

import java.io.File;
import net.minecraftforge.common.config.Configuration;

public class ModConfig
{
	private static Configuration config;

	private static final String CATEGORY_MAIN = "main";

	public static boolean DISABLE_HEALTH_REGEN = ModConfig.DISABLE_HEALTH_REGEN_DEFAULT;
	private static final String DISABLE_HEALTH_REGEN_NAME = "disable.natural.health.regen";
	private static final boolean DISABLE_HEALTH_REGEN_DEFAULT = true;
	private static final String DISABLE_HEALTH_REGEN_COMMENT =
			"If true, disables the constant passive health regeneration in peaceful mode";

	public static float MIN_HEALTH_FROM_STARVATION = ModConfig.MIN_HEALTH_FROM_STARVATION_DEFAULT;
	private static final String MIN_HEALTH_FROM_STARVATION_NAME = "min.health.from.starvation";
	private static final float MIN_HEALTH_FROM_STARVATION_DEFAULT = 0f;
	private static final String MIN_HEALTH_FROM_STARVATION_COMMENT =
			"The lowest amount your health can go from starving in peaceful mode (0 means that you can die from starvation)";

	public static void init(File file)
	{
		config = new Configuration(file);

		config.load();

		config.getCategory(CATEGORY_MAIN);

		DISABLE_HEALTH_REGEN = config.get(CATEGORY_MAIN, DISABLE_HEALTH_REGEN_NAME, DISABLE_HEALTH_REGEN_DEFAULT, DISABLE_HEALTH_REGEN_COMMENT).getBoolean(true);
		MIN_HEALTH_FROM_STARVATION = (float) config.get(CATEGORY_MAIN, MIN_HEALTH_FROM_STARVATION_NAME, MIN_HEALTH_FROM_STARVATION_DEFAULT, MIN_HEALTH_FROM_STARVATION_COMMENT).getDouble(MIN_HEALTH_FROM_STARVATION_DEFAULT);

		config.save();
	}
}
