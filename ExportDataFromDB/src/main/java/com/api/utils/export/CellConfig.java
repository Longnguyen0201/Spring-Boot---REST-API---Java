package com.api.utils.export;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CellConfig {
    private int columIndex;

    private String fieldName;
}
