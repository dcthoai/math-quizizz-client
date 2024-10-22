package math.client;

import math.client.common.Constants;
import math.client.controller.UserController;
import math.client.router.Router;
import math.client.service.utils.ConnectionUtil;
import math.client.service.utils.SessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author dctho
 */
public class MathClient {

    private static final Logger log = LoggerFactory.getLogger(MathClient.class);
    private static final ExecutorService pool = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        System.out.println(Constants.APP_NAME);
        log.info("Start application...");

        pool.execute(() -> ConnectionUtil.getInstance().run());
        Router.getInstance().run();
        SessionManager.getInstance().run();
        UserController.getInstance().run();

        log.info("Application is running");
        log.info(
                "\n----------------------------------------------------------\n\t" +
                    "Application '{}' is running! Access URLs:\n\t" +
                    "Socket server: \t ws://{}:{}\n\t" +
                "\n----------------------------------------------------------",
                MathClient.class.getSimpleName(),
                Constants.SERVER_HOST,
                Constants.SERVER_PORT
        );
    }
}
