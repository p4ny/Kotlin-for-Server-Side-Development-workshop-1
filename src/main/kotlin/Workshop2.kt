package org.example

// 1. กำหนด data class สำหรับเก็บข้อมูลสินค้า
data class Product(val name: String, val price: Double, val category: String)

// ฟังก์ชันคำนวณผลรวมราคาสินค้า Electronics ที่ราคา > 500 บาท
fun calculateTotalElectronicsPriceOver500(products: List<Product>): Double {
    return products
        .filter { it.category == "Electronics" && it.price > 500 }
        .sumOf { it.price }
}

// ฟังก์ชันนับจำนวนสินค้า Electronics ที่ราคา > 500
fun countElectronicsOver500(products: List<Product>): Int {
    return products.count { it.category == "Electronics" && it.price > 500 }
}

// ฟังก์ชันจัดกลุ่มสินค้าตามช่วงราคา
fun groupProductsByPriceRange(products: List<Product>): Map<String, List<Product>> {
    return products.groupBy { product ->
        when {
            product.price <= 1000 -> "กลุ่มของสินค้าที่ราคาไม่เกิน 1,000 บาท"
            product.price <= 9999.99 -> "กลุ่มของสินค้าที่ราคาอยู่ระหว่าง 1,000 - 9,999 บาท"
            else -> "กลุ่มของสินค้าราคา 10,000 ขึ้นไป"
        }
    }
}

fun main() {
    val products = listOf(
        Product("Laptop", 35000.0, "Electronics"),
        Product("Smartphone", 25000.0, "Electronics"),
        Product("T-shirt", 450.0, "Apparel"),
        Product("Monitor", 7500.0, "Electronics"),
        Product("Keyboard", 499.0, "Electronics"),
        Product("Jeans", 1200.0, "Apparel"),
        Product("Headphones", 1800.0, "Electronics")
    )

    println("รายการสินค้าทั้งหมด:")
    products.forEach { println(it) }
    println("--------------------------------------------------")

    // --- โจทย์: จงหาผลรวมราคาสินค้าทั้งหมดในหมวด 'Electronics' ที่มีราคามากกว่า 500 บาท ---

    // 3. วิธีที่ 1: การใช้ฟังก์ชัน calculateTotalElectronicsPriceOver500
    val calculateTotalElectronicsPriceOver500 = calculateTotalElectronicsPriceOver500(products)
    println("วิธีที่ 1: ใช้ Chaining กับ List")
    println("ผลรวมราคาสินค้า Electronics ที่ราคา > 500 บาท: $calculateTotalElectronicsPriceOver500 บาท")
    println("--------------------------------------------------")

    // 4. วิธีที่ 2: ใช้ฟังก์ชัน countElectronicsOver500 + รวมราคาด้วย calculateTotalElectronicsPriceOver500
    val countElectronicsOver500 = calculateTotalElectronicsPriceOver500(products) // ใช้ผลรวมอีกครั้ง (เหมือนกันกับวิธี 1)
    println("วิธีที่ 2: ใช้ .asSequence() (ขั้นสูง)")
    println("ผลรวมราคาสินค้า Electronics ที่ราคา > 500 บาท: $countElectronicsOver500 บาท")
    println("--------------------------------------------------")

    // จัดกลุ่มสินค้าแต่ละตัวตามช่วงราคา
    val grouped = groupProductsByPriceRange(products)

    for ((groupName, items) in grouped) {
        println(groupName)
        for (item in items) {
            println("${item.name} (${item.price})")
        }
        println()
    }

    println("อภิปรายความแตกต่างระหว่าง List และ Sequence:")
    println("1. List Operations (วิธีที่ 1):")
    println("   - ทุกครั้งที่เรียกใช้ operation (เช่น filter, map) จะมีการสร้าง Collection (List) ใหม่ขึ้นมาเพื่อเก็บผลลัพธ์ของขั้นนั้นๆ")
    println("   - ตัวอย่าง: filter ครั้งแรกสร้าง List ใหม่ -> filter ครั้งที่สองสร้าง List ใหม่อีกใบ -> map สร้าง List สุดท้าย -> sum ทำงาน")
    println("   - เหมาะกับข้อมูลขนาดเล็ก เพราะเข้าใจง่าย แต่ถ้าข้อมูลมีขนาดใหญ่มาก (ล้าน records) จะสิ้นเปลืองหน่วยความจำและเวลาในการสร้าง Collection ใหม่ๆ ซ้ำซ้อน")
    println()
    println("2. Sequence Operations (วิธีที่ 2):")
    println("   - ใช้การประมวลผลแบบ 'Lazy' (ทำเมื่อต้องการใช้ผลลัพธ์จริงๆ)")
    println("   - operations ทั้งหมด (filter, map) จะไม่ทำงานทันที แต่จะถูกเรียงต่อกันไว้")
    println("   - ข้อมูลแต่ละชิ้น (each element) จะไหลผ่าน Pipeline ทั้งหมดทีละชิ้น จนจบกระบวนการ")
    println("   - เช่น: 'Laptop' จะถูก filter category -> filter price -> map price จากนั้น 'Smartphone' ถึงจะเริ่มทำกระบวนการเดียวกัน")
    println("   - จะไม่มีการสร้าง Collection กลางทาง ทำให้ประหยัดหน่วยความจำและเร็วกว่ามากสำหรับชุดข้อมูลขนาดใหญ่ เพราะทำงานกับข้อมูลทีละชิ้นและทำทุกขั้นตอนให้เสร็จในรอบเดียว")
    println("   - การคำนวณจะเกิดขึ้นเมื่อมี 'Terminal Operation' มาเรียกใช้เท่านั้น (ในที่นี้คือ .sum())")
}
