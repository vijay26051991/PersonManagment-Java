package com.abc.core.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Json based validation error model
 *
 * @author vijaykumar.s 07/02/2019
 */
@ApiModel
public class ManagementApiFailure {
    public enum Type {BAD_REQUEST, NOT_FOUND, UN_AUTHORIZED}

    private final Type type;
    private final String message;

    public ManagementApiFailure(
        @JsonProperty("type") final Type type,
        @JsonProperty("message") final String message) {
        this.type = type;
        this.message = message;
    }

    @ApiModelProperty(required = true)
    public Type getType() {
        return type;
    }

    @ApiModelProperty(required = true)
    public String getMessage() {
        return message;
    }
}