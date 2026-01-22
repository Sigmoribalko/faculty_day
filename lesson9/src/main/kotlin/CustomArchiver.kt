import java.io.*
import java.util.zip.*

fun createArchive(sourceDir: String, archivePath: String) {
    val dir = File(sourceDir)
    
    if (!dir.exists()) {
        println("Папка не найдена")
        return
    }
    
    val fos = FileOutputStream(archivePath)
    
    val zos = ZipOutputStream(fos)
    
    addFiles(dir, "", zos)
    
    zos.close()
    
    fos.close()
    
    println("Готово!")
}

fun addFiles(dir: File, path: String, zos: ZipOutputStream) {
    val files = dir.listFiles()
    
    for (file in files) {
        if (file.isDirectory()) {
            addFiles(file, path + file.name + "/", zos)
        } else {
            val name = file.name
            
            if (name.endsWith(".txt") || name.endsWith(".log")) {
                
                val fullPath = path + name
                
                val entry = ZipEntry(fullPath)
                
                zos.putNextEntry(entry)
                
                val fis = FileInputStream(file)
                
                val buffer = ByteArray(1024)
                
                var len = fis.read(buffer)
                
                while (len > 0) {
                    zos.write(buffer, 0, len)
                    
                    len = fis.read(buffer)
                }
                fis.close()
                
                os.closeEntry()
            }
        }
    }
}
fun main() {
    val testDir = File("test_files")
    
    testDir.mkdir()
    
    File("test_files/readme.txt").writeText("Привет мир")
    
    File("test_files/app.log").writeText("Лог приложения\nОшибка 404")
    
    File("test_files/config.xml").writeText("<xml>данные</xml>")
    
    val subDir = File("test_files/logs")
    
    subDir.mkdir()
    
    File("test_files/logs/debug.log").writeText("Отладка")
    
    createArchive("test_files", "simple_archive.zip")
}