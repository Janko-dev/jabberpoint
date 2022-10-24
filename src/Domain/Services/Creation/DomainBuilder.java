package Domain.Services.Creation;

/**
 * The domain builder interface supplies an abstraction on the deserialization process of an arbitrary data structure.
 * It specifies methods that are used by the domain director, and in particular specifies a {@code setConverter(Deserializer)} so that an arbitrary deserializer can be used as a strategy.
 * @param <T> Generic parameter that represents the result of the building process.
 */
public interface DomainBuilder<T> {
    public void reset();
    public void setConverter(Deserializer converter);
    public void setTitle();
    public void setAuthor();
    public void setDate();
    public void appendContent();
    public T getResult();

}
