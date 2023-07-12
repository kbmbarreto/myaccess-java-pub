package br.com.lambdateam.myaccessjava.services;

import br.com.lambdateam.myaccessjava.exceptions.NotFoundException;
import br.com.lambdateam.myaccessjava.models.PasswordModel;
import br.com.lambdateam.myaccessjava.repositories.PasswordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static br.com.lambdateam.myaccessjava.utils.PasswordEncryptationUtils.decrypt;
import static br.com.lambdateam.myaccessjava.utils.PasswordEncryptationUtils.encrypt;

@AllArgsConstructor
@Service
public class PasswordService {

    private final PasswordRepository passwordRepository;

    public List<PasswordModel> findPasswordById(Long id) throws Exception {
        try {
            List<PasswordModel> passwords = passwordRepository.findPasswordById(id);

            for (PasswordModel password : passwords) {
                String decryptedPassword = decrypt(password.getPassword());
                password.setPassword(decryptedPassword);
            }

            return passwords;
        } catch (Exception e) {
            throw new Exception("Error while decrypting password");
        }
    }

    public List<PasswordModel> listAllPasswordsByUserId(Long userId) throws Exception{
        try{
            List<PasswordModel> passwords = passwordRepository.listAllByUserId(userId);

            for (PasswordModel password : passwords) {
                String decryptedPassword = decrypt(password.getPassword());
                password.setPassword(decryptedPassword);
            }

            return passwords;
        } catch (NotFoundException e) {
            throw new NotFoundException("The user id " + userId +" was not found.");
        } catch (Exception e) {
            throw new Exception("Error while decrypting password");
        }
    }

    public List<PasswordModel> findByDescriptionAndUserId(String description, Long userId) throws Exception {
        try{
            List<PasswordModel> passwords = passwordRepository.findByDescriptionAndUserId(description, userId);

            for (PasswordModel password : passwords) {
                String decryptedPassword = decrypt(password.getPassword());
                password.setPassword(decryptedPassword);
            }

            return passwords;
        } catch (Exception e) {
            throw new Exception("Error while decrypting password");
        }
    }

    @Transactional
    public PasswordModel createPassword(PasswordModel password) throws Exception {
        try{
            String encryptedPassword = encrypt(password.getPassword());
            password.setPassword(encryptedPassword);

            return passwordRepository.save(password);
        } catch (Exception e) {
            throw new Exception("Error while encrypting password");
        }
    }

    @Transactional
    public void updatePassword(Long id, PasswordModel password) throws Exception {
        try{
            if(!passwordRepository.existsById(id)) throw new NotFoundException("The password id " + id +" was not found.");
            password.setId(id);

            String encryptedPassword = encrypt(password.getPassword());
            password.setPassword(encryptedPassword);

            passwordRepository.save(password);
        } catch (Exception e) {
            throw new Exception("Error while encrypting password");
        }
    }

    @Transactional
    public void deletePasswordById(Long id) {
        try{
            if(!passwordRepository.existsById(id)) throw new NotFoundException("The password id " + id +" was not found.");
            passwordRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException("The password id " + id +" was not found.");
        }
    }
}