package com.civa.futbolista.user;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
        private final UserRepository userRepository;

        @Transactional
        public UserResponse updateUser(UserRequest userRequest) {

            User user = User.builder()
                    .id(userRequest.id)
                    .nombres(userRequest.getNombres())
                    .username(userRequest.getUsername())
                    .password(userRequest.getPassword())
                    .rol(Rol.USER)
                    .build();

            userRepository.updateUser(user.id, user.nombres);

            return new UserResponse("El usuario se registró satisfactoriamente");
        }

        public UserDTO getUser(Long id) {
            User user= userRepository.findById(id).orElse(null);

            if (user!=null)
            {
                UserDTO userDTO = UserDTO.builder()
                        .id(user.id)
                        .nombres(user.nombres)
                        .username(user.username)
                        .password(user.password)

                        .build();
                return userDTO;
            }
            return null;
        }
    }

