package com.gmail.sasha.myproject.service.service.impl;


import com.gmail.sasha.myproject.dao.dao.DiscountDao;
import com.gmail.sasha.myproject.dao.dao.RoleDao;
import com.gmail.sasha.myproject.dao.dao.UserDao;
import com.gmail.sasha.myproject.dao.model.Discount;
import com.gmail.sasha.myproject.dao.model.Profile;
import com.gmail.sasha.myproject.dao.model.Role;
import com.gmail.sasha.myproject.dao.model.User;
import com.gmail.sasha.myproject.service.converter.DTOConverter;
import com.gmail.sasha.myproject.service.converter.EntityConverter;
import com.gmail.sasha.myproject.service.exception.UserNotFoundException;
import com.gmail.sasha.myproject.service.model.ProfileDTO;
import com.gmail.sasha.myproject.service.model.UserDTO;
import com.gmail.sasha.myproject.service.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    @Autowired
    @Qualifier("saveUserConverter")
    private EntityConverter<UserDTO, User> saveUserConverter;
    @Autowired
    @Qualifier("profileEntityConverter")
    private EntityConverter<ProfileDTO, Profile> profileEntityConverter;
    @Autowired
    private UserDao userDao;
    @Autowired
    private DiscountDao discountDao;
    @Autowired
    @Qualifier("userEntityConverter")
    private EntityConverter<UserDTO, User> userConverter;
    @Autowired
    @Qualifier("userDTOConverter")
    private DTOConverter<UserDTO, User> userDTOConverter;
    @Autowired
    @Qualifier("passwordEncoder")
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private RoleDao roleDao;

    @Override
    public UserDTO save(UserDTO userDTO) {
        User userToSave = saveUserConverter.toEntity(userDTO);
        System.out.println("------------------");
        System.out.println(userToSave);
        userToSave.setStatus("active");
      /*  Profile profile = profileEntityConverter.toEntity(userDTO.getProfile());

        System.out.println("------------------prof");
        System.out.println(profile);
        profile.setProfileId(userToSave.getId());
        profile.setUser(userToSave);*/
        Role role = roleDao.findByName("CUSTOMER_USER");
        System.out.println("------------------role");
        System.out.println(role);
        userToSave.setRole(role);
      //  userToSave.setProfile(profile);
        userToSave.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDao.create(userToSave);
        return userDTOConverter.toDTO(userToSave);
    }

    @Override
    public void assignDiscountToUser() {
        Session session = userDao.getCurrentSession();
        try {
            Transaction tx = session.getTransaction();
            if (!tx.isActive()) {
                session.beginTransaction();
            }
            User user = userDao.findById(1L);
            Discount ds = discountDao.findById(1L);
            user.setDiscount(ds);
            session.flush();

            tx.commit();


        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public List<UserDTO> getUsers() {
        List<User> usersList = userDao.findAll();
        logger.info("--------------------");
        logger.info(usersList);
        return userDTOConverter.toDTOList(usersList);
    }

    @Override
    public UserDTO validateByEmail(String email) {
        User user = userDao.validateByEmail(email);
        return userDTOConverter.toDTO(user);
    }

    @Override
    public UserDTO getUserById(Long id) {
        return null;
    }

    @Override
    public List<UserDTO> findAllUsers(Integer page, Integer elementsOnPage) {
        List<User> users = userDao.getAllUsersPaginated(page, elementsOnPage);
        return userDTOConverter.toDTOList(users);
    }

    @Override
    public Long getAmountOfPages() {
        return null;
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userDao.findOne(id);
        if (user != null) {
            return userDTOConverter.toDTO(user);
        } else {
            throw new UserNotFoundException("User with id= " + id + " was not found");
        }
    }

    @Override
    public void updatePassword(String password, Long userId) {
        User updateUser = userDao.findOne(userId);
        updateUser.setPassword(passwordEncoder.encode(password));
        userDao.update(updateUser);
    }

    @Override
    public void enableUser(Long userId) {
        User user = userDao.findOne(userId);
        user.setStatus("active");
        userDao.update(user);
    }

    @Override
    public void disableUser(Long userId) {
        User user = userDao.findOne(userId);
        user.setStatus("disabled");
        userDao.update(user);
    }

    @Override
    public void softDeleteById(Long userId) {
        User user = userDao.findOne(userId);
        user.setStatus("deleted");
        userDao.update(user);
    }
}
