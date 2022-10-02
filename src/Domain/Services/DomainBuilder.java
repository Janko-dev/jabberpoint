package Domain.Services;

public interface DomainBuilder<T, U> {

    public void reset();
    public void setTitle(String title);
    public void setAuthor(String author);
    public void setDate(String date);

    public void appendSlide(U slide);

    public T getResult();

}
