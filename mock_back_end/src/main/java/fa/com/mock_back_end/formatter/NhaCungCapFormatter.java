package fa.com.mock_back_end.formatter;

import fa.com.mock_back_end.entity.NhaCungCap;
import fa.com.mock_back_end.service.NhaCungCapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class NhaCungCapFormatter implements Formatter<NhaCungCap> {

    @Autowired
    NhaCungCapService service;

    @Override
    public NhaCungCap parse(String text, Locale locale) throws ParseException {
        return service.findById(Long.parseLong(text)).orElse(null);
    }

    @Override
    public String print(NhaCungCap object, Locale locale) {
        return object.toString();
    }
}
