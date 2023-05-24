package edu.slcc.asdv.pojos;

import bl.Item;
import bl.Keyable;
import bl.ProductsForSale;
import bl.WarehouseSingleton;
import edu.slcc.asdv.beans.InitializationBean;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonWriter;

public class JsonSupplier
{
    /**
     * Creates a JsonObject by traversing the arralyList of LinkedHashMap
     *
     * @param trips ArrayList of LinkedHashMap
     * @return JsonObject
     */
    public static JsonObject createJsonObjectForSuppliers(ArrayList<Item> products)
    {
        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
        int counter = 1;
        for (Item product : products)
        {
// create an array of key-value pairs
            JsonArrayBuilder arraySupplierBld = Json.createArrayBuilder();
            // create each key-value pair as seperate object and add it to the array
//
//            Set<Map.Entry<String, String>> entrySet = Item.entrySet();
//            for (Map.Entry<String, String> entry : entrySet)
//              {
//                arraySupplierBld.add(Json.createObjectBuilder().add(entry.getKey(), entry.getValue()).build());
//              }
            arraySupplierBld.add(Json.createObjectBuilder().add("type", product.getType())
                    .add("category", product.getCategory())
                    .add("qty", product.getQty())
                    .add("height", product.getHeight())
                    .add("width", product.getWidth())
                    .add("price", product.getPrice())
                    .add("id", product.getId())
                    .add("description", product.getDescription())
                    .add("fakeImage", product.getFakeImage()).build());

            // add trip-array to object
            String objectID = "+" + (counter++);
            jsonBuilder.add(objectID, arraySupplierBld);
        }

        JsonObject allTripsJsonObject = jsonBuilder.build();
        return allTripsJsonObject;
    }

    public static JsonObject createJsonObjectForSupplier(String jsonData)
    {
        JsonReader jsonReader = Json.createReader(new StringReader(jsonData));
        JsonObject o = jsonReader.readObject();
        return o;
    }

    public static LinkedHashMap<String, String> createMapOfSupplier(
            String snumber,
            String sname,
            String status,
            String city
    )

    {
        LinkedHashMap<String, String> mapSupplier
                = new LinkedHashMap<String, String>();
        mapSupplier.put("snumber", snumber);
        mapSupplier.put("sname", sname);
        mapSupplier.put("status", status);
        mapSupplier.put("city", city);

        return mapSupplier;
    }

    public static void main(String a[])
    {
        WarehouseSingleton ws = WarehouseSingleton.instantiateWarehouse();
        System.out.println("warehouse in bean: " + InitializationBean.getWarehouse());
        ProductsForSale<String, Keyable> pfs = ws.getProducts();

        Collection c = pfs.findAll();
        ArrayList<Item> inventory = new ArrayList(pfs.findAll().size());
        for (Object i : c)
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
