package org.example.tests;

import org.example.config.Config;
import org.example.pages.MailPage;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class IncomeEmailTest extends BaseTest {
    private String NUMBER_IN_MAIL_TITLE = "1111";
    private String NUMBER_IN_MAIL = "1234";
    private String senderEmail = Config.getSenderEmail();
    MailPage mailPage;

    @Test
    @Parameters
    public void testEmail() {
        assertTrue(inboxPage.isEmailPresent(NUMBER_IN_MAIL_TITLE), "We don't have email with number: " + NUMBER_IN_MAIL_TITLE);

        mailPage = inboxPage.goToMailByNameContains(NUMBER_IN_MAIL_TITLE);
        assertTrue(mailPage.textMailContains(NUMBER_IN_MAIL));
        assertEquals(mailPage.getSenderEmail(), senderEmail);
    }
}
