package bl;

import java.util.Collection;

/**
 *
 * @author ASDV2
 * @param <K>
 * @param <V>
 */
public class WarehouseSingleton<K, V extends Keyable>

{
    private static ProductsForSale<String, Keyable> productsForSale;
    private static WarehouseSingleton warehouse;

    private WarehouseSingleton()
    {
        productsForSale = new ProductsForSale();
       
    }

    public static WarehouseSingleton instantiateWarehouse()
    {
        if (warehouse == null)
          {
            warehouse = new WarehouseSingleton();
          }
        return warehouse;
    }

    public ProductsForSale<String, Keyable> getProducts()
    {
        return productsForSale;
    }

    public static void main(String[] args)
    {
        WarehouseSingleton ws = WarehouseSingleton.instantiateWarehouse();
        ProductsForSale<String, Keyable> pfs = ws.getProducts();
        pfs.create(new Item("phone", "iphone1 serial number", "good", "string", 1000, 1000, 1000, 1239.89, "resources/images/pc"));
//        pfs.create(new Item("phone", "iphone2 serial number", "very good"));
//        pfs.create(new Item("phone", "android1 serial number", "very very good"));
//        pfs.create(new Item("computer", "HP serial number", "good PC"));
//        pfs.create(new Item("computer", "Macbook Pro serial number", "good laptop"));

        System.out.println(pfs.findAll());

    }

}
