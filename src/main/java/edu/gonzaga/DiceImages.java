package edu.gonzaga;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class DiceImages {
    ArrayList<ImageIcon> images;

    void loadImages(String imagesPath) {
        BufferedImage currPicture;
        images.add(null);
        for (int i = 1; i < 13; i++) {
            try {
                String filename = imagesPath + "/D6-" + i + ".png";
                currPicture = ImageIO.read(new File(filename));
                Image dimg = currPicture.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                ImageIcon scaledImage = new ImageIcon(dimg);
                images.add(scaledImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public DiceImages(String imagesPath) {
        images = new ArrayList<>(12);
        loadImages(imagesPath);
    }

    /**
     * This function returns the image of the die that corresponds to the die value
     * 
     * @param dieValue The value of the die.
     * @return The image of the die.
     */
    public ImageIcon getDieImage(int dieValue) {
        return images.get(dieValue);
    }
}