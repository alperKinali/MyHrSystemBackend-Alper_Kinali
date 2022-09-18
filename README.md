# Izmir Java Web Dev Bootcamp Bitirme Projesi

![ik yönetimi](hr.jpeg)

Bu bitirme projesi kapsamında bootcamp katılımcılarının insan kaynakları çalışanları için tasarlanmış işe alım süreci
yönetim sisteminin backend servislerini yazmaları beklenmektedir. Proje kapsamında beklenen minimum fonksiyonlar ve
teknik ihtiyaçlar aşağıda listelenmiştir.

Bitirme projesi bireysel olarak implemente edilecek birbiriyle aynı olan projeler başarısız sayılacaktır. Bunun yanında
yardımlaşma, fikir alışverişi herzamanki gibi desteklenmektedir :)

## Beklenen fonksiyonlar

* Kullanıcı yönetimi
* İlan yönetimi
* Mülakat yönetimi

### Kullanıcı Yönetimi

Sistemde iki farklı kullanıcı tipi tanımlanabilecek: İK çalışanı ve aday. İki çalışanları için ilan yönetimi başvuru
yönetimi ve mülakat yönetimi fonksiyonlarına izin verilirken aday tipindeki kullanıcılara sadece ilan görüntüleme ve
başvuru hakkı verilecektir.

### İlan yönetimi

- İK çalışanları için ilan yayınlama, girdiği ilanları görüntüleme, girdiği ilanlar için başvuru görüntüleme ve 
- güncelleme/silme yetkisi verilecek.
- Adaylar için ilan görüntüleme ve başvuru hakkı verilecektir. Ayrıca adayların başvurdukları ilanları sonradan ilan
  silinse bile görüntülemesine izin verilecektir.

### Mülakat yönetimi

İK çalışanları yayınladıkları her bir ilan için mülakat süreçlerini tanımlayabilecek ve ilanlar üzerinden diledikleri
aday için mülakat süreci başlatabilecek. Her bir mülakat adımı için gerekli olan notlar ve dökümanlar sisteme API 
aracılığı ile yüklenebilecek.

## Teknik beklentiler

Yukarda belirtilen fonksiyonların tamamı Java programlama dili 11 versiyonu kullanılarak implemente edilecektir, proje
genelinde bootcamp boyunca öğrendiğimiz OOP prensipleri doğru şekilde uygulanmalıdır.

Tüm fonksiyonlar REST API'lar aracılığı ile yerine getirilmeli ve API'lar swagger arayüzü ile dökümantasyonu
yapılmalıdır. Hata durumları uygun şekilde ele alınmalı ve hatalara uygun responselar üretilmeli.

Uygulama genelinde Spring Boot, Spring MVC ve JPA kullanılmalı, clean code ve SOLID prensiplerine uyulmalı, unit testler
eklenmelidir.
