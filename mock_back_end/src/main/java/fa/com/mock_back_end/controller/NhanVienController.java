package fa.com.mock_back_end.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fa.com.mock_back_end.entity.NhanVien;
import fa.com.mock_back_end.service.NhanVienService;

@RestController
@RequestMapping("/nhan_vien")
@CrossOrigin("http://localhost:3000/")
public class NhanVienController {
    @Autowired
    NhanVienService nhanVienService;

    @GetMapping("")
    public ResponseEntity<List<NhanVien>> getList() {
	return ResponseEntity.ok().body(nhanVienService.findAll());
    }

    @DeleteMapping("")
    public ResponseEntity<NhanVien> deleteItem(@RequestParam("id") String id) {
	NhanVien nhanVien = nhanVienService.delete(id);
	if (nhanVien != null) {
	    return ResponseEntity.ok().build();
	}
	return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/edit")
    public ResponseEntity<NhanVien> findItem(@RequestParam("id") String id) {
	Optional<NhanVien> nhanVien = nhanVienService.findById(id);
	return nhanVien.map(item -> ResponseEntity.ok().body(item)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "")
    public ResponseEntity<NhanVien> createItem(@Valid @RequestBody NhanVien nhanVien) {
	return ResponseEntity.ok().body(nhanVienService.save(nhanVien));
    }

    @PutMapping(value = "")
    public ResponseEntity<NhanVien> updateItem(@Valid @RequestBody NhanVien updateNhanVien) {
	return ResponseEntity.ok().body(nhanVienService.update(updateNhanVien));
    }
}
