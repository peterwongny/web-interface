package com.cs7s.webpage.database;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@AutoConfigureTestDatabase(replace=Replace.NONE) //uses our psql db
@EnableAutoConfiguration
@DataJpaTest
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { CustomerTest.class, Customer.class })
@SuppressWarnings("deprecation")
public class CustomerTest {
	@Autowired
	CustomerRepository customerRepo;
	
	@Test
	public void setAndGetCustomerWithSpecialRequest() throws Exception {
		boolean found = false;
		boolean thrown = false;
		Customer testCustomer = null;
		try {
			testCustomer = new Customer();
			testCustomer.setName("Tavisha");
			testCustomer.setCust_id("Z999999");
			testCustomer.setLine_id("ta.vish.a");
			testCustomer.setPhone("99999999");
			testCustomer.setAge(42);
			testCustomer.setTour_joined("9D99920171212");
			testCustomer.setAdult_num(2);
			testCustomer.setChildren_num(2);
			testCustomer.setToddler_num(2);
			testCustomer.setFee(9999);
			testCustomer.setAmt_paid(0);
			testCustomer.setSpecial_req("Test me");
			customerRepo.save(testCustomer);

			testCustomer.setAge("42");
			testCustomer.setAdult_num("2");
			testCustomer.setChildren_num("2");
			testCustomer.setToddler_num("2");
			testCustomer.setFee("9999");
			testCustomer.setAmt_paid("0");
			customerRepo.save(testCustomer);

			System.out.println("Hello");
			Iterable<Customer> customers = this.customerRepo.findAll();
			for (Customer customer : customers) {
				if (customer.getCust_id().equals(testCustomer.getCust_id())) {
					testCustomer = customer;
					found = true;
				}
			}
		} catch (Exception e) {
			System.out.println("Error occured: " + e.toString());
			thrown = true;
		}

		assertThat(!thrown).isEqualTo(true);
		assertThat(found).isEqualTo(true);
		assertThat(testCustomer).isNotNull();
		assertThat(testCustomer.getName()).isEqualTo("Tavisha");
		assertThat(testCustomer.getCust_id()).isEqualTo("Z999999");
		assertThat(testCustomer.getLine_id()).isEqualTo("ta.vish.a");
		assertThat(testCustomer.getPhone()).isEqualTo("99999999");
		assertThat(testCustomer.getAge()).isEqualTo("42");
		assertThat(testCustomer.getTour_joined()).isEqualTo("9D99920171212");
		assertThat(testCustomer.getAdult_num()).isEqualTo("2");
		assertThat(testCustomer.getChildren_num()).isEqualTo("2");
		assertThat(testCustomer.getToddler_num()).isEqualTo("2");
		assertThat(testCustomer.getFee()).isEqualTo("9999");
		assertThat(testCustomer.getAmt_paid()).isEqualTo("0");
		assertThat(testCustomer.getSpecial_req()).isEqualTo("Test me");
	}
	
	@Test
	public void setAndGetCustomerWithoutSpecialRequest() throws Exception {
		boolean found = false;
		boolean thrown = false;
		Customer testCustomer = null;
		try {
			testCustomer = new Customer();
			testCustomer.setName("Tavisha");
			testCustomer.setCust_id("Z999999");
			testCustomer.setLine_id("ta.vish.a");
			testCustomer.setPhone("99999999");
			testCustomer.setAge(42);
			testCustomer.setTour_joined("9D99920171212");
			testCustomer.setAdult_num(2);
			testCustomer.setChildren_num(2);
			testCustomer.setToddler_num(2);
			testCustomer.setFee(9999);
			testCustomer.setAmt_paid(0);
			customerRepo.save(testCustomer);

			testCustomer.setAge("42");
			testCustomer.setAdult_num("2");
			testCustomer.setChildren_num("2");
			testCustomer.setToddler_num("2");
			testCustomer.setFee("9999");
			testCustomer.setAmt_paid("0");
			customerRepo.save(testCustomer);

			Iterable<Customer> customers = this.customerRepo.findAll();
			for (Customer customer : customers) {
				if (customer.getCust_id().equals(testCustomer.getCust_id())) {
					testCustomer = customer;
					found = true;
				}
			}
		} catch (Exception e) {
			System.out.println("Error occured: " + e.toString());
			thrown = true;
		}

		assertThat(!thrown).isEqualTo(true);
		assertThat(found).isEqualTo(true);
		assertThat(testCustomer).isNotNull();
		assertThat(testCustomer.getName()).isEqualTo("Tavisha");
		assertThat(testCustomer.getCust_id()).isEqualTo("Z999999");
		assertThat(testCustomer.getLine_id()).isEqualTo("ta.vish.a");
		assertThat(testCustomer.getPhone()).isEqualTo("99999999");
		assertThat(testCustomer.getAge()).isEqualTo("42");
		assertThat(testCustomer.getTour_joined()).isEqualTo("9D99920171212");
		assertThat(testCustomer.getAdult_num()).isEqualTo("2");
		assertThat(testCustomer.getChildren_num()).isEqualTo("2");
		assertThat(testCustomer.getToddler_num()).isEqualTo("2");
		assertThat(testCustomer.getFee()).isEqualTo("9999");
		assertThat(testCustomer.getAmt_paid()).isEqualTo("0");
		assertThat(testCustomer.getSpecial_req()).isNull();
	}
}
