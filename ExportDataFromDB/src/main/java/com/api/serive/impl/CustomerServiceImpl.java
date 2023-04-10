package com.api.serive.impl;

import com.api.model.Customer;
import com.api.repository.CustomerRepository;
import com.api.serive.CustomerService;
import com.api.utils.CustomerDTO;
import com.api.utils.ExcelUtils;
import com.api.utils.FileFactory;
import com.api.utils.imports.BaseResponse;
import com.api.utils.imports.ImportConfig;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;

    @Override
    public BaseResponse importCustomerData(MultipartFile importFile) {
        //sử dụng workbook library to get data from Excel file.
        //call workbook
        Workbook workbook = FileFactory.getWorkbookStream(importFile);
        //Get data in workbook
        BaseResponse baseResponse = new BaseResponse();
        List<CustomerDTO> customerDTOList = ExcelUtils.getImportData(workbook, ImportConfig.customerImport);

        if (!CollectionUtils.isEmpty(customerDTOList)) {
            saveData(customerDTOList);
            baseResponse.setCode(String.valueOf(HttpStatus.OK));
            baseResponse.setMessage("Import success");
        } else {
            baseResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST));
            baseResponse.setMessage("Import failed");
        }
        return baseResponse;
    }

    private void saveData(List<CustomerDTO> customerDTOList){
        for (CustomerDTO customerDTO: customerDTOList){
            Customer customer = new Customer();
            customer.setCustomerNumber(customerDTO.getCustomerNumber());
            customer.setCustomerName(customerDTO.getCustomerName());
            customer.setAddressLine1(customerDTO.getAddressLine1());
            customer.setAddressLine2(customerDTO.getAddressLine2());
            customer.setCity(customerDTO.getCity());
            customer.setCountry(customerDTO.getCountry());
            customer.setPhone(customerDTO.getPhone());
            customer.setContactLastName(customerDTO.getContactLastName());
            customer.setContactFirstName(customerDTO.getContactFirstName());
            customer.setState(customerDTO.getState());
            customer.setPostalCode(customerDTO.getPostalCode());
            customer.setSalesRepEmployeeNumber(customerDTO.getSalesRepEmployeeNumber());
            customer.setCreditLimit(customerDTO.getCreditLimit());
            customerRepository.save(customer);
        }

    }
}

