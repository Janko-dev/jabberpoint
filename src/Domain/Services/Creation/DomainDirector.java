package Domain.Services.Creation;

import Infrastructure.Reader;

/**
 * Specification of the domain director responsible for orchestrating the building process of type {@code <T>}.
 * @param <T> Generic that ensures that the builder builds to a specific type and that the director returns the same type.
 */
public abstract class DomainDirector<T> {

    protected DomainBuilder<T> builder;
    protected Reader reader;

    /**
     * Set builder to a provided builder.
     * @param builder instance of {@code DomainBuilder<T>}
     */
    public void changeBuilder(DomainBuilder<T> builder){
        this.builder = builder;
    }

    /**
     * responsible for creating an instance of type {@code <T>}
     * @param filePath a String containing the file path to be read and build.
     * @return an instance of a concrete type {@code <T>}
     */
    public abstract T make(String filePath);
}
