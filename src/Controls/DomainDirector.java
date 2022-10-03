package Controls;

import Domain.Services.DomainBuilder;
import Infrastructure.Reader;

public abstract class DomainDirector<T> {

    protected DomainBuilder<T> builder;
    protected Reader reader;

    public void changeBuilder(DomainBuilder<T> builder){
        this.builder = builder;
    }

    public abstract T make(String filePath);
}
