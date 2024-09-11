package dto.employees;

import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.extern.slf4j.Slf4j;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
@Slf4j
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
@Data
public class Employee{
    public String firstName;
    public String lastName;
    public String department;
}

