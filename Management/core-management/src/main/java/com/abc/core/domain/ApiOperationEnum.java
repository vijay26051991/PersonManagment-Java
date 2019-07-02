    package com.abc.core.domain;

/**
 * Enum for API operation type.
 *
 * @author vijaykumar.s 06/28/2019
 * com.abc.@copyright@
 */
public enum ApiOperationEnum {

    SAVE("save-info"),
    DELETE("delete-info"),
    UPDATE("update-info"),
    GET("get-info");


    /**
     * API operation type.
     *
     * @since
     */
    private final String operationType;

    ApiOperationEnum(final String theOperationType) {
        this.operationType = theOperationType;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
