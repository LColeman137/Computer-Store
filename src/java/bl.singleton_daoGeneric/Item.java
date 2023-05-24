package bl;

import java.util.Objects;

public class Item implements Keyable<String>, Comparable<Item>, Categorable<String>
{
    private String category;
    private String id;
    private String description;
    private String type;
    private int qty; 
    private int height;
    private int width;
    private double price;
    private String fakeImage;
    
    public Item(){}

    public Item(String id)
    {
        this.id = id;
    }
    
    public Item(String category, String id, String description, String type, int qty, int height,int width, double price, String fakeImage)
    {
        this.category = category;
        this.id = id;
        this.description = description;
        this.type = type;
        this.qty = qty;
        this.height = height;
        this.width = width;
        this.price = price;
        this.fakeImage = fakeImage;
    }

 /**
     * Get the value of width
     *
     * @return the value of width
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * Set the value of width
     *
     * @param width new value of width
     */
    public void setWidth(int width)
    {
        this.width = width;
    }

    /**
     * Get the value of height
     *
     * @return the value of height
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * Set the value of height
     *
     * @param height new value of height
     */
    public void setHeight(int height)
    {
        this.height = height;
    }

    /**
     * Get the value of fakeImage
     *
     * @return the value of fakeImage
     */
    public String getFakeImage()
    {
        return fakeImage;
    }

    /**
     * Set the value of fakeImage
     *
     * @param fakeImage new value of fakeImage
     */
    public void setFakeImage(String fakeImage)
    {
        this.fakeImage = fakeImage;
    }

    /**
     * Get the value of price
     *
     * @return the value of price
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * Set the value of price
     *
     * @param price new value of price
     */
    public void setPrice(double price)
    {
        this.price = price;
    }

    /**
     * Get the value of qty
     *
     * @return the value of qty
     */
    public int getQty()
    {
        return qty;
    }

    /**
     * Set the value of qty
     *
     * @param qty new value of qty
     */
    public void setQty(int qty)
    {
        this.qty = qty;
    }

    /**
     * Get the value of type
     *
     * @return the value of type
     */
    public String getType()
    {
        return type;
    }

    /**
     * Set the value of type
     *
     * @param type new value of type
     */
    public void setType(String type)
    {
        this.type = type;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @Override
    public String getKey()
    {
        return getId();
    }

    @Override
    public void setKey(String key)
    {
        setKey(key);
    }

    @Override
    public String getCategory()
    {
        return category;
    }

    @Override
    public void setCategory(String t)
    {
        this.category = t;
    }

    @Override
    public int compareTo(Item o)
    {
        return this.id.compareTo(o.id);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Item other = (Item) obj;
        if (!Objects.equals(this.id, other.id))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Item{" + "category=" + category + ", id=" + id + ", type=" + type + ", qty=" + qty + ", height=" + height + ", width=" + width + ", price=" + price + ", fakeImage=" + fakeImage + '}';
    }



}
