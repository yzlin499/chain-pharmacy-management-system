package top.yzlin.chainpharmacymanagementsystem.entity.pass;

import lombok.Data;
import top.yzlin.chainpharmacymanagementsystem.entity.Store;
import top.yzlin.chainpharmacymanagementsystem.entity.User;

@Data
public class PassUser {
    private Long id;
    private String name;
    private String username;
    private String image;
    private String phone;
    private Store store;

    private String authority;

    public PassUser() {

    }

    public PassUser(User user) {
        id = user.getId();
        name = user.getName();
        username = user.getUsername();
        image = user.getImage();
        phone = user.getPhone();
        store = user.getStore();
        authority = user.getAuthorities().get(0).getAuthority();
    }
}
