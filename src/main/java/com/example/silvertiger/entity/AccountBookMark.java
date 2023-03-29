package com.example.silvertiger.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(AccountBookMarkPk.class)
@Table(name = "account_bookmark")
@Entity
public class AccountBookMark implements Serializable {
    @Id
    @JoinColumn(name="account_id")
    private String accountId;

    @Id
    @JoinColumn(name="bookmark_id")
    private String bookMarkId;
}