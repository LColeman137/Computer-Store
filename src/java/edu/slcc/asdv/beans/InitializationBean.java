package edu.slcc.asdv.beans;

import bl.Item;
import bl.Keyable;
import bl.ProductsForSale;
import bl.WarehouseSingleton;
import edu.slcc.asdv.utilities.Inventory;
import java.sql.SQLException;
import java.util.SortedMap;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;

/**
 *
 * @author 13378
 */
@Named(value = "initializationBean")
@ApplicationScoped
public class InitializationBean
{
private static WarehouseSingleton ws;
    public InitializationBean()
    {
    }
    
    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) throws SQLException
    {
        System.out.println("initialize singleton");
        ws = WarehouseSingleton.instantiateWarehouse();
        ProductsForSale<String, Keyable> pfs = ws.getProducts();
        SortedMap[] allProducts = Inventory.getInventory().getRows();
        for (SortedMap allProduct : allProducts)
        {
            pfs.create(new Item((String) allProduct.get("category"),
                    (String) allProduct.get("id"), (String) allProduct.get("description"),
                    (String) allProduct.get("type"), (int) allProduct.get("qty"),
                    (int) allProduct.get("height"), (int) allProduct.get("width"),
                    (double) allProduct.get("price"), (String) allProduct.get("fakeImage")));
        }
    }
    
    public static WarehouseSingleton getWarehouse()
    {
        return ws;
    }
    
    public void destroy(@Observes @Destroyed(ApplicationScoped.class) Object init)
    {
        //cleanup and save
    }
}
