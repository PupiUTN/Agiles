package app.services;

import app.models.support.FileJson;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StorageService {
    public List<FileJson> loadAll();
    public String store(MultipartFile remoteFile)throws IOException;
}