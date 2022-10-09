package Domain.Services.Creation;

public interface DomainBuilder<T> {

    public void reset();
    public void setConverter(Deserializer converter);
    public void setTitle();
    public void setAuthor();
    public void setDate();
    public void appendContent();
    public T getResult();

}
