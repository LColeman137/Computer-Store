package bl;

import java.util.Collection;

/**
 *
 * @author ASDV2
 * @param <T>
 */
public interface Product<T extends Keyable>
{

    public void create(T t);

    public void delete(T t);

    public void update(T t);

    public T find(T t);

    public Collection<T> findAll();

    public boolean updateDatabase();
}
