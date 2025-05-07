package org.example.tests;

import org.example.pages.DraftModalWindow;
import org.example.pages.DraftsPage;
import org.example.pages.NewMessageModalWindow;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.*;

public class DraftMessageTest extends BaseTest {
    NewMessageModalWindow newMessageModal;
    DraftsPage draftsPage;
    DraftModalWindow draftModalWindow;

    @Test(dataProvider = "draftData")
    public void testDraftMessage(String title, String body, String recipient) {
        SoftAssert softAssert = new SoftAssert();
        newMessageModal = sidebar.showNewMessageModalWindow();
        newMessageModal.writeTitle(title);
        newMessageModal.writeRecipient(recipient);
        newMessageModal.writeBody(body);
        newMessageModal.saveAndClose();

        draftsPage = sidebar.goToDraftsPage();
        softAssert.assertTrue(draftsPage.isNotEmptyDraftEmails());

        draftModalWindow = draftsPage.showDraftModalWindowByTitle(title);
        softAssert.assertEquals(draftModalWindow.getRecipient(), recipient);
        softAssert.assertEquals(draftModalWindow.getBody(), body);
        softAssert.assertEquals(draftModalWindow.getTitle(), title);

        draftModalWindow.deleteOpenDraft();
        assertFalse(draftsPage.isNotEmptyDraftEmails());
        softAssert.assertAll();
    }

    @DataProvider(name = "draftData")
    public Object[][] provideDraftData() {
        return new Object[][] {
                {"Title A", "Body A", "emailA@example.com"},
                {"Title B", "Body B", "emailB@example.com"}
        };
    }
}
