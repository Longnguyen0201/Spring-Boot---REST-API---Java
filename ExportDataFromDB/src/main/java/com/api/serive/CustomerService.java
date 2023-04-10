package com.api.serive;

import com.api.utils.imports.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

public interface CustomerService {
    BaseResponse importCustomerData(MultipartFile importFile);
}
