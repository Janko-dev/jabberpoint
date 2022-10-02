package Controls;

import Domain.Services.DomainBuilder;

public abstract class DomainDirector<T, U> {

    protected DomainBuilder<T, U> builder;

    public void changeBuilder(DomainBuilder<T, U> builder){
        this.builder = builder;
    }

    public abstract T make();
}
