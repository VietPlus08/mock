package fa.com.mock_back_end.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ibm.icu.text.Transliterator;

import fa.com.mock_back_end.dto.NhanVienDTOThangDN8;
import fa.com.mock_back_end.dto.UserInforDetails;
import fa.com.mock_back_end.entity.NhanVien;
import fa.com.mock_back_end.repository.NhanVienRepository;
import fa.com.mock_back_end.service.UserInforService;

@Service
public class UserInforServiceImpl implements UserInforService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserInforServiceImpl() {
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	Optional<NhanVien> userDetail = nhanVienRepository.findById(username);

	// Converting userDetail to UserDetails
	return userDetail.map(UserInforDetails::new)
		.orElseThrow(() -> new UsernameNotFoundException("Account not found" + username));
    }

    /**
     * @author ThangDN8 Lưu 1 nhân viên mới vào database. Tự tạo mã nhân viên dựa
     *         theo họ tên nhân viên và đánh số cho mã nhân viên nếu có mã trùng
     *         trong database.
     */
    public String addUser(NhanVienDTOThangDN8 nhanVienDTO) {
	// Tự tạo mã nhân viên dựa trên họ tên
	Transliterator transliterator = Transliterator.getInstance("Any-Latin; NFD; [:Nonspacing Mark:] Remove; NFC");
	String asciiHoTen = transliterator.transliterate(nhanVienDTO.getTenNhanVien().trim());

	String[] arrayHoTen = asciiHoTen.split(" "); // Tạo mảng chứa các từ trong họ tên
	StringBuffer maNhanVien = new StringBuffer(arrayHoTen[arrayHoTen.length - 1]); // Lấy tên
	// Add ký tự đầu tiên của họ và tên đệm vào đuôi của tên
	StringBuffer firstChar = new StringBuffer("");
	for (int i = 0; i < arrayHoTen.length - 1; i++) {
	    firstChar.append(arrayHoTen[i].charAt(0));
	}
	maNhanVien.append(firstChar);
	/**
	 * Kiểm tra trong database có bao nhiêu người trùng với mã nhân viên vừa được
	 * tạo, sau đó thêm số vào đuôi mã nhân viên dựa theo số lượng đã có trước đó
	 */
	List<String> maNVList = nhanVienRepository.getListMaNhanVien(maNhanVien.toString());
	if (!maNVList.isEmpty()) {
	    maNhanVien.append(maNVList.size());
	}

	// Tạo instance NhanVien từ NhanVienDTO
	NhanVien nhanVien = new NhanVien();
	nhanVien.setMaNhanVien(maNhanVien.toString());
	nhanVien.setTenNhanVien(nhanVienDTO.getTenNhanVien());
	nhanVien.setNgaySinh(nhanVienDTO.getNgaySinh());
	nhanVien.setGioiTinh(nhanVienDTO.getGioiTinh());
	nhanVien.setChucVu("ROLE_STAFF");
	nhanVien.setMatKhau(passwordEncoder.encode(nhanVienDTO.getMatKhau()));

	nhanVienRepository.save(nhanVien);
	return "User Added Successfully";
    }
}
