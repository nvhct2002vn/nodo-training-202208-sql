package demo.unit7.example.part2;

import java.nio.file.*;

public class WatchFolderExample {
    public static void watch(Path path) throws Exception {
        FileSystem fs = path.getFileSystem();
        try (WatchService service = fs.newWatchService()) {
            path.register(service, StandardWatchEventKinds.ENTRY_CREATE);
            while (true) {
                WatchKey key = service.take();
                if (!key.reset()) {
                    break;
                }
                for (WatchEvent<?> watchEvent : key.pollEvents()) {
                    if (StandardWatchEventKinds.ENTRY_CREATE == watchEvent.kind()) {
                        Path newPath = ((WatchEvent<Path>) watchEvent).context();
                        System.out.println(newPath);
                    }
                }
                if (!key.reset()) {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Path path = Paths.get("D:\\Users\\Document\\DataStudy\\Thuc_Tap\\Training Java\\");
        watch(path);
    }

}
