package bl;

import edu.slcc.asdv.utilities.Database;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductsForSale<K, V extends Keyable> implements Product<Keyable>
{
    private Map<K, V> map = new HashMap<>();

    @Override
    public void create(Keyable t)
    {
        map.put((K) t.getKey(), (V) t);
        // update database
    }

    @Override
    public void delete(Keyable t)
    {
        map.remove(t.getKey());
        try
        { 
            Database.soldOut("inventory", (String) t.getKey());//update database
            
        } catch (SQLException ex)
        {
            Logger.getLogger(ProductsForSale.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Keyable t)
    {
        map.replace((K) t.getKey(), (V) t);
        try
        {
            Database.updateInventory("inventory", (String) t.getKey()); //update database
        } catch (SQLException ex)
        {
            Logger.getLogger(ProductsForSale.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Keyable find(Keyable t)
    {
        return map.get(t.getKey());
    }

    @Override

    public Collection<Keyable> findAll()
    {
        return (Collection<Keyable>) map.values();
    }

    @Override
    public boolean updateDatabase()
    {
        //update the singleton of available products or the database
        //if there is no singleton
        return true;
    }

}
