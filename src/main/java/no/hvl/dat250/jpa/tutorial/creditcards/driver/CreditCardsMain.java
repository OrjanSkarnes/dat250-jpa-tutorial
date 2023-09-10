package no.hvl.dat250.jpa.tutorial.creditcards.driver;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import no.hvl.dat250.jpa.tutorial.creditcards.*;

import java.util.ArrayList;
import java.util.Collection;

public class CreditCardsMain {

  static final String PERSISTENCE_UNIT_NAME = "jpa-tutorial";

  public static void main(String[] args) {
    try (EntityManagerFactory factory = Persistence.createEntityManagerFactory(
        PERSISTENCE_UNIT_NAME);
      EntityManager em = factory.createEntityManager()) {
      em.getTransaction().begin();
      createObjects(em);
      em.getTransaction().commit();
    }
  }

  private static void createObjects(EntityManager em) {
    Customer customer = new Customer();
    customer.setName("Max Mustermann");

    Address address = new Address();
    address.setStreet("Inndalsveien");
    address.setNumber(28);

    CreditCard card = new CreditCard();
    card.setNumber(12345);
    card.setBalance(-5000);
    card.setCreditLimit(-10000);

    Pincode pincode = new Pincode();
    pincode.setCode("123");
    pincode.setCount(1);

    CreditCard card2 = new CreditCard();
    card2.setNumber(123);
    card2.setBalance(1);
    card2.setCreditLimit(2000);

    Bank bank = new Bank();
    bank.setName("Pengebank");

    Collection<Address> addresses = new ArrayList<>();
    addresses.add(address);
    customer.setAddresses(addresses);


    card2.setPincode(pincode);
    card.setPincode(pincode);

    card.setOwningBank(bank);
    card2.setOwningBank(bank);

    Collection<CreditCard> cards = new ArrayList<>();
    cards.add(card);
    cards.add(card2);

    customer.setCreditCards(cards);

    em.persist(address);
    em.persist(card);
    em.persist(card2);
    em.persist(pincode);
    em.persist(bank);
    em.persist(customer);
  }
}
