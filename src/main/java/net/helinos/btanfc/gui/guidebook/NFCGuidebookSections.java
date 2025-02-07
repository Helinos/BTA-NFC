package net.helinos.btanfc.gui.guidebook;

import net.helinos.btanfc.gui.guidebook.carpentry.GuidebookSectionCarpentry;
import net.minecraft.client.gui.guidebook.GuidebookSection;
import net.minecraft.client.gui.guidebook.GuidebookSections;

public abstract class NFCGuidebookSections {
    private static boolean initialized = false;

    public static GuidebookSection CARPENTRY;

    public static void init() {
        if (!initialized) {
            initialized = true;
            GuidebookSections.init();
            CARPENTRY = GuidebookSections.register(new GuidebookSectionCarpentry());
        }
    }
}
