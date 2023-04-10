package com.api.utils.imports;

import com.api.model.Customer;
import com.api.utils.CustomerDTO;
import com.api.utils.export.CellConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportConfig {

    private int sheetIndex;

    private int headerIndex;

    private int startRow;

    private Class dataClazz;

    private List<CellConfig> cellImportConfigs;


    public static final ImportConfig customerImport;
    static {
        customerImport = new ImportConfig();
        customerImport.setSheetIndex(0);
        customerImport.setHeaderIndex(0);
        customerImport.setStartRow(1);
        customerImport.setDataClazz(CustomerDTO.class);
        List<CellConfig> customerImportCellConfig = new ArrayList<>();
        customerImportCellConfig.add(new CellConfig(0,"customerNumber"));
        customerImportCellConfig.add(new CellConfig(1,"customerName"));
        customerImportCellConfig.add(new CellConfig(2,"contactLastName"));
        customerImportCellConfig.add(new CellConfig(3,"contactFirstName"));
        customerImportCellConfig.add(new CellConfig(4,"phone"));
        customerImportCellConfig.add(new CellConfig(5,"addressLine1"));
        customerImportCellConfig.add(new CellConfig(6,"addressLine2"));
        customerImportCellConfig.add(new CellConfig(7,"city"));
        customerImportCellConfig.add(new CellConfig(8,"state"));
        customerImportCellConfig.add(new CellConfig(9,"postalCode"));
        customerImportCellConfig.add(new CellConfig(10,"country"));
        customerImportCellConfig.add(new CellConfig(11,"salesRepEmployeeNumber"));
        customerImportCellConfig.add(new CellConfig(12,"creditLimit"));
        customerImport.setCellImportConfigs(customerImportCellConfig);
    }
}
