package com.tts.starsky.phonesweepcode.db.provider;

import com.tts.starsky.phonesweepcode.db.bean.Discount;

import org.greenrobot.greendao.test.DbTest;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiscountProviderTest extends DBInitTest {

    @Test
    public void discountInsert() {
        DiscountProvider discountProvider = new DiscountProvider();
        Discount discount = new Discount(null, "85折", 85, 0);
        discountProvider.discountInsert(discount);
    }
}