package edu.slcc.asdv.pojos;

/**
 *
 * @author 13378
 */
class Product
{   
    private String id;
    private String type;
    private int height;
    private int width;
    private int qty;
    private String description;
    private double price;
    private byte[] image;
    private String fakeImage;

    /**
     * Empty constructor for product.
     */
    public Product(){}
    
    /**
     * Parameter constructor for product.
     * @param id The id of the product
     * @param type The product type ex: hp 15 in. laptop
     * @param height The height of the product image
     * @param width The width of the product image
     * @param qty The number of products in inventory
     * @param description The description of the product
     * @param price The price of the product
     * @param image The image of the product
     * @param fakeImage The url of the product
     */
    public Product(String id, String type, int height, int width, int qty, String description, double price, byte[] image, String fakeImage)
    {
        this.id = id;
        this.type = type;
        this.height = height;
        this.width = width;
        this.qty = qty;
        this.description = description;
        this.price = price;
        this.image = image;
        this.fakeImage = fakeImage;
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
     * Get the value of image
     *
     * @return the value of image
     */
    public byte[] getImage()
    {
        return image;
    }

    /**
     * Set the value of image
     *
     * @param image new value of image
     */
    public void setImage(byte[] image)
    {
        this.image = image;
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
     * Get the value of description
     *
     * @return the value of description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Set the value of description
     *
     * @param description new value of description
     */
    public void setDescription(String description)
    {
        this.description = description;
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


    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public String getId()
    {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(String id)
    {
        this.id = id;
    }

}
