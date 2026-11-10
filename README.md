# Лаборатори №9 — JaCoCo код хамралт

Оюутны нэр, код: мэдээлэл өгөөгүй.

Орчны мэдээлэл:

```text
$ java -version
openjdk version "17.0.20.1" 2026-08-18
OpenJDK Runtime Environment Temurin-17.0.20.1+1 (build 17.0.20.1+1)
OpenJDK 64-Bit Server VM Temurin-17.0.20.1+1 (build 17.0.20.1+1, mixed mode, sharing)

$ mvn -version
Apache Maven 3.9.15 (98b2cdbfdb5f1ac8781f537ea9acccaed7922349)
Maven home: C:\Program Files\apache-maven-3.9.15
Java version: 21.0.5, vendor: Eclipse Adoptium, runtime: C:\Program Files\Eclipse Adoptium\jdk-21.0.5.11-hotspot
Default locale: en_US, platform encoding: UTF-8
OS name: "windows 11", version: "10.0", arch: "amd64", family: "windows"
```

## Хэсэг A — Эхний хамралтын хэмжилт

Хэвийн урсгалын 5 тест ажиллав: `Tests run: 5, Failures: 0, Errors: 0, Skipped: 0`. JaCoCo CSV-ээс мөрийн хамралт 15/25 = **60.0%**, салааны хамралт 21/41 = **51.2%** байна (`results/jacoco-A.csv`). Бүтэн Maven гаралт `results/mvn-verify-A.txt` дотор хадгалагдсан.

Шар салаанууд нь тэтгэлгийн босго бүрийн нөгөө нөхцөл, онооны мужуудын бусад үр дүн зэрэг дутуу шалгагдсан хувилбарууд юм. Улаан мөрүүдэд GPA-ийн validation болон дүнгийн F/default зам зэрэг огт хүрээгүй хэсгүүд орно. Эхний тестүүд дангаараа алдааны болон хязгаарын замуудыг шалгахгүйг тайлан харуулж байна.

## Хэсэг B — CFG ба 100% бүтцийн хамралт

`cfg.png` зурагт `scholarship()`-ийн зангилаа, ирмэгүүд болон тухайн ирмэгийг туулдаг тестийн методын нэрсийг тэмдэглэв. Үүнд эхний validation-ийн гурван нөхцөлийн `assertThrows` тестүүд тус тусдаа орсон.

100% мөр ба салааны хамралтад хүрэхэд A ба B-ийн нийлбэрээр нийт **19 тест** ажилласан (`results/mvn-verify-B.txt`: `Tests run: 19, Failures: 0, Errors: 0, Skipped: 0`). `results/jacoco-B.csv`-д `BRANCH_MISSED = 0`, `LINE_MISSED = 0` бөгөөд Branch 41/41, Line 25/25 байна. A ба B үеийн хэмжилтүүдийг `results/` дотор тус тусад нь хадгалсан.

## AI туслахын хэрэглээ

AI туслахыг зааврын хэсгүүдийг нэгтгэх, тестийн бүтэц болон тайлангийн CSV-г шалгахад ашиглав. Туршилтуудыг энэ компьютерт Maven-ээр ажиллуулж, гарсан үр дүнг `results/` файлд хадгалсан.
