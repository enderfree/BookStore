package com.fdmgroup.bookstore.data;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.bookstore.model.Order;
import com.fdmgroup.bookstore.model.User;

public class TestUserArrayListRepository {
	List<User> users;
	
	UserArrayListRepository userArrayListRepository;
	
	@BeforeEach
	void init() {
		users  = new ArrayList<User>(Arrays.asList(
				new User(101, "Fire", "Cloud", "firecloud", "elf", "firecloud@gmail.com", new ArrayList<Order>()), 
				new User(102, "Sinphia", "Kelnorae", "Sin", "love!", "hallofsin@azzagat.com", new ArrayList<Order>()), 
				new User(103, "Alivilia", "Zenit", "Alice", "123", "alice@forum.zh", new ArrayList<Order>()), 
				new User(104, "Indix", "Index", "i", "Passion", "i@gmail.com", new ArrayList<Order>()), 
				new User(105, "Nomizur", "Nomosuko", "Nomi", "DRAGON!", "nom@dragoncity.net", new ArrayList<Order>())
			));
		
		userArrayListRepository = new UserArrayListRepository(users);
	}
	
	@Test
	void testIfValidateReturnsTrueForExistingUsernameAndPassword() {
		assertEquals(true, userArrayListRepository.validate("Alice", "123"));
	}
	
	@Test
	void testIfValidateReturnsFalseForNonexistingUsernameAndPassword() {
		assertEquals(false, userArrayListRepository.validate("Alice", "1234"));
	}
	
	@Test
	void testIfValidateReturnsFalseForNonexistingUsernameAndPassword2() {
		assertEquals(false, userArrayListRepository.validate("Nina", "123"));
	}
	
	@Test
	void testIfValidateReturnsFalseForNonexistingUsernameAndPassword3() {
		assertEquals(false, userArrayListRepository.validate("Nina", "1234"));
	}
	
	@Test
	void testIfFindByUserNameReturnsRightUser() {
		User nina = new User(107, "Nina", "Sapphire", "GreatestOne101", "Eli", "Nina@gouv.edu.sh.co", new ArrayList<Order>());
		users.add(nina);
		
		assertEquals(nina, userArrayListRepository.findByUsername("GreatestOne101"));
	}
	
	@Test
	void testIfSaveAddsUserWithoutChangingId() {
		User nina = new User(107, "Nina", "Sapphire", "GreatestOne101", "Eli", "Nina@gouv.edu.sh.co", new ArrayList<Order>());
		userArrayListRepository.save(nina);
		
		assertEquals(107, userArrayListRepository.findByUsername("GreatestOne101").getUserId());
	}
	
	@Test
	void testIfSaveGivesIdToUserWithoutId(){
		User user = new User();
		userArrayListRepository.save(user);
		
		assertEquals(2, user.getUserId());
	}
	
	@Test
	void testIfSaveOverridesExistingUsers() {
		User nina = new User(101, "Nina", "Sapphire", "GreatestOne101", "Eli", "Nina@gouv.edu.sh.co", new ArrayList<Order>());
		userArrayListRepository.save(nina);
		
		assertEquals(nina, userArrayListRepository.findById(101));
	}
	
	@Test
	void testIfDeleteRemoveTheUserFromTheList() {
		User firecloud = userArrayListRepository.findByUsername("firecloud");
		
		userArrayListRepository.delete(firecloud);
		
		assertEquals(null, userArrayListRepository.findByUsername("firecloud"));
	}
	
	@Test
	void testIfGenerateIDReturnsTheNextID() {
		assertEquals(3, UserArrayListRepository.generateId()); // 3, not two since it is called in testIfSaveGivesIdToUserWithoutId()
	}
	
	@Test
	void testIfFindByIdReturnsTheProperUser() {
		User alice = userArrayListRepository.findByUsername("Alice");
		
		assertEquals(alice, userArrayListRepository.findById(103));
	}
	
	@Test
	void testIfFindAllReturnsTheWholeList() {
		assertEquals(users, userArrayListRepository.findAll());
	}
}
