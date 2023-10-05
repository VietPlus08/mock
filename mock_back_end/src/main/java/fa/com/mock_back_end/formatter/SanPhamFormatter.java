package fa.com.mock_back_end.formatter;

import fa.com.mock_back_end.entity.SanPham;
import fa.com.mock_back_end.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class SanPhamFormatter implements Formatter<SanPham> {

    @Autowired
    SanPhamService sanPhamService;

    @Override
    public SanPham parse(String text, Locale locale) throws ParseException {
        return sanPhamService.findById(Long.parseLong(text)).orElse(null);
    }

    @Override
    public String print(SanPham object, Locale locale) {
        return null;
    }
}
