package Domain.Services;

public interface DomainBuilder<T> {

    public void reset();
    public void setConverter(Deserializable converter);
    public void setTitle();
    public void setAuthor();
    public void setDate();
    public void appendSlides();

    public T getResult();

}
