package com.item.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: zhegong
 **/
public class IdempotentRecordEntity implements Serializable {
    private Long id;

    private String message_data_type;

    private Date create_time;

    private String status;

    private String message_body;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage_data_type() {
        return message_data_type;
    }

    public void setMessage_data_type(String message_data_type) {
        this.message_data_type = message_data_type;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage_body() {
        return message_body;
    }

    public void setMessage_body(String message_body) {
        this.message_body = message_body;
    }
}
