package com.lucas.rest_api.services.impl;

import com.lucas.rest_api.domain.User;
import com.lucas.rest_api.domain.dto.UserDTO;
import com.lucas.rest_api.repositories.UserRepository;
import com.lucas.rest_api.services.exceptions.DataIntegrityViolationException;
import com.lucas.rest_api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    public static final String NAME = "Lucas";
    public static final Integer ID = 1;
    public static final String EMAIL = "lucas@gmail.com";
    public static final String PASSWORD = "123";
    public static final String OBJECT_NOT_FOUND = "Object not found!";

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); //Inicializa os mocks anotados com @Mock na classe
        startUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(repository.findById(anyInt())).thenReturn(optionalUser);

        User response = service.findById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJECT_NOT_FOUND));

        try{
            service.findById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJECT_NOT_FOUND, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfUsers() {
        when(repository.findAll()).thenReturn(List.of(user));

        List<User> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(0).getClass());

        assertEquals(ID, response.get(0).getId());
        assertEquals(NAME, response.get(0).getName());
        assertEquals(EMAIL, response.get(0).getEmail());
        assertEquals(PASSWORD, response.get(0).getPassword());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(repository.save(ArgumentMatchers.any())).thenReturn(user);

        User response = service.create(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());

    }

    @Test
    void whenCreateThenReturnAnDataIntegrityViolationException() {
        when(repository.findByEmail(ArgumentMatchers.anyString())).thenReturn(optionalUser);

        userDTO = new UserDTO(null, "New User", EMAIL, "123");

        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            service.create(userDTO);
        });

        assertEquals("E-mail already exists", exception.getMessage());
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(repository.save(ArgumentMatchers.any())).thenReturn(user);

        User response = service.update(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());

    }

    @Test
    void whenUpdateThenReturnAnDataIntegrityViolationException() {
        when(repository.findByEmail(ArgumentMatchers.anyString())).thenReturn(optionalUser);

        UserDTO userDTO = new UserDTO(2, NAME, EMAIL, PASSWORD); // mesmo email e outro ID (usuario diferente)

        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            service.update(userDTO);
        });

        assertEquals("E-mail already exists", exception.getMessage());
    }

    @Test
    void deleteWithSuccess() {
        when(repository.findById(ArgumentMatchers.anyInt())).thenReturn(optionalUser);
        doNothing().when(repository).deleteById(anyInt());
        service.delete(ID);
        verify(repository, times(1)).deleteById(ID);
    }

    @Test
    void deleteWithObjectNotFoundException() {
        when (repository.findById(anyInt()))
                .thenThrow(new ObjectNotFoundException(OBJECT_NOT_FOUND));
        try {
            service.delete(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJECT_NOT_FOUND, ex.getMessage());
        }
    }

    //inicializar os dados
    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}