package math.client.router;

import math.client.common.Constants;
import math.client.dto.response.BaseResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A class to manage request, response from socket server <p>
 * Register classes and methods to handle responses and requests from the socket server based on the action attached. <p>
 * For responses without action information, the router will ignore them and let the method sending the request handle the response itself. <p>
 * Implements the RouterMapping interface and uses the @Action annotation for the classes and methods you want to route.
 * @author dcthoai
 */
@SuppressWarnings("unused")
public class Router implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(Router.class);
    private final Map<String, Method> routeMap = new HashMap<>();
    private static final Router instance = new Router();

    private Router() {}

    public static Router getInstance() {
        return instance;
    }

    private void initRouter() {
        try {
            // Look in the controller package and scan through all the classes and methods
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String path = Constants.CONTROLLER_PACKAGE.replace('.', '/');   // Convert to window file url
            File packageDirectory = new File(Objects.requireNonNull(classLoader.getResource(path)).toURI());
            log.info("Initialize client router successfully");

            if (packageDirectory.exists()) {
                log.info("Scanning controller package for router successfully");

                for (File file : Objects.requireNonNull(packageDirectory.listFiles())) {
                    if (file.getName().endsWith(".class")) {
                        String className = Constants.CONTROLLER_PACKAGE + '.' + file.getName().replace(".class", "");
                        Class<?> clazz = Class.forName(className);

                        if (RouterMapping.class.isAssignableFrom(clazz)) {  // Check if class implements RouterMapping interface
                            registerRoutes(clazz);
                            log.info("Register method for {} successfully", className);
                        }
                    }
                }
            } else {
                log.warn("No method found to register or empty package");
            }
        } catch (Exception e) {
            log.error("Router initialization failed", e);
        }
    }

    private void registerRoutes(Class<?> controller) {
        Method[] methods = controller.getDeclaredMethods(); // Get all methods of the controller class

        for (Method method : methods) {
            // Check if this method is marked with @Action annotation then register it as a router handler
            if (method.isAnnotationPresent(Action.class)) {
                Action endpoint = method.getAnnotation(Action.class);
                String route = controller.getAnnotation(Action.class).value() + endpoint.value();

                routeMap.put(route, method);
            }
        }
    }

    public void handleAction(BaseResponse response) {
        Method method = routeMap.get(response.getAction());

        if (Objects.nonNull(method)) {
            try {
                // If the response has an action, use the router to call the corresponding registered method to handle it.
                Object controllerInstance = method.getDeclaringClass().getDeclaredConstructor().newInstance();
                method.invoke(controllerInstance, response);
            } catch (Exception e) {
                log.error("Failed to handle response from server: {}", response.getAction(), e);
            }
        } else if (Objects.equals(response.getAction(), Constants.NO_ACTION)) {
            log.warn("Response action: {}", response.getAction());
        } else {
            log.error("Could not found method to handle this response. Action: {}", response.getAction());
        }
    }

    @Override
    public void run() {
        initRouter();
    }
}
