package org.sopt.laboratory.lab8.product.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Brand {
    TYPE_SERVICE("타입서비스"),
    NOTIA("노티아"),
    CIOR("시오르"),
    MILO("밀로")
    ;
    private final String name;
}
