//Created package gatlingdemostore.pageobjects with appropriate page-object-classes
package gatlingdemostore.pageobjects;


//Imported some additional Java-libs for test-execution
import io.gatling.javaapi.core.ChainBuilder;

//Imported some additional Java-libs for test-execution
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;



//Created class Checkout which includes two methods for cart viewing and purchase operation
public final class Checkout {


    //Created method viewcart - if customer has logined - check his cart
    public static final ChainBuilder viewCart =

            doIf(session -> !session.getBoolean("customerLoggedIn"))
                    .then(exec(Customer.login))
                    .exec(http("Load Cart Page")
                            .get("/cart/view")
                            //.check(css("grandTotal").isEL("$#{cartTotal}")));
                            .check(substring("$#{cartTotal}")));


    //Created method completeCheckout - order goods in user's cart
    public static final ChainBuilder completeCheckout =
            exec(http("Checkout Cart")
                    .get("/cart/checkout")
                    .check(substring("Thanks for your order! See you soon!"))
            );
}
