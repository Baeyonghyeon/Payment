package com.nhnacademy.payment;

@FunctionalInterface
public interface Discountable {
    /**
     * ν μΈμμ
     */
    Discountable NONE = originAmt -> 0;

    long getDiscountAmt(long originAmt);
}
