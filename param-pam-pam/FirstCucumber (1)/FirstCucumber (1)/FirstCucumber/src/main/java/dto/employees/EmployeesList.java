package dto.employees;

import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
@Data
public class EmployeesList {
    private List<Employee> employees;

}
