package com.example.bank.auth.dto;


        import com.example.bank.auth.entities.RoleEntity;
        import lombok.Builder;
        import lombok.Data;;

        import java.util.List;


@Data
@Builder
public class StaffLoginResponse {

    private Integer id;

    private String firstName;
    private String lastName;
    private String email;
    private List<RoleEntity> role;
    private String accessToken;


}
