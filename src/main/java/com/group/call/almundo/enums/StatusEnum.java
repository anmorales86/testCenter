package com.group.call.almundo.enums;

/**
 * Created by Anderson Q. on 3/06/2019.
 */
public enum StatusEnum {

    VACANT("VACANT"),
    BUSY("BUSY");

    private final String status;

    StatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static StatusEnum getStatusEnum(String status) {
        for (StatusEnum item : values()) {
            String statusStr = item.getStatus();
            if (statusStr.equalsIgnoreCase(status)) {
                return item;
            }
        }
        return null;
    }

}
