package math.client.common;

/**
 * All configuration constants and parameters used for the application
 * @author dcthoai
 */
@SuppressWarnings("unused")
public interface Constants {

    String SERVER_HOST = "0.tcp.ap.ngrok.io"; // ngrok host: 0.tcp.ap.ngrok.io
    Integer SERVER_PORT = 16521;

    String CONTROLLER_PACKAGE = "math.client.controller";

    String NO_ACTION = "NO_ACTION";
    String NO_BODY = "NO_BODY";
    Integer SUCCESS = 200;
    Integer BAD_REQUEST = 400;
    Integer UNAUTHORIZED = 401;
    Integer FORBIDDEN = 403;
    Integer NOT_FOUND = 404;
    Integer INTERNAL_SERVER_ERROR = 500;
    Integer SOCKET_CONNECT_SUCCESS = 1000;
    Integer SOCKET_DISCONNECT = 1001;
    Integer SOCKET_CONNECT_TIMEOUT = 1002;
    Integer SOCKET_SENT_DATA_SUCCESS = 2000;
    Integer SOCKET_INVALID_DATA = 4000;
    Integer UNKNOWN_SOCKET_SERVER = 5000;

    String TYPE_FRIENDSHIP = "Mời";
    String TYPE_PLAYER = "Kết bạn";

    Integer FRIENDSHIP_ACCEPT = 1;
    Integer FRIENDSHIP_REFUSE = 0;

    String ANSI_RESET = "\u001B[0m";
    String ANSI_RED = "\u001B[31m";
    String ANSI_GREEN = "\u001B[32m";

    String APP_NAME = "\n" +
            ANSI_GREEN + "\n\t ██╗     ██╗" + ANSI_RED + "    ██╗    ████████╗ ██╗   ██╗      ╔██████╗ ██╗       ██╗ ████████╗ ███╗   ██║ ████████╗ " + ANSI_RESET +
            ANSI_GREEN + "\n\t ███╗   ███║" + ANSI_RED + "  █║   █╗  ╚══██╔══╝ ██║   ██║     ██╔═════╝ ██║       ██║ ██╔═════╝ ██║█╗  ██║ ╚══██╔══╝ " + ANSI_RESET +
            ANSI_GREEN + "\n\t ██║█╗ █╝██║" + ANSI_RED + " ████████╗    ██║    ████████║     ██║       ██║       ██║ ██████╗   ██║ █╗ ██║    ██║    " + ANSI_RESET +
            ANSI_GREEN + "\n\t ██║ ██╝ ██║" + ANSI_RED + " ██╔═══██║    ██║    ██╔═══██║     ██║       ██║       ██║ ██╔═══╝   ██║  █╗██║    ██║    " + ANSI_RESET +
            ANSI_GREEN + "\n\t ██║     ██║" + ANSI_RED + " ██║   ██║    ██║    ██║   ██║     ╚███████╗ ████████╗ ██║ ████████╗ ██║  ╚███║    ██║    " + ANSI_RESET +
            ANSI_GREEN + "\n\t ╚═╝     ╚═╝" + ANSI_RED + " ╚═╝   ╚═╝    ╚═╝    ╚═╝   ╚═╝      ╚══════╝ ╚═══════╝ ╚═╝ ╚═══════╝ ╚═╝   ╚══╝    ╚═╝  \n" + ANSI_RESET;
}
