package com.myshop.admin.user;

import com.myshop.common.entity.Role;
import com.myshop.common.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateNewUserWithOneRole() {
        Role roleAdmin = entityManager.find(Role.class, 1);
        User user = new User("vladimir@gmail.com", "123456Aa!", "Waldemaar", "Fingerlos");
        user.addRole(roleAdmin);

        User savedUser = repo.save(user);

        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateNewUserWithTwoRoles() {
        User user1 = new User("Walde@gmail.com", "654321Aa!", "Miro", "Waldes");
        Role roleEditor = new Role(3);
        Role roleAssistant = new Role(5);

        user1.addRole(roleEditor);
        user1.addRole(roleAssistant);

        User savedUser = repo.save(user1);

        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAllUsers() {
        Iterable<User> listUsers = repo.findAll();
        listUsers.forEach(user -> System.out.println(user));
    }

    @Test
    public void testGetUserById() {
        User user = repo.findById(1).get();
        System.out.println(user);
        assertThat(user).isNotNull();
    }

    @Test
    public void testUpdateUserDetails() {
        User user = repo.findById(1).get();
        user.setEnabled(true);
        user.setEmail("vladimirprogrammer@gmail.com");

        repo.save(user);
    }

    @Test
    public void testUpdateUserRoles() {
        User user1 = repo.findById(2).get();
        Role roleEditor = new Role(3);
        Role roleSalesperson = new Role(2);

        user1.getRoles().remove(roleEditor);
        user1.addRole(roleSalesperson);

        repo.save(user1);
    }

    @Test
    public void testZDeleteUser() {
        Integer userId = 2;
        repo.deleteById(userId);

    }

//    @Test
//    public void testGetUserByEmail() {
//        String email = "ravi@gmail.com";
//        User user = repo.getUserByEmail(email);
//
//        assertThat(user).isNotNull();
//    }
//
//    @Test
//    public void testCountById() {
//        Integer id = 1;
//        Long countById = repo.countById(id);
//
//        assertThat(countById).isNotNull().isGreaterThan(0);
//    }
//
//    @Test
//    public void testDisableUser() {
//        Integer id = 1;
//        repo.updateEnabledStatus(id, false);
//
//    }
//
//    @Test
//    public void testEnableUser() {
//        Integer id = 3;
//        repo.updateEnabledStatus(id, true);
//
//    }
//
//    @Test
//    public void testListFirstPage() {
//        int pageNumber = 0;
//        int pageSize = 4;
//
//        Pageable pageable = PageRequest.of(pageNumber, pageSize);
//        Page<User> page = repo.findAll(pageable);
//
//        List<User> listUsers = page.getContent();
//
//        listUsers.forEach(user -> System.out.println(user));
//
//        assertThat(listUsers.size()).isEqualTo(pageSize);
//    }
//
//    @Test
//    public void testSearchUsers() {
//        String keyword = "bruce";
//
//        int pageNumber = 0;
//        int pageSize = 4;
//
//        Pageable pageable = PageRequest.of(pageNumber, pageSize);
//        Page<User> page = repo.findAll(keyword, pageable);
//
//        List<User> listUsers = page.getContent();
//
//        listUsers.forEach(user -> System.out.println(user));
//
//        assertThat(listUsers.size()).isGreaterThan(0);
//    }
}
