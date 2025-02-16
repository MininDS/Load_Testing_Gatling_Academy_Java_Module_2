//Created package gatlingdemostore.pageobjects with appropriate page-object-classes
package gatlingdemostore.pageobjects;

//Imported some Java-libs for test-execution
import io.gatling.javaapi.core.ChainBuilder;

//Imported some Java-libs for test-execution
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;



//Created public class CmsPage for homepage and aboutUs opening
public final class CmsPage {


    //Created method homepage - open homepage and do some checks
    public static final ChainBuilder homepage =
            exec(
                    http("Load Home Page")
                            .get("/")
                            .check(regex("<title>Gatling Demo-Store</title>").exists())
                            .check(css("#_csrf", "content").saveAs("csrfValue")));


    //Created method homepage - open AboutUs page and do some checks
    public static final ChainBuilder aboutUs =
            exec(
                    http("Load About Us Page")
                            .get("/about-us")
                            .check(substring("About Us")));

}
