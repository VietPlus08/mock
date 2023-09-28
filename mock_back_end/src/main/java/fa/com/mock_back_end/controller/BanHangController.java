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
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.*;

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

    @GetMapping(value = "/invoice")
    public ResponseEntity<List<DanhSachQuanLyGiaoDichDTO>> getItem() {
        List<DanhSachQuanLyGiaoDichDTO> lyGiaoDichDTO = new ArrayList<>();
        List<HoaDonBanHang> listHoaDonBanHang = hdbhService.findAll();

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
    @PostMapping(value = "/invoice") public ResponseEntity<HoaDonBanHangDTO> addItem(@Valid @RequestBody BanHangDTO banHangDTO) {

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
        List<TongHopHoaDonDTO> listChiTietHoaDon = banHangDTO.getChiTietHoaDonBanHang();

        for (TongHopHoaDonDTO items : listChiTietHoaDon) {
            ChiTietHoaDonBanHang chiTietHoaDonBanHang = modelMapper.map(items, ChiTietHoaDonBanHang.class);

            SanPham sanPham = sanPhamService.findById(items.getMaSanPham()).orElse(null);
            chiTietHoaDonBanHang.setSanPham(sanPham);
            chiTietHoaDonBanHang.setHoaDonBanHang(hoaDonBanHang);
            chiTietHoaDonBanHang = chiTietHDBHService.save(chiTietHoaDonBanHang);
        }
        HoaDonBanHangDTO hoaDonBanHangDTO = modelMapper.map(hoaDonBanHang, HoaDonBanHangDTO.class);
        return ResponseEntity.ok(hoaDonBanHangDTO) ;
    }
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//
//        ex.getBindingResult().getAllErrors().forEach(error -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//
//        return errors;
//    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//
//        // Lấy ra BindingResult cho DTO BanHangDTO
//        BindingResult banHangDTOBindingResult = ex.getBindingResult();
//
//        // Kiểm tra lỗi của BanHangDTO
//        if (banHangDTOBindingResult.hasErrors()) {
//            banHangDTOBindingResult.getAllErrors().forEach(error -> {
//                String fieldName = ((FieldError) error).getField();
//                String errorMessage = error.getDefaultMessage();
//                errors.put(fieldName, errorMessage);
//            });
//        }
//
//        // Lấy ra BindingResult cho DTO ChiTietHoaDonBanHangDTO
//        ChiTietHoaDonBanHangDTO chiTietDTO = null; // Thay bằng đối tượng DTO thực tế của bạn
//        BindingResult chiTietDTOBindingResult = new BeanPropertyBindingResult(chiTietDTO, "chiTietHoaDonBanHangDTO");
//
//        // Kiểm tra lỗi của ChiTietHoaDonBanHangDTO
//        if (chiTietDTOBindingResult.hasErrors()) {
//            chiTietDTOBindingResult.getAllErrors().forEach(error -> {
//                String fieldName = ((FieldError) error).getField();
//                String errorMessage = error.getDefaultMessage();
//                errors.put(fieldName, errorMessage);
//            });
//        }
//
//        return errors;
//    }
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
