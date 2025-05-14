package org.example.tests;

import org.example.pages.DraftModalWindow;
import org.example.pages.DraftsPage;
import org.example.pages.NewMessageModalWindow;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DraftMessageTest extends BaseTest {
    NewMessageModalWindow newMessageModal;
    DraftsPage draftsPage;
    DraftModalWindow draftModalWindow;

    @Test(dataProvider = "draftData")
    public void testDraftMessage(String title, String body, String recipient) {
        SoftAssert softAssert = new SoftAssert();

        createAndSaveDraftEmail(title, body, recipient);
        goToDrafts();
        verifyNotEmptyDraft(softAssert);
        verifyDraftContent(title, body, recipient, softAssert);
        deleteAndVerifyDraftRemoved(softAssert);

        softAssert.assertAll();
    }

    @DataProvider(name = "draftData")
    public Object[][] provideDraftData() {
        return new Object[][]{
                {"Title A", "Body A", "emailA@example.com"},
                {"Title B", "Body B", "emailB@example.com"},
                {"Title C", "Body C", "emailC@example.com"}
        };
    }

    private void createAndSaveDraftEmail(String title, String body, String recipient) {
        newMessageModal = sidebar.showNewMessageModalWindow();
        newMessageModal.writeTitle(title);
        newMessageModal.writeRecipient(recipient);
        newMessageModal.writeBody(body);
        newMessageModal.saveAndClose();
    }

    private void goToDrafts() {
        draftsPage = sidebar.goToDraftsPage();
    }

    private void verifyEmptyDraft(SoftAssert softAssert) {
        softAssert.assertTrue(draftsPage.isEmptyDraftEmails());
    }

    private void verifyNotEmptyDraft(SoftAssert softAssert) {
        softAssert.assertFalse(draftsPage.isEmptyDraftEmails());
    }

    private void verifyDraftContent(String title, String body, String recipient, SoftAssert softAssert) {
        draftModalWindow = draftsPage.showDraftModalWindowByTitle(title);
        softAssert.assertEquals(draftModalWindow.getRecipient(), recipient);
        softAssert.assertEquals(draftModalWindow.getBody(), body);
        softAssert.assertEquals(draftModalWindow.getTitle(), title);
    }

    private void deleteAndVerifyDraftRemoved(SoftAssert softAssert) {
        draftModalWindow.deleteOpenDraft();
        verifyEmptyDraft(softAssert);
    }
}
