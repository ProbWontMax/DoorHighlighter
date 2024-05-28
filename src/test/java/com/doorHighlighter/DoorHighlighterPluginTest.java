package com.doorHighlighter;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class DoorHighlighterPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(DoorHighlighterPlugin.class);
		RuneLite.main(args);
	}
}