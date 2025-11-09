package services;

import model.Compte;
import org.springframework.stereotype.Service;
import repositories.CompteRepository;

import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    private final CompteRepository compteRepo;
    public UserDetailsServiceImpl(CompteRepository compteRepo) {
        this.compteRepo = compteRepo; }
    @Override
    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException {
        Compte compte = compteRepo.findByUsername(username)
                .orElseThrow(() -> new
                        UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                compte.getUsername(),
                compte.getPassword(),
                compte.getRoles().stream()
                        .map(r -> new SimpleGrantedAuthority(r.getNom()))
                        .collect(Collectors.toList())
        );
    }
}

