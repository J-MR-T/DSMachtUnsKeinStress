package resources;

import org.apache.commons.vfs2.*;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;

public class ResourceLoader {
    private static FileSystemManager manager = null;

    private static FileSystemManager getManager() throws FileSystemException {
        if (manager == null) {
            manager = VFS.getManager();
            URL.setURLStreamHandlerFactory(manager.getURLStreamHandlerFactory());
        }
        return manager;
    }

    private static FileObject resRoot = null;

    private static FileObject getResRoot() throws FileSystemException {
        if (resRoot == null) getManager().resolveFile("res://");
        return resRoot;
    }

    public static FileObject getResource(String url) throws FileSystemException {
        return getManager().resolveFile(url);
    }

    public static InputStream getResourceAsStream(String url) throws FileSystemException {
        return getResource(url).getContent().getInputStream();
    }


}
