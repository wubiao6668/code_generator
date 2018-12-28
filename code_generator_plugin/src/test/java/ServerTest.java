import com.generator.AppBoot;
import com.generator.server.HttpServer;
import org.junit.Test;

public class ServerTest {


    @Test
    public void testServer() {
        AppBoot.appStart();
        while (true){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
