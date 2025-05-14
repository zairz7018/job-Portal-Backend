package com.jobportal.service;

import com.jobportal.dto.ProfileDTO;
import com.jobportal.entity.Profile;
import com.jobportal.entity.User;
import com.jobportal.exception.JobPortalException;
import com.jobportal.repository.ProfileRepository;
import com.jobportal.repository.UserRepository;
import com.jobportal.utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("profileService")

public class ProfileServiceImpl implements ProfileService{
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Long createProfile(String email) throws JobPortalException {
        // Récupérer l'utilisateur à partir de l'email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new JobPortalException("USER_NOT_FOUND"));

        // Créer un nouveau profil
        Profile profile = new Profile();
        profile.setId(Utilities.getNextSequence("profiles"));
        profile.setEmail(email);
        profile.setName(user.getName());  // Ajouter le nom de l'utilisateur au profil

        // Initialiser les autres champs du profil
        profile.setSkills(new ArrayList<>());
        profile.setExperiences(new ArrayList<>());
        profile.setCertifications(new ArrayList<>());

        // Sauvegarder le profil
        profileRepository.save(profile);

        // Retourner l'ID du profil créé
        return profile.getId();
    }


    @Override
    public ProfileDTO getProfile(Long id) throws JobPortalException {
        return profileRepository.findById(id).orElseThrow(
                ()->new JobPortalException("PROFILE_NOT_FOUND")
        ).toDTO();
    }

    @Override
    public ProfileDTO updateProfile(ProfileDTO profileDTO) throws JobPortalException {
        profileRepository.findById(profileDTO.getId()).orElseThrow(
                ()-> new JobPortalException("PROFILE_NOT_FOUND")
        );

        profileRepository.save(profileDTO.toEntity());
        return profileDTO;
    }

    @Override
    public List<ProfileDTO> getAllProfiles() {
        return profileRepository.findAll().stream().map((x)->
                x.toDTO()).toList();
    }
}
