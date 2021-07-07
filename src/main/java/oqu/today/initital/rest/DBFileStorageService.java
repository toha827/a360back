package oqu.today.initital.rest;

import oqu.today.initital.exceptions.FileStorageException;
import oqu.today.initital.exceptions.MyFileNotFoundException;
import oqu.today.initital.model.DBFile;
import oqu.today.initital.repository.DBFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
@Transactional
public class DBFileStorageService {

    @Autowired
    private DBFileRepository dbFileRepository;

    public DBFile storeFile(MultipartFile file, int userId) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        dbFileRepository.deleteByUserId(userId);
        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            if(!(fileName.contains(".jpg") || fileName.contains(".png") || fileName.contains(".jpeg"))) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());
            dbFile.setUserId(userId);
            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public DBFile getFile(String fileId) {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }
}