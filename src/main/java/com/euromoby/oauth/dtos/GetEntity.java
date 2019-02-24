package com.euromoby.oauth.dtos;

import com.euromoby.oauth.entities.AbstractEntity;

import java.util.Date;
import java.util.UUID;

public class GetEntity {
    private UUID id;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public GetEntity() { }

    public GetEntity(AbstractEntity entity) {
        id = entity.getId();
        createdAt = entity.getCreatedAt();
        updatedAt = entity.getUpdatedAt();
        deletedAt = entity.getDeletedAt();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }
}
