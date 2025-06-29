import org.example.celsiusToFahrenheit
import org.example.kilometersToMiles
import kotlin.test.Test
import kotlin.test.assertEquals
import org.example.Product
import org.example.calculateTotalElectronicsPriceOver500
import org.example.countElectronicsOver500


class WorkshopTest {

    // --- Tests for Workshop #1: Unit Converter ---

    // celsius input: 20.0
    // expected output: 68.0
    @Test
    fun `test celsiusToFahrenheit with positive value`() {
        // Arrange: ตั้งค่า input และผลลัพธ์ที่คาดหวัง
        val celsiusInput = 20.0
        val expectedFahrenheit = 68.0

        // Act: เรียกใช้ฟังก์ชันที่ต้องการทดสอบ
        val actualFahrenheit = celsiusToFahrenheit(celsiusInput)

        // Assert: ตรวจสอบว่าผลลัพธ์ที่ได้ตรงกับที่คาดหวัง
        assertEquals(expectedFahrenheit, actualFahrenheit, 0.001, "20°C should be 68°F")
    }

    // celsius input: 0.0
    // expected output: 32.0
    @Test
    fun `test celsiusToFahrenheit with zero`() {
        val celsiusInput = 0.0
        val expectedFahrenheit = 32.0

        val actualFahrenheit = celsiusToFahrenheit(celsiusInput)

        assertEquals(expectedFahrenheit, actualFahrenheit, 0.001, "0°C should be 32°F")
    }

    // celsius input: -10.0
    // expected output: 14.0
    @Test
    fun `test celsiusToFahrenheit with negative value`() {
        val celsiusInput = -10.0
        val expectedFahrenheit = 14.0

        val actualFahrenheit = celsiusToFahrenheit(celsiusInput)

        assertEquals(expectedFahrenheit, actualFahrenheit, 0.001, "-10°C should be 14°F")
    }

    // test for kilometersToMiles function
    // kilometers input: 1.0
    // expected output: 0.621371
    @Test
    fun `test kilometersToMiles with one kilometer`() {
        val kilometersInput = 1.0
        val expectedMiles = 0.621371

        val actualMiles = kilometersToMiles(kilometersInput)

        assertEquals(expectedMiles, actualMiles, 0.001, "1KM should be 0.621371M")
    }

    // --- Tests for Workshop #1: Unit Converter End ---

    // --- Tests for Workshop #2: Data Analysis Pipeline ---
    // ทำการแก้ไขไฟล์ Workshop2.kt ให้มีฟังก์ชันที่ต้องการทดสอบ
    // เช่น ฟังก์ชันที่คำนวณผลรวมราคาสินค้า Electronics ที่ราคา > 500 บาท
    // ในที่นี้จะสมมุติว่ามีฟังก์ชันชื่อ calculateTotalElectronicsPriceOver500 ที่รับ List<Product> และคืนค่า Double
    // จงเขียน test cases สำหรับฟังก์ชันนี้ โดยตรวจสอบผลรวมราคาสินค้า Electronics ที่ราคา > 500 บาท
    @Test
    fun `test calculateTotalElectronicsPriceOver500`() {
        val products = listOf(
            Product("Laptop", 35000.0, "Electronics"),
            Product("Smartphone", 25000.0, "Electronics"),
            Product("T-shirt", 450.0, "Apparel"),
            Product("Monitor", 7500.0, "Electronics"),
            Product("Keyboard", 499.0, "Electronics"), // ไม่รวม
            Product("Jeans", 1200.0, "Apparel"),
            Product("Headphones", 1800.0, "Electronics") // รวม
        )

        val expectedTotal = 35000.0 + 25000.0 + 7500.0 + 1800.0
        val actualTotal = calculateTotalElectronicsPriceOver500(products)

        assertEquals(expectedTotal, actualTotal, 0.001, "ควรได้ผลรวมราคาสินค้า Electronics ที่ > 500 บาท")
    }

    // จงเขียน test cases เช็คจำนวนสินค้าที่อยู่ในหมวด 'Electronics' และมีราคามากกว่า 500 บาท
    @Test
    fun `test countElectronicsOver500`() {
        val products = listOf(
            Product("Laptop", 35000.0, "Electronics"),
            Product("Smartphone", 25000.0, "Electronics"),
            Product("T-shirt", 450.0, "Apparel"),
            Product("Monitor", 7500.0, "Electronics"),
            Product("Keyboard", 499.0, "Electronics"), // ไม่รวม
            Product("Jeans", 1200.0, "Apparel"),
            Product("Headphones", 1800.0, "Electronics") // รวม
        )

        val expectedCount = 4 // มี 4 รายการ Electronics ที่ > 500
        val actualCount = countElectronicsOver500(products)

        assertEquals(expectedCount, actualCount, "ควรนับสินค้า Electronics ที่ราคา > 500 ได้ 4 ชิ้น")
    }
}


    // --- Tests for Workshop #2: Data Analysis Pipeline End ---