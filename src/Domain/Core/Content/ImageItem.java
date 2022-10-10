package Domain.Core.Content;

import Domain.Core.SlideItem;
import Domain.Services.Visitors.DomainVisitor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageItem extends SlideItem {

    private String src;
    private BufferedImage buffer;
    private int width, height;

    public String getSrc() {
        return src;
    }

    public BufferedImage getBuffer() {
        return buffer;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ImageItem(String src, int width, int height){
        this.src = src;
        this.width = width;
        this.height = height;
        try {
            buffer = ImageIO.read(new File(src));
            if (buffer == null) buffer = new BufferedImage(1, 1, BufferedImage.TYPE_BYTE_GRAY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void accept(DomainVisitor v) {
        v.visitImageItem(this);
    }
}
