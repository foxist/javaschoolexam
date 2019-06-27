package com.tsystems.javaschool.tasks.pyramid;

public class CannotBuildPyramidException extends RuntimeException {

    public CannotBuildPyramidException(String ex) {
        super(ex);
    }

    @Override
    public String getMessage() {
        return "Error";
    }
}
