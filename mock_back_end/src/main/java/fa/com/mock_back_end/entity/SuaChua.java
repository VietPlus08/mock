package fa.com.mock_back_end.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @Column(length = 50)
    private String tenSuaChua;
    private Long giaSuaChua;
    private boolean status = true;

    @OneToMany(mappedBy = "suaChua")
    List<ChiTietHoaDonBanHang> listChiTietHoaDonBanHang;
}

