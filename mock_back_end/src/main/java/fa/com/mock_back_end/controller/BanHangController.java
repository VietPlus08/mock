package fa.com.mock_back_end.controller;

import fa.com.mock_back_end.Converter.HoaDonConVerter;
import fa.com.mock_back_end.Converter.QuanLyGiaoDichConverTer;
import fa.com.mock_back_end.dto.*;
import fa.com.mock_back_end.entity.ChiTietHoaDonBanHang;
import fa.com.mock_back_end.entity.HoaDonBanHang;
import fa.com.mock_back_end.entity.KhachHang;
import fa.com.mock_back_end.entity.SanPham;
import fa.com.mock_back_end.service.ChiTietHDBHService;
import fa.com.mock_back_end.service.HDBHService;
import fa.com.mock_back_end.service.KhachHangService;
import fa.com.mock_back_end.service.SanPhamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;



import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;



/**
 * @Author Nguyễn Xuân Long
 * Bán Hàng controller phục vụ lưu hóa đơn và xuất lịch sử giao dịch
 */
@RestController
@CrossOrigin
public class BanHangController {

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private HDBHService hdbhService;

    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private ChiTietHDBHService chiTietHDBHService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private HoaDonConVerter hoaDonConVerter;

    @Autowired
    private QuanLyGiaoDichConverTer quanLyGiaoDichConverTer;




/**
 * @Author Nguyễn Xuân Long
 * Get danh sách giao dịch
 * Method Get
 * @return List<DanhSachQuanLyGiaoDichDTO>
 */
    @GetMapping(value = "/invoice")
    public ResponseEntity<List<DanhSachQuanLyGiaoDichDTO>> getItem() {
        List<DanhSachQuanLyGiaoDichDTO> lyGiaoDichDTO = new ArrayList<>();
//        List<HoaDonBanHang> listHoaDonBanHang = hdbhService.findAll();
        List<HoaDonBanHang> listHoaDonBanHang = Optional.ofNullable(hdbhService.findAll()).orElse(Collections.emptyList());

        for (HoaDonBanHang items : listHoaDonBanHang) {
            DanhSachQuanLyGiaoDichDTO danhSachQuanLyGiaoDichDTO = quanLyGiaoDichConverTer.toQuanLyGiaoDichDto(items);

            long tongHoadon = 0;
            List<ChiTietHoaDonBanHangDTO> list = new ArrayList<>();
            for (ChiTietHoaDonBanHang element : items.getListChiTietHoaDonBanHang()) {
                ChiTietHoaDonBanHangDTO chiTietHoaDonBanHangDTO = hoaDonConVerter.toDto(element);
                list.add(chiTietHoaDonBanHangDTO);
                tongHoadon = tongHoadon + element.getGiaBanThuc() * element.getSoLuong();
            }
            danhSachQuanLyGiaoDichDTO.setTongHoaDon(tongHoadon);
            danhSachQuanLyGiaoDichDTO.setChiTietHoaDonBanHang(list);
            lyGiaoDichDTO.add(danhSachQuanLyGiaoDichDTO);

        }
        return ResponseEntity.ok(lyGiaoDichDTO);
    }

    /**
     * @Author Nguyễn Xuân Long
     * Add thông tin hóa đơn
     * Method Post
     * @return HoaDonBanHangDTO
     */
    @PostMapping(value = "/invoice")

    public ResponseEntity<Map<String, String>> addItem(@Valid @RequestBody BanHangDTO banHangDTO) {

        List<TongHopHoaDonDTO> listChiTietHoaDon = banHangDTO.getChiTietHoaDonBanHang();

        Map<String, String> errors = new HashMap<>();
        int index = 0;
        for (TongHopHoaDonDTO items : listChiTietHoaDon) {
            SanPham sanPham = sanPhamService.findById(items.getMaSanPham()).orElse(null);

            if(items.getSoLuong()>sanPham.getSoLuong()){
                errors.put("chiTietHoaDonBanHang["+index+"].soLuong", "So luong san pham con lai khong dap ung yeu cau cua ban ");
            }
            index++;
        }

        if (!errors.isEmpty()) {
            HoaDonBanHangDTO errorResponse = new HoaDonBanHangDTO();
            errorResponse.setErrors(errors);
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        for (TongHopHoaDonDTO items : listChiTietHoaDon) {
            SanPham sanPham = sanPhamService.findById(items.getMaSanPham()).orElse(null);
            sanPham.setSoLuong(sanPham.getSoLuong()-items.getSoLuong());
            sanPhamService.save(sanPham);
        }

        KhachHang khachHang = new KhachHang();
        if (khachHangService.findByStatusTrueAndSoDienThoai(banHangDTO.getSoDienThoai()) == null) {
            khachHang = modelMapper.map(banHangDTO, KhachHang.class);
            khachHang = khachHangService.save(khachHang);
        } else {
            khachHang = khachHangService.findByStatusTrueAndSoDienThoai(banHangDTO.getSoDienThoai());
        }
        HoaDonBanHang hoaDonBanHang = new HoaDonBanHang();
        hoaDonBanHang.setKhachHang(khachHang);
        hoaDonBanHang.setThoiGianBanHang(LocalDateTime.now());
        hoaDonBanHang = hdbhService.save(hoaDonBanHang);


        for (TongHopHoaDonDTO items : listChiTietHoaDon) {
            ChiTietHoaDonBanHang chiTietHoaDonBanHang = modelMapper.map(items, ChiTietHoaDonBanHang.class);

            SanPham sanPham = sanPhamService.findById(items.getMaSanPham()).orElse(null);
            chiTietHoaDonBanHang.setSanPham(sanPham);
            chiTietHoaDonBanHang.setHoaDonBanHang(hoaDonBanHang);
            chiTietHoaDonBanHang = chiTietHDBHService.save(chiTietHoaDonBanHang);
        }
        HoaDonBanHangDTO hoaDonBanHangDTO = modelMapper.map(hoaDonBanHang, HoaDonBanHangDTO.class);
        Map<String,String> success=new HashMap<>();

        return ResponseEntity.ok(success) ;
    }
    /**
     * @Author Nguyễn Xuân Long
     * Xử lý dữ liệu nhận về không hợp lệ
     * @return Map<String, String>
     */
@ResponseStatus(HttpStatus.BAD_REQUEST)
@ExceptionHandler(MethodArgumentNotValidException.class)
public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult().getAllErrors().forEach(error -> {
        String fieldName = ((FieldError) error).getField();
        String errorMessage = error.getDefaultMessage();
        errors.put(fieldName, errorMessage);
    });

    return errors;
}


}
