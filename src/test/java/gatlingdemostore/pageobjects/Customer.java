//Created package gatlingdemostore.pageobjects with appropriate page-object-classes
package gatlingdemostore.pageobjects;


//Imported some needed Java-libs for test-execution
import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.FeederBuilder;


//Imported some needed Java-libs for test-execution
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;



//Created class Customer which included some methods for store client
public final class Customer {


    //Created feeder that took user credits from csv-file - loginDetails.csv
    private static final FeederBuilder<String> csvFeederLoginDetails =
            csv("data/loginDetails.csv").circular();


    //Created method login - takes user credits from feeder and login customer to the store, check success of logon process
    public static final ChainBuilder login =
            feed(csvFeederLoginDetails)
                    .exec(http("Load Login Page")
                            .get("/login")
                            .check(substring("Username:")))

                    //.exec(session -> {
                    //System.out.println("customerLoggedIn:" + session.get("customerLoggedIn").toString());
                    //return session;
                    //}
                    //)


                    .exec(http("Customer Login Action")
                            .post("/login")
                            .formParam("_csrf", "#{csrfValue}")
                            .formParam("username", "#{username}")
                            .formParam("password", "#{password}"))

                    .exec(session -> session.set("customerLoggedIn", true));

    //.exec(session -> {
    //                         System.out.println("customerLoggedIn:" + session.get("customerLoggedIn").toString());
    //                   return session;
    // }
    //);

}
