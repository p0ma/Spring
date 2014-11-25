package com.springapp.mvc.media;

/**
 * Created by Damager1 on 21.11.2014.
 */
public class ReorderDTO {
    private Long fromId;
    private Long toId;

    public ReorderDTO() {
    }

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public Long getToId() {
        return toId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }
}
