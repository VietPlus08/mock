package fa.com.mock_back_end.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuaChua {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maSuaChua;
    private String tenSuaChua;
    private Long giaSuaChua;
    private boolean status;

    @OneToMany(mappedBy = "suaChua")
    List<ChiTietHoaDonBanHang> listChiTietHoaDonBanHang;
}

