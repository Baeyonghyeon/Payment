package com.nhnacademy.payment;

public enum Coupon implements Discountable {

    /* 10% */
    RATE(0.1) {
        @Override
        public long getDiscountAmt(long originAmt) {
            return (long) (originAmt * this.getRate());
        }
    },
    /* 1_000 */
    FIXED(1_000L) {
        @Override
        public long getDiscountAmt(long originAmt) {
            if (originAmt < this.getFixed()) {
                return originAmt;
            }
            return this.getFixed();
        }
    };

    private final double rate;
    private final long fixed;

    public double getRate() {
        return rate;
    }

    public long getFixed() {
        return fixed;
    }

    Coupon(double rate, long fixed) {
        this.rate = rate;
        this.fixed = fixed;
    }

    Coupon(double rate) {
        this(rate, 0L);
    }

    Coupon(long fixed) {
        this(0, fixed);
    }

}

