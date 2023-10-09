package fa.com.mock_back_end.generator;

import java.util.List;

public class Generator {
    private static final String PREFIX_NHANVIEN = "NV";
    public static String getMaNhanVien(List<String> ids) {
        long max = ids.stream()
                .filter(i -> !i.equals("admin"))
                .map(i -> i.replace(PREFIX_NHANVIEN,""))
                .mapToLong(Long::parseLong).max().orElse(0L);
        return PREFIX_NHANVIEN + String.format("%04d", max + 1);
    }
}
