package soict.hedspi.garbage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NoGarbage {
    public static void main(String[] args) {
        String filename = "F:\\OneDrive - Hanoi University of Science and Technology\\HUST\\2024.2\\OOP_LAB_NguyenTuanAnh_20227556\\OtherProjects\\hust\\soict\\hedspi\\garbage\\test.txt";
        byte[] inputBytes = { 0 };
        long startTime, endTime;

        try {
            inputBytes = Files.readAllBytes(Paths.get(filename));
            startTime = System.currentTimeMillis();
            StringBuffer sb = new StringBuffer();
            for (byte b : inputBytes) {
                sb.append((char) b);
            }
            String outputString = sb.toString();
            endTime = System.currentTimeMillis();
            System.out.println("NoGarbage Time (using StringBuffer): " + (endTime - startTime) + " ms");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.err.println("Make sure '" + filename + "' exists and is accessible.");
        } catch (OutOfMemoryError e) {
            System.err.println("OutOfMemoryError! The file might be too large even for StringBuffer.");
        }
    }
}
// Chương trình này sẽ chạy nhanh hơn đáng kể và ít có khả năng gây lỗi bộ nhớ hơn.