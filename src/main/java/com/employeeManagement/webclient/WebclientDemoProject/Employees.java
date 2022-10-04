package com.employeeManagement.webclient.WebclientDemoProject;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employees {

    private int empId;
    private String empName;
    private String empEmail;
    private String empAddress;

}


