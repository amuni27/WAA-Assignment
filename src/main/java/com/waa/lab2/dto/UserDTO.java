package com.waa.lab2.dto;

import com.waa.lab2.domain.Post;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    long id;
    String name;
}
