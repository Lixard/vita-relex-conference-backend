package ru.relex.services.service;

public interface IPasswordEncoderService {
    String encode(String password);
    boolean matches(CharSequence rawPassword, String encodedPassword);
}
