package Domain.Core.Content;

import Domain.Core.Iterator.Iterator;
import Domain.Core.SlideItem;
import Domain.Services.Visitor;
import com.sun.javafx.iio.ImageStorage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageItem extends SlideItem {

    public String src;
    public BufferedImage buffer;

    public ImageItem(String src){
        this.src = src;
        try {
            buffer = ImageIO.read(new File(src));
            if (buffer == null) buffer = new BufferedImage(1, 1, BufferedImage.TYPE_BYTE_GRAY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Iterator<SlideItem> createIterator() {
        return null;
    }

    @Override
    public void accept(Visitor v) {
        v.visitImageItem(this);
    }
}
