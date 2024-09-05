package com.jz.test.redistest.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@Builder
public class User2 {

    @Builder.Default
    private Long id = 1L;

    private String name;

    private String address;

    private Long priority;

    private User2Inner user2Inner;


    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public User2(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public User2() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Data
    public static class User2Inner {
        private String name;
    }
}
