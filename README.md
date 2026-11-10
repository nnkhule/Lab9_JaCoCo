# Лаборатори №9 — JaCoCo код хамралт

Оюутны нэр: Б. Энхбаяр  
Оюутны код: B232270018

Орчны мэдээлэл:

```text
$ java -version
openjdk version "17.0.20.1" 2026-08-18
OpenJDK Runtime Environment Temurin-17.0.20.1+1 (build 17.0.20.1+1)
OpenJDK 64-Bit Server VM Temurin-17.0.20.1+1 (build 17.0.20.1+1, mixed mode, sharing)

$ mvn -version
Apache Maven 3.9.15 (98b2cdbfdb5f1ac8781f537ea9acccaed7922349)
Maven home: C:\Program Files\apache-maven-3.9.15
Java version: 17.0.20.1, vendor: Eclipse Adoptium, runtime: C:\Program Files\Eclipse Adoptium\jdk-17.0.20.101-hotspot
Default locale: en_US, platform encoding: Cp1252
OS name: "windows 11", version: "10.0", arch: "amd64", family: "windows"
```

## Хэсэг A — Эхний хамралтын хэмжилт

Хэвийн урсгалын 5 тест ажиллав: `Tests run: 5, Failures: 0, Errors: 0, Skipped: 0`. JaCoCo CSV-ээс мөрийн хамралт 15/25 = **60.0%**, салааны хамралт 21/41 = **51.2%** байна (`results/jacoco-A.csv`). Бүтэн Maven гаралт `results/mvn-verify-A.txt` дотор хадгалагдсан.

JaCoCo HTML тайланд шар буюу хэсэгчлэн хамрагдсан мөрүүд нь `20` (`letterGrade`-ийн хязгаарын шалгалт), `24` (B дүнгийн босго), `31` (`gpa`-ийн null/хоосон шалгалт), `36` (дүнгийн switch), `48` (`scholarship`-ийн оролтын шалгалт), `52–53` (тэтгэлгийн нийлмэл босгууд) байв. Улаан, огт хамрагдаагүй мөрүүд нь `21`, `25–27`, `32`, `39–40`, `49`, `54–55`: эдгээрт онооны алдааны зам, C/D/F үнэлгээ, GPA exception, scholarship validation exception, 50% болон эцсийн 0% буцаах зам орно. Switch-ийн F/default салаа нь тусдаа мөрөөр биш `36` дахь switch мөрөнд тоологддог бөгөөд A шатанд огт гүйцэтгээгүй.

## Хэсэг B — CFG ба 100% бүтцийн хамралт

`cfg.png`-д `scholarship()`-ийн зангилаа, ирмэгүүдийг үзүүлж, ирмэг бүрийг тестийн бодит методын нэртэй холбосон. Эхний validation-ийн гурван OR нөхцөл тус бүрийн алдааны зам `assertThrows` тесттэй.

100% мөр ба салааны хамралтад хүрэхэд A ба B-ийн нийлбэрээр нийт **19 тест** ажилласан (`results/mvn-verify-B.txt`: `Tests run: 19, Failures: 0, Errors: 0, Skipped: 0`). `results/jacoco-B.csv`-д `BRANCH_MISSED = 0`, `LINE_MISSED = 0` бөгөөд Branch 41/41, Line 25/25 байна. A ба B үеийн хэмжилтүүдийг `results/` дотор тус тусад нь хадгалсан.

## Хэсэг C — Давталтын заагийн тестүүд

`gpaEmptyScoresThrows` хоосон массивт exception шалгана (`gpaNullScoresThrows` нь null-ийг шалгана); `gpaSingleScoreLoopBoundary` нэг дүнтэй, `gpaMultipleScoresLoopBoundary` дөрвөн дүнтэй оролтыг шалгана. C үеийн тайланд 22 тест бүгд амжилттай (`results/mvn-verify-C.txt`). Дараах хяналтаар `gpaZeroScoresLoopBoundaryThrows` нь `gpaEmptyScoresThrows`-тай ижил оролт шалгаж байсныг олж, давхардсан тестийг хассан.

Эдгээр давталтын заагууд хамралтыг нэмэхгүй ч давталт огт ажиллаагүй, нэг удаа ажилласан, олон удаа ажилласан үеийн үр дүнг шалгана. Тиймээс 100% хамралттай кодонд ч хоосон болон ганц элементтэй массивын буруу боловсруулалт үлдээгүйг батлахад хэрэгтэй.

## Хэсэг D — Заагийн алдаа ба засвар

`letterGradeSpecificationBoundaries` тест 60, 59.99, 69.99, 0, 100, 89.99, 90 оноог шалгасан. Анхны `mvn test` ажиллуулахад `ScholarshipCalculatorTest.letterGradeSpecificationBoundaries:47 expected: <D> but was: <F>` гэж унасан (`results/mvn-test-D-fail.txt`). Заагийн тест ба уналтын логийг `Add failing letter grade boundary test` commit-д, харин `score > 60`-ийг `score >= 60` болгосон засвар болон эцсийн verify/CSV-г `Fix letterGrade: > 60 to >= 60, spec says 60 is D` гэсэн тусдаа commit-д оруулсан. Давхардсан тестийг хассаны дараах эцсийн `mvn verify`-д 22 тест амжилттай; `results/jacoco-final.csv`-д Branch 41/41, Line 25/25 байна.

100% branch хамралт нь шийдвэрийн салаа бүрээр код явсныг хэмждэг ч онооны бүх боломжит утгыг шалгасныг батлахгүй. `score > 60` ба `score >= 60` нь ижил салааны бүтцийг үүсгэдэг учраас 60 оноог туршаагүй үед хамралт 100% хэвээр байж болно. 60 дээрх тест `D` хүлээсэн ч `F` авснаар заагийн алдаа илэрсэн. Нөхцөлийг зассаны дараа заасан долоон заагийн утга бүгд зөв үнэлэгдэж байна. Иймээс хамралтыг зөв үр дүнгийн нотолгоо гэж бус, тестээр гүйцэтгэсэн кодын хэсгийн хэмжүүр гэж ойлгоно.

## AI туслахын хэрэглээ

AI туслахыг B хэсэгт CFG-ийн ирмэгүүдийг `scholarship` тестийн бодит методын нэртэй тулгах, C хэсэгт давталтын заагийн тестийн оролт/хүлээлтийг гаргахад ашиглав. D хэсэгт 60 болон бусад заагийн онооны тестийн хувилбаруудыг боловсруулах, `mvn-test-D-fail.txt` дахь уналтыг тайлбарлах, засварын дараах CSV-г шалгахад ашигласан. Мөн README-ийн A–D хэсэгт бичсэн тестийн тоо, хамралтын утгуудыг холбогдох `results/` файлуудтай тулган нягталсан.
