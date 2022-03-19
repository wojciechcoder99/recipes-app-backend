package com.courseapp.backend.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity implements NotifyAboutChanges {
    @PostLoad
    @Override
    public void afterLoad() {
        NotifyAboutChanges.super.afterLoad();
    }

    @PostPersist
    @Override
    public void afterSave() {
        NotifyAboutChanges.super.afterSave();
    }

    @PostUpdate
    @Override
    public void afterUpdate() {
        NotifyAboutChanges.super.afterUpdate();
    }

    @PostRemove
    @Override
    public void afterRemove() {
        NotifyAboutChanges.super.afterRemove();
    }
}
