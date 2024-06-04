package com.doorHighlighter;

import com.google.inject.Provides;
import javax.inject.Inject;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.*;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@PluginDescriptor(
		name = "Closed Door Highlighter"
)
public class DoorHighlighterPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private DoorHighlighterConfig config;

	@Getter
	private final List<WallObject> doors = new ArrayList<WallObject>();

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private DoorOverlay doorOverlay;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Closed Door Highlighter Started");
		overlayManager.add(doorOverlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(doorOverlay);
		doors.clear();
		log.info("Closed Door Highlighter Stop");
	}

	@Provides
	DoorHighlighterConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(DoorHighlighterConfig.class);
	}

	@Subscribe
	public void onWallObjectSpawned(WallObjectSpawned event)
	{
		if(Doors.DOOR_IDS.contains(event.getWallObject().getId())){
			doors.add(event.getWallObject());
		}
	}

	@Subscribe
	public void onWallObjectDespawned(WallObjectDespawned event)
	{
		if(Doors.DOOR_IDS.contains(event.getWallObject().getId())) {
			doors.remove(event.getWallObject());
		}
	}

}
