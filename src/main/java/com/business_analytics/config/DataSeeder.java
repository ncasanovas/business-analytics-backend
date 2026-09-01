package com.business_analytics.config;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.business_analytics.model.entity.Customer;
import com.business_analytics.model.entity.Event;
import com.business_analytics.model.entity.Order;
import com.business_analytics.model.entity.OrderItems;
import com.business_analytics.model.entity.Product;
import com.business_analytics.repository.CustomerRepository;
import com.business_analytics.repository.EventRepository;
import com.business_analytics.repository.OrderItemsRepository;
import com.business_analytics.repository.OrderRepository;
import com.business_analytics.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;

@Component
@Profile("dev") // solo corre con perfil dev, no en prod
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

  private final CustomerRepository customerRepository;
  private final OrderRepository orderRepository;
  private final EventRepository eventRepository;
  private final ProductRepository productRepository;
  private final OrderItemsRepository orderItemsRepository;

  private List<Product> products;

  private List<Customer> customers;
  private List<Order> orders;

  @Override
  public void run(String... args) {
    if (customerRepository.count() > 0)
      return; // no duplicar si ya hay data

    products = seedProducts(50);
    customers = seedCustomers(30);
    orders = seedOrders(500);
    seedOrderItems(6);
    seedEvents(6);
    seedOrdersAndEvents(customers, products, 2000);
  }

  private List<Customer> seedCustomers(int amount) {
    Faker faker = new Faker();
    List<Customer> customers = new ArrayList<>();
    for (int i = 0; i < amount; i++) {
      Customer c = Customer.builder()
          .name(faker.name().fullName())
          .email(faker.internet().emailAddress())
          .createdAt(randomDateInLast(180)) // últimos 180 días
          .build();
      customers.add(c);
    }
    return customerRepository.saveAll(customers);
  }

  private LocalDateTime randomDateInLast(int days) {
    long randomDays = ThreadLocalRandom.current().nextLong(days);
    return LocalDateTime.now().minusDays(randomDays);
  }

  private Double randomPrice(Double minPrice, Double maxPrice) {
    Double randomPrice = ThreadLocalRandom.current().nextDouble(minPrice, maxPrice);
    return randomPrice;
  }

  private int randomItemsAmount(int minAmount, int maxAmount) {
    int randomAmountItems = ThreadLocalRandom.current().nextInt(minAmount, maxAmount);
    return randomAmountItems;
  }

  private Long randomCustomer() {

    Long randomCustomer = ThreadLocalRandom.current().nextLong(0, customers.size());
    return randomCustomer;
  }

  private Long randomOrder() {

    Long randomOrder = ThreadLocalRandom.current().nextLong(0, orders.size());
    return randomOrder;
  }

  private Long randomProduct() {

    Long randomProduct = ThreadLocalRandom.current().nextLong(0, products.size());
    return randomProduct;
  }

  private   Event.EventType randomEventType() {

    Event.EventType randomEventType = Event.EventType.values()[ThreadLocalRandom.current().nextInt(Event.EventType.values().length()))];
    return randomEventType;
  }

  private List<Product> seedProducts(int amount) {
    Faker faker = new Faker();
    List<Product> products = new ArrayList<>();
    for (int i = 0; i < amount; i++) {
      Product c = Product.builder()
          .name(faker.name().fullName())
          .category("")
          .price(randomPrice(1.00, 50000.00))
          .build();
      products.add(c);
    }
    return productRepository.saveAll(products);
  }

  private List<Order> seedOrders(int amount) {
    Faker faker = new Faker();
    List<Order> orders = new ArrayList<>();
    for (int i = 0; i < amount; i++) {
      Order c = Order.builder()
          .customerId(randomCustomer())
          .totalAmount(randomPrice(1.00, 1000000.00))
          .createdAt(randomDateInLast(180)) // últimos 180 días
          .build();
      orders.add(c);
    }
    return orderRepository.saveAll(orders);
  }

  private List<OrderItems> seedOrderItems(int amount) {
    Faker faker = new Faker();
    List<OrderItems> orderItems = new ArrayList<>();
    for (int i = 0; i < amount; i++) {
      OrderItems c = OrderItems.builder()
          .orderId(randomOrder())
          .productId(randomProduct())
          .quantity(randomItemsAmount(1, 6))
          .price(randomPrice(500.00, 1000000.00))
          .build();
      orderItems.add(c);
    }
    return orderItemsRepository.saveAll(orderItems);
  }

  private List<Event> seedEvents(int amount) {
    Faker faker = new Faker();
    List<Event> events = new ArrayList<>();
    for (int i = 0; i < amount; i++) {
      Event c = Event.builder()
          .eventType(randomEventType())
          .customerId(randomCustomer())
          .createdAt(randomDateInLast(180))
          .build();
      events.add(c);
    }
    return eventRepository.saveAll(events);
  }
}
