package utils;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.BoundingBox;

import com.microsoft.playwright.options.*;
//import javax.swing.*;

public class Commondefs {

    public static void dragAndDropElement(Page page, Locator source, Locator target) {
        // Get bounding box of source and target
        BoundingBox sourceBox = source.boundingBox();
        BoundingBox targetBox = target.boundingBox();

        if (sourceBox == null || targetBox == null) {
            throw new RuntimeException("Could not get bounding box for source or target");
        }

        // Perform mouse drag and drop simulation
        page.mouse().move(sourceBox.x + sourceBox.width / 2, sourceBox.y + sourceBox.height / 2);
        page.mouse().down();
        // Small delay may help to simulate drag
        page.waitForTimeout(100);
        page.mouse().move(targetBox.x + targetBox.width / 2, targetBox.y + targetBox.height / 2, new Mouse.MoveOptions().setSteps(10));
        page.mouse().up();
    }

//
//    public static void dragAndDropUsingMouse(Frame frame, String sourceSelector, String targetSelector) {
//        // Get bounding box of source element
//        BoundingBox sourceBox = frame.locator(sourceSelector).boundingBox();
//        BoundingBox targetBox = frame.locator(targetSelector).boundingBox();
//
//        if (sourceBox == null || targetBox == null) {
//            throw new RuntimeException("Element bounding box not found");
//        }
//
//        // Calculate center positions
//        double startX = sourceBox.x + sourceBox.width / 2;
//        double startY = sourceBox.y + sourceBox.height / 2;
//        double endX = targetBox.x + targetBox.width / 2;
//        double endY = targetBox.y + targetBox.height / 2;
//
//        // Use mouse API for drag and drop
//        frame.page().mouse().move(startX, startY);
//        frame.page().mouse().down();
//        // You can add small pauses if needed to simulate user drag
//        frame.page().mouse().move(endX, endY, new Mouse.MoveOptions().setSteps(10));
//        frame.page().mouse().up();
//    }
}
