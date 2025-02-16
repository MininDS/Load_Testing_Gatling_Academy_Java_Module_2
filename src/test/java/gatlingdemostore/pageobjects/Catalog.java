//Created package gatlingdemostore.pageobjects with different typical pages, described like java-objects
package gatlingdemostore.pageobjects;


//Imported some needed Java-libs for execution
import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.FeederBuilder;

//Imported some needed Java-libs for execution
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.core.CoreDsl.substring;
import static io.gatling.javaapi.http.HttpDsl.http;


//Created class Catalog which described some typical actions with product catalog of demostore
public final class Catalog {


    //Announced some feeders which takes randomly needed keys and values from appropriate csv-files - categoryDetails.csv and productDetails.json
    private static final FeederBuilder<String> categoryFeeder =
            csv("data/categoryDetails.csv").random();
    private static final FeederBuilder<Object> jsonFeederProducts =
            jsonFile("data/productDetails.json").random();


    //Created additional class Category that takes appropriate category names from file and executed appropriate http-method
    public static class Category{
        public static final ChainBuilder view =
                feed(categoryFeeder)
                        .exec(
                                http("Load Category Page - #{categoryName}")
                                        .get("/category/#{categorySlug}")
                                        .check(css("#CategoryName").isEL("#{categoryName}")));
    }


    //Created additional class Product that takes appropriate product names from file and executed appropriate http-methods
    public static class Product {
        public static final ChainBuilder view =
                feed(jsonFeederProducts)
                        .exec(
                                http("Load Product Page - #{name}")
                                        .get("/product/#{slug}")
                                        .check(css("#ProductDescription").isEL("#{description}")));

        public static final ChainBuilder add =
                exec(view)
                        .exec(
                                http("Add Product to Cart")
                                        .get("/cart/add/#{id}")
                                        .check(substring("items in your cart")))
                        .exec(
                                session -> {
                                    double currentCartTotal = session.getDouble("cartTotal");
                                    double itemPrice = session.getDouble("price");
                                    return session.set("cartTotal", (currentCartTotal + itemPrice));
                                });

        //.exec(session -> {
        //System.out.println("cartTotal:" + session.get("cartTotal").toString());
        //return session;
        //}
        //);
    }
}