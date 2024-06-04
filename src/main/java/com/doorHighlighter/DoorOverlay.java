package com.doorHighlighter;

import lombok.extern.java.Log;
import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;

import javax.inject.Inject;
import java.awt.*;
import java.util.Collection;

import org.slf4j.Logger;

public class DoorOverlay extends Overlay {

    private final Client client;
    private final DoorHighlighterPlugin plugin;
    private final DoorHighlighterConfig config;

    @Inject
    public DoorOverlay(Client client, DoorHighlighterPlugin plugin, DoorHighlighterConfig config){

        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);
        this.plugin = plugin;
        this.config = config;
        this.client = client;
    }

    @Override
    public Dimension render(Graphics2D graphics2D) {
        WorldPoint playerLocation = client.getLocalPlayer().getWorldLocation();

        final Collection<WallObject> doors = plugin.getDoors();

        doors.forEach( (gameObject -> {
            if (gameObject.getWorldLocation().getPlane() != client.getPlane()){
                return;
            }
            if (gameObject.getWorldLocation().distanceTo(playerLocation) >= config.renderDistance())
            {
                return;
            }

            LocalPoint lp = LocalPoint.fromWorld(client, gameObject.getWorldLocation());
            if (lp == null)
            {
                return;
            }

            Shape poly = gameObject.getConvexHull();
            if (poly != null)
            {
                OverlayUtil.renderPolygon(graphics2D, poly, config.overlayColour());
            }
        }));
        return null;
    }

}



