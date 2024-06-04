package com.doorHighlighter;

import net.runelite.api.Client;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;

import javax.inject.Inject;
import java.awt.*;

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
        LocalPoint playerLocation = client.getLocalPlayer().getLocalLocation();
        //get convexHull
        plugin.getDoors().forEach( (gameObject -> {
            if (gameObject.getPlane() == client.getPlane()
                    && gameObject.getLocalLocation().distanceTo(playerLocation) < config.renderDistance()) {
                OverlayUtil.renderPolygon(graphics2D, gameObject.getConvexHull(), config.overlayColour());
            }
        }));
        return null;
    }

}



