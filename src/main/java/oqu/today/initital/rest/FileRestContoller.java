package oqu.today.initital.rest;

import javassist.bytecode.ByteArray;
import oqu.today.initital.model.DBFile;
import oqu.today.initital.model.User;
import oqu.today.initital.payload.UploadFileResponse;
import oqu.today.initital.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.awt.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/javaApi/api/")
public class FileRestContoller {

    final String localhost = "https://45.80.70.68";
    final String payment = "https://payment.oqu.today/";
    private static final Logger logger = LoggerFactory.getLogger(FileRestContoller.class);

    @Autowired
    private DBFileStorageService dbFileStorageService;

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(localhost)
    @PostMapping("/uploadFile")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("userId") long userId) {
        DBFile dbFile = dbFileStorageService.storeFile(file,userId);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId())
                .toUriString();

        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {
            userRepository.updateUser(user.get().getId(), user.get().getName(), user.get().getEmail(), user.get().getPassword(), user.get().getGender(), dbFile.getId(), user.get().getBday(), user.get().getPhone());
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(new UploadFileResponse(dbFile.getFileName(), dbFile.getId(),
                file.getContentType(), file.getSize()), HttpStatus.OK);
    }

    @CrossOrigin(localhost)
    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<String> downloadFile(@PathVariable String fileId) {
        // Load file from database
        DBFile dbFile = dbFileStorageService.getFile(fileId);
        ByteArray aa = new ByteArray();
        byte[] encoded = Base64.getEncoder().encode(dbFile.getData());
        System.out.println(new String(encoded));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new String(encoded));
    }

}