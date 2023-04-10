package com.api.controller;

import com.api.model.Customer;
import com.api.repository.CustomerRepository;
import com.api.serive.CustomerService;
import com.api.utils.ExcelUtils;
import com.api.utils.imports.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Customer>> getAll(){
        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/export")
    public ResponseEntity<Resource> exportCustomer() throws Exception {
        List<Customer> customerList = customerRepository.findAll();

        if (!CollectionUtils.isEmpty(customerList)){
            String fileName = "CustomerExport" + ".xlsx";

            ByteArrayInputStream in = ExcelUtils.exportCustomer(customerList,fileName);

            InputStreamResource inputStreamResource = new InputStreamResource(in);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; fileName=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8))
                    .contentType(MediaType.parseMediaType("application/vnd.ms-excel; charset =UTF-8"))
                    .body(inputStreamResource);

        }else {
            throw new Exception("No Data");
        }
    }

    @PostMapping("/import")
    public ResponseEntity<BaseResponse> importCustomerData(@RequestParam("file")MultipartFile importFile){
        BaseResponse baseResponse = customerService.importCustomerData(importFile);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

}
