package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    app.group().addFirstGroup(new GroupData().withName("newGroup"));
    app.goTo().homePage();
    app.contact().addFirstContact(new ContactData()
            .withFirstname("Fname").withMiddlename("Mname").withLastname("Lname").withNickname("Nname").withCompany("ooo\"company\"")
            .withEmail("Email@gmail.com").withHomePhone("5-55-55").withMobilePhone("333").withWorkPhone("7(222)").withGroup("newGroup"), true);
    app.goTo().homePage();
  }

  @Test
  public void testContactPhones(){
   // app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFromEditForm.getHomePhone())));
    assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())));
    assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));
  }

  public String cleaned(String phone){
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
