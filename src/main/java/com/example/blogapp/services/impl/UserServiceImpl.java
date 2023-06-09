package com.example.blogapp.services.impl;

import com.example.blogapp.config.Constants;
import com.example.blogapp.entities.Role;
import com.example.blogapp.entities.User;
import com.example.blogapp.exceptions.ResourceNotFoundException;
import com.example.blogapp.payloads.UserDto;
import com.example.blogapp.repositories.RoleRepo;
import com.example.blogapp.repositories.UserRepo;
import com.example.blogapp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private RoleRepo roleRepo;

    public UserServiceImpl(UserRepo userRepo,
                           ModelMapper mapper,
                           PasswordEncoder passwordEncoder,
                           RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.modelMapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.userDtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.userToUserDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        user = this.userRepo.save(user);

        return this.userToUserDto(user);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return this.userToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> allUser = this.userRepo.findAll();
        return allUser.stream().map(this::userToUserDto).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        this.userRepo.delete(user);
    }

    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);

        // Encoded the password
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        // Assign roles
        Role role = this.roleRepo.findById(Constants.NORMAL_USER).get();

        user.getRoles().add(role);

        User save = this.userRepo.save(user);

        return this.modelMapper.map(save, UserDto.class);
    }

    private User userDtoToUser(UserDto userDto) {
//        User user = new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setAbout(userDto.getAbout());
//        user.setPassword(userDto.getPassword());

        return this.modelMapper.map(userDto, User.class);
    }

    private UserDto userToUserDto(User user) {
//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setAbout(user.getAbout());
//        userDto.setPassword(user.getPassword());
//        return userDto;

        return this.modelMapper.map(user, UserDto.class);
    }
}
