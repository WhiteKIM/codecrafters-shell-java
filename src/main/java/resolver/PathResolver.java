package resolver;

import utils.NotExistPathException;

import java.io.File;
import java.nio.file.Paths;

public class PathResolver {
    
    private static String workingDir = Paths.get("").toAbsolutePath().toString();
    
    public static String getWorkingDir() {
        return workingDir;
    }
    
    public static void moveWorkingDir(String path) throws NotExistPathException {
        if(path.startsWith("/")) { // 절대경로
            if (isExistPath(path)) return;

            // 경로변경 실패
            throw new NotExistPathException();
        } else if(path.startsWith("../") || path.startsWith("./")) { // 상대경로
            if(path.startsWith("../") && path.startsWith("./")) {
                path = "/" + path;
            }

            String current = Paths.get(workingDir).resolve(path).normalize().toAbsolutePath().toString();
            if (isExistPath(current)) return;

            throw new NotExistPathException();
        } else if(path.startsWith("~")) {
            String home = System.getenv("HOME");

            if(isExistPath(home)) {
                path = path.replace("~", "");

                if(path.isBlank()) {
                    return;
                }

                // 여기서 더 남았다면 위로
                moveWorkingDir(path);
            }


            throw new NotExistPathException();
        }
    }

    private static boolean isExistPath(String path) throws NotExistPathException {
        if(path == null || path.isBlank()) {
            throw new NotExistPathException();
        }

        File dir = new File(path);

        if(dir.exists() && dir.isDirectory()) {
            workingDir = dir.getAbsolutePath();
            return true;
        }
        return false;
    }
}
