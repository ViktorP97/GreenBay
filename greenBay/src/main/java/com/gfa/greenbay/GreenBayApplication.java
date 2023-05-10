package com.gfa.greenbay;

import com.gfa.greenbay.models.Item;
import com.gfa.greenbay.models.UserEntity;
import com.gfa.greenbay.repositories.ItemRepository;
import com.gfa.greenbay.repositories.UserRepository;
import com.gfa.greenbay.security.RoleType;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GreenBayApplication implements CommandLineRunner {

	private final UserRepository userRepository;
	private final ItemRepository itemRepository;

	@Autowired
	public GreenBayApplication(UserRepository userRepository, ItemRepository itemRepository) {
		this.userRepository = userRepository;
		this.itemRepository = itemRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(GreenBayApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		UserEntity user1 = new UserEntity("user1", "password1", RoleType.USER);
		UserEntity user2 = new UserEntity("user2", "password2", RoleType.USER);
		UserEntity admin = new UserEntity("admin", "password3", RoleType.USER);

		Item item1 = new Item("Item 1", "Description 1", "http://example.com/item1.jpg", 50, 100);
		item1.setSeller(user1);
		Item item2 = new Item("Item 2", "Description 2", "http://example.com/item2.jpg", 100, 200);
		item2.setSeller(user2);
		Item item3 = new Item("Item 3", "Description 3", "http://example.com/item3.jpg", 75, 150);
		item3.setSeller(user2);
		Item item4 = new Item("Item 4", "Description 4", "http://example.com/item4.jpg", 200, 500);
		item4.setSeller(user2);
		Item item5 = new Item("Item 5", "Description 5", "http://example.com/item5.jpg", 150, 400);
		item5.setSeller(user2);
		Item item6 = new Item("Item 6", "Description 6", "http://example.com/item6.jpg", 300, 500);
		item6.setSeller(user1);
		Item item7 = new Item("Item 7", "Description 7", "http://example.com/item7.jpg", 500, 1500);
		item7.setSeller(user2);
		item7.setSold(true);
		item7.setBuyer(user1);
		Item item8 = new Item("Item 8", "Description 8", "http://example.com/item8.jpg", 250, 700);
		item8.setSeller(user2);
		item8.setSold(true);
		item8.setBuyer(admin);

		userRepository.saveAll(Arrays.asList(user1, user2, admin));
		itemRepository.saveAll(Arrays.asList(item1, item2, item3, item4, item5, item6, item7, item8));
	}
}
