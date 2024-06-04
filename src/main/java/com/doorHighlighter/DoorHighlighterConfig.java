package com.doorHighlighter;

import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import java.awt.*;

@ConfigGroup("DoorHighlighter")
public interface DoorHighlighterConfig extends Config
{
	@Alpha
	@ConfigItem(
		keyName = "overlayColour",
		name = "Overlay Color",
		description = "Color to highlight door"
	)
	default Color overlayColour() {
		return Color.YELLOW;
	}


	@ConfigItem(
			keyName = "renderDistance",
			name = "Render Distance",
			description = "Maximum Distance To Render door highlights"
	)
	default int renderDistance() {
		return 2350;
	}
}
