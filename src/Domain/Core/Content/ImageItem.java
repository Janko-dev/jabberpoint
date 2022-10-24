package Domain.Core.Content;

import Domain.Services.Visitors.DomainVisitor;

/**
 * Concrete leaf of the slide show components that represents an image item as
 * - a source file path
 * - a width as integer, and
 * - a height as integer
 */
public class ImageItem extends SlideItem {

    private String src;
    private int width, height;

    public String getSrc() {
        return src;
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
    }

    /**
     * The accept method that makes this class visitable.
     * calls the {@code visitImageItem(ImageItem)} method, passing itself as a parameter.
     * @param v Injected visitor of type {@code DomainVisitor}
     */
    @Override
    public void accept(DomainVisitor v) {
        v.visitImageItem(this);
    }
}
