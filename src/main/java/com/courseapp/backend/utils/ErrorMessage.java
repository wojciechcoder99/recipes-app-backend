package com.courseapp.backend.utils;

public enum ErrorMessage {
    INVALID_IDENTIFIER_TYPE {
        @Override
        public String toString() {
            return "not valid resource identifier type!";
        }
    },
    CONSTRAINT_VIOLATION {
        @Override
        public String toString() {
            return "not valid due to validation error!";
        }
    },

    INVALID_OBJECT {
        @Override
        public String toString() {
            return "Object is null!";
        }
    },
    PAGE_PARAM_SHOULD_BE_GREATER_THAN_ZERO {
        @Override
        public String toString() {
            return "Page param should be greater than zero!";
        }
    }
    }
