package bl;

/**
 *
 * @author ASDV2
 */
public interface Categorable<T extends CharSequence>
{
    public T getCategory();
    public void setCategory( T t );
    
}
