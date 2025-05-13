package com.jobportal.service;

import com.jobportal.dto.LoginDTO;
import com.jobportal.dto.NotificationDto;
import com.jobportal.dto.ResponseDto;
import com.jobportal.dto.UserDTO;
import com.jobportal.entity.OTP;
import com.jobportal.entity.User;
import com.jobportal.exception.JobPortalException;
import com.jobportal.repository.NotificationRepository;
import com.jobportal.repository.OTPRepository;
import com.jobportal.repository.UserRepository;
import com.jobportal.utility.Data;
import com.jobportal.utility.Utilities;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service(value = "userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OTPRepository otpRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private NotificatioService notificationService;

    @Override
    public UserDTO registerUser(UserDTO userDTO) throws JobPortalException {
        // 1. Vérification existence user
        Optional<User> optional = userRepository.findByEmail(userDTO.getEmail());
        if (optional.isPresent()) throw new JobPortalException("User_Found!");

        // 2. D'abord persister l'utilisateur
        userDTO.setId(Utilities.getNextSequence("users"));
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = userRepository.save(userDTO.toEntity()); // ◀◀◀ Sauvegarde d'abord

        // 3. Maintenant créer le profil
        userDTO.setProfileId(profileService.createProfile(user.getEmail())); // ◀◀◀ Email du user persisté

        return user.toDTO();
    }

    @Override
    public UserDTO loginUser(LoginDTO loginDTO) throws JobPortalException {
        User user=userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(()->new JobPortalException("USER_NOT_FOUND"));
        if (!passwordEncoder.matches(loginDTO.getPassword(),user.getPassword()))throw new JobPortalException("INVALID_CREDENTIALS");
        return user.toDTO();
    }

    @Override
    public Boolean sendOtp(String email) throws Exception {
        User user=userRepository.findByEmail(email).orElseThrow(()->new JobPortalException("USER_NOT_FOUND"));
        MimeMessage mm = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mm,true);
        message.setTo(email);
        message.setSubject("Your Otp Code");
        String genOtp = Utilities.generateOTP();
        OTP otp=new OTP(email,genOtp, LocalDateTime.now());
        otpRepository.save(otp);
        message.setText(Data.getMessageBody(genOtp,user.getName()), true);
        mailSender.send(mm);
        return true;
    }

    @Override
    public Boolean verifyOtp(String email, String otp) throws JobPortalException {
        OTP otpEntity = otpRepository.findById(email).orElseThrow(()->new JobPortalException("OTP_NOT_FOUND"));
        if(!otpEntity.getOtpCode().equals(otp))throw new JobPortalException("OTP_INCORRECT!");
        return true;
    }

    @Override
    public ResponseDto changePassword(LoginDTO loginDTO) throws JobPortalException {
        User user=userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(()->new JobPortalException("USER_NOT_FOUND"));
        user.setPassword(passwordEncoder.encode(loginDTO.getPassword()));
        userRepository.save(user);
        NotificationDto noti = new NotificationDto();
        noti.setUserId(user.getId());
        noti.setMessage("Your password has been changed");
        noti.setAction("Password Reset");
        notificationService.sendNotification(noti);
            return new ResponseDto("Password changed successfully!");
    }
    @Scheduled(fixedRate = 60000)
    public void removeExpiredOTPs(){
        LocalDateTime expiry = LocalDateTime.now().minusMinutes(5);
        List<OTP>expiredOTPs=otpRepository.findByCreationTimeBefore(expiry);
        if (!expiredOTPs.isEmpty()){
            otpRepository.deleteAll(expiredOTPs);
            System.out.println("Removed "+ expiredOTPs.size() + "expired OTPs . ");
        }
    }
}
