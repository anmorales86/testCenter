package com.group.call.almundo.enums;

/**
 * Created by Anderson Q. on 3/06/2019.
 */
public enum EmployeeEnum {

    OPERATOR("OPERATOR"),
    SUPERVISOR("SUPERVISOR"),
    DIRECTOR("DIRECTOR");

    private final String employee;

    EmployeeEnum(String employee) {
        this.employee = employee;
    }

    public String getEmployee() {
        return employee;
    }

    public static EmployeeEnum getEmployeeEnum(String employee) {
        for (EmployeeEnum item : values()) {
            String employeeStr = item.getEmployee();
            if (employeeStr.equalsIgnoreCase(employee)) {
                return item;
            }
        }
        return null;
    }


}
