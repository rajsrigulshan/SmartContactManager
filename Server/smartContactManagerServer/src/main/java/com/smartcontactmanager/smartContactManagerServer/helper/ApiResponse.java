package com.smartcontactmanager.smartContactManagerServer.helper;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private final boolean success;
    private final String message;
    private final T data;
    private final Integer errorCode;

    private ApiResponse(Builder<T> builder) {
        this.success = builder.success;
        this.message = builder.message;
        this.data = builder.data;
        this.errorCode = builder.errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public static class Builder<T> {
        private boolean success;
        private String message;
        private T data;
        private Integer errorCode;

        private Builder() {

        }

        public Builder<T> success(boolean success) {
            this.success = success;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Builder<T> errorCode(Integer errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public ApiResponse<T> build() {
            if (this.message == null || this.message.isEmpty()) {
                throw new IllegalStateException("Message cannot be null or empty.");
            }
            return new ApiResponse<>(this);
        }
    }
}
