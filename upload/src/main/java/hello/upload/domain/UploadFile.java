package hello.upload.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadFile {
    private String uploadFileName;
    private String storeFileName; // 같은 파일name을 안겹치게 저장하기 위함

}
