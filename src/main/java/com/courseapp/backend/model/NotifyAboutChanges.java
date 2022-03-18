package com.courseapp.backend.model;


public interface NotifyAboutChanges {
    String SUCCESSFULLY_SAVED = "An object has been saved successfully!";
    String SUCCESSFULLY_UPDATED = "An object has been updated successfully!";
    String SUCCESSFULLY_LOADED = "An object has been loaded successfully!";
    String SUCCESSFULLY_REMOVED = "An object has been removed successfully!";

    default void afterSave() {
        printMessage(SUCCESSFULLY_SAVED);
    }

    default void afterUpdate() {
        printMessage(SUCCESSFULLY_UPDATED);
    }

    default void afterLoad() {
        printMessage(SUCCESSFULLY_LOADED);
    }

    default void afterRemove() {
        printMessage(SUCCESSFULLY_REMOVED);
    }

    private void printMessage(String message) {
        System.out.println(message);
    }
}
