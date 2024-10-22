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
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String path = Constants.CONTROLLER_PACKAGE.replace('.', '/');
            File packageDirectory = new File(Objects.requireNonNull(classLoader.getResource(path)).toURI());
            log.info("Initialize client router successfully");

            if (packageDirectory.exists()) {
                log.info("Scanning controller package for router successfully");

                for (File file : Objects.requireNonNull(packageDirectory.listFiles())) {
                    if (file.getName().endsWith(".class")) {
                        String className = Constants.CONTROLLER_PACKAGE + '.' + file.getName().replace(".class", "");
                        Class<?> clazz = Class.forName(className);

                        if (RouterMapping.class.isAssignableFrom(clazz)) {
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
        Method[] methods = controller.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Action.class)) {
                Action endpoint = method.getAnnotation(Action.class);
                String route = controller.getAnnotation(Action.class).value() + endpoint.value();

                routeMap.put(route, method);
            }
        }
    }

    public void handleAction(BaseResponse<?> response) {
        Method method = routeMap.get(response.getAction());

        if (Objects.nonNull(method)) {
            try {
                Object controllerInstance = method.getDeclaringClass().getDeclaredConstructor().newInstance();
                method.invoke(controllerInstance, response.getResult());
            } catch (Exception e) {
                log.error("Failed to handle response from server", e);
            }
        } else {
            log.error("Could not found method to handle this response");
        }
    }

    @Override
    public void run() {
        initRouter();
    }
}
