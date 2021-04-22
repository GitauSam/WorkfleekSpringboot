package com.comulynx.wallet.rest.api;

import com.comulynx.wallet.rest.api.repository.TransactionRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TransactionRepository.class)
public class TransactionRepositoryTests {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void testGetMiniStatementUsingCustomerIdAndAccountNo() {
        transactionRepository.getMiniStatementUsingCustomerIdAndAccountNo("CUST1122", "ACT1122");
    }
}
