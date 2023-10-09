package fa.com.mock_back_end.controller;

import fa.com.mock_back_end.Converter.HoaDonConVerter;
import fa.com.mock_back_end.Converter.QuanLyGiaoDichConverTer;
import fa.com.mock_back_end.dto.*;
import fa.com.mock_back_end.entity.*;
import fa.com.mock_back_end.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;


/**
 * @Author Nguyễn Xuân Long
 * Bán Hàng controller phục vụ lưu hóa đơn và xuất lịch sử giao dịch
 */
@RestController
@RequestMapping(value = "/staff")
@CrossOrigin
public class BanHangController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private HDBHService hdbhService;

    @Autowired
    private ChiTietHDBHService chiTietHDBHService;
    @Autowired
    private HoaDonConVerter hoaDonConVerter;

    @Autowired
    private QuanLyGiaoDichConverTer quanLyGiaoDichConverTer;

    @Autowired
    private TongHopHoaDonService tongHopHoaDonService;


    /**
     * @return List<DanhSachQuanLyGiaoDichDTO>
     * @Author Nguyễn Xuân Long
     * Get danh sách giao dịch
     * Method Get
     */
    @GetMapping(value = "/invoice")
    public ResponseEntity<List<DanhSachQuanLyGiaoDichDTO>> getItem() {
        List<DanhSachQuanLyGiaoDichDTO> lyGiaoDichDTO = new ArrayList<>();
//        List<HoaDonBanHang> listHoaDonBanHang = hdbhService.findAll();
        List<HoaDonBanHang> listHoaDonBanHang = Optional.ofNullable(hdbhService.findAll()).orElse(Collections.emptyList());

        for (HoaDonBanHang items : listHoaDonBanHang) {
            DanhSachQuanLyGiaoDichDTO danhSachQuanLyGiaoDichDTO =     danhSachQuanLyGiaoDichDTO = quanLyGiaoDichConverTer.toQuanLyGiaoDichDto(items);

            List<ChiTietHoaDonBanHangDTO> list = new ArrayList<>();

            List<ChiTietHoaDonBanHang> chiTietHoaDonBanHangList = Optional.ofNullable(items.getListChiTietHoaDonBanHang()).orElse(Collections.emptyList());

            for (ChiTietHoaDonBanHang element : chiTietHoaDonBanHangList) {
                ChiTietHoaDonBanHangDTO chiTietHoaDonBanHangDTO = hoaDonConVerter.toDto(element);
                list.add(chiTietHoaDonBanHangDTO);

            }

            long tongHoadon = chiTietHDBHService.tongHoaDon(chiTietHoaDonBanHangList);

            danhSachQuanLyGiaoDichDTO.setTongHoaDon(tongHoadon);
            danhSachQuanLyGiaoDichDTO.setChiTietHoaDonBanHang(list);
            lyGiaoDichDTO.add(danhSachQuanLyGiaoDichDTO);

        }
        return ResponseEntity.ok(lyGiaoDichDTO);
    }

    /**
     * @return HoaDonBanHangDTO
     * @Author Nguyễn Xuân Long
     * Add thông tin hóa đơn
     * Method Post
     */
    @PostMapping(value = "/invoice")
    public ResponseEntity<Map<String, String>> addItem(@Valid @RequestBody BanHangDTO banHangDTO) {

        List<TongHopHoaDonDTO> listChiTietHoaDon = Optional.ofNullable(banHangDTO.getChiTietHoaDonBanHang()).orElse(Collections.emptyList());

        Map<String, Integer> map = new HashMap<>();

        Map<Long, Integer> danhSachTong = chiTietHDBHService.themHoaDonVaoMap(listChiTietHoaDon);
        Map<String, String> errors = chiTietHDBHService.checkSoLuongTrongKho(danhSachTong, listChiTietHoaDon);

        if (!errors.isEmpty()) {
            HoaDonBanHangDTO errorResponse = new HoaDonBanHangDTO();
            errorResponse.setErrors(errors);
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        // update số lượng sản phầm tổng kho
        chiTietHDBHService.thayDoiSoLuongSanPham(listChiTietHoaDon);

        KhachHang khachHang = chiTietHDBHService.themKhachHangMoi(banHangDTO);

        HoaDonBanHang hoaDonBanHang = tongHopHoaDonService.themHoaDonBanHang(khachHang, banHangDTO);

        hdbhService.themChiTietHoaDonBanHang(listChiTietHoaDon, hoaDonBanHang);
        Map<String, String> success = new HashMap<>();
        success.put("Success", "Hoa don duoc luu thanh cong ");
        return ResponseEntity.ok(success);
    }

}
