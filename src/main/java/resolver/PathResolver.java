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
            File dir = new File(workingDir + path);
            
            if(dir.exists() && dir.isDirectory()) {
                workingDir = dir.getAbsolutePath();
                return;
            }
            
            // 경로변경 실패
            throw new NotExistPathException();
        } else { // 상대경로
//            if(path.startsWith("./")) { // 현재 경로
//
//            } else if(path.startsWith("../")) { // 이전 경로
//
//            } else if() {   // 현재 경로 내 폴더
//
//            }
//
//            File dir = new File(workingDir + "/");

            throw new NotExistPathException();
        }
    }
}
