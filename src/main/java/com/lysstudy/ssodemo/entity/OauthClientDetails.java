package com.lysstudy.ssodemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OauthClientDetails {

    @Id
    private String clientId;
    private String clientSecret;
    private String authorities;
    private String authorizedGrantTypes;
    private String resourceIds;
    private String webServerRedirectUri;
    private String scope;
    private int accessTokenValidity;
    private int refreshTokenValidity;
    @Column(length = 4096)
    private String additionalInformation;
    private String autoapprove;
}
