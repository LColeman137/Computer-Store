package edu.slcc.asdv.beans;

import bl.Item;
import bl.Keyable;
import bl.ProductsForSale;
import bl.WarehouseSingleton;
import edu.slcc.asdv.pojos.JSONobj;
import static edu.slcc.asdv.pojos.JsonSupplier.createJsonObjectForSupplier;
import static edu.slcc.asdv.pojos.JsonSupplier.createJsonObjectForSuppliers;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.context.FacesContext;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;

/**
 *
 * @author 13378
 */
@Named(value = "productBean")
@SessionScoped
public class ProductBean implements Serializable
{
    WarehouseSingleton ws = WarehouseSingleton.instantiateWarehouse();
    private String search; //The user's search string
    private Item product = new Item(); //The product to be displayed.
    private List<Item> searchResult = new ArrayList<>();

    /**
     * Constructor which initializes the SortedMap arrays to the products from
     * the database and sets the product to the first product from allProducts
     *
     * @throws SQLException
     */
    public ProductBean() throws SQLException
    {
        System.out.println("warehouse in bean: " + InitializationBean.getWarehouse());
        ProductsForSale<String, Keyable> pfs = ws.getProducts();
        
        Collection c = ws.getProducts().findAll();
        ArrayList<Item> inventory = new ArrayList();
        for(Object i : c)
        {
            inventory.add((Item) i);
        }
        JsonObject j = createJsonObjectForSuppliers(inventory);
        StringWriter strWtr = new StringWriter();
        JsonWriter jsonWtr = Json.createWriter(strWtr);
        jsonWtr.writeObject(j);
        jsonWtr.close();
        
        JSONobj.readJASONdataUsingParser(strWtr.toString());
        JsonObject o = createJsonObjectForSupplier(strWtr.toString());
        System.out.println("----------------");
        JSONobj.writeObjectModelToStream(o);
       // System.out.println(pfs.findAll());
        product = (Item) pfs.find(new Item("0"));
    }

    public WarehouseSingleton getWs()
    {
        return ws;
    }

    public String getSearch()
    {
        return search;
    }

    public void setSearch(String search)
    {
        this.search = search;
    }

    public Item getProduct()
    {
        return product;
    }

    public List<Item> getSearchResult()
    {
        return searchResult;
    }

    public void setSearchResult(List<Item> searchResult)
    {
        this.searchResult = searchResult;
    }

    /**
     * Gets the FacesContext of the menuItem by calling method getProductId and
     * gets it's value, id. The value is used to set and display the correct
     * product.
     */
    public void sendProduct()
    {
        //>Gets the faces context
        FacesContext context = FacesContext.getCurrentInstance();
        //>Gets the value from the context parameter.
        String id = getProductId(context);
        //System.out.println(ws.getProducts().findAll());
        product = (Item) ws.getProducts().find(new Item(id));
    }

    /**
     * Gets the value from the FacesContext parameter and send it back to method
     * sendProduct()
     *
     * @param context
     * @return
     */
    private String getProductId(FacesContext context)
    {
        //>Get and return the parameter value with the given context.
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();

        return params.get("product");

    }

    /**
     * Uses the search String to find matches within a product's type and
     * description and adds them to searchResult. Navigates the user to the
     * searchResult page which displays matching products.
     *
     * @return
     */
    public String setSearchResult()
    {
        if(search != null && search.length() > 1)
        {
        searchResult = new ArrayList<>();
        //>ArrayList to hold all products
        ArrayList<Item> p2 = new ArrayList<>();
        String s = ".(";
        char[] c = search.toLowerCase().toCharArray();
        
        for(int i = 0; i < c.length - 2; ++i)
        {
            s +=  c[i] + "|";
        }
        s += c[c.length - 1] + "){" + (c.length - 1) + ",}";
        Pattern p = Pattern.compile(s);
        
        for (Object ar1 : ws.getProducts().findAll())
        {
            Item i = (Item) ar1;
            //>If there is a match in the search, add that product to the search result list.
                Matcher match1 = p.matcher(i.getType().toLowerCase());
                Matcher match2 = p.matcher(i.getDescription().toLowerCase());
                boolean m1 = match1.lookingAt();
                boolean m2 = match2.lookingAt();
                if (m1 || m2)
                {
                    searchResult.add((Item) ws.getProducts().find(i));
                }
            
            p2.add((Item) ws.getProducts().find(i));
        }

        //> Set searchProduct to p2 if it is empty, otherwise, send searchResult.
        if (searchResult.isEmpty())
        {
            searchResult.addAll(p2);
        }

        //>Navigate the user to searchResult to dusplay the products.
        return "searchResult";
        }
        return "index";
    }

    /**
     * Updates the database by reducing the quantity of the sold product.
     * Navigates the user to the sold page if no errors occur, or index.
     *
     * @return sold.xhtml if no errors occur, or index.xhtml
     * @throws SQLException
     */
    public String sellProduct() throws SQLException
    {
        //>Call method sellLaptops and if the item was sold, navigate the user to sold page.
        String sold = sellProducts();
        if (sold != null)
        {
            return sold;
        }

        //>Default value if there was an error.
        return "index";
    }

    /**
     * Updates items from the laptops database to reflect accurate quantity.
     *
     * @return sold if the sold product was a laptop, null otherwise.
     * @throws SQLException
     */
    private String sellProducts() throws SQLException
    {
        //>If it is the last product, call method to remove the product from the inventory.
        if (product.getQty() == 1)
        {
            ws.getProducts().delete(product);
            return "sold";
        } 
        //>If it is not the last product, call method to remove 1 from
        //>The inventory
        else
        {
            product.setQty(product.getQty() - 1);
            ws.getProducts().update(product);
            System.out.println(ws.getProducts().findAll().toString());
            
            return "sold";
        }

    }

    /**
     * Sets product to the value of its index minus one, or to the end of the
     * array if its index is zero.
     */
    public void swipeLeft()
    {
        Collection c = ws.getProducts().findAll();
        //>Set product to end of array if index is zero
        if (Integer.parseInt(product.getId()) == 0)
        {
            product = (Item) ws.getProducts().find(new Item(String.valueOf(c.size() - 1)));
        } //>Set product to index - 1.
        else
        {
            product = (Item) ws.getProducts().find(new Item(String.valueOf(Integer.parseInt(product.getId()) - 1)));
        }
    }

    /**
     * Sets product to the value of its index plus one, to the beginning of the
     * array id its index is length - 1.
     */
    public void swipeRight()
    {
        //>Set product to beginning of array if index is length - 1.
        if (ws.getProducts().findAll().size() - 1 == Integer.parseInt(product.getId()))
        {
            product = (Item) ws.getProducts().find(new Item(String.valueOf(0)));
        } //>Set product to index + 1.
        else
        {
            product = (Item) ws.getProducts().find(new Item(String.valueOf(Integer.parseInt(product.getId()) + 1)));
        }
    }
    public static void main(String[] args) throws SQLException
    {
        ProductBean p = new ProductBean();
        Collection c = p.ws.getProducts().findAll();
        
        ArrayList<Item> inventory = new ArrayList();
        for(Object i : c)
        {
            inventory.add((Item) i);
        }
        JsonObject j = createJsonObjectForSuppliers(inventory);
        StringWriter strWtr = new StringWriter();
        JsonWriter jsonWtr = Json.createWriter(strWtr);
        jsonWtr.writeObject(j);
        jsonWtr.close();
        
        JSONobj.readJASONdataUsingParser(strWtr.toString());
        JsonObject o = createJsonObjectForSupplier(strWtr.toString());
        System.out.println("----------------");
        JSONobj.writeObjectModelToStream(o);
    }
}
