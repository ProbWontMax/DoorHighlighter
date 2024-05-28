package com.doorHighlighter;

import net.runelite.api.Client;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;

import javax.inject.Inject;
import java.awt.*;

public class DoorOverlay extends Overlay {


    private final DoorHighlighterPlugin plugin;
    private final DoorHighlighterConfig config;

    @Inject
    public DoorOverlay(DoorHighlighterPlugin plugin, DoorHighlighterConfig config){

        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);
        this.plugin = plugin;
        this.config = config;
    }

    @Override
    public Dimension render(Graphics2D graphics2D) {
        //get convexHull
        plugin.getDoors().forEach( (gameObject -> {
            OverlayUtil.renderPolygon(graphics2D,gameObject.getConvexHull(), Color.BLUE);
        }));
        return null;
    }

}



