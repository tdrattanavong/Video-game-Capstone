package org.yearup.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.models.Profile;
import org.yearup.models.User;
import org.yearup.service.ProfileService;
import org.yearup.service.UserService;
import java.security.Principal;

@RestController
@RequestMapping("profile")
@CrossOrigin
@PreAuthorize("isAuthenticated()")   // all endpoints here require a logged-in user

public class ProfileController {

    private final ProfileService profileService;
    private final UserService userService;

    // Spring injects both services through the constructor
    public ProfileController(ProfileService profileService, UserService userService)
    {
        this.profileService = profileService;
        this.userService = userService;
    }

    // GET http://localhost:8080/profile
    // Returns the profile for whoever is currently logged in.
    // Principal is automatically populated by Spring Security from the JWT token.
    @GetMapping("")
    public Profile getProfile(Principal principal)
    {
        // Step 1: get the username from the token
        String userName = principal.getName();

        // Step 2: look up the full User object so we can get the userId
        User user = userService.getByUserName(userName);
        int userId = user.getId();

        return profileService.getUserId(userId);
    }

    // PUT http://localhost:8080/profile
    // Accepts an updated Profile in the request body and saves it.
    // The userId is always taken from the JWT — the client body cannot change whose profile gets updated.
    @PutMapping("")
    public Profile updateProfile(@RequestBody Profile profile, Principal principal)
    {
        // Step 1-2: same pattern as GET — resolve the userId from the token
        String userName = principal.getName();
        User user = userService.getByUserName(userName);
        int userId = user.getId();

        // Step 3: update and return the saved profile
        return profileService.update(userId, profile);
    }
}